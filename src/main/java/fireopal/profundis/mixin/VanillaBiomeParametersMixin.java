package fireopal.profundis.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.datafixers.util.Pair;

import fireopal.profundis.Profundis;
import fireopal.profundis.gen.ProfundisCaveBiomes;
import fireopal.profundis.gen.ProfundisCaveBiomes.CaveBiome;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;

@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {
	@Inject(at = @At("RETURN"), method = "writeCaveBiomes")
	private void init(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo info) {
		for (CaveBiome b : ProfundisCaveBiomes.DEFAULT_CAVE_BIOMES) {
			parameters.accept(
				Pair.of(
					MultiNoiseUtil.createNoiseHypercube(
						b.temperature, 
						b.humidity, 
						b.continentalness, 
						b.erosion, 
						b.depth,
						b.weirdness, 
						b.offset
					), 
					b.biome
				)
			);
		}
	}
}
