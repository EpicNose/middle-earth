package net.jukoz.me.entity.goose;

import net.jukoz.me.entity.beasts.trolls.TrollAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class GooseModel extends SinglePartEntityModel<GooseEntity> {
    private final ModelPart goose;
    protected final ModelPart head;
    protected final ModelPart neck;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    public GooseModel(ModelPart root) {
        this.goose = root.getChild("root");
        this.head = goose.getChild("body").getChild("head");
        this.neck = goose.getChild("body").getChild("head").getChild("neck");
        this.left_wing = goose.getChild("body").getChild("leftwing");
        this.right_wing = goose.getChild("body").getChild("rightwing");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 26.0F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.05F, -1.0585F, -6.8499F, 6.0F, 6.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(1.05F, -9.5781F, 6.0082F, -0.3054F, 0.0F, 0.0F));

        ModelPartData backfeathers_r1 = body.addChild("backfeathers_r1", ModelPartBuilder.create().uv(0, 40).cuboid(-2.4F, -2.0F, 0.0F, 5.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-0.05F, 1.0471F, 2.1114F, 1.6581F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create(), ModelTransform.of(-0.05F, 2.2749F, -6.0166F, 0.1745F, 0.0F, 0.0F));

        ModelPartData neck = head.addChild("neck", ModelPartBuilder.create().uv(0, 25).cuboid(-0.5F, -7.0F, -2.0F, 3.0F, 9.0F, 3.0F, new Dilation(-0.1F)), ModelTransform.of(-1.0F, -1.4614F, 0.7028F, 0.0436F, 0.0F, 0.0F));

        ModelPartData head2 = head.addChild("head2", ModelPartBuilder.create().uv(21, 0).cuboid(-1.5F, -3.0F, -2.5F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -1.0F, -4.5F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 17).cuboid(-1.0F, -2.0F, -3.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.4614F, 0.2028F, 0.1309F, 0.0F, 0.0F));

        ModelPartData mouth = head2.addChild("mouth", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, -2.5F, -0.5236F, 0.0F, 0.0F));

        ModelPartData mouth1 = mouth.addChild("mouth1", ModelPartBuilder.create().uv(38, 11).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData leftwing = body.addChild("leftwing", ModelPartBuilder.create().uv(24, 9).cuboid(-0.5F, -0.8822F, -0.8159F, 1.0F, 5.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(3.45F, -1.0585F, -5.3499F, -0.2442F, -0.0446F, -0.1234F));

        ModelPartData leftouterwing = leftwing.addChild("leftouterwing", ModelPartBuilder.create().uv(24, 39).cuboid(-0.5F, 0.3188F, -0.5F, 1.0F, 3.0F, 5.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, 3.6178F, -0.3159F, 1.0908F, 0.0F, 0.0F));

        ModelPartData outerouterwing = leftouterwing.addChild("outerouterwing", ModelPartBuilder.create(), ModelTransform.of(0.0F, 3.0F, 1.5F, 0.5236F, 0.0F, 0.0F));

        ModelPartData cube1 = outerouterwing.addChild("cube1", ModelPartBuilder.create().uv(2, 48).cuboid(-0.7F, 5.0F, -0.5876F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.8F, -5.8605F, -0.8548F, -0.0436F, 0.0F, 0.0F));

        ModelPartData rightwing = body.addChild("rightwing", ModelPartBuilder.create().uv(24, 9).mirrored().cuboid(-0.5F, -0.8822F, -0.8159F, 1.0F, 5.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.55F, -1.0585F, -5.3499F, -0.2396F, 0.0655F, 0.2083F));

        ModelPartData rightouterwing = rightwing.addChild("rightouterwing", ModelPartBuilder.create().uv(24, 39).mirrored().cuboid(-0.5F, 0.3188F, -0.5F, 1.0F, 3.0F, 5.0F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(0.0F, 3.6178F, -0.3159F, 1.1345F, 0.0F, 0.0F));

        ModelPartData outerouterwing2 = rightouterwing.addChild("outerouterwing2", ModelPartBuilder.create().uv(1, 48).mirrored().cuboid(-0.1F, -0.8605F, -2.6424F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 3.0F, 1.5F, 0.5236F, 0.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(57, 0).cuboid(-1.1F, -1.0926F, -0.8514F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(57, 6).cuboid(-1.1F, 2.9074F, -1.8514F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.95F, 5.0282F, -0.6631F, 0.3054F, 0.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(57, 0).mirrored().cuboid(-0.9F, -1.0926F, -0.8514F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(57, 6).mirrored().cuboid(-0.9F, 2.9074F, -1.8514F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.05F, 5.0282F, -0.6631F, 0.3054F, 0.0F, 0.0F));

        ModelPartData bone = rightleg.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        goose.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return goose;
    }

    @Override
    public void setAngles(GooseEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(GooseAnimations.WALK, limbAngle, limbDistance, 3f, 3f);
        this.updateAnimation(entity.swimmingAnimationState, GooseAnimations.SWIM, animationProgress, 1f);

        if(!entity.isOnGround()) {
            float angle = MathHelper.cos(MathHelper.cos(animationProgress * 0.5f));
            this.right_wing.roll = 4 - (4 * angle);
            this.left_wing.roll = -4 + (4 * angle);
        }
    }
}