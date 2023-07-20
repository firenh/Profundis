package fireopal.profundis.features.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;

import fireopal.profundis.features.features.config.LargeOreFeatureConfig;
import fireopal.profundis.util.ProfundisTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class TerracottaBandsLargeOreFeature extends LargeOreFeature {
    private static final BlockState[] TERRACOTTA_BLOCKS = new BlockState[]{
        Blocks.TERRACOTTA.getDefaultState(),
        Blocks.TERRACOTTA.getDefaultState(),
        Blocks.TERRACOTTA.getDefaultState(),
        Blocks.WHITE_TERRACOTTA.getDefaultState(),
        Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(),
        Blocks.GRAY_TERRACOTTA.getDefaultState(),
        Blocks.RED_TERRACOTTA.getDefaultState(),
        Blocks.ORANGE_TERRACOTTA.getDefaultState(),
        Blocks.YELLOW_TERRACOTTA.getDefaultState(),
        Blocks.BROWN_TERRACOTTA.getDefaultState(),
    };

    private static List<BlockState> terracottaList = new ArrayList<>();

    public TerracottaBandsLargeOreFeature(Codec<LargeOreFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    protected Optional<BlockState> getBlockState(StructureWorldAccess world, BlockPos pos, BlockState currentState, Random random, List<OreFeatureConfig.Target> targets) {
        if (currentState.isIn(ProfundisTags.BASE_STONE_OVERWORLD_PLUS)) {
            return Optional.of(getTerracotta(world, pos.getY()));
        }

        return Optional.empty();
    }

    private BlockState getTerracotta(StructureWorldAccess world, int y) {
        int adjY = y - world.getBottomY();

        if (terracottaList.size() > adjY) {
            return terracottaList.get(adjY);
        }

        Random seedBasedRandom = Random.create(world.getSeed());

        for (int i = 0; i <= (adjY + 1); i += 1) {
            terracottaList.add(
                TERRACOTTA_BLOCKS[seedBasedRandom.nextInt(TERRACOTTA_BLOCKS.length)]
            );
        }

        return terracottaList.get(adjY);
    }

    
}
