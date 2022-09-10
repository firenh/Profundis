package fireopal.profundis.mixin;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fireopal.profundis.gen.ProfundisCaveBiomes;
import fireopal.profundis.gen.ProfundisCaveBiomes.CaveBiome;
import fireopal.profundis.util.VanillaBiomeParametersHelper;

@Mixin(VanillaBiomeParameters.class)
public class MixinVanillaBiomeParameters {
    @Inject(at = @At("HEAD"), method = "writeCaveBiomes(Ljava/util/function/Consumer;)V")
    public void writeCaveBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo info) {
        for (CaveBiome c : ProfundisCaveBiomes.defaultCaveBiomes) {
            VanillaBiomeParametersHelper.writeCaveBiomeParameters(parameters,
                    c.temperature,
                    c.humidity,
                    c.continentalness,
                    c.erosion,
                    c.depth,
                    c.weirdness,
                    c.offset,
                    c.biome
            );
        }


    }
}
