package fireopal.profundis.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record ShelfFungiFeatureConfig(IntProvider radius, IntProvider iterations, BlockState upperState,
                                      BlockState underState) implements FeatureConfig {
    public static final Codec<ShelfFungiFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(ShelfFungiFeatureConfig::radius),
            IntProvider.VALUE_CODEC.fieldOf("iterations").forGetter(ShelfFungiFeatureConfig::iterations),
            BlockState.CODEC.fieldOf("upperState").forGetter(ShelfFungiFeatureConfig::upperState),
            BlockState.CODEC.fieldOf("underState").forGetter(ShelfFungiFeatureConfig::underState)
    ).apply(instance, instance.stable(ShelfFungiFeatureConfig::new)));
}
