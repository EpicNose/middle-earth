package net.jukoz.me.datageneration.content.models;

import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.block.StoneBlockSets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class SimpleWoodStoolModel {

    public record VanillaStool(Block base, Block origin) {}

    public static List<Block> stools = new ArrayList<>() {
        {
        }
    };

    public static List<VanillaStool> vanillaStools = new ArrayList<>() {
        {
            add(new VanillaStool(ModDecorativeBlocks.OAK_STOOL, Blocks.OAK_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.SPRUCE_STOOL, Blocks.SPRUCE_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.BIRCH_STOOL, Blocks.BIRCH_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.JUNGLE_STOOL, Blocks.JUNGLE_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.ACACIA_STOOL, Blocks.ACACIA_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.DARK_OAK_STOOL, Blocks.DARK_OAK_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.MANGROVE_STOOL, Blocks.MANGROVE_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.BAMBOO_STOOL, Blocks.BAMBOO_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.CHERRY_STOOL, Blocks.CHERRY_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.CRIMSON_STOOL, Blocks.CRIMSON_SLAB));
            add(new VanillaStool(ModDecorativeBlocks.WARPED_STOOL, Blocks.WARPED_SLAB));
        }
    };
}
