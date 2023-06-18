package fireopal.profundis.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record AmethystVeinFeatureConfig(boolean undergroundOnly, IntProvider radius, IntProvider range, IntProvider targetCount) implements FeatureConfig {
    public static final Codec<AmethystVeinFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
       Codec.BOOL.fieldOf("underground_only").forGetter(AmethystVeinFeatureConfig::undergroundOnly),
       IntProvider.NON_NEGATIVE_CODEC.fieldOf("radius").forGetter(AmethystVeinFeatureConfig::radius),
       IntProvider.POSITIVE_CODEC.fieldOf("range").forGetter(AmethystVeinFeatureConfig::range),
       IntProvider.POSITIVE_CODEC.fieldOf("target_count").forGetter(AmethystVeinFeatureConfig::targetCount)
    ).apply(instance, instance.stable(AmethystVeinFeatureConfig::new)));
}
