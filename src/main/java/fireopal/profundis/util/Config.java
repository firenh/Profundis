package fireopal.profundis.util;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import fireopal.profundis.Profundis;
import fireopal.profundis.gen.ProfundisCaveBiomes;

public class Config {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Config Default Values

    public String CONFIG_VERSION_DO_NOT_TOUCH_PLS = Profundis.VERSION.toString();

    public boolean logWhenLoaded = true;
    public boolean generateFrozenCaves = true;
    public boolean generateMushroomCaves = true;
    public boolean generateMoltenCaves = true;
    public boolean generateAmethystCaves = true;
    public Debug debug = new Debug();

    // public Advanced advancedSettings = new Advanced();
    
    //~~~~~~~~

    public static class Advanced {
        public String comment = "The following values control where the biomes appear. Do not touch unless you're an advanced user!";

        public List<ProfundisCaveBiomes.CaveBiome> caveBiomes = new ArrayList<>();

        public Advanced() {
            caveBiomes.addAll(ProfundisCaveBiomes.defaultCaveBiomes);
        }
    }

    public static class Debug {
        // public boolean enableMultinoiseCommand = false;
    }

    public static Config init() {
        Config config = null;

        try {
            Path configPath = Paths.get("", "config", "profundis.json");

            if (Files.exists(configPath)) {
                config = gson.fromJson(
                    new FileReader(configPath.toFile()),
                    Config.class
                );

                if (!config.CONFIG_VERSION_DO_NOT_TOUCH_PLS.equals(Profundis.VERSION.toString())) {
                    config.CONFIG_VERSION_DO_NOT_TOUCH_PLS = Profundis.VERSION.toString();

                    BufferedWriter writer = new BufferedWriter(
                        new FileWriter(configPath.toFile())
                    );

                    writer.write(gson.toJson(config));
                    writer.close();
                }

            } else {
                config = new Config();
                Paths.get("", "config").toFile().mkdirs();

                BufferedWriter writer = new BufferedWriter(
                    new FileWriter(configPath.toFile())
                );

                writer.write(gson.toJson(config));
                writer.close();
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return config;
    }
}
