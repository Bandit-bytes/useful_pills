package net.bandit.useful_pills;


import net.bandit.useful_pills.config.UsefulPillsConfig;

public final class UsefulPillsMod {
    public static final String MOD_ID = "useful_pills";

    public static void init() {
        ModItems.register();
        ModEffects.register();
        UsefulPillsConfig.loadConfig();
    }

    public static void initClient() {
    }
}
