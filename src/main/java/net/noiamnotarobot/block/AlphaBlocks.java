package net.noiamnotarobot.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.noiamnotarobot.NostalgicVersions;
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
    public static final AlphaGrassBlock GRASS = (AlphaGrassBlock) register(
            "grass",
            new AlphaGrassBlock(AbstractBlock.Settings.create()
                    .hardness(0.6F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .mapColor(MapColor.EMERALD_GREEN)
                    .ticksRandomly()),
            true
    );
    public static final Block DIRT = register(
            "dirt",
            new Block(AbstractBlock.Settings.create()
                    .hardness(0.5F)
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
    public static final Block PLANKS = register(
            "planks",
            new Block(AbstractBlock.Settings.create()
                    .strength(2.0F, 5.0F)
                    .sounds(AlphaBlockSoundGroups.WOOD)
                    .mapColor(MapColor.OAK_TAN)
                    .instrument(Instrument.BASS)
                    .burnable()),
            true
    );
    public static final Block SAPLING = register(
            "sapling",
            new AlphaSaplingBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block BEDROCK = register(
            "bedrock",
            new Block(AbstractBlock.Settings.create()
                    .strength(-1.0F, 6000000.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .dropsNothing()
                    .allowsSpawning(Blocks::never)),
            true
    );
    public static final Block WATER_MOVING = Blocks.WATER;
    public static final Block WATER_STILL = Blocks.WATER;
    public static final Block LAVA_MOVING = Blocks.LAVA;
    public static final Block LAVA_STILL = Blocks.LAVA;
    public static final Block SAND = register(
            "sand",
            new SandBlock(14406560, AbstractBlock.Settings.create()
                    .hardness(0.5F)
                    .sounds(AlphaBlockSoundGroups.SAND)
                    .mapColor(MapColor.PALE_YELLOW)
                    .instrument(Instrument.SNARE)),
            true
    );
    public static final Block GRAVEL = register(
            "gravel",
            new GravelBlock(AbstractBlock.Settings.create()
                    .hardness(0.6F)
                    .sounds(AlphaBlockSoundGroups.GRAVEL)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.SNARE)),
            true
    );
    public static final Block ORE_GOLD = register(
            "ore_gold",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 5.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block ORE_IRON = register(
            "ore_iron",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 5.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block ORE_COAL = register(
            "ore_coal",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 5.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block WOOD = register(
            "wood",
            new Block(AbstractBlock.Settings.create()
                    .hardness(2.0F)
                    .sounds(AlphaBlockSoundGroups.WOOD)
                    .mapColor(MapColor.OAK_TAN)
                    .instrument(Instrument.BASS)
                    .burnable()),
            true
    );
    public static final AlphaLeavesBlock LEAVES = (AlphaLeavesBlock) register(
            "leaves",
            new AlphaLeavesBlock(AbstractBlock.Settings.create()
                    .hardness(0.2F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .ticksRandomly()
                    .mapColor(MapColor.EMERALD_GREEN)
                    .nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)),
            true
    );
    public static final Block SPONGE = register(
            "sponge",
            new Block(AbstractBlock.Settings.create()
                    .hardness(0.6F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .mapColor(MapColor.YELLOW)),
            true
    );
    public static final Block GLASS = register(
            "glass",
            new Block(AbstractBlock.Settings.create()
                    .hardness(0.3F)
                    .sounds(AlphaBlockSoundGroups.GLASS)
                    .instrument(Instrument.HAT)
                    .nonOpaque()
                    .allowsSpawning(Blocks::never)
                    .solidBlock(Blocks::never)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)),
            true
    );
    public static final Block CLOTH = register(
            "cloth",
            new Block(AbstractBlock.Settings.create()
                    .hardness(0.8F)
                    .sounds(AlphaBlockSoundGroups.CLOTH)
                    .mapColor(MapColor.WHITE)
                    .instrument(Instrument.GUITAR)
                    .burnable()),
            true
    );
    public static final AlphaFlowerBlock PLANT_YELLOW = (AlphaFlowerBlock) register(
            "plant_yellow",
            new AlphaFlowerBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .noCollision()
                    .mapColor(MapColor.DARK_GREEN)
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final AlphaFlowerBlock PLANT_RED = (AlphaFlowerBlock) register(
            "plant_red",
            new AlphaFlowerBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .noCollision()
                    .mapColor(MapColor.DARK_GREEN)
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final AlphaFlowerBlock MUSHROOM_BROWN = (AlphaFlowerBlock) register(
            "mushroom_brown",
            new AlphaFlowerBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .luminance((state) -> 2)
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .noCollision()
                    .mapColor(MapColor.DARK_GREEN)
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final AlphaFlowerBlock MUSHROOM_RED = (AlphaFlowerBlock) register(
            "mushroom_red",
            new AlphaFlowerBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .noCollision()
                    .mapColor(MapColor.DARK_GREEN)
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block BLOCK_GOLD = Blocks.GOLD_BLOCK;
    public static final Block BLOCK_STEEL = Blocks.IRON_BLOCK;
    public static final Block STAIR_DOUBLE = Blocks.SMOOTH_STONE_SLAB;
    public static final Block STAIR_SINGLE = Blocks.SMOOTH_STONE_SLAB;
    public static final Block BRICK = register(
            "bricks",
            new Block(AbstractBlock.Settings.create()
                    .strength(2.0F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.RED)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block TNT = Blocks.TNT;
    public static final Block BOOKSHELF = register(
            "bookshelf",
            new Block(AbstractBlock.Settings.create()
                    .hardness(1.5F)
                    .sounds(AlphaBlockSoundGroups.WOOD)
                    .mapColor(MapColor.OAK_TAN)
                    .instrument(Instrument.BASS)
                    .burnable()),
            true
    );
    public static final Block COBBLESTONE_MOSSY = register(
            "cobblestone_mossy",
            new Block(AbstractBlock.Settings.create()
                    .strength(2.0F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block OBSIDIAN = register(
            "obsidian",
            new Block(AbstractBlock.Settings.create()
                    .strength(10.0F, 2000.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.BLACK)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block TORCH = Blocks.TORCH;
    public static final Block FIRE = Blocks.FIRE; //make type be AlphaBlockFire
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
        return Registry.register(Registries.BLOCK, NostalgicVersions.id(name), block);
    }
}