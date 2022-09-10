package fireopal.profundis.biomes;

import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Build;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ProfundisDefaultBiomeFeatures {
    public static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

    public static void addGenericCaveFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings, true);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addPlainsFeatures(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);
    }

    public static Biome createMushroomCaves() {
        Biome.Builder biome = new Biome.Builder();

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        addBasicFeatures(generationSettings);
        addGenericCaveFeatures(generationSettings);

        BiomeEffects.Builder biomeEffects = new BiomeEffects.Builder()
                .skyColor(8364543).fogColor(12638463).waterFogColor(329011);

        Build.properties(biome, Precipitation.RAIN, Biome.Category.UNDERGROUND, 0f, 0.5f);
        return Build.finalize(biome, spawnSettings, generationSettings, biomeEffects);
    }
}
