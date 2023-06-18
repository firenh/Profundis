package fireopal.profundis.features.features;

import java.util.Iterator;
import java.util.List;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.LargeOreFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.noise.InterpolatedNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.densityfunction.DensityFunction.UnblendedNoisePos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class LargeOreFeature extends Feature<LargeOreFeatureConfig> {

    public LargeOreFeature(Codec<LargeOreFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<LargeOreFeatureConfig> context) {
        Random random = context.getRandom();
        LargeOreFeatureConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        boolean returnVal = false;
        
        List<OreFeatureConfig.Target> targets = config.targets();
           int radius = config.radius().get(random);
          double scale = config.scale();
          double factor = config.factor();
         double smearing = config.smearing();
        double valueRange = config.valueRange();
        double valueOffset = config.valueOffset();
         boolean bordersAir = config.bordersAir();

        InterpolatedNoiseSampler noise = new InterpolatedNoiseSampler(random, scale, scale, factor, factor, smearing);

        Iterator<BlockPos> iter = BlockPos.iterateOutwards(origin, radius, radius, radius).iterator();

        while (iter.hasNext()) {
            BlockPos pos = iter.next();

            if (!origin.isWithinDistance(pos, radius)) continue;

            double value = noise.sample(new UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
            double distance = Math.sqrt(origin.getSquaredDistance(pos));
            double checkVal = value + (distance / radius);

            if (Math.abs(checkVal - valueOffset) < valueRange && world.isValidForSetBlock(pos)) {
                boolean hasAir = false;

                if (bordersAir) {
                    for (Direction d : Direction.values()) {
                        if (!world.getBlockState(pos.offset(d)).isOpaque()) {
                            // System.out.println("air");
                            hasAir = true;
                            break;
                        }
                    }
                } else {
                    hasAir = true;
                }

                if (hasAir) {

                    BlockState currentState = world.getBlockState(pos);

                    for (OreFeatureConfig.Target t : targets) {
                        RuleTest rule = t.target;
                        
                        if (rule.test(currentState, random)) {
                            world.setBlockState(pos, t.state, radius, radius);
                            returnVal = true;
                        }
                    }
                }
            }
        }

        return returnVal;
    }
    
}
