package net.bandit.useful_pills;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.useful_pills.item.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(UsefulPillsMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> WATER_PILL = ITEMS.register("water_pill",
            () -> new WaterPillItem(new Properties().stacksTo(32).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CURE_INSOMNIA_PILL = ITEMS.register("cure_insomnia_pill",
            () -> new CureInsomniaPillItem(new Item.Properties().stacksTo(4).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> STEALTH_PILL = ITEMS.register("stealth_pill",
            () -> new StealthPillItem(new Item.Properties().stacksTo(4).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> CREATIVE_FLIGHT_PILL = ITEMS.register("creative_flight_pill",
            () -> new CreativeFlightPillItem(new Item.Properties().stacksTo(4).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> FIRE_RESISTANCE_PILL = ITEMS.register("fire_resistance_pill",
            () -> new FireResistancePillItem(new Item.Properties().stacksTo(16).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> BLACK_OUT_PILL = ITEMS.register("black_out_pill",
            () -> new BlackOutPillItem(new Item.Properties().stacksTo(16).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).rarity(Rarity.UNCOMMON)));


    public static void register() {
        ITEMS.register();
    }
}
