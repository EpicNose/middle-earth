package net.jukoz.me.entity.pheasant;

import com.google.common.collect.Maps;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.trolls.cave.CaveTrollEntity;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PheasantRenderer extends MobEntityRenderer<PheasantEntity, PheasantModel> {
    private static final String PATH = "textures/entities/pheasants/";
    public PheasantRenderer(EntityRendererFactory.Context context) {
        super(context, new PheasantModel(context.getPart(ModEntityModelLayers.PHEASANT)), 0.35F);

    }

    public static final Map<PheasantVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PheasantVariant.class), (map) -> {
                map.put(PheasantVariant.MALE,
                        new Identifier(MiddleEarth.MOD_ID, PATH + "pheasant1.png"));
                map.put(PheasantVariant.FEMALE,
                        new Identifier(MiddleEarth.MOD_ID, PATH + "pheasant2.png"));
            });

    @Override
    public Identifier getTexture(PheasantEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    public void render(PheasantEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(1, 1, 1);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
