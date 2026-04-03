package net.noiamnotarobot.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.noiamnotarobot.MinecraftAlpha;

public class AlphaSounds {
    public static final SoundEvent SOUND_STONE_FOOTSTEP = register("step.stone");
    public static final SoundEvent SOUND_WOOD_FOOTSTEP = register("step.wood");
    public static final SoundEvent SOUND_GRAVEL_FOOTSTEP = register("step.gravel");
    public static final SoundEvent SOUND_GRASS_FOOTSTEP = register("step.grass");
    public static final SoundEvent SOUND_CLOTH_FOOTSTEP = register("step.cloth");
    public static final SoundEvent SOUND_SAND_FOOTSTEP = register("step.sand");

    public static final SoundEvent SOUND_GLASS_RANDOM = register("random.glass");

    public static void init() {}

    public static SoundEvent register(String name) {
        Identifier id = MinecraftAlpha.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}