package net.bandit.useful_pills;


public final class UsefulPillsMod {
    public static final String MOD_ID = "useful_pills";

    public static void init() {
        ModItems.register();
        ModEffects.register();
    }

    public static void initClient() {
    }
}
