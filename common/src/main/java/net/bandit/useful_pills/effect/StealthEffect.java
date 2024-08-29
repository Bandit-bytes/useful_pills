package net.bandit.useful_pills.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class StealthEffect extends MobEffect {

    public StealthEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x555555); // Grey color for stealth
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Suppress vibrations while this effect is active
        // No special tick behavior needed unless you want to add something
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
