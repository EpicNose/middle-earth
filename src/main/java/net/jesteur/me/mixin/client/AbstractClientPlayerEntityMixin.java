package net.jesteur.me.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jesteur.me.entity.barrow_wights.BarrowWightEntity;
import net.jesteur.me.statusEffects.ModStatusEffects;
import net.jesteur.me.utils.HallucinationData;
import net.jesteur.me.utils.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {

    @Shadow @Final public ClientWorld clientWorld;
    LivingEntity lookAt;

    @Inject(method = "getFovMultiplier()F", at =@At("TAIL"), cancellable = true)
    private void injected(CallbackInfoReturnable<Float> cir) {
    PlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        //player.changeLookDirection(2, 2);
        if(player.hasStatusEffect(ModStatusEffects.HALLUCINATION)){
            if( lookAt != null ){
                double dX = lookAt.getX() - player.getX(); // d
                double dY = lookAt.getY() - player.getY(); // e
                double dZ = lookAt.getZ() - player.getZ(); // f
                double g = Math.sqrt(dX * dX + dZ * dZ);

                float destPitch = MathHelper.wrapDegrees((float)(-(MathHelper.atan2(dY, g) * 57.2957763671875)));
                float destYaw = MathHelper.wrapDegrees((float)(MathHelper.atan2(dZ, dX) * 57.2957763671875) - 90.0f);

                destPitch = MathHelper.lerp(1/20f, destPitch, player.getPitch());
                destYaw = MathHelper.lerp(1/20f, destYaw, player.getYaw());
                //player.changeLookDirection(1920/Math.sin(destYaw), 1080/Math.sin(destPitch)); cant figure this out.

                //destPitch = player.getPitch();
                //destYaw = player.getYaw();
                player.setPitch(destPitch);
                player.setYaw(destYaw);
                player.setHeadYaw(player.getYaw());
                player.prevPitch = player.getPitch();
                player.prevYaw = player.getYaw();

                //Vec3d currentLookAt = player.getCameraPosVec(0);
                //MinecraftClient.getInstance().player.sendMessage(Text.literal("Dest Yaw: " + destYaw + " while player: " + player.getYaw()));

            }
            else
                lookAt = clientWorld.getClosestEntity(BarrowWightEntity.class, TargetPredicate.createNonAttackable(), null, player.getX(), player.getY(), player.getZ(), player.getBoundingBox().expand(12));

            float intensity = (float) HallucinationData.readHallucination((IEntityDataSaver) player) / 100f;
            cir.setReturnValue(cir.getReturnValue() * (1 - intensity/4));

        }
        else
            lookAt = null;



    }

}
