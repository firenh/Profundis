package fireopal.profundis.features.features;

import java.util.Optional;
import java.util.Random;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.NetherrackBaseFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class NetherrackBaseFeature extends Feature<NetherrackBaseFeatureConfig> {
    public NetherrackBaseFeature(Codec<NetherrackBaseFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<NetherrackBaseFeatureConfig> context) {
        final NetherrackBaseFeatureConfig config = context.getConfig();
        final BlockPos origin = context.getOrigin();
        final Random random = context.getRandom();
        final StructureWorldAccess world = context.getWorld();
        final int count = config.count().get(random);
        final int height = 3; // config.height().get(random);
        final int y = origin.getY();
        final int radius = config.radius().get(random);
        final int minX = origin.getX() - radius;
        final int maxX = origin.getX() + radius;
        final int minZ = origin.getZ() - radius;
        final int maxZ = origin.getZ() + radius;
        final BlockState topState = config.topState();
        final BlockState underState = config.underState();
        final RegistryEntryList<Block> canPlaceThrough = config.canPlaceThrough();
        final boolean sometimesUseUnderAsTop = config.sometimesUseUnderAsTop();
        final boolean isContained = config.contained();

        boolean hasPlaced = false;


        for (BlockPos blockPos : BlockPos.iterateRandomly(random, count, origin.getX() - (radius / 2), y, origin.getZ() - (radius / 2), origin.getX() + (radius / 2), y, origin.getZ() + (radius / 2))) {
            boolean bl = iterateOnce(blockPos, origin, radius, height, topState, underState, canPlaceThrough, sometimesUseUnderAsTop, world, random, isContained);
            hasPlaced |= bl; // hasPlaced = hasPlaced || bl
        }

        for (BlockPos blockPos : BlockPos.iterateRandomly(random, count, minX, y, minZ, maxX, y, maxZ)) {
            boolean bl = iterateOnce(blockPos, origin, radius, height, topState, underState, canPlaceThrough, sometimesUseUnderAsTop, world, random, isContained);
            hasPlaced |= bl;
        }

        return hasPlaced;
    }

    private boolean iterateOnce(BlockPos pos, BlockPos origin, int radius, int height, BlockState topState, BlockState underState, RegistryEntryList<Block> canPlaceThrough, boolean sometimesUseUnderAsTop, StructureWorldAccess world, Random random, boolean isContained) {
        // Profundis.LOGGER.info("Iter once");

        boolean hasPlaced = false;
        // Profundis.LOGGER.info("Original: " + pos);

        if (!pos.isWithinDistance(origin, radius)) return false;

        // Profundis.LOGGER.info("pos:: " + pos);
        Optional<BlockPos> posMatcher = adjustWorldPosition(pos, height, world);

        if (posMatcher.isPresent()) {
            // Profundis.LOGGER.info("Matching");

            // Profundis.LOGGER.info("Adjusted: " + posMatcher.get());
            hasPlaced = generatePillarGoingDown(radius, height, posMatcher.get(), origin, topState, underState, canPlaceThrough, world, isContained);
            // Profundis.LOGGER.info("This spot: " + posMatcher.get());

            for (Direction d : DIRECTIONS) {

                Optional<BlockPos> posMatcher2 = adjustWorldPosition(posMatcher.get().offset(d), 1 + (int) Math.ceil((float) height / 3.0f), world);

                if (posMatcher2.isPresent() && random.nextFloat() < (Math.sqrt(posMatcher2.get().getSquaredDistance(origin) / (double) radius))) {
                    generatePillarGoingDown(radius, 1 + ((height + 3) / 3), posMatcher2.get(), origin, (sometimesUseUnderAsTop && random.nextBoolean() ? underState : topState), underState, canPlaceThrough, world, isContained);
                }
            }
        }

        return hasPlaced;
    }

    private Optional<BlockPos> adjustWorldPosition(BlockPos blockPos, int height, StructureWorldAccess world) {
        BlockPos.Mutable pos = blockPos.mutableCopy();
        // Profundis.LOGGER.info("At: " + pos);
        // Profundis.LOGGER.info("Is AIr: " + world.isAir(pos));
        // Profundis.LOGGER.info("Is AIr: " + world.getBlockState(pos));

        if (world.isAir(pos)) {
            // Profundis.LOGGER.info(pos + "Going down");

            for (int i = 0; i < height; i += 1) {
                // Profundis.LOGGER.info(world.getBlockState(pos).isOpaque());

                if (world.getBlockState(pos).isOpaque() && world.isAir(pos.up())) {
                    // Profundis.LOGGER.info("Return adjustment: " + pos.toImmutable());
                    return Optional.of(pos.toImmutable());
                }

                pos.move(Direction.DOWN);
            }
        } else {
            // Profundis.LOGGER.info("Going up");

            for (int i = 0; i < height; i += 1) {
                // Profundis.LOGGER.info(world.getBlockState(pos).isOpaque());

                if (world.getBlockState(pos).isOpaque() && world.isAir(pos.up())) {
                    // Profundis.LOGGER.info("Return adjustment: " + pos.toImmutable());
                    return Optional.of(pos.toImmutable());
                }

                pos.move(Direction.UP);
            }
        }

        // Profundis.LOGGER.info("None");

        return Optional.empty();
    }

    private final Direction[] DIRECTIONS = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    private boolean generatePillarGoingDown(int maxRadius, int maxHeight, BlockPos target, BlockPos origin, BlockState topState, BlockState underState, RegistryEntryList<Block> canPlaceThrough, StructureWorldAccess world, boolean isContained) {
        // Profundis.LOGGER.info("Generating pillar");

        final double distanceToOrigin = Math.sqrt(target.getSquaredDistance(origin));
        final int height = maxHeight > 1 ? (int) Math.ceil(maxHeight * ((distanceToOrigin / (double) maxRadius))) : 1;

        // Profundis.LOGGER.info("distanceToOrigin = " + distanceToOrigin + " ; maxRadius = " + maxRadius);
        // Profundis.LOGGER.info("(distanceToOrigin / (double) maxRadius) = " + (distanceToOrigin / (double) maxRadius));
        // Profundis.LOGGER.info("Height: " + height);

        BlockPos pos;

        for (int i = 0; i < height; i += 1) {
            pos = target.down(i);
            BlockState stateAt = world.getBlockState(pos);

            boolean bl = false;

            for (RegistryEntry<Block> r : canPlaceThrough) {
                if (stateAt.isOf(r.value())) bl = true;
            }

            if (!bl) break;

            if (world.isAir(pos.up())) {
                attemptPlaceBlock(world, pos, topState, isContained);
            } else {
                attemptPlaceBlock(world, pos, underState, isContained);
            }
        }

        return height > 0;
    }

    private boolean attemptPlaceBlock(StructureWorldAccess world, BlockPos pos, BlockState state, boolean contained) {
        // Profundis.LOGGER.info("COntained!");

        if (contained) {
            for (Direction d : DIRECTIONS) {
                BlockState over = world.getBlockState(pos.offset(d));

                // Profundis.LOGGER.info("isOpaque: " + over.isOpaque()+ "; isOf:" + over.isOf(state.getBlock()));

                if (!over.isOpaque() && !over.isOf(state.getBlock())) return false;
            }

            BlockState over = world.getBlockState(pos.offset(Direction.DOWN));
            // Profundis.LOGGER.info("isOpaque: " + over.isOpaque()+ "; isOf:" + over.isOf(state.getBlock()));

            if (!over.isOpaque() && !over.isOf(state.getBlock())) return false;
        }

        // Profundis.LOGGER.info("Setting block!!!");

        this.setBlockState(world, pos, state);
        return true;
    }

}
