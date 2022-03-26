package fireopal.profundis.features;

import fireopal.profundis.Profundis;
import fireopal.profundis.features.features.CavePillarFeature;
import fireopal.profundis.features.features.CaveSurfaceFeature;
import fireopal.profundis.features.features.IcicleFeature;
import fireopal.profundis.features.features.LavaFixerFeature;
import fireopal.profundis.features.features.NetherrackBaseFeature;
import fireopal.profundis.features.features.NotStupidDeltaFeature;
import fireopal.profundis.features.features.config.CavePillarFeatureConfig;
import fireopal.profundis.features.features.config.CaveSurfaceFeatureConfig;
import fireopal.profundis.features.features.config.IcicleFeatureConfig;
import fireopal.profundis.features.features.config.NetherrackBaseFeatureConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ProfundisFeatures {
    private static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        return (F)Registry.register(Registry.FEATURE, Profundis.id(id), feature);
    }

    public final static Feature<IcicleFeatureConfig> ICICLE_FEATURE;
    public final static Feature<CaveSurfaceFeatureConfig> CAVE_SURFACE_FEATURE;
    public final static Feature<CavePillarFeatureConfig> CAVE_PILLAR_FEATURE;
    public final static Feature<DefaultFeatureConfig> LAVA_FIXER_FEATURE;
    public final static Feature<DeltaFeatureConfig> NOT_STUPID_DELTA_FEATURE;
    public final static Feature<NetherrackBaseFeatureConfig> NETHERRACK_BASE_FEATURE;

    static {
        ICICLE_FEATURE = register("icicle", new IcicleFeature(IcicleFeatureConfig.CODEC));
        CAVE_SURFACE_FEATURE = register("cave_surface_feature", new CaveSurfaceFeature(CaveSurfaceFeatureConfig.CODEC));
        CAVE_PILLAR_FEATURE = register("cave_pillar_feature", new CavePillarFeature(CavePillarFeatureConfig.CODEC));
        LAVA_FIXER_FEATURE = register("lava_fixer_feature", new LavaFixerFeature(DefaultFeatureConfig.CODEC));
        NOT_STUPID_DELTA_FEATURE = register("not_stupid_delta_feature", new NotStupidDeltaFeature(DeltaFeatureConfig.CODEC));
        NETHERRACK_BASE_FEATURE = register("netherrack_base_feature", new NetherrackBaseFeature(NetherrackBaseFeatureConfig.CODEC));
    }

    public static void init() {
        new ProfundisFeatures();
    }

}
