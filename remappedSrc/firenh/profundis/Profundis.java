package firenh.profundis;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// import firenh.profundis.biomes.ProfundisBiomes;
// import firenh.profundis.features.ProfundisConfiguredFeatures;
import firenh.profundis.features.ProfundisFeatures;
// import firenh.profundis.features.ProfundisPlacedFeatures;
import firenh.profundis.util.Config;
import firenh.profundis.util.FOModVersion;
// import firenh.profundis.util.firenhMultinoiseCommand;

public class Profundis implements ModInitializer {
	public static final String MODID = "profundis";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final FOModVersion VERSION = FOModVersion.fromString("1.7.0");

	private static Config config;

	public static Identifier id(String id) {
		return Identifier.of(MODID, id);
	}

	@Override
	public void onInitialize() {
		loadConfigFromFile();

		// firenhMultinoiseCommand.register();
		ProfundisFeatures.init();
		// ProfundisConfiguredFeatures.init();
		// ProfundisPlacedFeatures.init();
		// ProfundisBiomes.init();
	}

	public static Config getConfig() {
		if (Objects.isNull(config)) {
			loadConfigFromFile();
		}

		return config;
	}

	public static void log(String info) {
		LOGGER.info(info);
	}

	public static void loadConfigFromFile() {
        config = Config.init();
        if (config.logWhenLoaded) LOGGER.info("Loaded config for " + MODID);
    }
}
