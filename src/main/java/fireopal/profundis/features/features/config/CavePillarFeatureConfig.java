package fireopal.profundis.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public record CavePillarFeatureConfig(BlockState innerState, BlockState outerState, IntProvider sizeOfEnds, IntProvider lengthOfEnds, RegistryEntry<PlacedFeature> bottomFeature) implements FeatureConfig {
    public static final Codec<CavePillarFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
       BlockState.CODEC.fieldOf("innerState").forGetter(CavePillarFeatureConfig::innerState),
       BlockState.CODEC.fieldOf("outerState").forGetter(CavePillarFeatureConfig::outerState),
       IntProvider.VALUE_CODEC.fieldOf("sizeOfEnds").forGetter(CavePillarFeatureConfig::sizeOfEnds),
       IntProvider.VALUE_CODEC.fieldOf("lengthOfEnds").forGetter(CavePillarFeatureConfig::lengthOfEnds),
       PlacedFeature.REGISTRY_CODEC.fieldOf("bottom_feature").forGetter(CavePillarFeatureConfig::bottomFeature)
    ).apply(instance, instance.stable(CavePillarFeatureConfig::new)));
}