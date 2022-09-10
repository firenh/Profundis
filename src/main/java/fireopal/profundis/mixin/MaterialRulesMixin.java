package fireopal.profundis.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fireopal.profundis.biomes.ProfundisBiomeKeys;
import fireopal.profundis.util.MaterialRulesMixinHelper;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.BiomeMaterialCondition;

@Mixin(MaterialRules.class)
public class MaterialRulesMixin {
    @Inject(method = "biome(Ljava/util/List;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$BiomeMaterialCondition;",
            at = @At("RETURN"), cancellable = true
    )
    private static void biome(List<RegistryKey<Biome>> biomes, CallbackInfoReturnable<BiomeMaterialCondition> cir) {
        BiomeMaterialCondition returnValue = cir.getReturnValue();
        List<RegistryKey<Biome>> returnedBiomes = ((BiomeMaterialConditionInvoker) (Object) returnValue).getField_36414();

        for (RegistryKey<Biome> k : returnedBiomes) {
            // Add cave biomes to the surface rules of STONY_SHORE
            if (k.getValue().equals(BiomeKeys.STONY_SHORE.getValue())) {
                cir.setReturnValue(BiomeMaterialConditionInvoker._init_(
                        MaterialRulesMixinHelper.combine(returnedBiomes, ProfundisBiomeKeys.BIOMES))
                );
            }
        }
    }
}