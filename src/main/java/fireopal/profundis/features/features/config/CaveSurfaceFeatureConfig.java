package fireopal.profundis.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CaveSurfaceFeatureConfig(IntProvider size, int offset, BlockState blockState, float chance, boolean onSpecificBlockState, IntProvider amountOfCircles, RuleTest targetBlock, boolean onlyOnBaseStoneOverworld, boolean generateManyCircles) implements FeatureConfig {
    public static final Codec<CaveSurfaceFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
       IntProvider.VALUE_CODEC.fieldOf("size").forGetter(CaveSurfaceFeatureConfig::size),
       Codec.INT.fieldOf("offset").forGetter(CaveSurfaceFeatureConfig::offset),
       BlockState.CODEC.fieldOf("blockState").forGetter(CaveSurfaceFeatureConfig::blockState),
       Codec.FLOAT.fieldOf("chance").forGetter(CaveSurfaceFeatureConfig::chance),
       Codec.BOOL.fieldOf("onSpecificBlock").forGetter(CaveSurfaceFeatureConfig::onSpecificBlockState),
       IntProvider.VALUE_CODEC.fieldOf("amountOfCircles").forGetter(CaveSurfaceFeatureConfig::amountOfCircles),
       RuleTest.TYPE_CODEC.fieldOf("targetBlock").forGetter(CaveSurfaceFeatureConfig::targetBlock),
       Codec.BOOL.fieldOf("onlyOnBaseStoneOverworld").forGetter(CaveSurfaceFeatureConfig::onlyOnBaseStoneOverworld),
       Codec.BOOL.fieldOf("generateManyCircles").forGetter(CaveSurfaceFeatureConfig::generateManyCircles)
    ).apply(instance, instance.stable(CaveSurfaceFeatureConfig::new)));
}
