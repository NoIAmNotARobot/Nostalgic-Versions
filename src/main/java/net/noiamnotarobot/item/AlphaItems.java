package net.noiamnotarobot.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.noiamnotarobot.MinecraftAlpha;

public class AlphaItems {
    public static void init() {}

    public static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, MinecraftAlpha.id(name), item);
    }
}
