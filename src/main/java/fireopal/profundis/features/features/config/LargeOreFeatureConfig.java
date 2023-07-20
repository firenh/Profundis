package fireopal.profundis.features.features.config;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public record LargeOreFeatureConfig(
    List<OreFeatureConfig.Target> targets,
    IntProvider radius,
    double scale,
    double factor,
    double smearing,
    double valueRange,
    double valueOffset,
    boolean bordersAir
) implements FeatureConfig {

    public static final Codec<LargeOreFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.list(OreFeatureConfig.Target.CODEC).optionalFieldOf("targets", List.of()).forGetter(LargeOreFeatureConfig::targets),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("radius").forGetter(LargeOreFeatureConfig::radius),
            Codec.DOUBLE.fieldOf("scale").forGetter(LargeOreFeatureConfig::scale),
            Codec.DOUBLE.fieldOf("factor").forGetter(LargeOreFeatureConfig::factor),
            Codec.DOUBLE.fieldOf("smearing").forGetter(LargeOreFeatureConfig::smearing),
            Codec.DOUBLE.fieldOf("value_range").forGetter(LargeOreFeatureConfig::valueRange),
            Codec.DOUBLE.fieldOf("value_offset").forGetter(LargeOreFeatureConfig::valueOffset),
            Codec.BOOL.fieldOf("borders_air").forGetter(LargeOreFeatureConfig::bordersAir)

        ).apply(instance, instance.stable(LargeOreFeatureConfig::new)));
}
