package net.bandit.useful_pills.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlackOutPillItem extends Item {

    public BlackOutPillItem(Properties properties) {
        super(properties);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (!world.isClientSide && entity instanceof ServerPlayer player) {
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));

            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 1.0F, 1.0F);

            // Schedule the teleportation task
            player.getServer().execute(() -> {
                BlockPos bedLocation = player.getRespawnPosition();
                ResourceKey<Level> respawnDimension = player.getRespawnDimension();

                ServerLevel targetWorld = null;
                Vec3 respawnPos = null;

                if (bedLocation != null) {
                    targetWorld = player.getServer().getLevel(respawnDimension);
                    if (targetWorld != null) {
                        respawnPos = Player.findRespawnPositionAndUseSpawnBlock(targetWorld, bedLocation, player.getRespawnAngle(), player.isRespawnForced(), false).orElse(null);
                    }
                }
                if (respawnPos == null) {
                    targetWorld = player.getServer().getLevel(Level.OVERWORLD);
                    if (targetWorld != null) {
                        BlockPos worldSpawn = targetWorld.getSharedSpawnPos();
                        respawnPos = new Vec3(worldSpawn.getX() + 0.5, worldSpawn.getY() + 0.5, worldSpawn.getZ() + 0.5);
                    }
                }
                if (respawnPos != null && targetWorld != null) {
                    player.teleportTo(targetWorld, respawnPos.x, respawnPos.y, respawnPos.z, player.getYRot(), player.getXRot());
                }
            });
            if (!player.isCreative()) {
                stack.shrink(1);
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
        tooltip.add(Component.translatable("item.useful_pills.black_out_pill.tooltip")
                .withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.literal("A mysterious pill that comes with unexpected consequences...").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
    }
}