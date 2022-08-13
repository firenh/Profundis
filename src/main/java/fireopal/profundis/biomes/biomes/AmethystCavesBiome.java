package fireopal.profundis.biomes.biomes;

import fireopal.profundis.biomes.ProfundisDefaultBiomeFeatures;
import fireopal.profundis.features.ProfundisPlacedFeatures;
import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Build;
import fireopal.profundis.util.FireopalBiomeAPI_v1_2.Generation;
import net.minecraft.client.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class AmethystCavesBiome {
    public static Biome create() {
        Biome.Builder biome = new Biome.Builder();

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        ProfundisDefaultBiomeFeatures.addGenericCaveFeatures(generationSettings);
        Generation.undergroundDecorationFeatures(generationSettings,
            ProfundisPlacedFeatures.AMETHYST_VEINS,
            ProfundisPlacedFeatures.AMETHYST_VEINS_LARGE,
            ProfundisPlacedFeatures.AMETHYST_VEINS_AIR,
            ProfundisPlacedFeatures.AMETHYST_GEODE_AMETHYST_CAVES
        );

        BiomeEffects.Builder biomeEffects = new BiomeEffects.Builder()
            .skyColor(7842047).fogColor(12638463).waterColor(4159204).waterFogColor(329011)
            .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST));

        Build.properties(biome, Precipitation.RAIN, 0.9f, 1f);
        return Build.finalize(biome, spawnSettings, generationSettings, biomeEffects);
    }
}
