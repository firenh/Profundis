package fireopal.profundis.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record NetherrackBaseFeatureConfig(
    IntProvider radius, IntProvider height, BlockState topState, BlockState underState, 
    RuleTest canPlaceThrough, IntProvider count, boolean sometimesUseUnderAsTop, boolean contained

) implements FeatureConfig {

    public static final Codec<NetherrackBaseFeatureConfig> CODEC = RecordCodecBuilder
        .create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("radius").orElse(UniformIntProvider.create(4, 5))
                .forGetter(config -> config.radius),
            IntProvider.VALUE_CODEC.fieldOf("height").orElse(UniformIntProvider.create(3, 4))
                .forGetter(config -> config.height),
            BlockState.CODEC.fieldOf("top_state")
                .forGetter(config -> config.topState),
            BlockState.CODEC.fieldOf("under_state")
                .forGetter(config -> config.underState),
            RuleTest.TYPE_CODEC.fieldOf("can_be_placed_on")
                .forGetter(config -> config.canPlaceThrough),
            IntProvider.VALUE_CODEC.fieldOf("count").orElse(UniformIntProvider.create(4, 5))
                .forGetter(config -> config.count),
            Codec.BOOL.fieldOf("sometimes_use_under_as_top").orElse(false)
                .forGetter(config -> config.sometimesUseUnderAsTop),
            Codec.BOOL.fieldOf("contained").orElse(false)
                .forGetter(config -> config.contained)
        ).apply(instance, NetherrackBaseFeatureConfig::new)
    );
}
