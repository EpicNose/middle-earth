package net.jukoz.me.item.items.armor;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.dataComponents.HoodDataComponent;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.armor.ExtendedArmorMaterial;
import net.jukoz.me.item.utils.armor.ModDyeablePieces;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class CustomChestplateItem extends ArmorItem implements MEEquipmentTooltip {

    public ModFactions faction;
    public ModSubFactions subFaction;

    private ExtendedArmorMaterial material;

    public CustomChestplateItem(ExtendedArmorMaterial material, Settings settings, ModFactions faction) {
        super(material.material(), ArmorItem.Type.CHESTPLATE, settings.maxCount(1).maxDamage(Type.CHESTPLATE.getMaxDamage(material.durabilityModifier())));
        this.material = material;

        this.faction = faction;
        this.subFaction = null;
    }

    public CustomChestplateItem(ExtendedArmorMaterial material, Settings settings, ModSubFactions subFaction) {
        super(material.material(), ArmorItem.Type.CHESTPLATE, settings.maxCount(1).maxDamage(Type.CHESTPLATE.getMaxDamage(material.durabilityModifier())));
        this.material = material;

        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
    }

    @Override
    public List<Text> getAdditionalShiftLines(ItemStack stack) {
        List<Text> list = new ArrayList<>(List.of());
        list.add(Text.translatable("tooltip." + MiddleEarth.MOD_ID + ".tier_" + this.material.tier().toString().toLowerCase()));

        return list;
    }

    @Override
    public List<Text> getAdditionalAltLines(ItemStack stack) {
        List<Text> list = new ArrayList<>(List.of());
        CapeDataComponent capeDataComponent = stack.get(ModDataComponentTypes.CAPE_DATA);
        CustomDyeableDataComponent dyeDataComponent = stack.get(ModDataComponentTypes.DYE_DATA);

        if(dyeDataComponent != null){
            list.add(Text.translatable("tooltip." + MiddleEarth.MOD_ID + ".color").formatted(Formatting.GRAY).append(": " + String.format(MEEquipmentTooltip.COLOR_PREFIX, (0xFFFFFF & CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR)))).formatted(Formatting.GRAY));
        }
        if (capeDataComponent != null) {
            if (ModDyeablePieces.dyeableCapes.containsKey(capeDataComponent.cape())){
                list.add(Text.translatable("tooltip." + MiddleEarth.MOD_ID + "." + capeDataComponent.cape().getName())
                        .append(" (")
                        .append(Text.translatable("tooltip." + MiddleEarth.MOD_ID + ".color")
                                .append(": " + String.format("#%06X", (0xFFFFFF & CapeDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR))))
                                .append(")")).formatted(Formatting.GRAY));
            } else {
                list.add(Text.translatable("tooltip." + MiddleEarth.MOD_ID + "." + capeDataComponent.cape().getName()).formatted(Formatting.GRAY));
            }
        }

        return list;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
