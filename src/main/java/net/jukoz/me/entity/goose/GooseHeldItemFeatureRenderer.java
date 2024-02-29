//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.jukoz.me.entity.goose;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class GooseHeldItemFeatureRenderer extends FeatureRenderer<GooseEntity, GooseModel> {
    private final HeldItemRenderer heldItemRenderer;

    public GooseHeldItemFeatureRenderer(FeatureRendererContext<GooseEntity, GooseModel> context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, GooseEntity gooseEntity, float f, float g, float h, float j, float k, float l) {
        matrixStack.push();

        matrixStack.translate((this.getContextModel()).head.pivotX / 16.0F + (this.getContextModel()).neck.pivotX / 16.0F, (this.getContextModel()).neck.pivotY / 16.0F, (this.getContextModel()).head.pivotZ / 16.0F + (this.getContextModel()).neck.pivotZ / 16.0F);

        ItemStack itemStack = gooseEntity.getEquippedStack(EquipmentSlot.MAINHAND);
        if(itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof PickaxeItem || itemStack.getItem() instanceof AxeItem || itemStack.getItem() instanceof ShovelItem || itemStack.getItem() instanceof HoeItem) {
            matrixStack.translate(-0.1F, 0.65F, 0.25F);

            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(135.0F));
        } else {
            matrixStack.translate(0.05F, 0.65F, 0.2F);

            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F));
        }

        this.heldItemRenderer.renderItem(gooseEntity, itemStack, ModelTransformationMode.GROUND, false, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
    }
}
