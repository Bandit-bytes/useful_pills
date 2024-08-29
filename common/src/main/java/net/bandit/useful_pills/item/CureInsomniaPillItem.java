package net.bandit.useful_pills.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CureInsomniaPillItem extends Item {

    public CureInsomniaPillItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (!world.isClientSide && entity instanceof Player player) {
            // Reset the player's sleep timer by accessing their sleep counter
            player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));

            // Play a sound to indicate the pill was used
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.HONEY_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);

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
        tooltip.add(Component.translatable("item.useful_pills.cure_insomnia_pill.tooltip")
                .withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("item.useful_pills.cure_insomnia_pill.lore").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
    }
}
