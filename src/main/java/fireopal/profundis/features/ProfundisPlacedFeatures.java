package fireopal.profundis.features;

import java.util.List;

import fireopal.profundis.Profundis;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountMultilayerPlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.EnvironmentScanPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.decorator.SurfaceThresholdFilterPlacementModifier;
import net.minecraft.world.gen.feature.MiscConfiguredFeatures;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.heightprovider.VeryBiasedToBottomHeightProvider;

@SuppressWarnings("deprecation")
public class ProfundisPlacedFeatures {
    // NetherPlacedFeatures
    // UndergroundPlacedFeatures
    // OreConfiguredFeatures

    public static PlacedFeature register(String id, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, Profundis.id("placed_" + id), feature);
    }

    public static final PlacedFeature 
        NO_OP,
        ICY_SURFACE,
        SNOW_LAYER_CAVE,
        FROZEN_AQUIFER,
        FROZEN_LAVA_AQUIFER,
        SMALL_ICICLES,
        LARGE_ICICLES,
        FROZEN_CAVES_REDSTONE_ORE,
        ICE_PILLAR,
        MYCELIUM_CAVE_SURFACE,
        CAVE_HUGE_MUSHROOMS,
        MUSHROOM_CAVES_RED,
        MUSHROOM_CAVES_BROWN,
        MOLTEN_CAVES_DELTA,
        MAGMA_REPLACE_LAVA,
        SMOOTH_BASALT_PEPPERS,
        GRANITE_PEPPERS,
        ANDESITE_PEPPERS,
        MOLTEN_CAVES_BASALT_COLUMNS,
        BASALT_PILLAR_MOLTEN_CAVES,
        LAVA_FIXER,
        INCREASED_UNDERWATER_MAGMA,
        INCREASED_LAVA_SPRINGS,
        MOLTEN_CAVES_IRON
    ; 

    public static void init() {
        new ProfundisPlacedFeatures();
    }

    static {
        NO_OP = register("no_op",
            ProfundisConfiguredFeatures.NO_OP.withPlacement(new PlacementModifier[0])
        );

        ICY_SURFACE = register("icy_surface",
            ProfundisConfiguredFeatures.ICY_SURFACE.withPlacement(
                CountPlacementModifier.of(32), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), 
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), 
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
                BiomePlacementModifier.of()
            )
        );

        SNOW_LAYER_CAVE = register("snow_layer_cave",
            ProfundisConfiguredFeatures.SNOW_LAYER_CAVE.withPlacement(
                CountPlacementModifier.of(32), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE, 
                SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), 
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
                BiomePlacementModifier.of()
            )
        );

        FROZEN_AQUIFER = register("frozen_aquifer",
            ProfundisConfiguredFeatures.FROZEN_AQUIFER.withPlacement(
                CountPlacementModifier.of(128), 
                SquarePlacementModifier.of(), 
                SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
                PlacedFeatures.BOTTOM_TO_120_RANGE, 
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
                BiomePlacementModifier.of()
            )
        );

        FROZEN_LAVA_AQUIFER = register("frozen_lava_aquifer",
            ProfundisConfiguredFeatures.FROZEN_AQUIFER.withPlacement(
                CountPlacementModifier.of(48), 
                SquarePlacementModifier.of(),   
                SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), 
                BiomePlacementModifier.of()
            )
        );

        SMALL_ICICLES = register("small_icicles",
            ProfundisConfiguredFeatures.SMALL_ICICLES.withPlacement(
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
            )
        );

        LARGE_ICICLES = register("large_icicles",
            ProfundisConfiguredFeatures.LARGE_ICICLES.withPlacement(
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
            )
        );

        FROZEN_CAVES_REDSTONE_ORE = register("frozen_caves_redstone_ore",
            OreConfiguredFeatures.ORE_REDSTONE.withPlacement(
                modifiersWithCount(
                    32, HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.aboveBottom(120))
                )
            )
        );

        ICE_PILLAR = register("ice_pillars",
            ProfundisConfiguredFeatures.ICE_PILLAR.withPlacement(
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
            )
        );

        MYCELIUM_CAVE_SURFACE = register("mycelium_cave_surface",
            ProfundisConfiguredFeatures.MYCELIUM_CAVE_SURFACE.withPlacement(
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
            )
        );

        CAVE_HUGE_MUSHROOMS = register("cave_huge_mushrooms",
            VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION.withPlacement(
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
            )
        );

        MUSHROOM_CAVES_RED = register("mushroom_caves_red",
            VegetationConfiguredFeatures.PATCH_RED_MUSHROOM.withPlacement(
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
            )
        );

        MUSHROOM_CAVES_BROWN = register("mushroom_caves_brown",
            VegetationConfiguredFeatures.PATCH_BROWN_MUSHROOM.withPlacement(
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
            )
        );

        MOLTEN_CAVES_DELTA = register("molten_caves_delta", 
            ProfundisConfiguredFeatures.MOLTEN_CAVES_DELTA.withPlacement(
                CountMultilayerPlacementModifier.of(16),
                BiomePlacementModifier.of()
            )
        );

        MAGMA_REPLACE_LAVA = register("magma_replace_lava",
            ProfundisConfiguredFeatures.MAGMA_REPLACE_LAVA.withPlacement(
                CountPlacementModifier.of(128), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(
                    Direction.DOWN, BlockPredicate.solid(), 
                    BlockPredicate.alwaysTrue(), 12
                ),                 
                BiomePlacementModifier.of()
            )
        );

        SMOOTH_BASALT_PEPPERS = register("smooth_basalt_peppers",
            ProfundisConfiguredFeatures.SMOOTH_BASALT_PEPPERS.withPlacement(
                CountPlacementModifier.of(96), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(
                    Direction.DOWN, BlockPredicate.solid(), 
                    BlockPredicate.alwaysTrue(), 12
                ),                 
                BiomePlacementModifier.of()
            )
        );

        GRANITE_PEPPERS = register("granite_peppers",
            ProfundisConfiguredFeatures.GRANITE_PEPPERS.withPlacement(
                CountPlacementModifier.of(48), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(
                    Direction.DOWN, BlockPredicate.solid(), 
                    BlockPredicate.alwaysTrue(), 12
                ),                 
                BiomePlacementModifier.of()
            )
        );

        ANDESITE_PEPPERS = register("andesite_peppers",
            ProfundisConfiguredFeatures.ANDESITE_PEPPERS.withPlacement(
                CountPlacementModifier.of(48), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(
                    Direction.DOWN, BlockPredicate.solid(), 
                    BlockPredicate.alwaysTrue(), 12
                ),                 
                BiomePlacementModifier.of()
            )
        );

        MOLTEN_CAVES_BASALT_COLUMNS = register("molten_caves_basalt_columns", 
            ProfundisConfiguredFeatures.MOLTEN_CAVES_BASALT_COLUMNS.withPlacement(
                CountMultilayerPlacementModifier.of(3),
                SquarePlacementModifier.of(), 
                BiomePlacementModifier.of()
            )
        );

        BASALT_PILLAR_MOLTEN_CAVES = register("basalt_pillar_molten_caves",
            ProfundisConfiguredFeatures.BASALT_PILLAR_MOLTEN_CAVES.withPlacement(
                CountMultilayerPlacementModifier.of(6),
                SquarePlacementModifier.of(),
                BiomePlacementModifier.of()
            )
        );

        LAVA_FIXER = register("lava_fixer",
            ProfundisConfiguredFeatures.LAVA_FIXER.withPlacement(
                CountPlacementModifier.of(10),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                BiomePlacementModifier.of()
            )
        );

        INCREASED_UNDERWATER_MAGMA = register("increased_underwater_magma",
            UndergroundConfiguredFeatures.UNDERWATER_MAGMA.withPlacement(
                CountPlacementModifier.of(256), 
                SquarePlacementModifier.of(), 
                PlacedFeatures.BOTTOM_TO_120_RANGE, 
                SurfaceThresholdFilterPlacementModifier.of(
                    Heightmap.Type.OCEAN_FLOOR_WG, 
                    Integer.MIN_VALUE, -2
                ), BiomePlacementModifier.of()
            )
        );

        INCREASED_LAVA_SPRINGS = register("increased_spring_lava",
            MiscConfiguredFeatures.SPRING_LAVA_OVERWORLD.withPlacement(
                CountPlacementModifier.of(128), 
                SquarePlacementModifier.of(), 
                HeightRangePlacementModifier.of(
                    VeryBiasedToBottomHeightProvider.create(
                        YOffset.getBottom(), YOffset.belowTop(8), 8
                    )
                ), BiomePlacementModifier.of()
            )
        );

        MOLTEN_CAVES_IRON = register("molten_caves_iron", 
            OreConfiguredFeatures.ORE_IRON.withPlacement(
                modifiersWithCount(
                    15, 
                    HeightRangePlacementModifier.uniform(
                        YOffset.aboveBottom(0), YOffset.aboveBottom(120)
                    )
                )
            )
        );


    }

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
