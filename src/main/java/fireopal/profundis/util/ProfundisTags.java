package fireopal.profundis.util;

import fireopal.profundis.Profundis;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class ProfundisTags {
    public static final TagKey<Biome> FROZE_WATER_CAVE_FEATURE_WORKS = biome("frozen_water_cave_feature_works");  
    
    public static final TagKey<Block> BASE_STONE_OVERWORLD_PLUS = block("base_stone_overworld_plus");  


    private static TagKey<Block> block(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Profundis.id(id));
    }

    private static TagKey<Biome> biome(String id) {
        return TagKey.of(RegistryKeys.BIOME, Profundis.id(id));
    }
}
