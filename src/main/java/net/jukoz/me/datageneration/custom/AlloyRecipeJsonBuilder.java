package net.jukoz.me.datageneration.custom;

import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.recipe.AlloyingRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AlloyRecipeJsonBuilder implements CraftingRecipeJsonBuilder {

    private final RecipeCategory category;
    private final DefaultedList<Ingredient> inputs = DefaultedList.of();
    private final String metalOutput;
    private final int metalAmount;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    private String group;

    public AlloyRecipeJsonBuilder(RecipeCategory category, String metalOutput, int metalAmount) {
        this.category = category;
        this.metalOutput = metalOutput;
        this.metalAmount = metalAmount;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return ModResourceItems.ROD;
    }

    public String getOutputMetal() {
        return this.metalOutput;
    }

    public int getMetalAmount() {
        return this.metalAmount;
    }

    public static AlloyRecipeJsonBuilder createAlloyRecipe(RecipeCategory category, String output, int amount) {
        return new AlloyRecipeJsonBuilder(category, output, amount);
    }

    public AlloyRecipeJsonBuilder input(TagKey<Item> tag) {
        return this.input(Ingredient.fromTag(tag));
    }

    public AlloyRecipeJsonBuilder input(ItemConvertible itemProvider) {
        return this.input((ItemConvertible)itemProvider, 1);
    }

    public AlloyRecipeJsonBuilder input(ItemConvertible itemProvider, int size) {
        for(int i = 0; i < size; ++i) {
            this.input(Ingredient.ofItems(new ItemConvertible[]{itemProvider}));
        }
        return this;
    }

    public AlloyRecipeJsonBuilder input(Ingredient ingredient) {
        return this.input((Ingredient)ingredient, 1);
    }

    public AlloyRecipeJsonBuilder input(Ingredient ingredient, int size) {
        for(int i = 0; i < size; ++i) {
            this.inputs.add(ingredient);
        }
        return this;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder().criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::criterion);
        AlloyingRecipe alloyRecipeJsonBuilder = new AlloyingRecipe((String)Objects.requireNonNullElse(this.group, ""), CraftingRecipeJsonBuilder.toCraftingCategory(this.category), this.metalOutput, this.inputs, this.metalAmount);
        exporter.accept(recipeId, alloyRecipeJsonBuilder, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    @Override
    public AlloyRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(recipeId));
        }
    }
}
