package fireopal.profundis.features.features;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.AmethystVeinFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler.NoiseParameters;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;

public class AmethystVeinFeature extends Feature<AmethystVeinFeatureConfig> {
    private static final Block[] AMETHYST_BUDS = {Blocks.SMALL_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.LARGE_AMETHYST_BUD};
    private static final BlockStateProvider OUTLINE_STATE_PROVIDER = new NoiseBlockStateProvider(956016019L, new NoiseParameters(1, 1), 4f, List.of(Blocks.CALCITE.getDefaultState(), Blocks.SMOOTH_BASALT.getDefaultState()));
    private static final BlockStateProvider SUSPENDED_STATE_PROVIDER = new NoiseBlockStateProvider(326971689L, new NoiseParameters(1, 1), 0.25f, List.of(Blocks.AMETHYST_BLOCK.getDefaultState(), Blocks.CALCITE.getDefaultState()));

    public AmethystVeinFeature(Codec<AmethystVeinFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<AmethystVeinFeatureConfig> context) {
        Random random = context.getRandom();
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        AmethystVeinFeatureConfig config = context.getConfig();
        
        return generate(world, random, origin, config.undergroundOnly(), config.range().get(random), config.targetCount().get(random), config.radius().get(random));
    }

    private boolean generate(StructureWorldAccess world, Random random, BlockPos origin, boolean undergroundOnly, int range, int targetCount, double radius) {
        ArrayList<BlockPos> targets = new ArrayList<>();
        Iterator<BlockPos> iter = BlockPos.iterateRandomly(random, Integer.MAX_VALUE, origin, range).iterator();

        for (int i = 0; i < targetCount * 16 && targets.size() < targetCount; i += 1) {
            BlockPos pos = iter.next();
            if (world.isAir(pos) || !adjacentToAir(world, pos)) {
                // Profundis.LOGGER.info("Invalid target: " + pos);
                continue;
            }

            targets.add(pos.mutableCopy());
        }

        // Profundis.LOGGER.info("Targets: " + targets);

        if (targets.size() == 0) {
            return false;
        }

        ArrayList<Long> futureOutline = new ArrayList<>();

        for (BlockPos target : targets) {
            // Profundis.LOGGER.info("On target: " + target + " !");

            final int factor = 1;
            Vec3d offsetVector = getOffsetVector(origin, target, factor);
            final double maxDistance = distance(origin, target);
            
            int toDo = (int) Math.ceil(factor * Math.sqrt(origin.getSquaredDistance(target)));

            for (int i = 0; i < toDo; i += 1) {
                BlockPos pos = new BlockPos(blockPosToVec3dCenter(origin).add(offsetVector.multiply(i)));
                setBlocks(world, pos, random, undergroundOnly, radius, maxDistance, distance(origin, pos), futureOutline);
            }
        }

        setOutline(world, futureOutline, random);
        
        return true;
    }

    private double distance(BlockPos origin, BlockPos target) {
        return Math.sqrt(origin.getSquaredDistance(target));
    }

    private Vec3d blockPosToVec3dCenter(BlockPos blockPos) {
        return new Vec3d(
            blockPos.getX() + 0.5,
            blockPos.getY() + 0.5,
            blockPos.getZ() + 0.5
        );
    }

    private void setBlocks(StructureWorldAccess world, BlockPos blockPos, Random random, boolean undergroundOnly, double maxRadius, double maxDistance, double distance, ArrayList<Long> futureOutline) {
        double amethystRadius = (undergroundOnly ? (2.0 / 3.0) : 1) * (maxRadius - ((distance / (maxDistance * (undergroundOnly ? 1 : 0.5))) * maxRadius)) + (undergroundOnly ? 0 : 1);
        // double calciteRadius = (5.0 / 6.0) * (maxRadius - ((distance / (maxDistance)) * maxRadius));
        double radius = maxRadius - (undergroundOnly ? 1 : 2) *((distance / maxDistance) * maxRadius) + (undergroundOnly ? 0 : 1);
        int ceilRadius = (int) Math.ceil(radius);

        Iterator<BlockPos> iter = BlockPos.iterateOutwards(blockPos, ceilRadius, ceilRadius, ceilRadius).iterator();

        while (iter.hasNext()) {
            BlockPos pos = iter.next();

            if (undergroundOnly && world.isAir(pos)) {
                continue;
            }

            if (pos.isWithinDistance(blockPos, radius)) {

                if (pos.isWithinDistance(blockPos, amethystRadius)) {
                    this.setAmethyst(world, pos, random, undergroundOnly);
                    futureOutline.remove(pos.asLong());
                } else {
                    futureOutline.add(pos.asLong());
                }

            }
        }
    }

    private void setOutline(StructureWorldAccess world, ArrayList<Long> outline, Random random) {
        for (long l : outline) {
            BlockPos pos = BlockPos.fromLong(l);
            BlockState stateAt = world.getBlockState(pos);

            if (random.nextFloat() < 0.333333333f || !(stateAt.isIn(BlockTags.BASE_STONE_OVERWORLD))) continue;

            this.setBlockState(world, pos, OUTLINE_STATE_PROVIDER.getBlockState(random, pos));
        }
    }

    private Vec3d getOffsetVector(BlockPos origin, BlockPos target, double factor) {
        Vec3i offsetI = origin.add(target.multiply(-1));
        double distance = Math.sqrt(origin.getSquaredDistance(target));
        return new Vec3d(offsetI.getX(), offsetI.getY(), offsetI.getZ()).multiply(factor / distance);
    }

    private boolean isAmethystBudOrCluster(BlockState state) {
        for (Block b : AMETHYST_BUDS) {
            if (state.isOf(b)) return true;
        }

        return state.isOf(Blocks.AMETHYST_CLUSTER);
    }

    private boolean adjacentToAir(StructureWorldAccess world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            if (world.isAir(pos.offset(dir))) {
                return true;
            }
        }

        return false;
    } 

    private void setAmethyst(StructureWorldAccess world, BlockPos pos, Random random, boolean undergroundOnly) {
        if (undergroundOnly && isAmethystBudOrCluster(world.getBlockState(pos))) {
            return;
        }

        ArrayList<Pair<BlockPos, Direction>> adjacentAir = new ArrayList<>();
        BlockState state = undergroundOnly ? Blocks.AMETHYST_BLOCK.getDefaultState() : SUSPENDED_STATE_PROVIDER.getBlockState(random, pos);
        
        this.setBlockState(world, pos, state);

        if (!state.isOf(Blocks.AMETHYST_BLOCK)) return;

        for (Direction dir : Direction.values()) {
            if (world.isAir(pos.offset(dir)) || world.isWater(pos.offset(dir))) {
                adjacentAir.add(Pair.of(pos.offset(dir), dir));
            }
        }

        while (adjacentAir.size() > 0) {
            int index = random.nextInt(adjacentAir.size());

            if (random.nextFloat() < 0.33333333333) {
                Pair<BlockPos, Direction> pair = adjacentAir.get(index);
                this.setBlockState(world, pair.getFirst(), getAmethystCrystal(random).getDefaultState().with(Properties.FACING, pair.getSecond()).with(Properties.WATERLOGGED, world.isWater(pair.getFirst())));
            }

            adjacentAir.remove(index);
        }
    }


    private Block getAmethystCrystal(Random random) {
        if (random.nextFloat() < 0.333333333333333333333333) {
            return Blocks.AMETHYST_CLUSTER;
        }

        return AMETHYST_BUDS[random.nextInt(AMETHYST_BUDS.length)];
    }
}
