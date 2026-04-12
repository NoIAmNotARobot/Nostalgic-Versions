package net.noiamnotarobot.nostalgicversions.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

public class AlphaBlockEntityTypes {
    public static final BlockEntityType<AlphaMobSpawnerBlockEntity> MOB_SPAWNER = register(
            "mob_spawner",
            FabricBlockEntityTypeBuilder.create(AlphaMobSpawnerBlockEntity::new, AlphaBlocks.MOB_SPAWNER).build()
    );
    public static final BlockEntityType<AlphaChestBlockEntity> CHEST = register(
            "chest",
            FabricBlockEntityTypeBuilder.create(AlphaChestBlockEntity::new, AlphaBlocks.CHEST).build()
    );

    public static void init() {}

    public static <T extends BlockEntityType<?>> T register(String name, T type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, NostalgicVersions.Util.id(name), type);
    }
}