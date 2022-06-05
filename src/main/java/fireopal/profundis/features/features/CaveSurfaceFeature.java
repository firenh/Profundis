package fireopal.profundis.features.features;

import java.util.Iterator;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.CaveSurfaceFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CaveSurfaceFeature extends Feature<CaveSurfaceFeatureConfig> {
    public CaveSurfaceFeature(Codec<CaveSurfaceFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<CaveSurfaceFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        CaveSurfaceFeatureConfig config = context.getConfig();

        int size = config.size().get(random);
        int offset = config.offset();
        BlockState blockState = config.blockState();
        float chance = config.chance();
        boolean onSpecificBlockState = config.onSpecificBlockState();
        int amountOfCircles = config.amountOfCircles().get(random);
        BlockState targetBlock = config.targetBlock();
        boolean onlyOnBaseStoneOverworld = config.onlyOnBaseStoneOverworld();
        boolean generateManyCircles = config.generateManyCircles();

        if (generateManyCircles) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int i = 0; i < amountOfCircles; i += 1) {
                mutable.set(randomInRange(size, random) + origin.getX(), randomInRange(size, random) + origin.getY(), randomInRange(size, random) + origin.getZ());
                if (origin.isWithinDistance(mutable, size)) {
                    makeOneCircle(origin, mutable, random, world, size / 2, offset, blockState, chance, onSpecificBlockState, targetBlock, onlyOnBaseStoneOverworld);
                } else {
                    i -= 1;
                }
            }
        } else {
            makeOneCircle(origin, origin, random, world, size / 2, offset, blockState, chance, onSpecificBlockState, targetBlock, onlyOnBaseStoneOverworld);
        }

        return true;
    }

    private void makeOneCircle(BlockPos originalOrigin, BlockPos origin, Random random, StructureWorldAccess world, int size, int offset,
            BlockState blockState, float chance, boolean onSpecificBlockState, BlockState targetBlock, boolean onlyOnBaseStoneOverworld) {
        Iterator<BlockPos> iterator = BlockPos.iterateOutwards(origin, size, size, size).iterator();

        while (iterator.hasNext()) {
            BlockPos.Mutable mutable = iterator.next().mutableCopy();
            BlockState onState = world.getBlockState(mutable);

            if (originalOrigin.isWithinDistance(origin, size)) {
                if (!onSpecificBlockState) {
                    if (onState.isOpaque() && world.isAir(mutable.up()) && !onState.equals(blockState) && useBaseStoneOverworld(onlyOnBaseStoneOverworld, onState) && mutable.isWithinDistance(origin, size)) {
                        if (random.nextFloat() <= chance) world.setBlockState(mutable.offset(Axis.Y, offset), blockState, 0);
                    }
                } else {
                    if ((onState.equals(targetBlock) || onState == targetBlock) && world.isAir(mutable.up()) && mutable.isWithinDistance(origin, size)) {
                        if (random.nextFloat() <= chance) world.setBlockState(mutable.offset(Axis.Y, offset), blockState, 0);
                    }
                }
            }
        }
    }

    private int randomInRange(int size, Random random) {
        return random.nextInt(size * 2 + 1) - size;
    }

    private boolean useBaseStoneOverworld(boolean onlyOnBaseStoneOverworld, BlockState blockState) {
        if (onlyOnBaseStoneOverworld == false) {
            return true;
        }

        if (blockState.isIn(BlockTags.BASE_STONE_OVERWORLD)) {
            return true;
        }

        return false;
    } 
}
