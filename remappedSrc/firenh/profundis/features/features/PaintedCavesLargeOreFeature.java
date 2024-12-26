package firenh.profundis.features.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;

import firenh.profundis.features.features.config.LargeOreFeatureConfig;
import firenh.profundis.util.ProfundisTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;

public class PaintedCavesLargeOreFeature extends LargeOreFeature {
    private static final List<BlockState> TERRACOTTA_BLOCKS = List.of(
        Blocks.YELLOW_TERRACOTTA.getDefaultState(),
        Blocks.LIME_TERRACOTTA.getDefaultState(),
        Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(),
        Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(),
        Blocks.MAGENTA_TERRACOTTA.getDefaultState(),
        Blocks.PINK_TERRACOTTA.getDefaultState(),
        Blocks.ORANGE_TERRACOTTA.getDefaultState(),
        Blocks.YELLOW_TERRACOTTA.getDefaultState(),
        Blocks.YELLOW_TERRACOTTA.getDefaultState(),
        Blocks.LIME_TERRACOTTA.getDefaultState(),
        Blocks.LIME_TERRACOTTA.getDefaultState(),
        Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(),
        Blocks.MAGENTA_TERRACOTTA.getDefaultState()
    );

    
    private final float SCALE = 2;
    private final long seed = -224983484687588995L;
    private final DoublePerlinNoiseSampler.NoiseParameters noiseParameters = new DoublePerlinNoiseSampler.NoiseParameters(-4, 1);
    private final NoiseBlockStateProvider STATE_PROVIDER = new NoiseBlockStateProvider(seed, noiseParameters, SCALE, TERRACOTTA_BLOCKS);

    public PaintedCavesLargeOreFeature(Codec<LargeOreFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    protected Optional<BlockState> getBlockState(StructureWorldAccess world, BlockPos pos, BlockState currentState, Random random, List<OreFeatureConfig.Target> targets) {
        try {
            if (currentState.isIn(BlockTags.BASE_STONE_OVERWORLD)) {
                return Optional.of(
                    STATE_PROVIDER.get(random, pos)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // private double getNoiseValue(BlockPos pos, double scale) {
    //     return this.noiseSampler.sample((double)pos.getX() * scale, (double)pos.getY() * scale, (double)pos.getZ() * scale);
    // }

    
}
