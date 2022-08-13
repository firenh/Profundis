package fireopal.profundis.biomes;

import fireopal.profundis.biomes.biomes.AmethystCavesBiome;
import fireopal.profundis.biomes.biomes.FrozenCavesBiome;
import fireopal.profundis.biomes.biomes.MoltenCavesBiome;
import fireopal.profundis.biomes.biomes.MushroomCavesBiome;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ProfundisBiomes {
    public static final Biome FROZEN_CAVES = FrozenCavesBiome.create();
    public static final Biome MUSHROOM_CAVES = MushroomCavesBiome.create();
    public static final Biome MOLTEN_CAVES = MoltenCavesBiome.create();
    public static final Biome AMETHYST_CAVES = AmethystCavesBiome.create();

    private static void register(RegistryKey<Biome> key, Biome biome) {
        Registry.register(BuiltinRegistries.BIOME, key.getValue(), biome);
    }

    public static void init() {
        register(ProfundisBiomeKeys.FROZEN_CAVES, FROZEN_CAVES);
        register(ProfundisBiomeKeys.MUSHROOM_CAVES, MUSHROOM_CAVES);
        register(ProfundisBiomeKeys.MOLTEN_CAVES, MOLTEN_CAVES);
        register(ProfundisBiomeKeys.AMETHYST_CAVES, AMETHYST_CAVES);
    }
}
