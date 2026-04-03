package net.noiamnotarobot.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.noiamnotarobot.MinecraftAlpha;
import net.noiamnotarobot.item.AlphaItems;
import net.noiamnotarobot.sound.AlphaBlockSoundGroups;

public class AlphaBlocks {
    public static final Block STONE = register(
            "stone",
            new Block(AbstractBlock.Settings.create()
                    .strength(1.5F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true);
    public static final Block GRASS = Blocks.GRASS_BLOCK;
    public static final Block DIRT = register(
            "dirt",
            new Block(AbstractBlock.Settings.create()
                    .strength(0.5F)
                    .sounds(AlphaBlockSoundGroups.GRAVEL)
                    .mapColor(MapColor.DIRT_BROWN)),
            true);
    public static final Block COBBLESTONE = register(
            "cobblestone",
            new Block(AbstractBlock.Settings.create()
                    .strength(2.0F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true);
    public static final Block PLANKS = Blocks.OAK_PLANKS;
    public static final Block SAPLING = Blocks.OAK_SAPLING;
    public static final Block BEDROCK = Blocks.BEDROCK;
    public static final Block WATER_MOVING = Blocks.WATER;
    public static final Block WATER_STILL = Blocks.WATER;
    public static final Block LAVA_MOVING = Blocks.LAVA;
    public static final Block LAVA_STILL = Blocks.LAVA;
    public static final Block SAND = Blocks.SAND;
    public static final Block GRAVEL = Blocks.GRAVEL;
    public static final Block ORE_GOLD = Blocks.GOLD_ORE;
    public static final Block ORE_IRON = Blocks.IRON_ORE;
    public static final Block ORE_COAL = Blocks.COAL_ORE;
    public static final Block WOOD = Blocks.OAK_LOG;
    public static final Block LEAVES = Blocks.OAK_LEAVES;
    public static final Block SPONGE = Blocks.SPONGE;
    public static final Block GLASS = Blocks.GLASS;
    public static final Block CLOTH = Blocks.WHITE_WOOL;
    public static final Block PLANT_YELLOW = Blocks.DANDELION;
    public static final Block PLANT_RED = Blocks.POPPY;
    public static final Block MUSHROOM_BROWN = Blocks.BROWN_MUSHROOM;
    public static final Block MUSHROOM_RED = Blocks.RED_MUSHROOM;
    public static final Block BLOCK_GOLD = Blocks.GOLD_BLOCK;
    public static final Block BLOCK_STEEL = Blocks.IRON_BLOCK;
    public static final Block STAIR_DOUBLE = Blocks.SMOOTH_STONE_SLAB;
    public static final Block STAIR_SINGLE = Blocks.SMOOTH_STONE_SLAB;
    public static final Block BRICK = Blocks.BRICKS;
    public static final Block TNT = Blocks.TNT;
    public static final Block BOOKSHELF = Blocks.BOOKSHELF;
    public static final Block COBBLESTONE_MOSSY = Blocks.MOSSY_COBBLESTONE;
    public static final Block OBSIDIAN = Blocks.OBSIDIAN;
    public static final Block TORCH = Blocks.TORCH;
    public static final Block FIRE = Blocks.FIRE;
    public static final Block MOB_SPAWNER = Blocks.SPAWNER;
    public static final Block STAIR_COMPACT_WOOD = Blocks.OAK_STAIRS;
    public static final Block CHEST = Blocks.CHEST;
    public static final Block REDSTONE_WIRE = Blocks.REDSTONE_WIRE;
    public static final Block ORE_DIAMOND = Blocks.DIAMOND_ORE;
    public static final Block BLOCK_DIAMOND = Blocks.DIAMOND_BLOCK;
    public static final Block WORKBENCH = Blocks.CRAFTING_TABLE;
    public static final Block CROPS = Blocks.WHEAT;
    public static final Block TILLED_FIELD = Blocks.FARMLAND;
    public static final Block STONE_OVEN_IDLE = Blocks.FURNACE;
    public static final Block STONE_OVEN_ACTIVE = Blocks.FURNACE;
    public static final Block SIGN_STANDING = Blocks.OAK_SIGN;
    public static final Block DOOR_WOOD = Blocks.OAK_DOOR;
    public static final Block LADDER = Blocks.LADDER;
    public static final Block MINECART_TRACK = Blocks.RAIL;
    public static final Block STAIR_COMPACT_STONE = Blocks.STONE_STAIRS;
    public static final Block SIGN_WALL = Blocks.OAK_WALL_SIGN;
    public static final Block LEVER = Blocks.LEVER;
    public static final Block PRESSURE_PLATE_STONE = Blocks.STONE_PRESSURE_PLATE;
    public static final Block DOOR_STEEL = Blocks.IRON_DOOR;
    public static final Block PRESSURE_PLATE_WOOD = Blocks.OAK_PRESSURE_PLATE;
    public static final Block ORE_REDSTONE = Blocks.REDSTONE_ORE;
    public static final Block ORE_REDSTONE_GLOWING = Blocks.REDSTONE_ORE;
    public static final Block TORCH_REDSTONE_IDLE = Blocks.REDSTONE_TORCH;
    public static final Block TORCH_REDSTONE_ACTIVE = Blocks.REDSTONE_TORCH;
    public static final Block BUTTON = Blocks.STONE_BUTTON;
    public static final Block SNOW = Blocks.SNOW;
    public static final Block ICE = Blocks.ICE;
    public static final Block BLOCK_SNOW = Blocks.SNOW_BLOCK;
    public static final Block CACTUS = Blocks.CACTUS;
    public static final Block BLOCK_CLAY = Blocks.CLAY;
    public static final Block REED = Blocks.SUGAR_CANE;
    public static final Block JUKEBOX = Blocks.JUKEBOX;
    public static final Block FENCE = Blocks.OAK_FENCE;

    public static void init() {
    }

    private static Block register(String name, Block block, boolean makeBlockItem) {
        if (makeBlockItem) AlphaItems.register(name, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, MinecraftAlpha.id(name), block);
    }
}