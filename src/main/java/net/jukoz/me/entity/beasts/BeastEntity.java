package net.jukoz.me.entity.beasts;

import net.jukoz.me.entity.beasts.trolls.TrollEntity;
import net.jukoz.me.item.items.TrollArmorItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Predicate;

// Beasts are mostly aggressive Entities which work much like wolves, while also allowing the player to mount them.
public class BeastEntity extends AbstractDonkeyEntity {
    public static final TrackedData<Boolean> CHARGING = DataTracker.registerData(BeastEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> SITTING = DataTracker.registerData(BeastEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    private int attackTicksLeft = 0;

    protected int chargeTimeout;

    public static final int ATTACK_COOLDOWN = 10;
    public static final float RESISTANCE = 0.15f;
    protected Vec3d targetDir = Vec3d.ZERO;
    private static final UUID BEAST_ARMOR_BONUS_ID = UUID.fromString("667E1665-8B10-40C8-8F9D-CF9B1667F295");

    // Initializing ====================================================================================================
    protected BeastEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(CHARGING, false);
        this.dataTracker.startTracking(SITTING, false);
    }

    @Override
    protected void initAttributes(Random random) {

    }

    protected void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if(this.isSitting()) {
            this.sittingAnimationState.startIfNotRunning(this.age);
        }
        else {
            this.sittingAnimationState.stop();
        }
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (!this.firstUpdate && CHARGING.equals(data)) {
            this.chargeTimeout = this.chargeTimeout == 0 ? maxChargeCooldown() : this.chargeTimeout;
        }
        super.onTrackedDataSet(data);
    }

