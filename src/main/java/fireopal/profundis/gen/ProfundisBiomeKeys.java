package fireopal.profundis.gen;

import fireopal.profundis.Profundis;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;

public class ProfundisBiomeKeys {
    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Profundis.id(name));
    }

    public static final RegistryKey<Biome> FROZEN_CAVES = register("frozen_caves");
    public static final RegistryKey<Biome> MUSHROOM_CAVES = register("mushroom_caves");
    public static final RegistryKey<Biome> MOLTEN_CAVES = register("molten_caves");
    public static final RegistryKey<Biome> AMETHYST_CAVES = register("amethyst_caves");
    public static final RegistryKey<Biome> BLACK_CAVES = register("black_caves");
    public static final RegistryKey<Biome> ARID_CAVES = register("arid_caves");
    public static final RegistryKey<Biome> FLORAL_LUSH_CAVES = register("floral_lush_caves");
    public static final RegistryKey<Biome> DIRT_CAVES = register("dirt_caves");
    public static final RegistryKey<Biome> SPARSE_LUSH_CAVES = register("sparse_lush_caves");
}
