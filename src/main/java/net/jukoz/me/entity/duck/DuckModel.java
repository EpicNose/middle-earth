package net.jukoz.me.entity.duck;

import net.jukoz.me.entity.deer.DeerAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class DuckModel extends SinglePartEntityModel<DuckEntity> {
    private final ModelPart duck;
    private final ModelPart right_wing;
    private final ModelPart left_wing;
    public DuckModel(ModelPart root) {
        this.duck = root.getChild("root");
        this.right_wing = this.duck.getChild("body").getChild("right_wing");
        this.left_wing = this.duck.getChild("body").getChild("left_wing");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 25.0F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.0F, 0.0F));

        ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -2.0076F, -2.8257F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        ModelPartData right_wing = body.addChild("right_wing", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 3.0F, 0.0F));

        ModelPartData cube_r2 = right_wing.addChild("cube_r2", ModelPartBuilder.create().uv(22, 7).cuboid(-0.2763F, -6.0059F, -1.3526F, 0.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 3.5576F, 0.8109F, -1.3963F, -0.0038F, -3.0981F));

        ModelPartData rightmiddlewing = right_wing.addChild("rightmiddlewing", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 8.0F, 0.0F));

        ModelPartData cube_r3 = rightmiddlewing.addChild("cube_r3", ModelPartBuilder.create().uv(10, 32).cuboid(-0.2261F, -0.9138F, -1.7436F, 1.0F, 3.0F, 4.0F, new Dilation(-0.1F)), ModelTransform.of(-4.0F, -4.4829F, 0.3922F, 0.5233F, -0.0076F, -3.0547F));

        ModelPartData rightouterwing = rightmiddlewing.addChild("rightouterwing", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -8.4829F, 0.3922F));

        ModelPartData cube_r4 = rightouterwing.addChild("cube_r4", ModelPartBuilder.create().uv(0, 21).cuboid(-0.5076F, -3.0755F, -3.5F, 1.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 1.084F, 0.9168F, 0.0869F, -0.0076F, -3.0547F));

        ModelPartData left_wing = body.addChild("left_wing", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 3.0F, 0.0F));

        ModelPartData cube_r5 = left_wing.addChild("cube_r5", ModelPartBuilder.create().uv(0, 21).mirrored().cuboid(-0.4924F, -3.0755F, -3.5F, 1.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.6012F, 1.309F, 0.0869F, 0.0076F, 3.0547F));

        ModelPartData leftmiddlewing = left_wing.addChild("leftmiddlewing", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 8.0F, 0.0F));

        ModelPartData cube_r6 = leftmiddlewing.addChild("cube_r6", ModelPartBuilder.create().uv(10, 32).mirrored().cuboid(-0.7739F, -0.9138F, -1.7436F, 1.0F, 3.0F, 4.0F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(2.0F, -4.4829F, 0.3922F, 0.5233F, 0.0076F, 3.0547F));

        ModelPartData leftouterwing = leftmiddlewing.addChild("leftouterwing", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, -8.4829F, 0.3922F));

        ModelPartData cube_r7 = leftouterwing.addChild("cube_r7", ModelPartBuilder.create().uv(22, 7).mirrored().cuboid(0.2801F, -5.0211F, -1.5261F, 0.0F, 7.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(7.5F, 4.0405F, 1.4187F, -1.3963F, 0.0038F, 3.0981F));

        ModelPartData Tail = body.addChild("Tail", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 13.0F, 7.0F, -0.0873F, 0.0F, 0.0F));

        ModelPartData cube_r8 = Tail.addChild("cube_r8", ModelPartBuilder.create().uv(28, 7).cuboid(-1.4295F, -0.0694F, -0.2067F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -8.0F, -3.0F, 0.1703F, 0.3042F, -0.0275F));

        ModelPartData cube_r9 = Tail.addChild("cube_r9", ModelPartBuilder.create().uv(24, 29).cuboid(-0.563F, -0.2251F, -0.1377F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -8.0F, -3.0F, 0.1703F, -0.3042F, 0.0275F));

        ModelPartData cube_r10 = Tail.addChild("cube_r10", ModelPartBuilder.create().uv(0, 13).cuboid(-2.5F, -0.5284F, -0.6537F, 5.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, -3.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(20, 0).cuboid(-2.1705F, -2.8F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-1.6705F, -0.8F, -4.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 1.0F, -1.0F));

        ModelPartData neck = head.addChild("neck", ModelPartBuilder.create(), ModelTransform.pivot(-0.1705F, 2.0079F, 0.0F));

        ModelPartData cube_r11 = neck.addChild("cube_r11", ModelPartBuilder.create().uv(28, 22).cuboid(-1.5F, -2.3727F, -1.5872F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.0358F, 0.2771F, 0.175F, -0.0873F, 0.0F, 0.0F));

        ModelPartData left_leg = body.addChild("left_leg", ModelPartBuilder.create().uv(0, 4).cuboid(-0.5F, -1.75F, 1.0F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 3).cuboid(-1.0F, 2.25F, -1.5F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 7.75F, 1.0F));

        ModelPartData right_leg = body.addChild("right_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.75F, 0.0F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, 3.25F, -2.5F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 6.75F, 2.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void setAngles(DuckEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(DuckAnimations.WALK, limbSwing, limbSwingAmount, 4f, 5.5f);

        this.right_wing.roll = 0;
        this.left_wing.roll = 0;

        if(!entity.isOnGround()) {
            float angle = MathHelper.cos(MathHelper.cos(ageInTicks * 0.5f));
            this.right_wing.roll = 4 - (4 * angle);
            this.left_wing.roll = -4 + (4 * angle);
        }
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        duck.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return duck;
    }
}


