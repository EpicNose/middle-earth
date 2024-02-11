package net.jukoz.me.entity.duck;

import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.beasts.trolls.TrollEntity;
import net.jukoz.me.entity.goals.DuckFeedGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DuckEntity extends AnimalEntity {
    public static final TrackedData<Boolean> EATING = DataTracker.registerData(DuckEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int hungryTimeout = 0;

    private static final Ingredient BREEDING_INGREDIENT
            = Ingredient.ofItems(Items.WHEAT_SEEDS);

    public DuckEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(EATING, false);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.15));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(4, new TemptGoal(this, 1.1, BREEDING_INGREDIENT, false));
        this.goalSelector.add(5, new DuckFeedGoal(this));
        this.goalSelector.add(6, new FollowParentGoal(this, 1.05));
        this.goalSelector.add(7, new WanderAroundGoal(this, 1.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
        this.goalSelector.add(10, new FleeEntityGoal<>(this, WolfEntity.class, 8.0F, 0.9, 1.2));
    }

    public static DefaultAttributeContainer.Builder createDuckAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    public void tickMovement() {
        super.tickMovement();
        Vec3d vec3d = this.getVelocity();
        if (!this.isOnGround() && vec3d.y < 0.0) {
            this.setVelocity(vec3d.multiply(1.0, 0.6, 1.0));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient() && this.isEating() && this.age % 3 == 0) {
            Vec3d vec3d = (new Vec3d(((double)this.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0)).rotateX(-this.getPitch() * 0.017453292F).rotateY(-this.getYaw() * 0.017453292F);
            this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.BREAD)), this.getX() + this.getRotationVector().x / 2.0, this.getY() + 0.5f, this.getZ() + this.getRotationVector().z / 2.0, vec3d.x, vec3d.y + 0.05, vec3d.z);
        }
        if(this.hungryTimeout > 0) {
            --this.hungryTimeout;
        }
    }

    public void setEating(boolean eating) {
        this.dataTracker.set(EATING, eating);
    }

    public boolean isEating() {
        return this.dataTracker.get(EATING);
    }

    public void setHungryTimeout(int hungryTimeout) {
        this.hungryTimeout = hungryTimeout;
    }
    public int getHungryTimeout() {
        return this.hungryTimeout;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_TURTLE_SWIM;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 2.0F);
    }

    @Nullable
    public DuckEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (DuckEntity) ModEntities.DUCK.create(serverWorld);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public DuckVariant getVariant() {
        return DuckVariant.byId(this.getId());
    }


    public boolean canBreatheInWater() {
        return false;
    }

    public boolean isPushedByFluids() {
        return true;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }
}
