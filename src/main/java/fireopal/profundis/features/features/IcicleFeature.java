package fireopal.profundis.features.features;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.IcicleFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class IcicleFeature extends Feature<IcicleFeatureConfig> {
    public IcicleFeature(Codec<IcicleFeatureConfig> configCodec) {
        super(configCodec);
    }

    private static final Direction[] DIRECTIONS = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

    @Override
    public boolean generate(FeatureContext<IcicleFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();

        IcicleFeatureConfig config = context.getConfig();
        int minSize = config.size().getMin();
        int size = config.size().get(random);
        int amount = config.amount().get(random);
        int spread = config.spread().get(random) / 2;
        BlockState innerState = config.innerState();
        BlockState outerState = config.outerState();

        spread = spread < 16 ? spread : 16;

        if (!world.isAir(origin) || world.isAir(origin.up())) {
            return false;
        }

        makeOneIcicle(world, origin, size, innerState, outerState, random);
        amount -= 1;

        while (amount > 0) {
            amount -= 1;
            BlockPos.Mutable mutable = origin.mutableCopy();
            mutable = mutable.add(random.nextInt(1 + spread * 2) - spread, 0, random.nextInt(1 + spread * 2) - spread).mutableCopy();

            boolean bl = false;
            
            if (world.isAir(mutable) && world.isAir(mutable.up())) {
                for (int i = 0; i < 3 && !bl; i += 1) {
                    mutable = mutable.add(0, 1, 0).mutableCopy();

                    if (world.isAir(mutable) && !world.isAir(mutable.up()) && !world.isAir(mutable.up(2))) {
                        bl = true;
                    }
                }
            } else if (!world.isAir(mutable)) {
                for (int i = 0; i < 3 && !bl; i += 1) {
                    mutable = mutable.add(0, -1, 0).mutableCopy();

                    if (world.isAir(mutable) && !world.isAir(mutable.up()) && !world.isAir(mutable.up(2))) {
                        bl = true;
                    }
                }
            }

            int nextSize = random.nextInt(size) + 1;
            nextSize = nextSize > minSize ? nextSize : minSize;

            if (!world.isAir(mutable.up()) && !world.isAir(mutable.up(2))) makeOneIcicle(world, mutable, nextSize, innerState, outerState, random);
        }
        
        return true;
    }

    public static void makeOneIcicle(StructureWorldAccess world, BlockPos origin, int size, BlockState innerState, BlockState outerState, Random random) {
        BlockPos.Mutable cursor = origin.mutableCopy();

        for (int i = 0; i < -1 + (2 * size / 3); i += 1) {
            if (world.isAir(cursor)) {
                world.setBlockState(cursor, innerState, 0);
            } else {
                break;
            }

            cursor = cursor.down().mutableCopy();
        }

        for (int i = 0; i < 1 + (size / 3); i += 1) {
            if (world.isAir(cursor)) {
                world.setBlockState(cursor, outerState, 0);
            } else {
                break;
            }

            cursor = cursor.down().mutableCopy();
        }

        if (size > 3) {
            for (Direction d : DIRECTIONS) {
                if (world.isAir(origin.offset(d)) && !world.isAir(origin.offset(d).up())) makeOneIcicle(world, origin.offset(d), random.nextInt(2 + (size / 3)), innerState, outerState, random);
            }
        }
    }
    
}
