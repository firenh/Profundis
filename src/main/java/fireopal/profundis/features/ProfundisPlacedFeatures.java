package fireopal.profundis.features;

import java.util.List;

import fireopal.profundis.Profundis;
import fireopal.profundis.util.GeneralUtil;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.MiscConfiguredFeatures;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.heightprovider.VeryBiasedToBottomHeightProvider;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountMultilayerPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.EnvironmentScanPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceThresholdFilterPlacementModifier;

@SuppressWarnings("deprecation")
public class ProfundisPlacedFeatures {
    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, Profundis.id(id), new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier ... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static void init() {
        new ProfundisPlacedFeatures();
    }
    
    public static final PlacementModifier BOTTOM_TO_ZERO = HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0));
    public static final PlacementModifier ZERO_TO_120 = HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(120));


    public static final RegistryEntry<PlacedFeature> ICY_SURFACE = register("icy_surface",
        ProfundisConfiguredFeatures.ICY_SURFACE,
        CountPlacementModifier.of(24), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), 
        EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), 
        RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> LARGE_ICY_SURFACE = register("large_icy_surface",
        ProfundisConfiguredFeatures.LARGE_ICY_SURFACE,
        CountPlacementModifier.of(6), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), 
        EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), 
        RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> SNOW_LAYER_CAVE = register("snow_layer_cave",
        ProfundisConfiguredFeatures.SNOW_LAYER_CAVE,
        CountPlacementModifier.of(32), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
        EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), 
        RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FROZEN_AQUIFER = register("frozen_aquifer",
        ProfundisConfiguredFeatures.FROZEN_AQUIFER,
        CountPlacementModifier.of(128), 
        SquarePlacementModifier.of(), 
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FROZEN_LAVA_AQUIFER = register("frozen_lava_aquifer",
        ProfundisConfiguredFeatures.FROZEN_AQUIFER,
        CountPlacementModifier.of(48), 
        SquarePlacementModifier.of(),   
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> SMALL_ICICLES = register("small_icicles",
        ProfundisConfiguredFeatures.SMALL_ICICLES,
        CountPlacementModifier.of(96), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
        EnvironmentScanPlacementModifier.of(
            Direction.UP, 
            BlockPredicate.hasSturdyFace(Direction.DOWN), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(-1)
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> LARGE_ICICLES = register("large_icicles",
        ProfundisConfiguredFeatures.LARGE_ICICLES,
        CountPlacementModifier.of(32), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
        EnvironmentScanPlacementModifier.of(
            Direction.UP, 
            BlockPredicate.hasSturdyFace(Direction.DOWN), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(-1)
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FROZEN_CAVES_REDSTONE_ORE = register("frozen_caves_redstone_ore",
        OreConfiguredFeatures.ORE_REDSTONE,
        modifiersWithCount(
            32, HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.aboveBottom(120))
        )
    );

    public static final RegistryEntry<PlacedFeature> ICE_PILLAR = register("ice_pillars",
        ProfundisConfiguredFeatures.ICE_PILLAR,
        CountPlacementModifier.of(6), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
        EnvironmentScanPlacementModifier.of(
            Direction.UP, 
            BlockPredicate.hasSturdyFace(Direction.DOWN), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(-1)
        ), BiomePlacementModifier.of()

    );

    public static final RegistryEntry<PlacedFeature> MYCELIUM_CAVE_SURFACE = register("mycelium_cave_surface",
    ProfundisConfiguredFeatures.MYCELIUM_CAVE_SURFACE,
        CountPlacementModifier.of(256), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> CAVE_HUGE_MUSHROOMS = register("cave_huge_mushrooms",
        VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION,
        CountPlacementModifier.of(192), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 14
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> MUSHROOM_CAVES_RED = register("mushroom_caves_red",
        VegetationConfiguredFeatures.PATCH_RED_MUSHROOM,
        CountPlacementModifier.of(128),
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 14
        ),
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> MUSHROOM_CAVES_BROWN = register("mushroom_caves_brown",
        VegetationConfiguredFeatures.PATCH_BROWN_MUSHROOM,
        CountPlacementModifier.of(128),
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 14
        ),
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), BiomePlacementModifier.of()
    );

    private static final List<PlacementModifier> COUNT_20 = List.of(CountPlacementModifier.of(20));

    private static final List<PlacementModifier> SHELF_FUNGI_PLACEMENT_MODIFIERS = List.of(
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.alwaysTrue(), 
            BlockPredicate.matchingBlockTag(BlockTags.BASE_STONE_OVERWORLD), 14
        ),
        BiomePlacementModifier.of() 
    );

    public static final RegistryEntry<PlacedFeature> SHELF_FUNGI = register("shelf_fungi",
        ProfundisConfiguredFeatures.SHELF_FUNGI,
        GeneralUtil.combineLists(COUNT_20, SHELF_FUNGI_PLACEMENT_MODIFIERS)
    );

    public static final RegistryEntry<PlacedFeature> MOLTEN_CAVES_DELTA = register("molten_caves_delta", 
        ProfundisConfiguredFeatures.MOLTEN_CAVES_DELTA,
        CountMultilayerPlacementModifier.of(16),
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> MAGMA_REPLACE_LAVA = register("magma_replace_lava",
        ProfundisConfiguredFeatures.MAGMA_REPLACE_LAVA,
        CountPlacementModifier.of(128), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.alwaysTrue(), 12
        ),                 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> BASALT_PATCHES_LOWER = register("basalt_patches_lower",
        ProfundisConfiguredFeatures.BASALT_PATCHES,
        CountPlacementModifier.of(12), 
        SquarePlacementModifier.of(), 
        BOTTOM_TO_ZERO,
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.alwaysTrue(), 12
        ),                 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> BASALT_PATCHES_UPPER = register("basalt_patches_upper",
        ProfundisConfiguredFeatures.BASALT_PATCHES,
        CountPlacementModifier.of(8), 
        SquarePlacementModifier.of(), 
        ZERO_TO_120,
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.alwaysTrue(), 12
        ),                 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> GRANITE_PEPPERS = register("granite_peppers",
        ProfundisConfiguredFeatures.GRANITE_PEPPERS,
        CountPlacementModifier.of(48), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.alwaysTrue(), 12
        ),                 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> ANDESITE_PEPPERS = register("andesite_peppers",
        ProfundisConfiguredFeatures.ANDESITE_PEPPERS,
        CountPlacementModifier.of(48), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.alwaysTrue(), 12
        ),                 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> MOLTEN_CAVES_BASALT_COLUMNS = register("molten_caves_basalt_columns", 
        ProfundisConfiguredFeatures.MOLTEN_CAVES_BASALT_COLUMNS,
        CountMultilayerPlacementModifier.of(3),
        SquarePlacementModifier.of(), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> BASALT_PILLAR_MOLTEN_CAVES = register("basalt_pillar_molten_caves",
        ProfundisConfiguredFeatures.BASALT_PILLAR_MOLTEN_CAVES,
        CountMultilayerPlacementModifier.of(6),
        SquarePlacementModifier.of(),
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> LAVA_FIXER = register("lava_fixer",
        ProfundisConfiguredFeatures.LAVA_FIXER,
        CountPlacementModifier.of(10),
        PlacedFeatures.BOTTOM_TO_120_RANGE,
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> INCREASED_UNDERWATER_MAGMA = register("increased_underwater_magma",
        UndergroundConfiguredFeatures.UNDERWATER_MAGMA,
        CountPlacementModifier.of(256), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        SurfaceThresholdFilterPlacementModifier.of(
            Heightmap.Type.OCEAN_FLOOR_WG, 
            Integer.MIN_VALUE, -2
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> INCREASED_LAVA_SPRINGS = register("increased_spring_lava",
        MiscConfiguredFeatures.SPRING_LAVA_OVERWORLD,
        CountPlacementModifier.of(128), 
        SquarePlacementModifier.of(), 
        HeightRangePlacementModifier.of(
            VeryBiasedToBottomHeightProvider.create(
                YOffset.getBottom(), YOffset.belowTop(8), 8
            )
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> MOLTEN_CAVES_IRON = register("molten_caves_iron", 
        OreConfiguredFeatures.ORE_IRON,
        modifiersWithCount(
            15, 
            HeightRangePlacementModifier.uniform(
                YOffset.aboveBottom(0), YOffset.aboveBottom(120)
            )
        )
    );

    public static final RegistryEntry<PlacedFeature> AMETHYST_VEINS = register("amethyst_veins", 
        ProfundisConfiguredFeatures.AMETHYST_VEIN,
        CountPlacementModifier.of(ConstantIntProvider.create(48)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> AMETHYST_VEINS_LARGE = register("amethyst_veins_large", 
        ProfundisConfiguredFeatures.AMETHYST_VEIN_LARGE,
        CountPlacementModifier.of(ConstantIntProvider.create(6)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> AMETHYST_VEINS_AIR = register("amethyst_veins_air", 
        ProfundisConfiguredFeatures.AMETHYST_VEIN_AIR,
        CountPlacementModifier.of(ConstantIntProvider.create(6)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_120_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.UP, 
            BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 
            12
        ),
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> AMETHYST_GEODE_AMETHYST_CAVES = register("amethyst_geode_amethyst_caves", 
        UndergroundConfiguredFeatures.AMETHYST_GEODE, 
        RarityFilterPlacementModifier.of(24), 
        SquarePlacementModifier.of(), 
        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(64)), 
        BiomePlacementModifier.of()
    );

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModfier) {
        return modifiers(CountPlacementModifier.of(count), heightModfier);
    }

    // private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
    //     return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    // }
}
