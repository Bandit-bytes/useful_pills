package net.bandit.useful_pills;

import net.bandit.useful_pills.effect.CreativeFlightEffect;
import net.bandit.useful_pills.effect.LavaVisionEffect;
import net.bandit.useful_pills.effect.StealthEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(UsefulPillsMod.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> LAVA_VISION = MOB_EFFECTS.register("lava_vision",
            LavaVisionEffect::new);
    public static final RegistrySupplier<MobEffect> STEALTH = MOB_EFFECTS.register("stealth_effect",
            StealthEffect::new);
    public static final RegistrySupplier<MobEffect> CREATIVE_FLIGHT = MOB_EFFECTS.register("creative_flight",
            CreativeFlightEffect::new);


    public static void register() {
        MOB_EFFECTS.register();
    }
}
