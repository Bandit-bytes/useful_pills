package net.bandit.useful_pills.fabric;

import net.fabricmc.api.ModInitializer;

import net.bandit.useful_pills.UsefulPillsMod;

public final class UsefulPillModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        UsefulPillsMod.init();
    }
}
