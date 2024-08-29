package net.bandit.useful_pills.fabric.client;

import net.bandit.useful_pills.UsefulPillsMod;
import net.fabricmc.api.ClientModInitializer;

public final class UsefulPillModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UsefulPillsMod.initClient();
    }
}