    private StackReference createInventoryStackReference(final int slot, final Predicate<ItemStack> predicate) {
        return new StackReference(){

            @Override
            public ItemStack get() {
                return BeastEntity.this.items.getStack(slot);
            }

            @Override
            public boolean set(ItemStack stack) {
                if (!predicate.test(stack)) {
                    return false;
                }
                BeastEntity.this.items.setStack(slot, stack);
                BeastEntity.this.updateSaddle();
                return true;
            }
        };
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("ChestedBeast", this.hasChest());
        if (this.hasChest()) {
            NbtList nbtList = new NbtList();
            for(int i = 2; i < this.items.size(); ++i) {
                ItemStack itemStack = this.items.getStack(i);
                if (!itemStack.isEmpty()) {
                    NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putByte("Slot", (byte)i);
                    itemStack.writeNbt(nbtCompound);
                    nbtList.add(nbtCompound);
                }
            }
            nbt.put("Items", nbtList);
        }
        if (!this.items.getStack(1).isEmpty()) {
            nbt.put("ArmorItem", this.items.getStack(1).writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasChest(nbt.getBoolean("ChestedBeast"));
        this.onChestedStatusChanged();
        if (this.hasChest()) {
            NbtList nbtList = nbt.getList("Items", 10);

            for(int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                int j = nbtCompound.getByte("Slot") & 255;
                if (j >= 2 && j < this.items.size()) {
                    this.items.setStack(j, ItemStack.fromNbt(nbtCompound));
                }
            }
        }
        if (nbt.contains("ArmorItem", 10)) {
            ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("ArmorItem"));
            if (!itemStack.isEmpty() && this.isHorseArmor(itemStack)) {
                this.items.setStack(1, itemStack);
            }
        }
        this.updateSaddle();
    }

    // Getters and Setters =============================================================================================

    public boolean canCharge() {
        return !this.isSitting();
    }
    @Override
    protected float getJumpVelocity() {
        return 0.5f * this.getJumpVelocityMultiplier() + this.getJumpBoostVelocityModifier();
    }

    @Override
    public int getJumpCooldown() {
        return this.chargeTimeout;
    }


    public double getMountedHeightOffset() {
        float f = Math.min(0.25F, this.limbAnimator.getSpeed());
        float g = this.limbAnimator.getPos();
        return (double)this.getHeight() - 0.19 + (double)(0.12F * MathHelper.cos(g * 1.5F) * 2.0F * f);
    }

    @Override
    protected Vec3d getControlledMovementInput(PlayerEntity controllingPlayer, Vec3d movementInput) {
        if (this.isOnGround() && this.jumpStrength == 0.0f && this.isAngry() && !this.jumping || this.isSitting()) {
            return Vec3d.ZERO;
        }
        float f = controllingPlayer.sidewaysSpeed * 0.5f;
        float g = controllingPlayer.forwardSpeed * 0.75f;
        if (g <= 0.0f) {
            g *= 0.25f;
        }
        return new Vec3d(f, 0.0, g);
    }

    public PlayerEntity getOwner() {
        if(this.getOwnerUuid() != null) {
            return getPlayerByUuid(this.getOwnerUuid());
        }
        return null;
    }

    @Override
    public boolean isPersistent() {
        return isTame();
    }

    public boolean isSitting() {
        return this.dataTracker.get(SITTING);
    }

    public void setSitting(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
    }

    public boolean isCommandItem(ItemStack stack) {
        return false;
    }

    public void setCharging(boolean charging) {
        this.dataTracker.set(CHARGING, charging);
    }

    public boolean isCharging() {
        return this.dataTracker.get(CHARGING);
    }

    public int getChargeTimeout() {
        return this.chargeTimeout;
    }
    public void setChargeTimeout(int chargeTimeout) {
        this.chargeTimeout = chargeTimeout;
    }

    public int maxChargeCooldown() {
        return 400;
    }
    public int chargeDuration() {
        return 20;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    // Equipment =======================================================================================================

    protected void dropInventory() {
        super.dropInventory();
        if (this.hasChest()) {
            if (!this.getWorld().isClient) {
                this.dropItem(Blocks.CHEST);
            }

            this.setHasChest(false);
        }
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
        ItemStack itemStack = this.getArmorType();
        super.onInventoryChanged(sender);
        ItemStack itemStack2 = this.getArmorType();
        if (this.age > 20 && this.isHorseArmor(itemStack2) && itemStack != itemStack2) {
            this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F, 1.0F);
        }
    }

    @Override
    public StackReference getStackReference(int mappedIndex) {
        int j;
        int i = mappedIndex - 400;
        if (i >= 0 && i < 2 && i < this.items.size()) {
            if (i == 0) {
                return this.createInventoryStackReference(i, stack -> stack.isEmpty() || stack.isOf(Items.SADDLE));
            }
            if (i == 1) {
                if (!this.hasArmorSlot()) {
                    return StackReference.EMPTY;
                }
                return this.createInventoryStackReference(i, stack -> stack.isEmpty() || this.isHorseArmor((ItemStack)stack));
            }
        }
        if ((j = mappedIndex - 500 + 2) >= 2 && j < this.items.size()) {
            return StackReference.of(this.items, j);
        }
        return super.getStackReference(mappedIndex);
    }

    protected int getInventorySize() {
        return this.hasChest() ? 17 : super.getInventorySize();
    }

    public int getInventoryColumns() {
        return 5;
    }

    private void addChest(PlayerEntity player, ItemStack chest) {
        this.setHasChest(true);
        this.playAddChestSound();
        if (!player.getAbilities().creativeMode) {
            chest.decrement(1);
        }
        this.onChestedStatusChanged();
    }

    protected void playAddChestSound() {
        this.playSound(SoundEvents.ENTITY_DONKEY_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    private void equipArmor(ItemStack stack) {
        this.equipStack(EquipmentSlot.CHEST, stack);
        this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public ItemStack getArmorType() {
        return this.getEquippedStack(EquipmentSlot.CHEST);
    }

    @Override
    public boolean hasArmorSlot() {
        return true;
    }

    private void setArmorTypeFromStack(ItemStack stack) {
        this.equipArmor(stack);
        if (!this.getWorld().isClient) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).removeModifier(BEAST_ARMOR_BONUS_ID);
            if (this.isHorseArmor(stack)) {
                int i = ((TrollArmorItem)stack.getItem()).getBonus();
                if (i != 0) {
                    this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addTemporaryModifier(new EntityAttributeModifier(BEAST_ARMOR_BONUS_ID, "Beast armor bonus", (double)i, EntityAttributeModifier.Operation.ADDITION));
                }
            }
        }
    }

    @Override
    protected void updateSaddle() {
        if (!this.getWorld().isClient) {
            super.updateSaddle();
            this.setArmorTypeFromStack(this.items.getStack(1));
            this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0F);
        }
    }

    @Override
    public SoundEvent getSaddleSound() {
        return super.getSaddleSound();
    }

    // Move Set and Behavior ===========================================================================================
    @Override
    protected void jump(float strength, Vec3d movementInput) {
        if(this.isSitting()) {
            this.setSitting(false);
        }
        else if(this.chargeTimeout <= 0) {
            this.setCharging(true);
            this.chargeTimeout = maxChargeCooldown();
        }
    }

    @Override
    public void startJumping(int height) {
        if(!this.isSitting()) {
            this.playSound(SoundEvents.ENTITY_CAMEL_DASH, 1.0f, 1.0f);
            this.setCharging(true);
        }
        else {
            this.setSitting(false);
        }
    }

    public void tryBonding(PlayerEntity player) {
        if(random.nextDouble() <= 0.1d) {
            if (player instanceof ServerPlayerEntity) {
                this.setOwnerUuid(player.getUuid());
                this.setTame(true);
                Criteria.TAME_ANIMAL.trigger((ServerPlayerEntity)player, this);
            }
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);

            this.chargeTimeout = 0;
        }
        else {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
        }
    }


    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        boolean bl = !this.isBaby() && this.isTame() && player.shouldCancelInteraction();

