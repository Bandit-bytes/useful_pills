package net.bandit.useful_pills.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WaterPillItem extends Item {

    public WaterPillItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (!world.isClientSide) {
            MobEffectInstance currentEffect = entity.getEffect(MobEffects.WATER_BREATHING);
            int additionalDuration = 600;

            if (currentEffect != null) {
                int newDuration = currentEffect.getDuration() + additionalDuration;
                entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, newDuration));
            } else {
                entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, additionalDuration));
            }
        }
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundSource.PLAYERS, 1.0F, 1.0F);
        if (!world.isClientSide) {
            world.addParticle(ParticleTypes.BUBBLE, entity.getX(), entity.getY(), entity.getZ(), 0.5, 0.5, 0.5);
        }

        if (entity instanceof Player player) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }


    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return 1;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.useful_pills.water_pill.tooltip")
                .withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("item.useful_pills.water_pill.lore")
                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
}
