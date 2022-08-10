package fireopal.profundis.features.features;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class LavaFixerFeature extends Feature<DefaultFeatureConfig> {
    public LavaFixerFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    private Direction[] directionsToCheck = {Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        final int chunkSnappedX = chunkSnap(origin.getX());
        final int chunkSnappedZ = chunkSnap(origin.getZ());
        
        BlockPos cursor = new BlockPos(chunkSnappedX, 120, chunkSnappedZ);

        // Profundis.LOGGER.info("lava fixer is doing stuff pt 1");

        while (cursor.getY() >= 0) {
            // Profundis.LOGGER.info("lava fixer is doing stuff pt 2");

            if (world.getBlockState(cursor).isOf(Blocks.LAVA)) {
                boolean fixed = false;
                BlockState setState = cursor.getY() > 0 ? Blocks.STONE.getDefaultState() : Blocks.DEEPSLATE.getDefaultState();

                for (Direction d : directionsToCheck) {
                    BlockPos checkingCursor = cursor.offset(d);
                    if (!world.isAir(checkingCursor) 
                            && !(world.getBlockState(checkingCursor).isOf(Blocks.LAVA))
                            && !(world.getBlockState(checkingCursor).isSolidBlock(world, checkingCursor))
                            && !(world.getBlockState(checkingCursor).isOpaque())
                        ) {
                        // Profundis.LOGGER.info("fixed lava at " + cursor.getX() + "x, " + cursor.getY() + "y, " + cursor.getZ() + "z");
                        world.setBlockState(cursor, setState, 0);
                        fixed = true;
                        break;
                    }
                }

                if (fixed == false && world.isAir(cursor.down())) {
                    // Profundis.LOGGER.info("fixed lava at " + cursor.getX() + "x, " + cursor.getY() + "y, " + cursor.getZ() + "z");
                    world.setBlockState(cursor, setState, 0);
                    fixed = true;
                }
            }

            if (cursor.getX() == chunkSnappedX + 15 && cursor.getZ() == chunkSnappedZ + 15) {
                cursor = new BlockPos(chunkSnappedX, cursor.getY() - 1, chunkSnappedZ);
            } else if (cursor.getX() == chunkSnappedX + 15) {
                cursor = new BlockPos(chunkSnappedX, cursor.getY(), cursor.getZ() + 1);
            } else {
                cursor = new BlockPos(cursor.getX() + 1, cursor.getY(), cursor.getZ());
            }
        }

        return true;
    }
    
    private int chunkSnap(int input) {
        return (input / 16) * 16;
    }
}
