// package fireopal.profundis.structure;

// import java.util.Map;
// import java.util.function.Function;

// import com.google.common.collect.Maps;
// import com.mojang.serialization.Codec;

// import net.minecraft.block.Block;
// import net.minecraft.block.BlockState;
// import net.minecraft.block.Blocks;
// import net.minecraft.block.SlabBlock;
// import net.minecraft.block.StairsBlock;
// import net.minecraft.registry.tag.BlockTags;
// import net.minecraft.registry.tag.TagKey;
// import net.minecraft.structure.StructurePlacementData;
// import net.minecraft.structure.StructureTemplate;
// import net.minecraft.structure.processor.StructureProcessor;
// import net.minecraft.structure.processor.StructureProcessorType;
// import net.minecraft.util.Util;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.world.WorldView;

// public class RandomWoodReplacementStructureProcessor extends StructureProcessor {
//     public static enum WoodType {
//         OAK(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.),
//         SPRUCE, 
//         BIRCH, 
//         JUNGLE, 
//         ACACIA, 
//         DARK_OAK, 
//         MANGROVE, 
//         CHERRY, 
//         BAMBOO;

//         public final Block log;
//         public final Block strippedLog;
//         public final Block planks;
//         public final Block woodenSlab;
//         public final Block woodenStair;
//         public final Block woodenFence;
//         public final Block woodenPressurePl;
//         public final Block woodenButton;
//         public final Block woodenDoor;
//         public final Block woodenTrapdoor;
        
//         private WoodType(Block log, Block strippedLog, Block planks, Block woodenSlab, Block woodenStair,
//                 Block woodenFence, Block woodenPressurePl, Block woodenButton, Block woodenDoor, Block woodenTrapdoor) {
//             this.log = log;
//             this.strippedLog = strippedLog;
//             this.planks = planks;
//             this.woodenSlab = woodenSlab;
//             this.woodenStair = woodenStair;
//             this.woodenFence = woodenFence;
//             this.woodenPressurePl = woodenPressurePl;
//             this.woodenButton = woodenButton;
//             this.woodenDoor = woodenDoor;
//             this.woodenTrapdoor = woodenTrapdoor;
//         }

//         public Block getLog() {
//             return log;
//         }
//         public Block getStrippedLog() {
//             return strippedLog;
//         }
//         public Block getPlanks() {
//             return planks;
//         }
//         public Block getWoodenSlab() {
//             return woodenSlab;
//         }
//         public Block getWoodenStair() {
//             return woodenStair;
//         }
//         public Block getWoodenFence() {
//             return woodenFence;
//         }
//         public Block getWoodenPressurePl() {
//             return woodenPressurePl;
//         }
//         public Block getWoodenButton() {
//             return woodenButton;
//         }
//         public Block getWoodenDoor() {
//             return woodenDoor;
//         }
//         public Block getWoodenTrapdoor() {
//             return woodenTrapdoor;
//         }
//     }

//     public static final RandomWoodReplacementStructureProcessor INSTANCE = new RandomWoodReplacementStructureProcessor();
//     public static final Codec<RandomWoodReplacementStructureProcessor> CODEC = Codec.unit(() -> INSTANCE);
//     public static final Map<TagKey<Block>, Function<WoodType, Block>> replacementMap = Util.make(Maps.newHashMap(), replacements -> {
//         replacements.put(BlockTags.LOGS, w -> w.LOG);
//     });

//     private RandomWoodReplacementStructureProcessor() {
//     }

//     @Override
//     public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        

        
//         if (block == null) {
//             return currentBlockInfo;
//         }
//         BlockState currentBlockState = currentBlockInfo.state();
//         BlockState newBlockState = block.getDefaultState();
//         if (currentBlockState.contains(StairsBlock.FACING)) {
//             newBlockState = (BlockState)newBlockState.with(StairsBlock.FACING, currentBlockState.get(StairsBlock.FACING));
//         }
//         if (currentBlockState.contains(StairsBlock.HALF)) {
//             newBlockState = (BlockState)newBlockState.with(StairsBlock.HALF, currentBlockState.get(StairsBlock.HALF));
//         }
//         if (currentBlockState.contains(SlabBlock.TYPE)) {
//             newBlockState = (BlockState)newBlockState.with(SlabBlock.TYPE, currentBlockState.get(SlabBlock.TYPE));
//         }
//         return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), newBlockState, currentBlockInfo.nbt());
//     }

//     @Override
//     protected StructureProcessorType<?> getType() {
//         return ProfundisStructureProcessorType.RANDOM_WOOD_REPLACEMENT;
//     }
// }
