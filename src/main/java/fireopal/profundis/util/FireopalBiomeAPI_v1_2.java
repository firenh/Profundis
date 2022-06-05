package fireopal.profundis.util;

import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

/* A simple API to help with creating new Biomes using BiomeCreator methods
 * Created by FireNH (https://github.com/firenh)
 * Currently in version v1.2
 */

public class FireopalBiomeAPI_v1_2 {

    //Don't let anyone instantantianizeationate this class
    private FireopalBiomeAPI_v1_2() {}

    /* An ObjectSwap stores two Class Data Types (Generic) 
     * and allows you to retrieve one using a boolean. Useful for storing two
     * versions of the same feature, say for instance when trying
     * to store both a Crimson and a Warped variant of basically the same
     * feature.
     */

    public static class ObjectSwap<O> {
        O trueObject;
        O falseObject;

        public ObjectSwap(O trueObject, O falseObject) {
            this.trueObject = trueObject;
            this.falseObject = falseObject;
        }

        //Using get(true) will retrieve the first object, and get(false) the second.
        public O get(boolean swap) {
            return (swap) ? this.trueObject : this.falseObject;
        }
    } 

    //SpawnSettings

    public class Spawns {

        //Adds in spawn entries from an array to a specific spawn group to a SpawnSettings.Builder variable
        public static void spawn(SpawnSettings.Builder spawnSettings, SpawnGroup spawnGroup, SpawnEntry... spawnEntries) {
            for(SpawnEntry s : spawnEntries) {
                spawnSettings.spawn(SpawnGroup.CREATURE, s);
            }
        }
    
        //Calls FireopalBiomeAPI_v1.Spawns.spawn for preset spawn groups.

        public static void ambients(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.AMBIENT, spawnEntries);
        }

