// package fireopal.profundis.structure;

// import com.mojang.serialization.Codec;

// import fireopal.profundis.Profundis;
// import net.minecraft.registry.Registries;
// import net.minecraft.registry.Registry;
// import net.minecraft.structure.processor.CappedStructureProcessor;
// import net.minecraft.structure.processor.StructureProcessor;
// import net.minecraft.structure.processor.StructureProcessorType;

// public class ProfundisStructureProcessorType {
//     public static final StructureProcessorType<CappedStructureProcessor> RANDOM_WOOD_REPLACEMENT = StructureProcessorType.register("random_wood_replacement", CappedStructureProcessor.CODEC);

//     public static <P extends StructureProcessor> StructureProcessorType<P> register(String id, Codec<P> codec) {
//         return Registry.register(Registries.STRUCTURE_PROCESSOR, Profundis.id(id), () -> codec);
//     }
// }
