package fireopal.profundis.biomes.biomes;

import fireopal.profundis.biomes.ProfundisDefaultBiomeFeatures;
import fireopal.profundis.features.ProfundisPlacedFeatures;
import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Build;
import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Generation;
import net.minecraft.client.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class MushroomCavesBiome {
    public static Biome create() {
        Biome.Builder biome = new Biome.Builder();

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        ProfundisDefaultBiomeFeatures.addBasicFeatures(generationSettings);
        ProfundisDefaultBiomeFeatures.addGenericCaveFeatures(generationSettings);
        Generation.undergroundDecorationFeatures(generationSettings,
                ProfundisPlacedFeatures.MYCELIUM_CAVE_SURFACE,
                ProfundisPlacedFeatures.CAVE_HUGE_MUSHROOMS,
                ProfundisPlacedFeatures.MUSHROOM_CAVES_RED,
                ProfundisPlacedFeatures.MUSHROOM_CAVES_BROWN,
                ProfundisPlacedFeatures.SHELF_FUNGI
        );

        BiomeEffects.Builder biomeEffects = new BiomeEffects.Builder()
                .skyColor(7842047).fogColor(12638463).waterColor(4159204).waterFogColor(329011)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_LUSH_CAVES));

        Build.properties(biome, Precipitation.RAIN, Biome.Category.UNDERGROUND, 0.9f, 1f);
        return Build.finalize(biome, spawnSettings, generationSettings, biomeEffects);
    }
}
