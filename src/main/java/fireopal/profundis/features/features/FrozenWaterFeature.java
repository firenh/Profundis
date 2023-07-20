package fireopal.profundis.features.features;

import java.util.Iterator;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FrozenWaterFeature extends Feature<DefaultFeatureConfig> {
    
    // private static final Direction[] DIRECTIONS = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

    public FrozenWaterFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        int checks = 12;

        while (world.isAir(pos) && checks > 0) {
            pos = pos.down();
            checks -= 1;
        }

        if (world.isWater(pos)) {
            setIce(world, pos);
            return true;
        }

        return false;
    }
    
    private void setIce(StructureWorldAccess world, BlockPos pos) {
        if (world.isWater(pos) && !world.isWater(pos.up()) && !world.getBlockState(pos.up()).isOpaque()) {
            this.setBlockState(world, pos, Blocks.ICE.getDefaultState());

            Iterator<BlockPos.Mutable> iter = BlockPos.iterateInSquare(pos, 16, Direction.NORTH, Direction.WEST).iterator();

            while (iter.hasNext()) {
                BlockPos next = iter.next();

                if (pos.isWithinDistance(next, 16) && world.isWater(next) && !world.isWater(next.up()) && !world.getBlockState(next.up()).isOpaque()) {
                    this.setBlockState(world, next, Blocks.ICE.getDefaultState());
                }
            }
        }
    } 
}
