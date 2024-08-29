package net.bandit.useful_pills.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.EnvExecutor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.bandit.useful_pills.UsefulPillsMod;

@Mod(UsefulPillsMod.MOD_ID)
public final class UsefulPillsModForge {
    public UsefulPillsModForge() {
        EventBuses.registerModEventBus(UsefulPillsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        UsefulPillsMod.init();
        EnvExecutor.runInEnv(Dist.CLIENT, () -> UsefulPillsMod::initClient);
    }
}
