package fireopal.profundis;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fireopal.profundis.biomes.ProfundisBiomes;
import fireopal.profundis.features.ProfundisConfiguredFeatures;
import fireopal.profundis.features.ProfundisFeatures;
import fireopal.profundis.features.ProfundisPlacedFeatures;
import fireopal.profundis.util.Config;
import fireopal.profundis.util.FOModVersion;
import fireopal.profundis.util.FireopalMultinoiseCommand;

public class Profundis implements ModInitializer {
	public static final String MODID = "profundis";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final FOModVersion VERSION = FOModVersion.fromString("1.1.0");

	private static Config config;

	public static Identifier id(String id) {
		return new Identifier(MODID, id);
	}

	@Override
	public void onInitialize() {
		loadConfigFromFile();

		FireopalMultinoiseCommand.register();
		ProfundisFeatures.init();
		ProfundisConfiguredFeatures.init();
		ProfundisPlacedFeatures.init();
		ProfundisBiomes.init();
	}

	public static Config getConfig() {
		return config;
	}

	public static void loadConfigFromFile() {
        config = Config.init();
        if (config.logWhenLoaded) LOGGER.info("Loaded config for " + MODID);
    }
}
