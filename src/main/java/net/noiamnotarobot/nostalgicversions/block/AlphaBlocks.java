package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import net.noiamnotarobot.nostalgicversions.item.AlphaItems;
import net.noiamnotarobot.nostalgicversions.sound.AlphaBlockSoundGroups;

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
    public static final Block BLOCK_GOLD = register(
            "block_gold",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.METAL)
                    .mapColor(MapColor.GOLD)
                    .instrument(Instrument.BELL)
                    .requiresTool()),
            true
    );
    public static final Block BLOCK_STEEL = register(
            "block_steel",
            new Block(AbstractBlock.Settings.create()
                    .strength(5.0F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.METAL)
                    .mapColor(MapColor.IRON_GRAY)
                    .instrument(Instrument.IRON_XYLOPHONE)
                    .requiresTool()),
            true
    );
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
    public static final AlphaFireBlock FIRE = (AlphaFireBlock) register(
            "fire",
            new AlphaFireBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .luminance((state) -> 15)
                    .sounds(AlphaBlockSoundGroups.WOOD)
                    .nonOpaque()
                    .ticksRandomly()
                    .mapColor(MapColor.BRIGHT_RED)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block MOB_SPAWNER = register(
            "mob_spawner",
            new AlphaMobSpawnerBlock(AbstractBlock.Settings.create()
                    .hardness(5.0F)
                    .sounds(AlphaBlockSoundGroups.METAL)
                    .nonOpaque()
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block STAIR_COMPACT_WOOD = Blocks.OAK_STAIRS;
    public static final Block CHEST = register(
            "chest",
            new AlphaChestBlock(AbstractBlock.Settings.create()
                    .hardness(2.5F)
                    .sounds(AlphaBlockSoundGroups.WOOD)
                    .mapColor(MapColor.OAK_TAN)
                    .instrument(Instrument.BASS)),
            true
    );
    public static final Block REDSTONE_WIRE = Blocks.REDSTONE_WIRE;
    public static final Block ORE_DIAMOND = register(
            "ore_diamond",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 5.0F)
                    .sounds(AlphaBlockSoundGroups.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()),
            true
    );
    public static final Block BLOCK_DIAMOND = register(
            "block_diamond",
            new Block(AbstractBlock.Settings.create()
                    .strength(5.0F, 10.0F)
                    .sounds(AlphaBlockSoundGroups.METAL)
                    .mapColor(MapColor.DIAMOND_BLUE)
                    .requiresTool()),
            true
    );
    public static final Block WORKBENCH = register(
            "workbench",
            new AlphaWorkbenchBlock(AbstractBlock.Settings.create()
                    .hardness(2.5F)
                    .sounds(AlphaBlockSoundGroups.WOOD)
                    .mapColor(MapColor.OAK_TAN)
                    .instrument(Instrument.BASS)),
            true
    );
    public static final Block CROPS = register(
            "crops",
            new AlphaCropsBlock(AbstractBlock.Settings.create()
                    .hardness(0.0F)
                    .sounds(AlphaBlockSoundGroups.GRASS)
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block TILLED_FIELD = register(
            "tilled_field",
            new AlphaFarmlandBlock(AbstractBlock.Settings.create()
                    .hardness(0.6F)
                    .sounds(AlphaBlockSoundGroups.GRAVEL)
                    .ticksRandomly()
                    .nonOpaque()
                    .mapColor(MapColor.DIRT_BROWN)
                    .blockVision(Blocks::always)
                    .suffocates(Blocks::always)),
            true
    );
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
    public static final Block SNOW = register(
            "snow",
            new AlphaSnowBlock(AbstractBlock.Settings.create()
                    .hardness(0.1F)
                    .sounds(AlphaBlockSoundGroups.CLOTH)
                    .noCollision()
                    .nonOpaque()
                    .requiresTool()
                    .mapColor(MapColor.WHITE)
                    .replaceable()
                    .ticksRandomly()
                    .blockVision(Blocks::never)
                    .pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block ICE = register(
            "ice",
            new AlphaIceBlock(AbstractBlock.Settings.create()
                    .hardness(0.5F)
                    .slipperiness(0.98F)
                    .sounds(AlphaBlockSoundGroups.GLASS)
                    .mapColor(MapColor.PALE_PURPLE)
                    .nonOpaque().allowsSpawning(Blocks::never)
                    .solidBlock(Blocks::never)
                    .ticksRandomly()),
            true
    );
    public static final Block BLOCK_SNOW = register(
            "block_snow",
            new AlphaSnowBlockBlock(AbstractBlock.Settings.create()
                    .hardness(0.2F)
                    .sounds(AlphaBlockSoundGroups.CLOTH)
                    .mapColor(MapColor.WHITE)
                    .requiresTool()
                    .ticksRandomly()),
            true
    );
    public static final Block CACTUS = Blocks.CACTUS;
    public static final Block BLOCK_CLAY = register(
            "block_clay",
            new Block(AbstractBlock.Settings.create()
                    .hardness(0.6F)
                    .sounds(AlphaBlockSoundGroups.GRAVEL)
                    .mapColor(MapColor.LIGHT_BLUE_GRAY)
                    .instrument(Instrument.FLUTE)),
            true
    );
    public static final Block REED = Blocks.SUGAR_CANE;
    public static final Block JUKEBOX = Blocks.JUKEBOX;
    public static final Block FENCE = Blocks.OAK_FENCE;

    public static void init() {
    }

    private static Block register(String name, Block block, boolean makeBlockItem) {
        if (makeBlockItem) AlphaItems.register(name, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, NostalgicVersions.Util.id(name), block);
    }
}