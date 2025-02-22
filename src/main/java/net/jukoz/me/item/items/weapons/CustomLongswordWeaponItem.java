package net.jukoz.me.item.items.weapons;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.ModWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;

public class CustomLongswordWeaponItem extends ReachWeaponItem {
    public static final Identifier ENTITY_INTERACTION_RANGE_MODIFIER_ID = Identifier.of(MiddleEarth.MOD_ID, "entity_interaction_range");

    public CustomLongswordWeaponItem(ToolMaterial toolMaterial) {
        super(toolMaterial, ModWeaponTypes.LONGSWORD);
    }

    public CustomLongswordWeaponItem(ToolMaterial toolMaterial, ModFactions faction) {
        super(toolMaterial, faction, ModWeaponTypes.LONGSWORD);
    }

    public CustomLongswordWeaponItem(ToolMaterial toolMaterial, ModSubFactions subFaction) {
        super(toolMaterial, subFaction, ModWeaponTypes.LONGSWORD);
    }
}
