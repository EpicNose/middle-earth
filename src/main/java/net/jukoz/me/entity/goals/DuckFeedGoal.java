package net.jukoz.me.entity.goals;

import net.jukoz.me.entity.duck.DuckEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class DuckFeedGoal extends Goal {
    DuckEntity duck;
    static final Predicate<ItemEntity> BREAD_FILTER = item -> !item.cannotPickup() && item.isAlive() && item.getStack().isOf(Items.BREAD);
    int feedTime;

    public DuckFeedGoal(DuckEntity duck) {
        this.duck = duck;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if(this.duck.getAttacker() != null) {
            return false;
        }

        return !this.duck.getWorld().getEntitiesByClass(ItemEntity.class, this.duck.getBoundingBox().expand(5.0f, 5.0f, 5.0f), BREAD_FILTER).isEmpty() && this.duck.getHungryTimeout() <= 0;
    }

    @Override
    public void start() {
        this.feedTime = 40;
        List<ItemEntity> bread = this.duck.getWorld().getEntitiesByClass(ItemEntity.class, this.duck.getBoundingBox().expand(5.0f, 5.0f, 5.0f), BREAD_FILTER);
        if (!bread.isEmpty()) {
            this.duck.getNavigation().startMovingTo(bread.get(0), 1.0f);
        }
    }

    @Override
    public void tick() {
        List<ItemEntity> items = this.duck.getWorld().getEntitiesByClass(ItemEntity.class, this.duck.getBoundingBox().expand(5.0f, 5.0f, 5.0f), BREAD_FILTER);
        if (!items.isEmpty()) {
            ItemEntity bread = items.get(0);
            this.duck.getNavigation().startMovingTo(bread, 1.0f);
            if(this.duck.getNavigation().isIdle()) {
                this.duck.setEating(true);
                --this.feedTime;
                if(this.feedTime == 0) {
                    bread.getStack().decrement(1);
                    this.duck.setEating(false);
                    this.duck.setHungryTimeout(this.duck.getRandom().nextInt(200) + 1100);
                }
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        this.duck.setEating(false);
    }
}
