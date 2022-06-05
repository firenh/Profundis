package fireopal.profundis.features.features;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.CavePillarFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CavePillarFeature extends Feature<CavePillarFeatureConfig> {
    public CavePillarFeature(Codec<CavePillarFeatureConfig> configCodec) {
        super(configCodec);
    }

    private static final Direction[] DIRECTIONS = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

    @Override
    public boolean generate(FeatureContext<CavePillarFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        CavePillarFeatureConfig config = context.getConfig();
        
        BlockState innerState = config.innerState();
        BlockState outerState = config.outerState();
        IntProvider lengthOfEnds = config.lengthOfEnds();
        RegistryEntry<PlacedFeature> bottomFeature = config.bottomFeature();

        if (!world.isAir(origin) || world.isAir(origin.up())) {
            return false;
        }

        BlockPos.Mutable cursor = origin.mutableCopy();

        while (world.isAir(cursor)) {
            world.setBlockState(cursor, innerState, 0);
            cursor = cursor.down().mutableCopy();
        }

        for (Direction d : DIRECTIONS) {
            addEnds(world, origin.offset(d, 1), cursor.offset(d, 1), lengthOfEnds, random, innerState, outerState);
        }

        bottomFeature.value().generate(world, context.getGenerator(), random, cursor);

        return true;
    }

    private void addEnds(StructureWorldAccess world, BlockPos top, BlockPos bottom, IntProvider length, Random random, BlockState innerState, BlockState outerState) {
        int lengthTop = length.get(random);
        BlockPos.Mutable cursor = top.mutableCopy();

        if (world.isAir(cursor.up())) for (int i = 0; i < 4; i += 1) {
            cursor = cursor.up().mutableCopy();
            if (world.getBlockState(cursor.up()).isOpaque()) break;
        }

        for (int i = 0; i < lengthTop; i += 1) {
            BlockState place = i + 1 < 2 * lengthTop / 3 ? innerState : outerState;
            world.setBlockState(cursor, place, 0);
            cursor = cursor.down().mutableCopy();
        }

        int lengthBottom = length.get(random);
        cursor = bottom.mutableCopy();

        if (world.isAir(cursor.down())) for (int i = 0; i < 4; i += 1) {
            cursor = cursor.down().mutableCopy();
            if (world.getBlockState(cursor.down()).isOpaque()) break;
        }

        for (int i = 0; i < lengthBottom; i += 1) {
            BlockState place = i + 1 < 2 * lengthBottom / 3 ? innerState : outerState;
            world.setBlockState(cursor, place, 0);
            cursor = cursor.up().mutableCopy();
        }
    }
}
