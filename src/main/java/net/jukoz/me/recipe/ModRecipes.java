package net.jukoz.me.recipe;

import net.jukoz.me.MiddleEarth;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeType ARTISAN_TABLE_RECIPE_TYPE = register("artisan_table");

    static <T extends Recipe<?>> RecipeType register(final String id) {
        return Registry.register(Registries.RECIPE_TYPE, new Identifier(MiddleEarth.MOD_ID, ArtisanRecipe.Type.ID),ArtisanRecipe.Type.INSTANCE);
    }

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER,
                new Identifier(MiddleEarth.MOD_ID, AlloyRecipe.Serializer.ID),
                AlloyRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE,
                new Identifier(MiddleEarth.MOD_ID, AlloyRecipe.Type.ID),
                AlloyRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER,
                new Identifier(MiddleEarth.MOD_ID, ArtisanRecipe.Serializer.ID),
                ArtisanRecipe.Serializer.INSTANCE);
        /*Registry.register(Registries.RECIPE_TYPE,
                new Identifier(MiddleEarth.MOD_ID, ArtisanRecipe.Type.ID),
                ArtisanRecipe.Type.INSTANCE);*/
    }
}
