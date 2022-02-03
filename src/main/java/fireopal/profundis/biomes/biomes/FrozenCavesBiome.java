package fireopal.profundis.biomes.biomes;

import fireopal.profundis.biomes.ProfundisDefaultBiomeFeatures;
import fireopal.profundis.features.ProfundisPlacedFeatures;
import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Build;
import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Generation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class FrozenCavesBiome {
    public static Biome create() {
        Biome.Builder biome = new Biome.Builder();

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        ProfundisDefaultBiomeFeatures.addBasicFeatures(generationSettings);
        ProfundisDefaultBiomeFeatures.addGenericCaveFeatures(generationSettings);
        Generation.undergroundOresFeatures(generationSettings, ProfundisPlacedFeatures.FROZEN_CAVES_REDSTONE_ORE);
        Generation.undergroundDecorationFeatures(generationSettings,
            ProfundisPlacedFeatures.ICY_SURFACE,
            ProfundisPlacedFeatures.SNOW_LAYER_CAVE,
            ProfundisPlacedFeatures.FROZEN_AQUIFER,
            ProfundisPlacedFeatures.FROZEN_LAVA_AQUIFER,
            ProfundisPlacedFeatures.SMALL_ICICLES,
            ProfundisPlacedFeatures.LARGE_ICICLES,
            ProfundisPlacedFeatures.ICE_PILLAR
        );

        BiomeEffects.Builder biomeEffects = new BiomeEffects.Builder()
            .skyColor(8364543).fogColor(12638463).waterColor(3750089).waterFogColor(329011);

        Build.properties(biome, Precipitation.SNOW, Biome.Category.UNDERGROUND, 0f, 0.5f);
        return Build.finalize(biome, spawnSettings, generationSettings, biomeEffects);
    }
}
