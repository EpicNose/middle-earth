package net.jukoz.me.world.biomes.surface;

import net.jukoz.me.world.biomes.BiomeColorsDTO;
import net.jukoz.me.world.biomes.MEBiomeKeys;
import net.jukoz.me.world.spawners.ModSpawnSettingsBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.registry.Registerable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModBiomes {
    public static final int defaultSky = 7907327;
    public static final int defaultFog = 12638463;
    public static final int defaultWater = 4159204;
    public static final int defaultWaterFog = 329011;
    public static final int hillySky = 8233727;
    public static final int waterSky = 8103167;
    public static final int nearHaradSky = 7254527;
    public static final int nearHaradSkyFog = 12902399;

    private static List<RegistryKey<PlacedFeature>> vegetation = new ArrayList<>();;
    private static ArrayList<RegistryKey<PlacedFeature>> undergroundOres = new ArrayList<>();;

    public static void bootstrap(Registerable<Biome> context) {
        context.register(MEBiomeKeys.ANDUIN_VALES, createAnduinBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 8703593, 8703593)));
        context.register(MEBiomeKeys.ANORIEN, createAnorienBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 6799458, 6662221)));
        context.register(MEBiomeKeys.BARROW_DOWNS, createBarrowDownsBiome(context, new BiomeColorsDTO(
                5993621, 7635851, 4812936, 3034721, 6721389, 6525545)));
        context.register(MEBiomeKeys.BELFALAS, createBelfalasBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 5951101, 5485154), false));
        context.register(MEBiomeKeys.BELFALAS_HILLS, createBelfalasBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 6011255, 5614178), true));
        context.register(MEBiomeKeys.BLUE_MOUNTAINS, createBlueMountainsBiome(context, new BiomeColorsDTO(
                7903952, 12241898, defaultWater, defaultWaterFog, 6791282, 7773545), false));
        context.register(MEBiomeKeys.BLUE_MOUNTAINS_FOOTHILLS, createBlueMountainsBiome(context, new BiomeColorsDTO(
                7905261, 12241911, defaultWater, defaultWaterFog, 7125373, 8703593), true));
        context.register(MEBiomeKeys.CORSAIR_COASTS, createCorsairCoastBiome(context, new BiomeColorsDTO(
                nearHaradSky, defaultFog, 5212644, 333363, 12107900, 10860366)));
        context.register(MEBiomeKeys.DALE, createDaleBiome(context, new BiomeColorsDTO(
                8230911, 12178175, defaultWater, defaultWaterFog, 6400105, 8703593)));
        context.register(MEBiomeKeys.DARK_ANDUIN_VALES, createAnduinBiome(context, new BiomeColorsDTO(
                7246271, 8951443, 4354703, 1128527, 8032632, 7901046)));
        context.register(MEBiomeKeys.DARK_MIRKWOOD, createMirkwoodBiome(context, new BiomeColorsDTO(
                5269900, 4806731, 4544130, 338483, 4151612, 3823415), true, true));
        context.register(MEBiomeKeys.DARK_MIRKWOOD_EDGE, createMirkwoodBiome(context, new BiomeColorsDTO(
                5861275, 8293250, 4551554, 467756, 4083260, 3690038), false, true));
        context.register(MEBiomeKeys.DOL_GULDUR, createMirkwoodBiome(context, new BiomeColorsDTO(
                3684976, 5065045, 2503248, 462892, 3554356, 3424049), false, true));
        context.register(MEBiomeKeys.DORWINION, createDorwinionBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 4631895, 4431186)));
        context.register(MEBiomeKeys.DORWINION_HILLS, createDorwinionHillsBiome(context, new BiomeColorsDTO(
                hillySky, defaultFog, defaultWater, defaultWaterFog, 9084547, 8427113)));
        context.register(MEBiomeKeys.DUNLAND_FOOTHILLS, createDunlandFoothillsBiome(context, new BiomeColorsDTO(
                7508201, 10863086, defaultWater, defaultWaterFog, 6722387, 6198343)));
        context.register(MEBiomeKeys.EASTERN_RHOVANION, createRhunBiome(context, new BiomeColorsDTO(
                7571933, defaultFog, 12440052, 591663, 8627523, 7052347)));
        context.register(MEBiomeKeys.EMYN_MUIL, createEmynMuilBiome(context, new BiomeColorsDTO(
                11715292, 10924223, defaultWater, defaultWaterFog, 8229209, 7899480)));
        context.register(MEBiomeKeys.ENEDWAITH, createEnedwaithBiome(context, new BiomeColorsDTO(
                7181795, 10731509, 4618461, defaultWaterFog, 8761449, 7842644)));
        context.register(MEBiomeKeys.LONELY_MOUNTAIN, createLonelyMountainBiome(context, new BiomeColorsDTO(
                hillySky, 13031679, defaultWater, defaultWaterFog, 7188600, 6529388), 0));
        context.register(MEBiomeKeys.LONELY_MOUNTAIN_FOOTHILLS, createLonelyMountainBiome(context, new BiomeColorsDTO(
                hillySky, 12637183, defaultWater, defaultWaterFog, 6927733, 6005862), 1));
        context.register(MEBiomeKeys.LONELY_MOUNTAIN_PEAKS, createLonelyMountainBiome(context, new BiomeColorsDTO(
                hillySky, 13031679, defaultWater, defaultWaterFog, 8106628, 6987890), 2));
        context.register(MEBiomeKeys.EREGION, createEregionBiome(context, new BiomeColorsDTO(
                7908853, 11393279, 5077445, 331050, 6858575, 6198343)));
        context.register(MEBiomeKeys.ERIADOR, createEriadorBiome(context, new BiomeColorsDTO(
                defaultSky, 12637429, 4615389, 658236, 7582562, 6592327)));
        context.register(MEBiomeKeys.ETHIR_ANDUIN, createSwampAnduin(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 6400102, 6137428)));
        context.register(MEBiomeKeys.FANGORN, createFangornBiome(context, new BiomeColorsDTO(
                7972607, defaultFog, 4293787, 338483, 3559947, 1789719)));
        context.register(MEBiomeKeys.FORODWAITH, createForodwaithBiome(context, new BiomeColorsDTO(
                8364543, 10335206, 3823818, 66852, 3494723, 4478280)));
        context.register(MEBiomeKeys.FROZEN_OCEAN, createOceanBiome(context, new BiomeColorsDTO(
                8628223, 10599910, 3750089, 263470, 3494723, 4478280)));
        context.register(MEBiomeKeys.FROZEN_POND, createFrozenPond(context, new BiomeColorsDTO(
                8628223, 10599910, 3750089, 263470, 3494723, 4478280)));
        context.register(MEBiomeKeys.GONDOR, createGondorBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 7582562, 6592327)));
        context.register(MEBiomeKeys.GREY_MOUNTAINS, createGreyMountainsBiome(context, new BiomeColorsDTO(
                8098794, 12701936, defaultWater, defaultWaterFog, 8823414, 9022583)));
        context.register(MEBiomeKeys.GREY_PLAINS, createGreyPlainsBiome(context, new BiomeColorsDTO(
                hillySky, 12637179, defaultWater, defaultWaterFog, 5939024, 8566393)));
        context.register(MEBiomeKeys.HARAD, createHaradBiome(context, new BiomeColorsDTO(
                nearHaradSky, nearHaradSkyFog, 5407446, 1120828, 12301405, 13356379)));
        context.register(MEBiomeKeys.HARAD_DESERT, createHaradDesertBiome(context, new BiomeColorsDTO(
                nearHaradSky, nearHaradSkyFog, 5407446, 1120828, 13419633, 9615182)));
        context.register(MEBiomeKeys.HARONDOR, createHarondorBiome(context, new BiomeColorsDTO(
                nearHaradSky, nearHaradSkyFog, 5406149, 1120828, 12305028, 10860366)));
        context.register(MEBiomeKeys.HILLS_OF_EVENDIM, createHillsOfElvendim(context, new BiomeColorsDTO(
                hillySky, defaultFog, defaultWater, defaultWaterFog, 9087338, 9218155)));
        context.register(MEBiomeKeys.IRON_HILLS, createIronHillsBiome(context, new BiomeColorsDTO(
                10140415, 13031679, defaultWater, defaultWaterFog, 6922099, 7119988), false));
        context.register(MEBiomeKeys.IRON_HILLS_FOOTHILLS, createIronHillsBiome(context, new BiomeColorsDTO(
                8761343, defaultFog, defaultWater, defaultWaterFog, 7187321, 6793328), true));
        context.register(MEBiomeKeys.IRON_HILLS_FRONTIER, createIronHillsFrontierBiome(context, new BiomeColorsDTO(
                hillySky, 12637183, defaultWater, defaultWaterFog, 7253882, 7580788)));
        context.register(MEBiomeKeys.ITHILIEN, createIthilienBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 5152072, 4889668), false));
        context.register(MEBiomeKeys.ITHILIEN_WASTES, createIthilienBiome(context, new BiomeColorsDTO(
                9085388, 11254223, 4944318, 723757, 5733716, 5537108), true));
        context.register(MEBiomeKeys.LAMEDON, createLamedonBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 9162899, 8043898)));
        context.register(MEBiomeKeys.LEBENNIN, createLebennin(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 5883985, 7449969)));
        context.register(MEBiomeKeys.LINDON, createLindonBiome(context, new BiomeColorsDTO(
                8827134, 12771327, defaultWater, defaultWaterFog, 6929025, 5157703)));
        context.register(MEBiomeKeys.LONG_LAKE, createLakeBiome(context, new BiomeColorsDTO(
                waterSky, defaultFog, 4352228, 525619, 7576434, 6588506)));
        context.register(MEBiomeKeys.LORIEN_EDGE, createLorienEdgeBiome(context, new BiomeColorsDTO(
                defaultSky, 11455105, defaultWater, defaultWaterFog, 10601262, 6202980)));
        context.register(MEBiomeKeys.LOTHLORIEN, createLothlorienBiome(context, new BiomeColorsDTO(
                defaultSky, 13748853, defaultWater, defaultWaterFog, 12961832, 6989412)));
        context.register(MEBiomeKeys.MINHIRIATH, createMinhiriathBiome(context, new BiomeColorsDTO(
                defaultSky, 12637429, 4615389, 658236, 8626526, 8231005)));
        context.register(MEBiomeKeys.MIRKWOOD, createMirkwoodBiome(context, new BiomeColorsDTO(
                6849446, 6780008, 4949147, 338483, 4151612, 3823415), true, false));
        context.register(MEBiomeKeys.MIRKWOOD_EDGE, createMirkwoodBiome(context, new BiomeColorsDTO(
                7578800, 7897724, 4293787, 338483, 4546876, 4284215), false, false));
        context.register(MEBiomeKeys.MIRKWOOD_FOOTHILLS, createMirkwoodMountainsBiome(context, new BiomeColorsDTO(
                7575984, 7897724, 4293787, 338483, 4546876, 4284215), true));
        context.register(MEBiomeKeys.MIRKWOOD_MOUNTAINS, createMirkwoodMountainsBiome(context, new BiomeColorsDTO(
                7972607, 9873053, 4293787, 338483, 4678724, 4482114), false));
        context.register(MEBiomeKeys.MIRKWOOD_SWAMP, createMirkwoodSwampBiome(context, new BiomeColorsDTO(
                6981536, 8821922, 5080729, 1458241, 4546876, 4284215)));
        context.register(MEBiomeKeys.MISTY_FOOTHILLS, createMistyMountainsBiome(context, new BiomeColorsDTO(
                hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862), true));
        context.register(MEBiomeKeys.MISTY_MOUNTAINS, createMistyMountainsBiome(context, new BiomeColorsDTO(
                9745151, 13426943, defaultWater, defaultWaterFog, 7643011, 7709826), false));
        context.register(MEBiomeKeys.MORDOR, createMordorBiome(context, new BiomeColorsDTO(
                2695203, 1905947, 6450777, 1513734, 3550502, 2169880)));
        context.register(MEBiomeKeys.MORDOR_MOUNTAINS, createMordorBiome(context, new BiomeColorsDTO(
                4142646, 3090215, 6450777, 1513734, 3550502, 2169880)));
        context.register(MEBiomeKeys.MORDOR_MOUNTAINS_FOOTHILLS, createMordorBiome(context, new BiomeColorsDTO(
                3747117, 2629407, 6450777, 1513734, 5129527, 3486247)));
        context.register(MEBiomeKeys.MORDOR_WASTES, createMordorWastesBiome(context, new BiomeColorsDTO(
                5460048, 4999240, 5860962, 731161, 6252369, 4735297)));
        context.register(MEBiomeKeys.NINDALF, createSwampAnduin(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 6263143, 5869915)));
        context.register(MEBiomeKeys.NORTH_DOWNS, createNorthDowns(context, new BiomeColorsDTO(
                hillySky, defaultFog, defaultWater, defaultWaterFog, 9414008, 9018483)));
        context.register(MEBiomeKeys.NORTHERN_DUNLAND, createNorthDunlandBiome(context, new BiomeColorsDTO(
                7508201, 12964294, defaultWater, defaultWaterFog, 6722387, 6198343), true));
        context.register(MEBiomeKeys.NORTHERN_WASTELANDS, createNorthernWastelands(context, new BiomeColorsDTO(
                hillySky, defaultFog, defaultWater, defaultWaterFog, 5932658, 7973008)));
        context.register(MEBiomeKeys.NURN, createNurnBiome(context, new BiomeColorsDTO(
                8954077, 11781331, 5860970, 1321760, 7443043, 6982236)));
        context.register(MEBiomeKeys.NURN_RIVER, createNurnWaterBiome(context, new BiomeColorsDTO(
                8952797, 11779800, 5860963, 863008, 5465422, 5663573)));
        context.register(MEBiomeKeys.NURN_SEA, createNurnWaterBiome(context, new BiomeColorsDTO(
                8952797, 11779800, 5860963, 863008, 5465422, 5663573)));
        context.register(MEBiomeKeys.OCEAN, createOceanBiome(context, new BiomeColorsDTO(
                waterSky, defaultFog, 4157145, defaultWaterFog, 7576434, 6588506)));
        context.register(MEBiomeKeys.OCEAN_COAST, createOceanCoastBiome(context, new BiomeColorsDTO(
                8104447, defaultFog, 5145060, 330291, 7971954, 6590810)));
        context.register(MEBiomeKeys.OLD_ANGMAR, createOldAngmarBiome(context, new BiomeColorsDTO(
                8954077, 11781343, 4814544, 460593, 7443043, 6982236)));
        context.register(MEBiomeKeys.OLD_ARTHEDAIN, createOldArthedainBiome(context, new BiomeColorsDTO(
                7907327, defaultFog, defaultWater, defaultWaterFog, 12508275, 11652468)));
        context.register(MEBiomeKeys.OLD_CARDOLAN, createOldCardolanBiome(context, new BiomeColorsDTO(
                defaultSky, 12637429, 4615389, 658236, 9154400, 9154400)));
        context.register(MEBiomeKeys.OLD_RHUDAUR, createOldRhudaurBiome(context, new BiomeColorsDTO(
                7508201, 10863086, 4618461, defaultWaterFog, 6722387, 6198343)));
        context.register(MEBiomeKeys.OASIS, createOasisBiome(context, new BiomeColorsDTO(
                nearHaradSky, nearHaradSkyFog, 5407446, 1120828, 7253092, 6592350)));
        context.register(MEBiomeKeys.POND, createPondBiome(context, new BiomeColorsDTO(
                waterSky, defaultFog, 4290786, defaultWaterFog, 7583083, 6592339)));
        context.register(MEBiomeKeys.RHUN, createRhunBiome(context, new BiomeColorsDTO(
                8041727, 12773631, 4618980, defaultWaterFog, 10995507, 7181907)));
        context.register(MEBiomeKeys.RIVENDELL, createRivendellBiome(context, new BiomeColorsDTO(
                9090047, 13426943, defaultWater, defaultWaterFog, 7915639, 6858340)));
        context.register(MEBiomeKeys.RIVENDELL_FOOTHILLS, createRivendellFoothillsBiome(context, new BiomeColorsDTO(
                8695029, 12637426, defaultWater, defaultWaterFog, 8569732, 7644782)));
        context.register(MEBiomeKeys.RIVER, createRiverBiome(context, new BiomeColorsDTO(
                waterSky, defaultFog, 4290790, defaultWaterFog, 7583083, 6592339)));
        context.register(MEBiomeKeys.ROHAN, createRohanBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 10342513, 6918471)));
        context.register(MEBiomeKeys.SEA_OF_RHUN, createLakeBiome(context, new BiomeColorsDTO(
                waterSky, 12638463, 4159204, defaultWaterFog, 10995507, 7181907)));
        context.register(MEBiomeKeys.SHIRE, createShireBiome(context, new BiomeColorsDTO(
                8695029, 12637426, defaultWater, defaultWaterFog, 8704848, 5804821), 0));
        context.register(MEBiomeKeys.SHIRE_EDGE, createShireBiome(context, new BiomeColorsDTO(
                8695029, 12637426, defaultWater, defaultWaterFog, 7978571, 6132766), 1));
        context.register(MEBiomeKeys.SHIRE_WOODS, createShireBiome(context, new BiomeColorsDTO(
                8695029, 12637426, defaultWater, defaultWaterFog, 6662455, 5803292), 2));
        context.register(MEBiomeKeys.SOUTHEAST_RHOVANION, createRhunBiome(context, new BiomeColorsDTO(
                7905517, 11055575, defaultWater, defaultWaterFog, 8950352, 7836758)));
        context.register(MEBiomeKeys.SOUTHERN_DUNLAND, createNorthDunlandBiome(context, new BiomeColorsDTO(
                7508201, 10863086, defaultWater, defaultWaterFog, 8302697, 7252827), false));
        context.register(MEBiomeKeys.SOUTHERN_FOROCHEL, createSouthernForochelBiome(context, new BiomeColorsDTO(
                hillySky, defaultFog, defaultWater, defaultWaterFog, 7177842, 7971216)));
        context.register(MEBiomeKeys.THE_ANGLE, createTheAngleBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, 4615389, 658236, 9878641, 8431193)));
        context.register(MEBiomeKeys.THE_OLD_FOREST, createTheOldForestBiome(context, new BiomeColorsDTO(
                6785744, 10004675, 4421513, 402733, 2311707, 2050588)));
        context.register(MEBiomeKeys.THE_WOLD, createRohanBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 12309403, 12242068)));
        context.register(MEBiomeKeys.TOLFALAS, createTolfalasBiome(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 10927716, 9615182)));
        context.register(MEBiomeKeys.TROLLSHAWS, createTrollshawsBiome(context, new BiomeColorsDTO(
                6980296, 10402016, 4618461, defaultWaterFog, 9666387, 9731143)));
        context.register(MEBiomeKeys.UMBAR, createUmbarBiome(context, new BiomeColorsDTO(
                7254527, 12638463, 5212644, 333363, 11059059, 9284946)));
        context.register(MEBiomeKeys.WASTE_POND, createWastePondBiome(context, new BiomeColorsDTO(
                8163746, 10926783, 5860963, 863008, 4020033, 2371608)));
        context.register(MEBiomeKeys.WHITE_MOUNTAINS, createWhiteMountainsBiome(context, new BiomeColorsDTO(
                hillySky, 12638463, defaultWater, defaultWaterFog, 7183466, 7513204), false));
        context.register(MEBiomeKeys.WHITE_MOUNTAINS_FOOTHILLS, createWhiteMountainsBiome(context, new BiomeColorsDTO(
                hillySky, 12638463, defaultWater, defaultWaterFog, 7185769, 6857066), true));
        context.register(MEBiomeKeys.WOODLAND_REALM, createWoodlandRealmBiome(context, new BiomeColorsDTO(
                8497918, 10666932, 4492967, 471355, 2780195, 2713634)));
    }

    public static Biome createAnduinBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addFlowerGreenJewel(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addMapleTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createAnorienBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addGondorVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addTuftGrass(vegetation);
        ModBiomeFeatures.addDioriteBoulder(vegetation);

        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addRareLebethronTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createBarrowDownsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        ModBiomeFeatures.addGravelOre(vegetation);

        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createBelfalasBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean hills) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addSwan(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addCornflower(vegetation);
        ModBiomeFeatures.addTuftGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addCalciteBoulder(vegetation);
        ModBiomeFeatures.addWhiteSand(vegetation);

        if(hills) {
            addNordicVegetation(generationSettings);
            ModBiomeFeatures.addCalciteOre(vegetation);
            ModBiomeFeatures.addDioriteOre(vegetation);
            ModBiomeFeatures.addTuffOre(vegetation);
            ModBiomeFeatures.addGrassStoneOre(vegetation);
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addPineTrees(vegetation);
            ModBiomeFeatures.addRareSpruceTrees(vegetation);
            ModBiomeFeatures.addSpruceBushes(vegetation);
        } else {
            addGondorVegetation(generationSettings);
            vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
            ModBiomeFeatures.addRareBirchTrees(vegetation);
            ModBiomeFeatures.addRareLebethronTrees(vegetation);
        }

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createBlueMountainsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean foothills) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        float temperature = 0.3f;
        if(foothills) {
            addNordicVegetation(generationSettings);
            ModBiomeFeatures.addCornflower(vegetation);
            ModBiomeFeatures.addStoneGrassAbundantOre(vegetation);
            ModBiomeFeatures.addCommonLarchTrees(vegetation);
            ModBiomeFeatures.addAbundantPineTrees(vegetation);
            ModBiomeFeatures.addAbundantSpruceTrees(vegetation);
            ModBiomeFeatures.addCommonSpruceBushes(vegetation);
        } else {
            ModBiomeFeatures.addFrozenStone(vegetation);
            temperature = 0f;

        }
        ModBiomeFeatures.addBlueTuff(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, temperature, true);
    }

    public static Biome createCorsairCoastBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addHaradMobs(spawnSettings);
        ModSpawnSettingsBuilder.addLlama(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addHaradVegetation(generationSettings);
        ModBiomeFeatures.addCoastalFoliage(vegetation);
        vegetation.add(VegetationPlacedFeatures.PATCH_CACTUS_DESERT);
        ModBiomeFeatures.addGraniteBoulder(vegetation);
        ModBiomeFeatures.addSandStoneBoulder(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addSandOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);

        ModBiomeFeatures.addPalmTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createDaleBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addCornflower(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);

        addNordicTrees(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createDorwinionBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addGoose(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addFlowerDorwinion(vegetation);
        ModBiomeFeatures.addCornflower(vegetation);
        ModBiomeFeatures.addTuftGrass(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addRareHeather(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);

        ModBiomeFeatures.addWildBeetroot(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);

        ModBiomeFeatures.addCalciteBoulder(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addSparseBirchTrees(vegetation);
        ModBiomeFeatures.addVeryRareMegaOakTrees(vegetation);
        ModBiomeFeatures.addMegaBirchTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createDorwinionHillsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addTuffOre(vegetation);
        ModBiomeFeatures.addCalciteOre(vegetation);
        ModBiomeFeatures.addDioriteOre(vegetation);
        ModBiomeFeatures.addLimestoneOre(vegetation);
        ModBiomeFeatures.addStoneGrassAbundantOre(vegetation);
        ModBiomeFeatures.addCommonOakBush(vegetation);
        ModBiomeFeatures.addBirchTrees(vegetation);
        ModBiomeFeatures.addLarchTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createDunlandFoothillsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addDolomiteOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addPodzolOre(vegetation);
        ModBiomeFeatures.addStoneGrassOre(vegetation);
        ModBiomeFeatures.addTuffOre(vegetation);
        ModBiomeFeatures.addCommonLarchTrees(vegetation);
        ModBiomeFeatures.addAbundantPineTrees(vegetation);
        ModBiomeFeatures.addAbundantSpruceTrees(vegetation);
        ModBiomeFeatures.addCommonSpruceBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createEmynMuilBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addRabbits(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addStoneGrassOre(vegetation);
        ModBiomeFeatures.addTerracottaOre(vegetation);
        ModBiomeFeatures.addTuffOre(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addTuftGrass(vegetation);
        ModBiomeFeatures.addCommonOakBush(vegetation);
        ModBiomeFeatures.addGraniteBoulder(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createEnedwaithBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addRareHeather(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLonelyMountainBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, int step) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        float temperature = 0.4f;

        if(step == 0) {
            ModBiomeFeatures.addStoneGrassAbundantOre(vegetation);
            ModBiomeFeatures.addCoarseDirtOre(vegetation);
            addNordicVegetation(generationSettings);
            ModBiomeFeatures.addCommonLarchTrees(vegetation);
            ModBiomeFeatures.addAbundantPineTrees(vegetation);
            ModBiomeFeatures.addAbundantSpruceTrees(vegetation);
            ModBiomeFeatures.addCommonSpruceBushes(vegetation);
        } else if (step == 1) {
            ModBiomeFeatures.addStoneGrassOre(vegetation);
            ModBiomeFeatures.addDolomiteOre(vegetation);
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addPineTrees(vegetation);
            ModBiomeFeatures.addCommonSpruceTrees(vegetation);
            vegetation.add(VegetationPlacedFeatures.PATCH_LARGE_FERN);
            vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
            vegetation.add(VegetationPlacedFeatures.PATCH_BERRY_RARE);
        } else {
            ModBiomeFeatures.addDolomiteOre(vegetation);
            ModBiomeFeatures.addFrozenStone(vegetation);
            temperature = 0.0f;
        }
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addAbundantTuffOre(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, temperature, true);
    }

    public static Biome createEregionBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addDeer(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addPodzolOre(vegetation);
        ModBiomeFeatures.addBirchTrees(vegetation);
        ModBiomeFeatures.addBeechTrees(vegetation);
        ModBiomeFeatures.addLarchTrees(vegetation);
        ModBiomeFeatures.addOakTrees(vegetation);
        ModBiomeFeatures.addRareMegaOakTrees(vegetation);
        ModBiomeFeatures.addPineTrees(vegetation);
        ModBiomeFeatures.addSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createEriadorBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addEriadorVegetation(generationSettings);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addRareHeather(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareBirchTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createSwampAnduin(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addSwampMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        vegetation.add(VegetationPlacedFeatures.FLOWER_SWAMP);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE_SWAMP);
        ModBiomeFeatures.addWaterDelta(vegetation);
        ModBiomeFeatures.addAbundantMudOre(vegetation);
        ModBiomeFeatures.addPackedMudOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addReedsFoliage(vegetation);
        ModBiomeFeatures.addWheatGrass(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addOakVinesTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createFangornBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        vegetation.add(VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        ModBiomeFeatures.addMossyBoulder(vegetation);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addForestBlockMoss(vegetation);
        ModBiomeFeatures.addOldPodzolOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addRareMorsel(vegetation);
        ModBiomeFeatures.addRareWhiteMushroom(vegetation);

        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addMegaBirchCommonTrees(vegetation);
        ModBiomeFeatures.addMegaDarkOakCommonTrees(vegetation);
        ModBiomeFeatures.addMegaOakCommonTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createForodwaithBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addForochelMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addForodwaithVegetation(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings, -0.8f, true);
    }

    public static Biome createGondorBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addGondorVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addDioriteBoulder(vegetation);

        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareLebethronTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createGreyMountainsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addStoneGrassOre(vegetation);
        ModBiomeFeatures.addAshBlockOre(vegetation);
        ModBiomeFeatures.addSmoothBasaltOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addAbundantTuffOre(vegetation);
        addNordicVegetation(generationSettings);
        addNordicTrees(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createGreyPlainsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addRareForestMoss(vegetation);

        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addRareSpruceTrees(vegetation);
        ModBiomeFeatures.addSpruceBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createHaradBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addHaradMobs(spawnSettings);
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addHaradVegetation(generationSettings);
        ModBiomeFeatures.addSandOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addAcaciaTrees(vegetation);
        vegetation.add(VegetationPlacedFeatures.PATCH_DEAD_BUSH);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createHarondorBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addHaradMobs(spawnSettings);
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addHaradVegetation(generationSettings);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addSandOre(vegetation);
        ModBiomeFeatures.addSandStoneBoulder(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);

        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addVeryRareBeechTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createHaradDesertBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addHaradMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addHaradDesertVegetation(generationSettings);
        vegetation.add(VegetationPlacedFeatures.PATCH_DEAD_BUSH_2);

        return createBiome(biomeColors, spawnSettings, generationSettings, 0.8f, false);
    }

    public static Biome createHillsOfElvendim(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addGrassStoneOre(vegetation);
        ModBiomeFeatures.addAbundantTuffOre(vegetation);
        addArthedainVegetation(generationSettings);

        ModBiomeFeatures.addCommonLarchTrees(vegetation);
        ModBiomeFeatures.addPineTrees(vegetation);
        ModBiomeFeatures.addSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createIronHillsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean foothills) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        if(foothills) {
            ModBiomeFeatures.addGrassStoneOre(vegetation);
            ModBiomeFeatures.addCornflower(vegetation);
        }
        else {
            ModBiomeFeatures.addStoneGrassOre(vegetation);
        }
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addAbundantTuffOre(vegetation);
        addNordicVegetation(generationSettings);
        addNordicTrees(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createIronHillsFrontierBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addCornflower(vegetation);
        addNordicVegetation(generationSettings);
        addNordicTrees(generationSettings);
        ModBiomeFeatures.addSpruceBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createIthilienBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean wastes) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addDeer(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addForestBlockMoss(vegetation);
        ModBiomeFeatures.addRareMorsel(vegetation);
        ModBiomeFeatures.addRareWhiteMushroom(vegetation);
        ModBiomeFeatures.addMossyBoulder(vegetation);
        ModBiomeFeatures.addCommonOakBush(vegetation);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addDioriteBoulder(vegetation);
        ModBiomeFeatures.addOakTrees(vegetation);
        addGondorVegetation(generationSettings);
        if(!wastes) {
            ModBiomeFeatures.addFlowerGreenJewel(vegetation);
            ModBiomeFeatures.addWilderGrass(vegetation);
            ModBiomeFeatures.addBeechTrees(vegetation);
            ModBiomeFeatures.addBirchTrees(vegetation);
            ModBiomeFeatures.addCommonLebethronTrees(vegetation);
            ModBiomeFeatures.addMegaOakTrees(vegetation);
            ModBiomeFeatures.addSparsePineTrees(vegetation);
        } else {
            ModBiomeFeatures.addCommonToughBerries(undergroundOres);
            ModBiomeFeatures.addAshenDirtOre(undergroundOres);
            ModBiomeFeatures.addRareBeechTrees(vegetation);
            ModBiomeFeatures.addRareBirchTrees(vegetation);
            ModBiomeFeatures.addLebethronTrees(vegetation);
            ModBiomeFeatures.addDeadPineTrees(vegetation);
        }

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLamedonBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addGondorVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addRareCalciteOre(vegetation);
        ModBiomeFeatures.addCalciteBoulder(vegetation);
        ModBiomeFeatures.addDioriteBoulder(vegetation);

        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareLebethronTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLebennin(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addGondorVegetation(generationSettings);
        ModBiomeFeatures.addLebenninFlowers(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addCalciteBoulder(vegetation);
        ModBiomeFeatures.addDioriteBoulder(vegetation);

        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareOakTrees(vegetation);
        ModBiomeFeatures.addRareLebethronTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLindonBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addCornflower(vegetation);
        ModBiomeFeatures.addRareForestMoss(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);
        ModBiomeFeatures.addWhiteSand(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addRareBirchTrees(vegetation);
        ModBiomeFeatures.addMegaBirchTrees(vegetation);
        ModBiomeFeatures.addRareMegaOakTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLorienEdgeBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addSwan(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addLothlorienVegetation(generationSettings);
        ModBiomeFeatures.addMallornTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLothlorienBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addSwan(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addLothlorienVegetation(generationSettings);
        ModBiomeFeatures.addSmallMallornTress(vegetation);
        ModBiomeFeatures.addOakTrees(vegetation);
        ModBiomeFeatures.addSparseBirchTrees(vegetation);
        ModBiomeFeatures.addMegaMallornTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createMinhiriathBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addEriadorVegetation(generationSettings);
        ModBiomeFeatures.addAndesiteBoulder(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addTuftGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addWheatGrass(vegetation);
        ModBiomeFeatures.addCommonOakBush(vegetation);
        ModBiomeFeatures.addWildCarrot(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createMirkwoodBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean bigTrees, boolean dark) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addWolves(spawnSettings);
        if(!dark) ModSpawnSettingsBuilder.addDeer(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addMirkwoodVegetation(generationSettings);
        ModBiomeFeatures.addMirkwoodRoots(vegetation);
        ModBiomeFeatures.addMudOre(vegetation);
        ModBiomeFeatures.addRareMorsel(vegetation);
        ModBiomeFeatures.addRareWhiteMushroom(vegetation);

        if(bigTrees) {
            addMegaMirkwoodTrees(generationSettings);
            ModBiomeFeatures.addSmallMirkwoodTrees(vegetation);
            ModBiomeFeatures.addCorruptedMoss(vegetation);
        } else {
            addMirkwoodTrees(generationSettings);
            if(!dark) {
                ModBiomeFeatures.addForestMoss(vegetation);
                ModBiomeFeatures.addForestBlockMoss(vegetation);
            }
        }

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createMirkwoodMountainsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean foothill) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        ModBiomeFeatures.addGraniteBoulder(vegetation);
        float temperature = 0.3f;
        if(foothill) {
            addMirkwoodVegetation(generationSettings);
            ModBiomeFeatures.addSmallMirkwoodTrees(vegetation);
            ModBiomeFeatures.addGrassStoneOre(vegetation);
            ModBiomeFeatures.addGravelOre(vegetation);
            ModBiomeFeatures.addForestMoss(vegetation);
            ModBiomeFeatures.addMirkwoodRoots(vegetation);
            ModBiomeFeatures.addMudOre(vegetation);
            ModBiomeFeatures.addRareMorsel(vegetation);
            ModBiomeFeatures.addRareWhiteMushroom(vegetation);
        } else {
            temperature = 0.0f;
            ModBiomeFeatures.addGraniteOre(vegetation);
            ModBiomeFeatures.addDripstoneOre(vegetation);
            ModBiomeFeatures.addTuffOre(vegetation);
        }

        return createBiome(biomeColors, spawnSettings, generationSettings, temperature, true);
    }

    public static Biome createMirkwoodSwampBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addSwampMobs(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addMirkwoodVegetation(generationSettings);
        ModBiomeFeatures.addWaterDelta(vegetation);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addReedsFoliage(vegetation);
        ModBiomeFeatures.addWheatGrass(vegetation);
        ModBiomeFeatures.addMudOre(vegetation);

        addMirkwoodTrees(generationSettings);
        ModBiomeFeatures.addWillowTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createMistyMountainsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean foothills) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        float temperature = -0.6f;
        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addDolomiteOre(vegetation);
        ModBiomeFeatures.addTuffOre(vegetation);
        if(foothills) {
            temperature = 0.2f;
            ModBiomeFeatures.addCoarseDirtOre(vegetation);
            ModBiomeFeatures.addGravelOre(vegetation);
            ModBiomeFeatures.addStoneGrassOre(vegetation);
            ModBiomeFeatures.addCommonLarchTrees(vegetation);
            ModBiomeFeatures.addAbundantPineTrees(vegetation);
            ModBiomeFeatures.addAbundantSpruceTrees(vegetation);
            ModBiomeFeatures.addCommonSpruceBushes(vegetation);
        } else {
            ModBiomeFeatures.addPowderSnowOre(vegetation);
        }
        return createBiome(biomeColors, spawnSettings, generationSettings,temperature, true);
    }

    public static Biome createMordorBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addMordorVegetation(generationSettings);
        ModBiomeFeatures.addAshenDirtStoneOre(vegetation);
        ModBiomeFeatures.addLavaMagmaLake(generationSettings);
        ModBiomeFeatures.addBasaltPile(vegetation);
        ModBiomeFeatures.addBlackStonePile(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, 0.7f, false);
    }

    public static Biome createMordorWastesBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addMordorVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addMudOre(vegetation);
        ModBiomeFeatures.addAshenDirtOre(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createNorthDowns(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addGrassStoneOre(vegetation);
        ModBiomeFeatures.addAbundantTuffOre(vegetation);
        addEriadorVegetation(generationSettings);

        ModBiomeFeatures.addLarchTrees(vegetation);
        ModBiomeFeatures.addPineTrees(vegetation);
        ModBiomeFeatures.addSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createNorthDunlandBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean trees) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        ModSpawnSettingsBuilder.addWolves(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addSpruceBushes(vegetation);

        if(trees) {
            ModBiomeFeatures.addForestMoss(vegetation);
            ModBiomeFeatures.addBirchTrees(vegetation);
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addOakTrees(vegetation);
            ModBiomeFeatures.addCommonPineTrees(vegetation);
            ModBiomeFeatures.addSpruceTrees(vegetation);
        } else {
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addSparsePineTrees(vegetation);
            ModBiomeFeatures.addRareSpruceTrees(vegetation);
        }
        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createNorthernWastelands(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_BERRY_RARE);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addAndesiteBoulder(vegetation);

        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addRareSpruceTrees(vegetation);
        ModBiomeFeatures.addSpruceBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, -0.1f, true);
    }

    public static Biome createNurnBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addToughBerries(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addAshenDirtOre(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addDyingGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);

        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addVeryRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareBirchTrees(vegetation);
        ModBiomeFeatures.addVeryRareMegaOakTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createNurnWaterBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addSwampMobs(spawnSettings);
        ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);
        ModBiomeFeatures.addMudOre(vegetation);

        ModBiomeFeatures.addWillowTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createOldAngmarBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_LARGE_FERN);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_BERRY_RARE);
        ModBiomeFeatures.addAndesiteBoulder(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addBrownBolete(vegetation);
        ModBiomeFeatures.addMorsel(vegetation);
        ModBiomeFeatures.addWhiteMushroom(vegetation);
        ModBiomeFeatures.addToughBerriesRare(vegetation);
        ModBiomeFeatures.addAshBlockOre(vegetation);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addDyingGrass(vegetation);
        ModBiomeFeatures.addRareForestMoss(vegetation);

        ModBiomeFeatures.addDeadPineTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addScarceSpruceTrees(vegetation);
        ModBiomeFeatures.addSpruceBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, 0.35f, true);
    }

    public static Biome createOldArthedainBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addArthedainVegetation(generationSettings);

        ModBiomeFeatures.addOakBushes(vegetation);
        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addVeryRareBeechTrees(vegetation);
        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addVeryRareMegaOakTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createOldCardolanBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addEriadorVegetation(generationSettings);
        vegetation.add(VegetationPlacedFeatures.PATCH_BERRY_RARE);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addRareHeather(vegetation);
        ModBiomeFeatures.addRedHeather(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareBirchTrees(vegetation);
        ModBiomeFeatures.addOakTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createOldRhudaurBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addWolves(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addPodzolOre(vegetation);

        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addDeadPineTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addSpruceTrees(vegetation);
        ModBiomeFeatures.addSpruceBushes(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createRhunBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addRhunVegetation(generationSettings);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);

        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addRareBirchTrees(vegetation);
        ModBiomeFeatures.addOakTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createRivendellBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addRivendellVegetation(generationSettings);

        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addSparseBirchTrees(vegetation);
        ModBiomeFeatures.addVeryRareMegaOakTrees(vegetation);
        ModBiomeFeatures.addMegaBirchTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createRivendellFoothillsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addDolomiteOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addLimestoneOre(vegetation);
        ModBiomeFeatures.addStoneGrassAbundantOre(vegetation);
        ModBiomeFeatures.addTuffOre(vegetation);

        ModBiomeFeatures.addSparseBirchTrees(vegetation);
        ModBiomeFeatures.addLarchTrees(vegetation);
        ModBiomeFeatures.addCommonPineTrees(vegetation);
        ModBiomeFeatures.addCommonSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, 0.4f, true);
    }

    public static Biome createRohanBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addPlainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addGraniteBoulder(vegetation);
        ModBiomeFeatures.addFlowerGreenJewel(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);

        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addRareBeechTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createShireBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, int step) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addShireVegetation(generationSettings);

        if(step < 2) {
            ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
            ModSpawnSettingsBuilder.addGoose(spawnSettings);
            ModSpawnSettingsBuilder.addRareSnails(spawnSettings);
            ModBiomeFeatures.addFlowerGreenJewel(vegetation);
            ModBiomeFeatures.addRareBeechTrees(vegetation);
            ModBiomeFeatures.addRareBirchTrees(vegetation);
            vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
            if(step == 1) {
                ModBiomeFeatures.addOakTrees(vegetation);
            } else {
                ModBiomeFeatures.addStrawberries(vegetation);
            }
            ModBiomeFeatures.addVeryRareMegaOakTrees(vegetation);
        } else {
            ModSpawnSettingsBuilder.addDeer(spawnSettings);
            ModBiomeFeatures.addMossyBoulder(vegetation);
            ModBiomeFeatures.addForestMoss(vegetation);
            ModBiomeFeatures.addForestBlockMoss(vegetation);
            ModBiomeFeatures.addCoarseDirtOre(vegetation);
            ModBiomeFeatures.addPodzolOre(vegetation);
            ModBiomeFeatures.addRareMorsel(vegetation);

            vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
            ModBiomeFeatures.addBeechTrees(vegetation);
            ModBiomeFeatures.addBirchTrees(vegetation);
            ModBiomeFeatures.addMegaDarkOakTrees(vegetation);
            ModBiomeFeatures.addOakBushes(vegetation);
            ModBiomeFeatures.addOakTrees(vegetation);
            ModBiomeFeatures.addPineTrees(vegetation);
            ModBiomeFeatures.addVeryRareSpruceTrees(vegetation);
            ModBiomeFeatures.addMegaBirchTrees(vegetation);
            ModBiomeFeatures.addMegaOakCommonTrees(vegetation);
        }


        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createSouthernForochelBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addNordicMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addRareSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings, -0.4f, true);
    }

    public static Biome createTheAngleBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addEriadorMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addEriadorVegetation(generationSettings);
        ModBiomeFeatures.addRareHeather(vegetation);
        ModBiomeFeatures.addRareForestMoss(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);

        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addRareBeechTrees(vegetation);
        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addVeryRareSpruceTrees(vegetation);
        ModBiomeFeatures.addMapleTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createTheOldForestBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addDeer(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addEriadorVegetation(generationSettings);
        vegetation.add(VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        ModBiomeFeatures.addMossyBoulder(vegetation);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addForestBlockMoss(vegetation);
        ModBiomeFeatures.addOldPodzolOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addRareMorsel(vegetation);
        ModBiomeFeatures.addRareWhiteMushroom(vegetation);

        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addMegaBirchCommonTrees(vegetation);
        ModBiomeFeatures.addMegaDarkOakCommonTrees(vegetation);
        ModBiomeFeatures.addMegaOakCommonTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createTolfalasBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addGrassStoneOre(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);

        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addRareLebethronTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addRareSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createTrollshawsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addDeer(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addEriadorVegetation(generationSettings);
        vegetation.add(VegetationPlacedFeatures.PATCH_LARGE_FERN);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
        ModBiomeFeatures.addDyingGrass(vegetation);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addPodzolOre(vegetation);
        ModBiomeFeatures.addRareMorsel(vegetation);

        ModBiomeFeatures.addCommonOakBush(vegetation);
        ModBiomeFeatures.addCommonBeechTrees(vegetation);
        ModBiomeFeatures.addCommonOakTrees(vegetation);
        ModBiomeFeatures.addRareMegaOakTrees(vegetation);
        ModBiomeFeatures.addPineTrees(vegetation);
        ModBiomeFeatures.addSpruceTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createUmbarBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addHaradMobs(spawnSettings);
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addHaradVegetation(generationSettings);
        ModBiomeFeatures.addGraniteBoulder(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createRiverBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addRiverDisks(undergroundOres);
        vegetation.add(OceanPlacedFeatures.KELP_COLD);
        vegetation.add(OceanPlacedFeatures.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addReedsFoliage(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createLakeBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createOasisBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addCamel(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addOasisVegetation(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createWastePondBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(OceanPlacedFeatures.KELP_WARM);
        vegetation.add(OceanPlacedFeatures.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        ModBiomeFeatures.addReedsFoliage(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createWhiteMountainsBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors, boolean foothills) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addMountainsMobs(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addCalciteOre(vegetation);
        ModBiomeFeatures.addDioriteOre(vegetation);
        ModBiomeFeatures.addTuffOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        float temperature = 0.1f;

        if(foothills) {
            temperature = 0.35f;
            ModBiomeFeatures.addGrassStoneOre(vegetation);
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addPineTrees(vegetation);
            ModBiomeFeatures.addScarceSpruceTrees(vegetation);
            ModBiomeFeatures.addSpruceBushes(vegetation);
        } else {
            ModBiomeFeatures.addSnowOre(vegetation);
            ModBiomeFeatures.addCalciteStoneOre(vegetation);
        }



        return createBiome(biomeColors, spawnSettings, generationSettings, temperature, true);
    }

    public static Biome createWoodlandRealmBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addWolves(spawnSettings);
        ModSpawnSettingsBuilder.addDeer(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addMirkwoodVegetation(generationSettings);
        ModBiomeFeatures.addForestMoss(vegetation);
        ModBiomeFeatures.addFlowerGreenJewel(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);
        vegetation.add(VegetationPlacedFeatures.FLOWER_FOREST_FLOWERS);
        ModBiomeFeatures.addRareMorsel(vegetation);
        addMirkwoodTrees(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createOceanBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addOceanAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createOceanCoastBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addOceanAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);
        ModBiomeFeatures.addCoastalFoliage(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createPondBiome(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addDuck(spawnSettings);
        ModSpawnSettingsBuilder.addGoose(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);
        ModBiomeFeatures.addRiverSand(undergroundOres);
        ModBiomeFeatures.addWillowTrees(vegetation);

        return createBiome(biomeColors, spawnSettings, generationSettings);
    }

    public static Biome createFrozenPond(Registerable<Biome> context, BiomeColorsDTO biomeColors) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        ModSpawnSettingsBuilder.addColdWaterAnimals(spawnSettings);
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        
        ModBiomeFeatures.addDisks(undergroundOres);

        vegetation.add(OceanPlacedFeatures.KELP_COLD);
        vegetation.add(OceanPlacedFeatures.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);

        return createBiome(biomeColors, spawnSettings, generationSettings, -0.1f, true);
    }

    public static void addNordicTrees(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addLarchTrees(vegetation);
        ModBiomeFeatures.addSparsePineTrees(vegetation);
        ModBiomeFeatures.addRareSpruceTrees(vegetation);
    }

    public static void addMirkwoodTrees(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addMirkwoodTrees(vegetation);
    }

    public static void addMegaMirkwoodTrees(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addMegaMirkwoodTrees(vegetation);
    }

    public static void addDefaultVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addWildBeetroot(vegetation);
        ModBiomeFeatures.addWildCucumber(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
        ModBiomeFeatures.addWildGarlic(vegetation);
    }

    public static void addArthedainVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addHeather(vegetation);
        ModBiomeFeatures.addHeatherBush(vegetation);
        ModBiomeFeatures.addWheatGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addWildGarlic(vegetation);
        ModBiomeFeatures.addWildLeek(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildPotato(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
    }

    public static void addEriadorVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addFlowerGreenJewel(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addWildLeek(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
        ModBiomeFeatures.addGraniteBoulder(vegetation);
    }

    public static void addForodwaithVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        ModBiomeFeatures.addToughBerriesRare(vegetation);
    }

    public static void addGondorVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addWildCarrot(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
        ModBiomeFeatures.addWildGarlic(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
    }

    public static void addHaradVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_SAVANNA);
        vegetation.add(VegetationPlacedFeatures.PATCH_TALL_GRASS);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        ModBiomeFeatures.addHaradFoliage(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addWildBellPepper(vegetation);
        ModBiomeFeatures.addWildTomato(vegetation);
    }

    public static void addHaradDesertVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_SAVANNA);
        ModBiomeFeatures.addDryGrass(vegetation);
        ModBiomeFeatures.addSandStoneBoulder(vegetation);
    }

    public static void addLothlorienVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_MEADOW);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addDryDirtOre(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);
        ModBiomeFeatures.addTuftGrass(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addMallornBushes(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addMallos(vegetation);
        ModBiomeFeatures.addWildCarrot(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
    }

    public static void addMirkwoodVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        vegetation.add(VegetationPlacedFeatures.PATCH_LARGE_FERN);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_BERRY_RARE);
        ModBiomeFeatures.addCoarseDirtOre(vegetation);
        ModBiomeFeatures.addOldPodzolOre(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
        ModBiomeFeatures.addWildGarlic(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
        ModBiomeFeatures.addMossyBoulder(vegetation);
    }

    public static void addMordorVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        ModBiomeFeatures.addMordorLichen(vegetation);
        ModBiomeFeatures.addAshBlockOre(vegetation);
        ModBiomeFeatures.addBasaltOre(vegetation);
        ModBiomeFeatures.addBlackSand(vegetation);
        ModBiomeFeatures.addCommonToughBerries(vegetation);
    }

    public static void addNordicVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_LARGE_FERN);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_BERRY_RARE);
        ModBiomeFeatures.addAndesiteBoulder(vegetation);
        ModBiomeFeatures.addWildGrass(vegetation);
        ModBiomeFeatures.addBrownBolete(vegetation);
        ModBiomeFeatures.addMorsel(vegetation);
        ModBiomeFeatures.addWhiteMushroom(vegetation);
        ModBiomeFeatures.addWildBeetroot(vegetation);
        ModBiomeFeatures.addWildPotato(vegetation);
    }

    public static void addOasisVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(OceanPlacedFeatures.KELP_WARM);
        vegetation.add(OceanPlacedFeatures.SEAGRASS_WARM);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_SAVANNA);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addHaradFoliage(vegetation);
        ModBiomeFeatures.addWildBellPepper(vegetation);
        ModBiomeFeatures.addWildTomato(vegetation);
    }

    public static void addOceanVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(OceanPlacedFeatures.KELP_COLD);
        vegetation.add(OceanPlacedFeatures.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addReedsFoliage(vegetation);
    }

    public static void addRivendellVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addFlowerMeadow(vegetation);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addWhiteSand(vegetation);

        ModBiomeFeatures.addWildLeek(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);
    }

    public static void addRhunVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.TREES_PLAINS);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addWildCarrot(vegetation);
        ModBiomeFeatures.addWildBellPepper(vegetation);
        ModBiomeFeatures.addWildFlax(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
    }

    public static void addShireVegetation(GenerationSettings.LookupBackedBuilder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacedFeatures.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacedFeatures.PATCH_PUMPKIN);
        ModBiomeFeatures.addWilderGrass(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addWildBeetroot(vegetation);
        ModBiomeFeatures.addWildCarrot(vegetation);
        ModBiomeFeatures.addWildCucumber(vegetation);
        ModBiomeFeatures.addWildGarlic(vegetation);
        ModBiomeFeatures.addWildLeek(vegetation);
        ModBiomeFeatures.addWildLettuce(vegetation);
        ModBiomeFeatures.addWildOnion(vegetation);
        ModBiomeFeatures.addWildPipeweed(vegetation);
        ModBiomeFeatures.addWildPotato(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
    }

    public static Biome createBiome(BiomeColorsDTO biomeColors, SpawnSettings.Builder spawnSettings, GenerationSettings.LookupBackedBuilder generationSettings) {
        return createBiome(biomeColors, spawnSettings, generationSettings, 0.5f, true);
    }

    public static Biome createBiome(BiomeColorsDTO biomeColors, SpawnSettings.Builder spawnSettings, GenerationSettings.LookupBackedBuilder generationSettings, float temperature, boolean precipitation) {
        undergroundOres.add(OrePlacedFeatures.ORE_DIRT);
        undergroundOres.add(OrePlacedFeatures.ORE_GRAVEL);
        undergroundOres.add(OrePlacedFeatures.ORE_GRANITE_UPPER);
        undergroundOres.add(OrePlacedFeatures.ORE_GRANITE_LOWER);
        undergroundOres.add(OrePlacedFeatures.ORE_DIORITE_UPPER);
        undergroundOres.add(OrePlacedFeatures.ORE_DIORITE_LOWER);
        undergroundOres.add(OrePlacedFeatures.ORE_ANDESITE_UPPER);
        undergroundOres.add(OrePlacedFeatures.ORE_ANDESITE_LOWER);
        undergroundOres.add(OrePlacedFeatures.ORE_TUFF);
        undergroundOres.add(OrePlacedFeatures.ORE_COAL_UPPER);
        vegetation.add(UndergroundPlacedFeatures.GLOW_LICHEN);

        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        vegetation = vegetation.stream().sorted(Comparator.comparing(a -> a.getValue().toString())).toList();
        for (RegistryKey<PlacedFeature> feature: vegetation) {
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, feature);
        }
        for (RegistryKey<PlacedFeature> feature: undergroundOres.stream().sorted(Comparator.comparing(a -> a.getValue().toString())).toList()) {
            generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, feature);
        }

        Biome biome = (new Biome.Builder())
                .precipitation(precipitation)
                .temperature(temperature)
                .downfall(0.5F)
                .effects((new BiomeEffects.Builder())
                        .skyColor(biomeColors.skyColor)
                        .fogColor(biomeColors.fogColor)
                        .waterColor(biomeColors.waterColor)
                        .waterFogColor(biomeColors.waterFogColor)
                        .grassColor(biomeColors.grassColor)
                        .foliageColor(biomeColors.foliageColor)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
        vegetation = new ArrayList<>();
        undergroundOres = new ArrayList<>();
        return biome;
    }
}
