package fireopal.profundis.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record IcicleFeatureConfig(IntProvider size, IntProvider amount, IntProvider spread, BlockState innerState,
        BlockState outerState) implements FeatureConfig 
{
    public static final Codec<IcicleFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("size").forGetter(IcicleFeatureConfig::size),
            IntProvider.VALUE_CODEC.fieldOf("amount").forGetter(IcicleFeatureConfig::amount),
            IntProvider.VALUE_CODEC.fieldOf("spread").forGetter(IcicleFeatureConfig::spread),
            BlockState.CODEC.fieldOf("innerState").forGetter(IcicleFeatureConfig::innerState),
            BlockState.CODEC.fieldOf("outerState").forGetter(IcicleFeatureConfig::outerState))
            .apply(instance, instance.stable(IcicleFeatureConfig::new)));
}
