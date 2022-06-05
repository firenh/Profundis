package fireopal.profundis.features.features;

import java.util.Iterator;
import java.util.Random;

import com.mojang.serialization.Codec;

import fireopal.profundis.Profundis;
import fireopal.profundis.features.features.config.ShelfFungiFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ShelfFungiFeature extends Feature<ShelfFungiFeatureConfig> {
    public ShelfFungiFeature(Codec<ShelfFungiFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<ShelfFungiFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        ShelfFungiFeatureConfig config = context.getConfig();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();

        final double[] randomVals = {random.nextDouble(), random.nextDouble(), random.nextDouble()};
        BlockState upperState = config.upperState();
        BlockState underState = config.underState();
        int radius = config.radius().get(random);
        int iterations = config.iterations().get(random);
        int radiusExt = (int)(radius * 1.5);
        int placements = 0;

        // this.setBlockState(world, origin, upperState);

        boolean hasPlaced = false;

        Iterator<BlockPos> iter = BlockPos.iterateOutwards(origin, radiusExt, 0, radiusExt).iterator();

        while (iter.hasNext()) {
            BlockPos next = iter.next();
            // this.setBlockState(world, next, upperState);

            if (isValidLocation(origin, next, iterations, radius, random, world)) {
                this.setBlockState(world, next, upperState);
                hasPlaced = true;
                placements += 1;
                Profundis.LOGGER.info("placements: " + placements);
            }
        }

        if (!hasPlaced) return false;

        if (radius < 6) return hasPlaced;

        Iterator<BlockPos> iter2 = BlockPos.iterateOutwards(origin.down(), radius, 0, radius).iterator();

        while (iter2.hasNext()) {
            BlockPos next = iter2.next();
            if (isValidLocation(origin.down(), next, iterations, radius * 3 / 4, random, world)) {
                this.setBlockState(world, next, underState);
            }
        }

        if (radius < 8) return hasPlaced;

        Iterator<BlockPos> iter3 = BlockPos.iterateOutwards(origin.up(), radius, 0, radius).iterator();

        while (iter3.hasNext()) {
            BlockPos next = iter3.next();
            if (isValidLocation(origin.up(), next, iterations, radius * 2 / 3, random, world)) {
                this.setBlockState(world, next, upperState);
            }
        }

        return hasPlaced;
    }

    public boolean isValidLocation(BlockPos origin, BlockPos toPlaceAt, int iterations, int radius, Random random, StructureWorldAccess world) {
        BlockState placeAtState = world.getBlockState(toPlaceAt);
        
        if (placeAtState.isOpaque()) {
            if (placeAtState.isIn(BlockTags.MOSS_REPLACEABLE)) {
                if (
                    world.getBlockState(toPlaceAt.up()).isOpaque()
                    && world.getBlockState(toPlaceAt.down()).isOpaque()
                ) {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        // Profundis.LOGGER.info("radius: " + radius);

        double rad = 0;
        double theta = getAngle((toPlaceAt.getZ() - origin.getZ()), (toPlaceAt.getX() - origin.getX()));
        // Profundis.LOGGER.info("theta: " + theta);
        
        for (int i = 1; i <= 1; i += 1) {
            // Profundis.LOGGER.info("rad: " + rad);

            rad += (
                0.1 * random.nextDouble() * Math.sin((2 * Math.PI * random.nextDouble()) + (theta * Math.ceil(0.5 / (random.nextDouble()))))
            );
        }

        return origin.isWithinDistance(toPlaceAt, (1 + rad) * radius);
    }

    double getAngle(double height, double base) {
        if (base == 0) {
            return height >= 0 ? Math.PI : Math.PI * 3;
        }

        return Math.atan(height / base);
    }
}
