package fireopal.profundis.features;


import fireopal.profundis.Profundis;
import fireopal.profundis.features.features.config.CavePillarFeatureConfig;
import fireopal.profundis.features.features.config.CaveSurfaceFeatureConfig;
import fireopal.profundis.features.features.config.IcicleFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.BasaltColumnsFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
public class ProfundisConfiguredFeatures {
    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Profundis.id(id), configuredFeature);
    }

    public static final ConfiguredFeature<DefaultFeatureConfig, ?> 
        NO_OP,
        LAVA_FIXER
    ;

    public static final ConfiguredFeature<CaveSurfaceFeatureConfig, ?> 
        ICY_SURFACE, 
        SNOW_LAYER_CAVE, 
        FROZEN_AQUIFER, 
        FROZEN_LAVA_AQUIFER,
        MYCELIUM_CAVE_SURFACE,
        MAGMA_REPLACE_LAVA,
        SMOOTH_BASALT_PEPPERS,
        GRANITE_PEPPERS,
        ANDESITE_PEPPERS
    ;

    public static final ConfiguredFeature<BasaltColumnsFeatureConfig, ?> MOLTEN_CAVES_BASALT_COLUMNS;

    public static final ConfiguredFeature<IcicleFeatureConfig, ?> SMALL_ICICLES, LARGE_ICICLES;
    public static final ConfiguredFeature<CavePillarFeatureConfig, ?> ICE_PILLAR, BASALT_PILLAR_MOLTEN_CAVES;

    public static final ConfiguredFeature<DeltaFeatureConfig, ?> MOLTEN_CAVES_DELTA;

    public static void init() {
        new ProfundisConfiguredFeatures();
    }

    static {
        NO_OP = register("no_op",
            Feature.NO_OP.configure(new DefaultFeatureConfig())
        );


        ICY_SURFACE = register("icy_surface", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
                new CaveSurfaceFeatureConfig(
                    UniformIntProvider.create(6, 16),
                    0,
                    Blocks.ICE.getDefaultState(),
                    0.9f,
                    false,
                    UniformIntProvider.create(3, 5),
                    Blocks.AIR.getDefaultState(),
                    true,
                    true
                )
            )
        );

        SNOW_LAYER_CAVE = register("snow_layer_cave", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
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
            )
        );

        FROZEN_AQUIFER = register("frozen_aquifer", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
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
            )
        );

        FROZEN_LAVA_AQUIFER = register("frozen_lava_aquifer", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
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
            )
        );

        SMALL_ICICLES = register("small_icicles", 
            ProfundisFeatures.ICICLE_FEATURE.configure(
                new IcicleFeatureConfig(
                    UniformIntProvider.create(2, 6),
                    UniformIntProvider.create(4, 6),
                    UniformIntProvider.create(5, 8),
                    Blocks.ICE.getDefaultState(),
                    Blocks.ICE.getDefaultState()
                )
            )
        );
         
        LARGE_ICICLES = register("large_icicles", 
            ProfundisFeatures.ICICLE_FEATURE.configure(
                new IcicleFeatureConfig(
                    UniformIntProvider.create(7, 16),
                    UniformIntProvider.create(4, 8),
                    UniformIntProvider.create(8, 12),
                    Blocks.PACKED_ICE.getDefaultState(),
                    Blocks.ICE.getDefaultState()
                )
            )
        );

        ICE_PILLAR = register("ice_pillars",
            ProfundisFeatures.CAVE_PILLAR_FEATURE.configure(
                new CavePillarFeatureConfig(
                    Blocks.BLUE_ICE.getDefaultState(), 
                    Blocks.ICE.getDefaultState(), 
                    ConstantIntProvider.ZERO, 
                    UniformIntProvider.create(2, 7)
                )
            )
        );

        MYCELIUM_CAVE_SURFACE = register("mycelium_cave_surface", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
                new CaveSurfaceFeatureConfig(
                    UniformIntProvider.create(13, 17),
                    0,
                    Blocks.MYCELIUM.getDefaultState(),
                    0.7f,
                    false,
                    UniformIntProvider.create(2, 4),
                    Blocks.AIR.getDefaultState(),
                    true,
                    true
                )
            )
        );

        MOLTEN_CAVES_DELTA = register("molten_caves_delta", 
            ProfundisFeatures.NOT_STUPID_DELTA_FEATURE.configure(
                new DeltaFeatureConfig(
                    Blocks.LAVA.getDefaultState(), 
                    Blocks.MAGMA_BLOCK.getDefaultState(), 
                    UniformIntProvider.create(3, 7), 
                    UniformIntProvider.create(0, 2)
                )
            )
        );

        MAGMA_REPLACE_LAVA = register("magma_replace_lava", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
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
            )
        );

        SMOOTH_BASALT_PEPPERS = register("smooth_basalt_peppers", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
                new CaveSurfaceFeatureConfig(
                    UniformIntProvider.create(13, 17),
                    0,
                    Blocks.SMOOTH_BASALT.getDefaultState(),
                    0.35f,
                    true,
                    UniformIntProvider.create(2, 4),
                    Blocks.DEEPSLATE.getDefaultState(),
                    false,
                    true
                )
            )
        );

        GRANITE_PEPPERS = register("granite_peppers", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
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
            )
        );

        ANDESITE_PEPPERS = register("andesite_peppers", 
            ProfundisFeatures.CAVE_SURFACE_FEATURE.configure(
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
            )
        );

        MOLTEN_CAVES_BASALT_COLUMNS = register("molten_caves_basalt_columns",
            Feature.BASALT_COLUMNS.configure(
                new BasaltColumnsFeatureConfig(
                    UniformIntProvider.create(1, 1), 
                    UniformIntProvider.create(2, 4)
                )
            )
        );

        LAVA_FIXER = register("lava_fixer",
            ProfundisFeatures.LAVA_FIXER_FEATURE.configure(
                new DefaultFeatureConfig()
            )
        );

        BASALT_PILLAR_MOLTEN_CAVES = register("basalt_pillar_molten_caves",
            ProfundisFeatures.CAVE_PILLAR_FEATURE.configure(
                new CavePillarFeatureConfig(
                    Blocks.BASALT.getDefaultState(), 
                    Blocks.BASALT.getDefaultState(), 
                    ConstantIntProvider.ZERO, 
                    UniformIntProvider.create(2, 7)
                )
            )
        );



    }
}
