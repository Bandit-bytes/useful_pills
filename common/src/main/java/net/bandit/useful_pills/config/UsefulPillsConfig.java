package net.bandit.useful_pills.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UsefulPillsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE = "config/useful_pills_config.json";

    public static ConfigData configData = new ConfigData();

    public static void loadConfig() {
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                configData = GSON.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveConfig(); // Save the default config if the file doesn't exist
        }
    }

    public static void saveConfig() {
        File file = new File(CONFIG_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(configData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ConfigData {
        public String defaultDimension = "minecraft:the_end";

    }

    public static ResourceKey<Level> getDefaultDimension() {
        if (configData.defaultDimension.isEmpty()) {
            return null;
        }
        return ResourceKey.create(Registries.DIMENSION, new ResourceLocation(configData.defaultDimension));
    }
}
