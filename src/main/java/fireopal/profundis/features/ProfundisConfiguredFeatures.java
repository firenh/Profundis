package fireopal.profundis.features;

import java.util.List;

import fireopal.profundis.Profundis;
import fireopal.profundis.features.features.config.AmethystVeinFeatureConfig;
import fireopal.profundis.features.features.config.CavePillarFeatureConfig;
import fireopal.profundis.features.features.config.CaveSurfaceFeatureConfig;
import fireopal.profundis.features.features.config.IcicleFeatureConfig;
import fireopal.profundis.features.features.config.NetherrackBaseFeatureConfig;
import fireopal.profundis.features.features.config.ShelfFungiFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.feature.BasaltColumnsFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
public class ProfundisConfiguredFeatures {
    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<?, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, Profundis.id(id), new ConfiguredFeature<FC, F>(feature, config));
    }

    public static void init() {
        new ProfundisConfiguredFeatures();
    }

    //  Hardcoded tag values lmao
    public static final RegistryEntryList<Block> BASE_STONE_OVERWORLD = RegistryEntryList.of(Block::getRegistryEntry, 
        Blocks.STONE,
        Blocks.GRANITE,
        Blocks.DIORITE,
        Blocks.ANDESITE,
        Blocks.TUFF,
        Blocks.DEEPSLATE
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> ICY_SURFACE = register("small_icy_surface", 
        ProfundisFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(3, 8),
            BiasedToBottomIntProvider.create(1, 2),
            Blocks.ICE.getDefaultState(),
            Blocks.ICE.getDefaultState(),
            BASE_STONE_OVERWORLD,
            UniformIntProvider.create(20, 35),
            true,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> NO_OP = register("no_op",
        Feature.NO_OP,
        new DefaultFeatureConfig()
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_ICY_SURFACE = register("large_icy_surface", 
        ProfundisFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(5, 12),
            BiasedToBottomIntProvider.create(3, 10),
            Blocks.ICE.getDefaultState(),
            Blocks.ICE.getDefaultState(),
            BASE_STONE_OVERWORLD,
            UniformIntProvider.create(30, 60),
            true,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SNOW_LAYER_CAVE = register("snow_layer_cave", 
        ProfundisFeatures.CAVE_SURFACE_FEATURE,
        new CaveSurfaceFeatureConfig(
            UniformIntProvider.create(6, 16),
            1,
            Blocks.SNOW.getDefaultState(),
            0.7f,
            false,
            UniformIntProvider.create(3, 5),
            Blocks.AIR.getDefaultState(),
            true,
            true
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> FROZEN_AQUIFER = register("frozen_aquifer", 
        ProfundisFeatures.CAVE_SURFACE_FEATURE,
        new CaveSurfaceFeatureConfig(
            ConstantIntProvider.create(16),
            0,
            Blocks.ICE.getDefaultState(),
            1.0f,
            true,
            UniformIntProvider.create(3, 5),
            Blocks.WATER.getDefaultState(),
            false,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> FROZEN_LAVA_AQUIFER = register("frozen_lava_aquifer", 
        ProfundisFeatures.CAVE_SURFACE_FEATURE,
        new CaveSurfaceFeatureConfig(
            UniformIntProvider.create(6, 16),
            0,
            Blocks.OBSIDIAN.getDefaultState(),
            1.0f,
            true,
            UniformIntProvider.create(3, 5),
            Blocks.LAVA.getDefaultState(),
            false,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_ICICLES = register("small_icicles", 
        ProfundisFeatures.ICICLE_FEATURE,
        new IcicleFeatureConfig(
            UniformIntProvider.create(2, 6),
            UniformIntProvider.create(4, 6),
            UniformIntProvider.create(5, 8),
            Blocks.ICE.getDefaultState(),
            Blocks.ICE.getDefaultState()
        )
    );
        
    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_ICICLES = register("large_icicles", 
        ProfundisFeatures.ICICLE_FEATURE,
        new IcicleFeatureConfig(
            UniformIntProvider.create(7, 16),
            UniformIntProvider.create(4, 8),
            UniformIntProvider.create(8, 12),
            Blocks.PACKED_ICE.getDefaultState(),
            Blocks.ICE.getDefaultState()
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> ICE_PILLAR = register("ice_pillars",
        ProfundisFeatures.CAVE_PILLAR_FEATURE,
        new CavePillarFeatureConfig(
            Blocks.BLUE_ICE.getDefaultState(), 
            Blocks.ICE.getDefaultState(), 
            ConstantIntProvider.ZERO, 
            UniformIntProvider.create(2, 7),
            PlacedFeatures.createEntry(LARGE_ICY_SURFACE)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> MYCELIUM_CAVE_SURFACE = register("mycelium_cave_surface", 
        ProfundisFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(6, 16),
            BiasedToBottomIntProvider.create(2, 5),
            Blocks.MYCELIUM.getDefaultState(),
            Blocks.DIRT.getDefaultState(),
            BASE_STONE_OVERWORLD,
            UniformIntProvider.create(30, 50),
            false,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_BROWN_SHELF_FUNGI = register("large_brown_shelf_fungi",
        ProfundisFeatures.SHELF_FUNGI_FEATURE,
        new ShelfFungiFeatureConfig(
            UniformIntProvider.create(4, 10),
            UniformIntProvider.create(3, 7),
            Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false),
            Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState()
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.WEST, false)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_BROWN_SHELF_FUNGI = register("small_brown_shelf_fungi",
        ProfundisFeatures.SHELF_FUNGI_FEATURE,
        new ShelfFungiFeatureConfig(
            UniformIntProvider.create(2, 5),
            UniformIntProvider.create(3, 7),
            Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false),
            Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState()
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.WEST, false)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_WHITE_SHELF_FUNGI = register("large_white_shelf_fungi",
        ProfundisFeatures.SHELF_FUNGI_FEATURE,
        new ShelfFungiFeatureConfig(
            UniformIntProvider.create(4, 10),
            UniformIntProvider.create(3, 7),
            Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.DOWN, false),
            Blocks.MUSHROOM_STEM.getDefaultState()
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.WEST, false)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_WHITE_SHELF_FUNGI = register("small_white_shelf_fungi",
        ProfundisFeatures.SHELF_FUNGI_FEATURE,
        new ShelfFungiFeatureConfig(
            UniformIntProvider.create(2, 5),
            UniformIntProvider.create(3, 7),
            Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.DOWN, false),
            Blocks.MUSHROOM_STEM.getDefaultState()
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.WEST, false)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_RED_SHELF_FUNGI = register("large_red_shelf_fungi",
        ProfundisFeatures.SHELF_FUNGI_FEATURE,
        new ShelfFungiFeatureConfig(
            UniformIntProvider.create(4, 10),
            UniformIntProvider.create(3, 7),
            Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false),
            Blocks.RED_MUSHROOM_BLOCK.getDefaultState()
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.WEST, false)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_RED_SHELF_FUNGI = register("small_red_shelf_fungi",
        ProfundisFeatures.SHELF_FUNGI_FEATURE,
        new ShelfFungiFeatureConfig(
            UniformIntProvider.create(2, 5),
            UniformIntProvider.create(3, 7),
            Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false),
            Blocks.RED_MUSHROOM_BLOCK.getDefaultState()
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.WEST, false)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SHELF_FUNGI = register("shelf_fungi",
        Feature.RANDOM_SELECTOR,
        new RandomFeatureConfig(
            List.of(
                new RandomFeatureEntry(
                    PlacedFeatures.createEntry(LARGE_BROWN_SHELF_FUNGI),
                    2f/ 15f
                ),
                new RandomFeatureEntry(
                    PlacedFeatures.createEntry(SMALL_BROWN_SHELF_FUNGI),
                    4f/ 15f
                ),
                new RandomFeatureEntry(
                    PlacedFeatures.createEntry(LARGE_WHITE_SHELF_FUNGI),
                    2f/ 15f
                ),
                new RandomFeatureEntry(
                    PlacedFeatures.createEntry(SMALL_WHITE_SHELF_FUNGI),
                    4f/ 15f
                ),
                new RandomFeatureEntry(
                    PlacedFeatures.createEntry(LARGE_RED_SHELF_FUNGI),
                    1f/ 15f
                ),
                new RandomFeatureEntry(
                    PlacedFeatures.createEntry(SMALL_RED_SHELF_FUNGI),
                    2f/ 15f
                )
            ),
            PlacedFeatures.createEntry(SMALL_BROWN_SHELF_FUNGI)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> MOLTEN_CAVES_DELTA = register("molten_caves_delta", 
        ProfundisFeatures.NOT_STUPID_DELTA_FEATURE,
        new DeltaFeatureConfig(
            Blocks.LAVA.getDefaultState(), 
            Blocks.MAGMA_BLOCK.getDefaultState(), 
            UniformIntProvider.create(3, 7), 
            UniformIntProvider.create(0, 2)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> MAGMA_REPLACE_LAVA = register("magma_replace_lava", 
        ProfundisFeatures.CAVE_SURFACE_FEATURE,
        new CaveSurfaceFeatureConfig(
            UniformIntProvider.create(13, 17),
            0,
            Blocks.MAGMA_BLOCK.getDefaultState(),
            0.35f,
            true,
            UniformIntProvider.create(2, 4),
            Blocks.LAVA.getDefaultState(),
            false,
            true
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> BASALT_PATCHES = register("smooth_basalt_peppers", 
        ProfundisFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(6, 16),
            BiasedToBottomIntProvider.create(2, 5),
            Blocks.SMOOTH_BASALT.getDefaultState(),
            Blocks.BASALT.getDefaultState(),
            BASE_STONE_OVERWORLD,
            UniformIntProvider.create(30, 50),
            true,
            false
        )
    );;

    public static final RegistryEntry<ConfiguredFeature<?, ?>> GRANITE_PEPPERS = register("granite_peppers", 
        ProfundisFeatures.CAVE_SURFACE_FEATURE,
        new CaveSurfaceFeatureConfig(
            UniformIntProvider.create(13, 17),
            0,
            Blocks.GRANITE.getDefaultState(),
            0.35f,
            true,
            UniformIntProvider.create(2, 4),
            Blocks.STONE.getDefaultState(),
            false,
            true
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> ANDESITE_PEPPERS = register("andesite_peppers", 
        ProfundisFeatures.CAVE_SURFACE_FEATURE,
        new CaveSurfaceFeatureConfig(
            UniformIntProvider.create(13, 17),
            0,
            Blocks.ANDESITE.getDefaultState(),
            0.35f,
            true,
            UniformIntProvider.create(2, 4),
            Blocks.STONE.getDefaultState(),
            false,
            true
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> MOLTEN_CAVES_BASALT_COLUMNS = register("molten_caves_basalt_columns",
        Feature.BASALT_COLUMNS,
        new BasaltColumnsFeatureConfig(
            UniformIntProvider.create(1, 1), 
            UniformIntProvider.create(2, 4)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LAVA_FIXER = register("lava_fixer",
        ProfundisFeatures.LAVA_FIXER_FEATURE,
        new DefaultFeatureConfig()
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> BASALT_PILLAR_MOLTEN_CAVES = register("basalt_pillar_molten_caves",
        ProfundisFeatures.CAVE_PILLAR_FEATURE,
        new CavePillarFeatureConfig(
            Blocks.BASALT.getDefaultState(), 
            Blocks.BASALT.getDefaultState(), 
            ConstantIntProvider.ZERO, 
            UniformIntProvider.create(2, 7), 
            PlacedFeatures.createEntry(NO_OP)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> AMETHYST_VEIN = register("amethyst_vein", 
        ProfundisFeatures.AMETHYST_VEIN_FEATURE,
        new AmethystVeinFeatureConfig(
            true,
            ConstantIntProvider.create(2), 
            ConstantIntProvider.create(16), 
            UniformIntProvider.create(5, 10)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> AMETHYST_VEIN_LARGE = register("amethyst_vein_large", 
        ProfundisFeatures.AMETHYST_VEIN_FEATURE,
        new AmethystVeinFeatureConfig(
            true,
            UniformIntProvider.create(4, 5), 
            ConstantIntProvider.create(16), 
            UniformIntProvider.create(5, 10)
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> AMETHYST_VEIN_AIR = register("amethyst_vein_air", 
        ProfundisFeatures.AMETHYST_VEIN_FEATURE,
        new AmethystVeinFeatureConfig(
            false,
            ConstantIntProvider.create(2),
            ConstantIntProvider.create(16), 
            UniformIntProvider.create(5, 10)
        )
    );
}
