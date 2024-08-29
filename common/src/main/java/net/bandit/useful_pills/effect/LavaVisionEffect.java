package net.bandit.useful_pills.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class LavaVisionEffect extends MobEffect {

    public LavaVisionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF6A00); // Orange color
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // The effect is active as long as duration > 0
    }

    @Override
    public String getDescriptionId() {
        return "effect.useful_pills.lava_vision";
    }
}
