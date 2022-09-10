package fireopal.profundis.biomes;

import java.util.List;

import fireopal.profundis.Profundis;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ProfundisBiomeKeys {
    private static RegistryKey<Biome> register(String id) {
        return RegistryKey.of(Registry.BIOME_KEY, Profundis.id(id));
    }

    public static final RegistryKey<Biome> FROZEN_CAVES = register("frozen_caves");
    public static final RegistryKey<Biome> MUSHROOM_CAVES = register("mushroom_caves");
    public static final RegistryKey<Biome> MOLTEN_CAVES = register("molten_caves");

    public static final List<RegistryKey<Biome>> BIOMES = List.of(
            FROZEN_CAVES,
            MUSHROOM_CAVES,
            MOLTEN_CAVES
    );
}