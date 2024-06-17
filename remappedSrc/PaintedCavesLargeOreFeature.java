// package firenh.profundis.features.features;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import com.mojang.serialization.Codec;

// import firenh.profundis.features.features.config.LargeOreFeatureConfig;
// import firenh.profundis.util.ProfundisTags;
// import net.minecraft.block.BlockState;
// import net.minecraft.block.Blocks;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.util.math.MathHelper;
// import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
// import net.minecraft.util.math.random.Random;
// import net.minecraft.world.StructureWorldAccess;
// import net.minecraft.world.gen.feature.OreFeatureConfig;

// public class PaintedCavesLargeOreFeature extends LargeOreFeature {
//     private static final BlockState[] TERRACOTTA_BLOCKS = new BlockState[]{
//         // Blocks.ORANGE_TERRACOTTA.getDefaultState(),
//         // Blocks.YELLOW_TERRACOTTA.getDefaultState(),
//         Blocks.LIME_TERRACOTTA.getDefaultState(),
//         Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(),
//         Blocks.MAGENTA_TERRACOTTA.getDefaultState(),
//         Blocks.PINK_TERRACOTTA.getDefaultState(),
//         Blocks.RED_TERRACOTTA.getDefaultState(),
//         Blocks.ORANGE_TERRACOTTA.getDefaultState(),
//         Blocks.YELLOW_TERRACOTTA.getDefaultState(),
//         // Blocks.LIME_TERRACOTTA.getDefaultState(),
//         // Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(),
//     };
    
//     private final float SCALE = 2;

//     private final long seed;
//     private final DoublePerlinNoiseSampler.NoiseParameters noiseParameters;
//     private final DoublePerlinNoiseSampler noiseSampler;

//     private static List<BlockState> terracottaList = new ArrayList<>();

//     public PaintedCavesLargeOreFeature(Codec<LargeOreFeatureConfig> configCodec) {
//         super(configCodec);
//     }

//     @Override
//     protected Optional<BlockState> getBlockState(StructureWorldAccess world, BlockPos pos, BlockState currentState, Random random, List<OreFeatureConfig.Target> targets) {
//         if (currentState.isIn(ProfundisTags.BASE_STONE_OVERWORLD_PLUS)) {
//             return Optional.of(getTerracotta(world, pos.getY()));
//         }

//         return Optional.empty();
//     }

//     private BlockState getTerracotta(StructureWorldAccess world, int y) {
//         double d = MathHelper.clamp((1.0 + SCALE) / 2.0, 0.0, 0.9999);
//         return TERRACOTTA_BLOCKS[((int)(d * (double)TERRACOTTA_BLOCKS.length))];
//     }

//     private double getNoiseValue(BlockPos pos, double scale) {
//         return this.noiseSampler.sample((double)pos.getX() * scale, (double)pos.getY() * scale, (double)pos.getZ() * scale);
//     }

    
// }