        if(this.isTame() && isCommandItem(player.getStackInHand(hand)) && player.getUuid() == this.getOwnerUuid()) {
            this.setSitting(!isSitting());
        }

        if(this.isTame() && !isCommandItem(player.getStackInHand(hand))) {
            super.interactMob(player, hand);
        }

        if(player.getStackInHand(hand).isOf(getBondingItem()) && !this.isTame() && !this.getWorld().isClient) {
            this.tryBonding(player);
        }

        if (!this.hasPassengers() && !bl) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (!itemStack.isEmpty()) {
                if (!this.hasChest() && itemStack.isOf(Items.CHEST)) {
                    this.addChest(player, itemStack);
                    return ActionResult.success(this.getWorld().isClient);
                }
                if (this.hasArmorSlot() && this.isHorseArmor(itemStack) && !this.hasArmorInSlot()) {
                    this.equipHorseArmor(player, itemStack);
                    return ActionResult.success(this.getWorld().isClient);
                }
            }
        }

        return ActionResult.success(this.getWorld().isClient);
    }

    public Item getBondingItem() {
        return null;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(!source.equals(getDamageSources().drown()) && !source.equals(getDamageSources().lava())
                && !source.equals(getDamageSources().cramming()) && !source.equals(getDamageSources().magic())) {
            amount *= (1 - RESISTANCE);
        }
        return super.damage(source, amount);
    }

    public void chargeAttack() {
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.attackTicksLeft = ATTACK_COOLDOWN;
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        float f = this.getAttackDamage();
        float g = (int)f > 0 ? f / 2.0f + (float)this.random.nextInt((int)f) : f;
        boolean bl = target.damage(this.getDamageSources().mobAttack(this), g);
        if (bl) {
            double d;
            if (target instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)target;
                d = livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            } else {
                d = 0.0;
            }
            double e = Math.max(0.0, 1.0 - d);
            target.setVelocity(target.getVelocity().multiply(1f + (0.8f * e))); //.add(0.0, (double)0.1f * e, 0.0));
            this.applyDamageEffects(this, target);
        }
        this.playSound(SoundEvents.ENTITY_HOGLIN_ATTACK, 1.5f, 0.8f);
        return bl;
    }

    // Tick Management =================================================================================================
    @Override
    public void tick() {
        super.tick();

        if(this.getTarget() != null) {
            this.getLookControl().lookAt(this.getTarget());
        }

        if(this.isCharging()) {
            chargeAttack();
            if(!chargeAnimationState.isRunning()) {
                this.chargeAnimationState.start(this.age);
            }
        }
        if(this.chargeTimeout <= (maxChargeCooldown() - chargeDuration()) || !isCharging()) {
            this.setCharging(false);
            this.targetDir = Vec3d.ZERO;
        }
        if(!this.isCharging()) {
            this.chargeAnimationState.stop();
        }
        if(chargeTimeout > 0) {
            --this.chargeTimeout;
        }

        if (this.getWorld().isClient) {
            setupAnimationStates();
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.attackTicksLeft > 0) {
            --this.attackTicksLeft;
        }
    }

    // =================================================================================================================

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_ATTACK_SOUND) {
            this.attackTicksLeft = ATTACK_COOLDOWN;
        }
        if(status == 4){
            this.attackAnimationState.start(this.age);
        }
        if (status == EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES) {
            this.spawnPlayerReactionParticles(true);
        } else if (status == EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES) {
            this.spawnPlayerReactionParticles(false);
        } else {
            super.handleStatus(status);
        }
    }

    public PlayerEntity getPlayerByUuid(UUID uuid) {
        for (int i = 0; i < this.getWorld().getPlayers().size(); ++i) {
            PlayerEntity playerEntity = this.getWorld().getPlayers().get(i);
            if (!uuid.equals(playerEntity.getUuid())) continue;
            return playerEntity;
        }
        return null;
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WARDEN_STEP, 0.15F, 2.0F);
    }

    @Override
    public boolean cannotBeSilenced() {
        return super.cannotBeSilenced();
    }

    @Override
    public EntityView method_48926() {
        return null;
    }
}
