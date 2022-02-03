package fireopal.profundis.gen;

import com.google.common.collect.ImmutableList;

import fireopal.profundis.biomes.ProfundisBiomeKeys;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.ParameterRange;

public class ProfundisCaveBiomes {
    public static class CaveBiome {
        public final ParameterRange temperature, humidity, continentalness, erosion, depth, weirdness;
        public final float offset;
        public final RegistryKey<Biome> biome;

        public CaveBiome(ParameterRange temperature, ParameterRange humidity, ParameterRange continentalness, ParameterRange erosion, ParameterRange depth, ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.continentalness = continentalness;
            this.erosion = erosion;
            this.depth = depth;
            this.weirdness = weirdness;
            this.offset = offset; 
            this.biome = biome;
        }

        public static CaveBiome of(ParameterRange temperature, ParameterRange humidity, ParameterRange continentalness, ParameterRange erosion, ParameterRange depth, ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
            return new CaveBiome(temperature, humidity, continentalness, erosion, depth, weirdness, offset, biome);
        }
    }

    private static final MultiNoiseUtil.ParameterRange DEFAULT_PARAMETER = MultiNoiseUtil.ParameterRange.of(-1.0f, 1.0f);
    static final ParameterRange ALL_HEIGHT_RANGE = ParameterRange.of(0.2f, 0.9f);
    static final ParameterRange ALL_HEIGHT_RANGE_DEEPER = ParameterRange.of(-0.2f, 0.9f);
    static final ParameterRange HIGH_RANGE = ParameterRange.of(0.55f, 0.9f);

    /* Ordering of terms:
     *      temperature,
     *      humidity,
     *      continentalness,
     *      erosion,
     *      depth,
     *      weirdness,
     *      offset
     *      biome
     */ 

    public static ImmutableList<CaveBiome> defaultCaveBiomes = ImmutableList.of(
        CaveBiome.of(
            ParameterRange.of(-1.0f, -0.6f),
            DEFAULT_PARAMETER,
            DEFAULT_PARAMETER,
            DEFAULT_PARAMETER,
            ALL_HEIGHT_RANGE,
            ParameterRange.of(0.7f, 1.0f),
            0f,
            ProfundisBiomeKeys.FROZEN_CAVES
        ),

        CaveBiome.of(
            ParameterRange.of(-1.0f, -0.6f),
            DEFAULT_PARAMETER,
            DEFAULT_PARAMETER,
            DEFAULT_PARAMETER,
            ALL_HEIGHT_RANGE,
            ParameterRange.of(-1.0f, -0.7f),
            0f,
            ProfundisBiomeKeys.FROZEN_CAVES
        ),

        CaveBiome.of(
            ParameterRange.of(-0.45f, -0.45f),
            ParameterRange.of(-0.8f, -0.4f),
            ParameterRange.of(0f, 0.35f),
            DEFAULT_PARAMETER,
            ALL_HEIGHT_RANGE,
            DEFAULT_PARAMETER,
            0f,
            ProfundisBiomeKeys.MUSHROOM_CAVES
        ),

        CaveBiome.of(
            DEFAULT_PARAMETER,
            DEFAULT_PARAMETER,
            ParameterRange.of(0.4f, 0.5f),
            DEFAULT_PARAMETER,
            ALL_HEIGHT_RANGE,
            ParameterRange.of(0.8f, 1.0f),
            0f,
            ProfundisBiomeKeys.MOLTEN_CAVES
        )
    );
}
