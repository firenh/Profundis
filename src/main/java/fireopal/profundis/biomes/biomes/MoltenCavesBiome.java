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

public class MoltenCavesBiome {
    public static Biome create() {
        Biome.Builder biome = new Biome.Builder();

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        ProfundisDefaultBiomeFeatures.addBasicFeatures(generationSettings);
        ProfundisDefaultBiomeFeatures.addGenericCaveFeatures(generationSettings);
        Generation.undergroundDecorationFeatures(generationSettings,
            ProfundisPlacedFeatures.MOLTEN_CAVES_DELTA,
            ProfundisPlacedFeatures.MAGMA_REPLACE_LAVA,
            ProfundisPlacedFeatures.SMOOTH_BASALT_PEPPERS,
            ProfundisPlacedFeatures.GRANITE_PEPPERS,
            ProfundisPlacedFeatures.ANDESITE_PEPPERS,
            ProfundisPlacedFeatures.MOLTEN_CAVES_BASALT_COLUMNS,
            ProfundisPlacedFeatures.BASALT_PILLAR_MOLTEN_CAVES,
            // ProfundisPlacedFeatures.LAVA_FIXER,
            ProfundisPlacedFeatures.INCREASED_UNDERWATER_MAGMA,
            ProfundisPlacedFeatures.INCREASED_LAVA_SPRINGS,
            ProfundisPlacedFeatures.MOLTEN_CAVES_IRON
            
        );

        BiomeEffects.Builder biomeEffects = new BiomeEffects.Builder()
            .skyColor(7842047).fogColor(12638463).waterColor(4159204).waterFogColor(329011);

        Build.properties(biome, Precipitation.RAIN, Biome.Category.UNDERGROUND, 0.9f, 1f);
        return Build.finalize(biome, spawnSettings, generationSettings, biomeEffects);
    }
}
