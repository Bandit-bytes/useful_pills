package net.bandit.useful_pills.item;

import net.bandit.useful_pills.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireResistancePillItem extends Item {

    public FireResistancePillItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (!world.isClientSide && entity instanceof Player player) {
            // Check if the player already has Fire Resistance and refresh or stack the effect
            MobEffectInstance fireResEffect = player.getEffect(MobEffects.FIRE_RESISTANCE);
            if (fireResEffect != null) {
                // Refresh the duration or increase the amplifier level
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,
                        fireResEffect.getDuration() + 600, fireResEffect.getAmplifier()));
            } else {
                // Apply Fire Resistance for 5 minutes (6000 ticks)
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0));
            }

            // Check if the player already has the Lava Vision effect and refresh or stack the effect
            MobEffectInstance lavaVisionEffect = player.getEffect(ModEffects.LAVA_VISION.get());
            if (lavaVisionEffect != null) {
                // Refresh the duration or increase the amplifier level
                player.addEffect(new MobEffectInstance(ModEffects.LAVA_VISION.get(),
                        lavaVisionEffect.getDuration() + 600, lavaVisionEffect.getAmplifier()));
            } else {
                // Apply the Lava Vision effect for 5 minutes (6000 ticks)
                player.addEffect(new MobEffectInstance(ModEffects.LAVA_VISION.get(), 600, 0));
            }

            // Play a sound to indicate the pill was used
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.PLAYERS, 1.0F, 1.0F);

            if (!player.isCreative()) {
                stack.shrink(1); // Decrease item stack size by 1 after use
            }
        }

        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }


    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE; // No animation, instant consume
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 1; // Instant use
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.useful_pills.fire_resistance_pill.tooltip")
                .withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal("An ancient concoction from the depths of the Nether...").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
        tooltip.add(Component.literal("...rumored to protect even the bravest adventurers.").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
    }
}
