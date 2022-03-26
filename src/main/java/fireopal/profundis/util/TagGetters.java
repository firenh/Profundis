package fireopal.profundis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fireopal.profundis.Profundis;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntryList;

public class TagGetters {
    public static RegistryEntryList<Block> getBlocks(TagKey<Block> key) {
        return getEntries(Registry.BLOCK, key);
    }

    public static <T> RegistryEntryList<T> getEntries(Registry<T> registry, TagKey<T> key) {
        Optional<RegistryEntryList.Named<T>> registryEntryList = registry.getEntryList(key);
        if (registryEntryList.isEmpty()) return RegistryEntryList.of();
        return registryEntryList.get();
    }

    public static <T> List<T> registryEntryListToList(RegistryEntryList<T> list) {
        List<T> newList = new ArrayList<T>();

        list.forEach(t -> {
            newList.add(t.value());
        });

        return newList;
    }

    public static RegistryEntryList<Block> baseStoneOverworld() {
        var returned = getBlocks(BlockTags.BASE_STONE_OVERWORLD);
        Profundis.LOGGER.info("Base Stone Overworld:" + registryEntryListToList(returned).size());
        return returned;
    }
}
