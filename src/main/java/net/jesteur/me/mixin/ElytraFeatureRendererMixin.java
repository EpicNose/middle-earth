package net.jesteur.me.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.jesteur.me.MiddleEarth;
import net.jesteur.me.entity.equipments.CloakCapeEntityModel;
import net.jesteur.me.item.ModEquipmentItems;
import net.jesteur.me.utils.IntToRGB;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(ElytraFeatureRenderer.class)
public class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier CLOAK_CAPE_TEXTURE = new Identifier(MiddleEarth.MOD_ID, "textures/models/armor/cloak_features.png");

    private final CloakCapeEntityModel<T> cloakCapeModel = new CloakCapeEntityModel<>(CloakCapeEntityModel.getTexturedModelData().createModel());

    public ElytraFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
        Item item = itemStack.getItem();
        if (item == ModEquipmentItems.CLOAK) {
            matrices.push();

            int color = ((DyeableItem)itemStack.getItem()).getColor(itemStack);
            Vec3d rgb = IntToRGB.ex(color);

            this.getContextModel().copyStateTo(this.cloakCapeModel);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(CLOAK_CAPE_TEXTURE), false, itemStack.hasGlint());

            this.cloakCapeModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, (float)rgb.x, (float)rgb.y, (float)rgb.z, 1.0F);
            this.cloakCapeModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            matrices.pop();
        }
    }
}
