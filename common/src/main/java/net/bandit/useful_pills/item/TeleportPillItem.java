package net.bandit.useful_pills.item;

import net.bandit.useful_pills.config.UsefulPillsConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportPillItem extends Item {
    public static final String DIMENSION_KEY = "TeleportDimension";

    public TeleportPillItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            CompoundTag tag = stack.getOrCreateTag();

            if (!tag.contains(DIMENSION_KEY)) {
                ResourceKey<Level> defaultDimension = UsefulPillsConfig.getDefaultDimension();
                if (defaultDimension != null) {
                    tag.putString(DIMENSION_KEY, defaultDimension.location().toString());
                }
            }

            if (tag.contains(DIMENSION_KEY)) {
                ResourceKey<Level> dimension = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString(DIMENSION_KEY)));
                if (player instanceof ServerPlayer serverPlayer) {
                    teleportPlayer(serverPlayer, dimension);
                }
            } else {
                player.sendSystemMessage(Component.literal("This pill doesn't seem to be set to a valid dimension!"));
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    private void teleportPlayer(ServerPlayer player, ResourceKey<Level> destination) {
        if (player.level().dimension() != destination) {
            ServerLevel targetLevel = player.getServer().getLevel(destination);
            if (targetLevel != null) {
                Vec3 spawnPos = targetLevel.getSharedSpawnPos().getCenter();
                float spawnAngle = targetLevel.getSharedSpawnAngle();
                player.teleportTo(targetLevel, spawnPos.x, spawnPos.y, spawnPos.z, spawnAngle, 0.0F);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        ResourceKey<Level> defaultDimension = UsefulPillsConfig.getDefaultDimension();
        String dimension = (defaultDimension != null) ? defaultDimension.location().toString() : "No destination set";

        tooltip.add(Component.literal("Teleport to: " + dimension));

        tooltip.add(Component.literal("Right-click to teleport to the bound dimension."));
    }

}
