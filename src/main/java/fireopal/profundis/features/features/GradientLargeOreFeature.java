package fireopal.profundis.features.features;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.LargeOreFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class GradientLargeOreFeature extends LargeOreFeature {

    public GradientLargeOreFeature(Codec<LargeOreFeatureConfig> configCodec) {
        super(configCodec);
    }

    protected Optional<BlockState> getBlockState(StructureWorldAccess world, BlockPos pos, BlockState currentState, Random random, List<OreFeatureConfig.Target> targets) {
        for (OreFeatureConfig.Target t : targets) {
            RuleTest rule = t.target;
            
            if (rule.test(currentState, random)) {
                return Optional.of(t.state);
            }
        }

        return Optional.empty();
    }
}