        public static void creatures(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.CREATURE, spawnEntries);
        }

        public static void misc(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.MISC, spawnEntries);
        }

        public static void undergroundWaterCreatures(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.UNDERGROUND_WATER_CREATURE, spawnEntries);
        }
    
        public static void monsters(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.MONSTER, spawnEntries);
        }

        public static void waterAmbients(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.WATER_AMBIENT, spawnEntries);
        }

        public static void waterCreatures(SpawnSettings.Builder spawnSettings, SpawnEntry... spawnEntries) {
            spawn(spawnSettings, SpawnGroup.WATER_CREATURE, spawnEntries);
        }

        public static SpawnEntry spawn(EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
            return new SpawnSettings.SpawnEntry(type, weight, minGroupSize, maxGroupSize);
        }

        
        //Adds spawn costs from an array of SpawnCost objects defined below.

        public static void cost(SpawnSettings.Builder spawnSettings, SpawnCost... spawnCosts) {
            for(SpawnCost s : spawnCosts) {
                spawnSettings.spawnCost(s.getEntityType(), s.getMass(), s.getGravityLimit());
            }
        }

        /* A SpawnCost object stores the EntityType, mass, and gravityLimit for use
         * when using the cost() method above 
         */


        public static class SpawnCost {
            EntityType<?> entityType;
            double mass, gravityLimit;

            public SpawnCost(EntityType<?> entityType, double mass, double gravityLimit) {
                this.entityType = entityType;
                this.mass = mass;
                this.gravityLimit = gravityLimit;
            }

            public EntityType<?> getEntityType() {
                return this.entityType;
            }

            public double getMass() {
                return this.mass;
            }

            public double getGravityLimit() {
                return this.gravityLimit;
            }
        }

        //A method to generate the constructor, if you're into that sort of thing.

        public static SpawnCost spawnCost(EntityType<?> entityType, double mass, double gravityLimit) {
            return new SpawnCost(entityType, mass, gravityLimit);
        }
    }

    //GenerationSettings
    
    public class Generation {
        //Adds features to a specific Generation Step
        
        @SafeVarargs
        public static void features(GenerationSettings.Builder generationSettings, GenerationStep.Feature generationStep, RegistryEntry<PlacedFeature>... placedFeatures) {
            for (RegistryEntry<PlacedFeature> p : placedFeatures) {
                generationSettings.feature(generationStep, p);
            }
        }

        public static void features(GenerationSettings.Builder generationSettings, GenerationStep.Feature generationStep, List<RegistryEntry<PlacedFeature>> placedFeatures) {
            for (RegistryEntry<PlacedFeature> p : placedFeatures) {
                generationSettings.feature(generationStep, p);
            }
        }

        //Adds structure features

        // public static void structureFeatures(GenerationSettings.Builder generationSettings, ConfiguredStructureFeature<?, ?>... configuredStructureFeatures) {
        //     for (ConfiguredStructureFeature<?, ?> c : configuredStructureFeatures) {
        //         generationSettings.structureFeature(c);
        //     }
        // }

        //Adds carvers to a specific Generation Step

        @SafeVarargs
        public static void carvers(GenerationSettings.Builder generationSettings, GenerationStep.Carver generationStep, RegistryEntry<ConfiguredCarver<?>>... configuredCarver) {
            for (RegistryEntry<ConfiguredCarver<?>> c : configuredCarver) {
                generationSettings.carver(generationStep, c);
            }
        }

        //Adds features to set generation steps

        @SafeVarargs
        public static void lakeFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.LAKES, placedFeatures);
        }
        
        @SafeVarargs
        public static void localModificationFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.LOCAL_MODIFICATIONS, placedFeatures);
        }
        
        @SafeVarargs
        public static void rawGenerationFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.RAW_GENERATION, placedFeatures);
        }
        
        @SafeVarargs
        public static void strongholdFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.STRONGHOLDS, placedFeatures);
        }
        
        @SafeVarargs
        public static void surfaceStructureFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.SURFACE_STRUCTURES, placedFeatures);
        }
        
        @SafeVarargs
        public static void topLayerModificationFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.TOP_LAYER_MODIFICATION, placedFeatures);
        }
        
        @SafeVarargs
        public static void undergroundStructureFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.UNDERGROUND_DECORATION, placedFeatures);
        }
        
        @SafeVarargs
        public static void undergroundOresFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.UNDERGROUND_ORES, placedFeatures);
        }
        
        @SafeVarargs
        public static void undergroundDecorationFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.UNDERGROUND_STRUCTURES, placedFeatures);
        }
        
        @SafeVarargs
        public static void vegetalDecorationFeatures(GenerationSettings.Builder generationSettings, RegistryEntry<PlacedFeature>... placedFeatures) {
            features(generationSettings, GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures);
        }

        //Adds carvers to set generation steps
        
        @SafeVarargs
        public static void airCarver(GenerationSettings.Builder generationSettings, RegistryEntry<ConfiguredCarver<?>>... configuredCarver) {
            carvers(generationSettings, GenerationStep.Carver.AIR, configuredCarver);
        }
        
        @SafeVarargs
        public static void liquidCarver(GenerationSettings.Builder generationSettings, RegistryEntry<ConfiguredCarver<?>>... configuredCarver) {
            carvers(generationSettings, GenerationStep.Carver.LIQUID, configuredCarver);
        }
    }
    
    public class Effects {
        public static class Colors {
            int skyColor,
            fogColor,
            waterColor,
            waterFogColor,
            grassColor,
            foliageColor;

            public Colors(int skyColor, int fogColor, int waterColor, int waterFogColor, int grassColor, int foliageColor) {
                this.skyColor = skyColor;
                this.fogColor = fogColor;
                this.waterColor = waterColor;
                this.waterFogColor = waterFogColor;
                this.grassColor = grassColor;
                this.foliageColor = foliageColor;
            }

        }

        public static class BiomeSounds {
            SoundEvent loopSound;
            BiomeMoodSound moodSound;
            BiomeAdditionsSound additionsSound;
            MusicSound music;

            public BiomeSounds(SoundEvent loopSound, BiomeMoodSound moodSound, BiomeAdditionsSound additionsSound, MusicSound music) {
                this.loopSound = loopSound;
                this.moodSound = moodSound;
                this.additionsSound = additionsSound;
                this.music = music;
            }

            public SoundEvent getLoopSound() {
                return loopSound;
            }

            public BiomeMoodSound getMoodSound() {
                return moodSound;
            }

            public BiomeAdditionsSound getAdditionsSound() {
                return additionsSound;
            }

            public MusicSound getMusic() {
                return music;
            }
        }

        public static void addBiomeSounds(BiomeEffects.Builder biomeEffects, BiomeSounds biomeSounds) {
            biomeEffects
                .loopSound(biomeSounds.getLoopSound())
                .moodSound(biomeSounds.getMoodSound())
                .additionsSound(biomeSounds.getAdditionsSound())
                .music(biomeSounds.getMusic());
        }

        public static int getSkyColor(float temperature) {
            float f = temperature / 3.0F;
            f = MathHelper.clamp(f, -1.0F, 1.0F);
            return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
        }

        public static void setSkyColor(BiomeEffects.Builder biomeEffects, float temperature) {
            biomeEffects.skyColor(getSkyColor(temperature));
        }
    }

    public class Build {
        public static void properties(Biome.Builder biome, Precipitation precipitation, float temperature, float downfall) {
            biome
                .precipitation(precipitation)
                // .category(category)
                .temperature(temperature)
                .downfall(downfall);
        }

        public static Biome finalize(Biome.Builder biome, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, BiomeEffects.Builder biomeEffects) {
            return biome
                .effects(biomeEffects.build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
        }
    }
}
