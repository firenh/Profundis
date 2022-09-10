package fireopal.profundis.features.features;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DeltaFeature;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class NotStupidDeltaFeature extends DeltaFeature {
    public NotStupidDeltaFeature(Codec<DeltaFeatureConfig> codec) {
        super(codec);
    }

    private static final ImmutableList<Block> BLOCKS = ImmutableList.of(Blocks.BEDROCK, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_WART, Blocks.CHEST, Blocks.SPAWNER);
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public boolean generate(FeatureContext<DeltaFeatureConfig> context) {
        boolean bl = false;
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        DeltaFeatureConfig deltaFeatureConfig = context.getConfig();
        BlockPos blockPos = context.getOrigin();
        boolean bl2 = random.nextDouble() < 0.9;
        int i = bl2 ? deltaFeatureConfig.getRimSize().get(random) : 0;
        int j = bl2 ? deltaFeatureConfig.getRimSize().get(random) : 0;
        boolean bl3 = bl2 && i != 0 && j != 0;
        int k = deltaFeatureConfig.getSize().get(random);
        int l = deltaFeatureConfig.getSize().get(random);
        int m = Math.max(k, l);
        for (BlockPos blockPos2 : BlockPos.iterateOutwards(blockPos, k, 0, l)) {
            BlockPos blockPos3;
            if (blockPos2.getManhattanDistance(blockPos) > m) break;
            if (!this.canPlace(structureWorldAccess, blockPos2, deltaFeatureConfig)) continue;
            if (bl3) {
                bl = true;
                this.setBlockState(structureWorldAccess, blockPos2, deltaFeatureConfig.getRim());
            }
            if (!this.canPlace(structureWorldAccess, blockPos3 = blockPos2.add(i, 0, j), deltaFeatureConfig)) continue;
            bl = true;
            this.setBlockState(structureWorldAccess, blockPos3, deltaFeatureConfig.getContents());
        }
        return bl;
    }

    private boolean canPlace(WorldAccess world, BlockPos pos, DeltaFeatureConfig config) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isOf(config.getContents().getBlock())) {
            return false;
        }
        if (world.getBlockState(pos.up()).isOf(Blocks.WATER)) {
            return false;
        }
        if (BLOCKS.contains(blockState.getBlock())) {
            return false;
        }

        for (Direction direction : DIRECTIONS) {
            BlockState replaceBlockstate = world.getBlockState(pos.offset(direction));
            boolean bl = replaceBlockstate.isOf(config.getContents().getBlock()) || (replaceBlockstate.isFullCube(world, pos));
            if ((bl || direction == Direction.UP) && (!bl || direction != Direction.UP)) continue;
            return false;
        }
        return true;
    }
}