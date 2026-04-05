package net.noiamnotarobot;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.noiamnotarobot.block.AlphaBlocks;
import net.noiamnotarobot.item.AlphaItems;

import java.util.concurrent.CompletableFuture;

public class NostalgicVersionsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(NostalgicVersionsModelProvider::new);
        pack.addProvider(NostalgicVersionsBlockLootTableProvider::new);
        pack.addProvider(NostalgicVersionsEnglishLangProvider::new);
        pack.addProvider(NostalgicVersionsBlockTagProvider::new);
	}

    private static class NostalgicVersionsModelProvider extends FabricModelProvider {
        public NostalgicVersionsModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator generator) {
            generator.registerSimpleCubeAll(AlphaBlocks.STONE);
            generator.registerParentedItemModel(AlphaBlocks.GRASS, Models.CUBE_BOTTOM_TOP.upload(AlphaBlocks.GRASS,
                    TextureMap.sideAndTop(AlphaBlocks.GRASS).put(TextureKey.BOTTOM, NostalgicVersions.id("block/dirt")),
                    generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.GRASS);
            generator.registerSimpleCubeAll(AlphaBlocks.DIRT);
            generator.registerSimpleCubeAll(AlphaBlocks.COBBLESTONE);
            generator.registerSimpleCubeAll(AlphaBlocks.PLANKS);
            generator.registerTintableCross(AlphaBlocks.SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
            generator.registerSimpleCubeAll(AlphaBlocks.BEDROCK);
            generator.registerSimpleCubeAll(AlphaBlocks.SAND);
            generator.registerSimpleCubeAll(AlphaBlocks.GRAVEL);
            generator.registerSimpleCubeAll(AlphaBlocks.ORE_GOLD);
            generator.registerSimpleCubeAll(AlphaBlocks.ORE_IRON);
            generator.registerSimpleCubeAll(AlphaBlocks.ORE_COAL);
            generator.registerParentedItemModel(AlphaBlocks.WOOD, Models.CUBE_COLUMN.upload(AlphaBlocks.WOOD,
                    TextureMap.sideEnd(AlphaBlocks.WOOD), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.WOOD);
            generator.registerSimpleCubeAll(AlphaBlocks.LEAVES);
            generator.registerSimpleCubeAll(AlphaBlocks.SPONGE);
            generator.registerSimpleCubeAll(AlphaBlocks.GLASS);
            generator.registerSimpleCubeAll(AlphaBlocks.CLOTH);
            generator.registerTintableCross(AlphaBlocks.PLANT_YELLOW, BlockStateModelGenerator.TintType.NOT_TINTED);
            generator.registerTintableCross(AlphaBlocks.PLANT_RED, BlockStateModelGenerator.TintType.NOT_TINTED);
            generator.registerTintableCross(AlphaBlocks.MUSHROOM_BROWN, BlockStateModelGenerator.TintType.NOT_TINTED);
            generator.registerTintableCross(AlphaBlocks.MUSHROOM_RED, BlockStateModelGenerator.TintType.NOT_TINTED);
            generator.registerSimpleCubeAll(AlphaBlocks.BRICK);
            generator.registerParentedItemModel(AlphaBlocks.BOOKSHELF, Models.CUBE_COLUMN.upload(AlphaBlocks.BOOKSHELF,
                    new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.BOOKSHELF, "_side"))
                            .put(TextureKey.END, NostalgicVersions.id("block/planks")), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.BOOKSHELF);
            generator.registerSimpleCubeAll(AlphaBlocks.COBBLESTONE_MOSSY);
            generator.registerSimpleCubeAll(AlphaBlocks.OBSIDIAN);
        }

        @Override
        public void generateItemModels(ItemModelGenerator generator) {
            generator.register(AlphaItems.SHOVEL, Models.HANDHELD);
            generator.register(AlphaItems.PICKAXE_STEEL, Models.HANDHELD);
            generator.register(AlphaItems.AXE_STEEL, Models.HANDHELD);
            generator.register(AlphaItems.STRIKER, Models.GENERATED);
            generator.register(AlphaItems.APPLE_RED, Models.GENERATED);
            generator.register(AlphaItems.BOW, Models.GENERATED);
            generator.register(AlphaItems.ARROW, Models.GENERATED);
            generator.register(AlphaItems.COAL, Models.GENERATED);
            generator.register(AlphaItems.DIAMOND, Models.GENERATED);
            generator.register(AlphaItems.INGOT_IRON, Models.GENERATED);
            generator.register(AlphaItems.INGOT_GOLD, Models.GENERATED);
            generator.register(AlphaItems.SWORD_STEEL, Models.HANDHELD);
            generator.register(AlphaItems.SWORD_WOOD, Models.HANDHELD);
            generator.register(AlphaItems.SHOVEL_WOOD, Models.HANDHELD);
            generator.register(AlphaItems.PICKAXE_WOOD, Models.HANDHELD);
            generator.register(AlphaItems.AXE_WOOD, Models.HANDHELD);
            generator.register(AlphaItems.SWORD_STONE, Models.HANDHELD);
            generator.register(AlphaItems.SHOVEL_STONE, Models.HANDHELD);
            generator.register(AlphaItems.PICKAXE_STONE, Models.HANDHELD);
            generator.register(AlphaItems.AXE_STONE, Models.HANDHELD);
            generator.register(AlphaItems.SWORD_DIAMOND, Models.HANDHELD);
            generator.register(AlphaItems.SHOVEL_DIAMOND, Models.HANDHELD);
            generator.register(AlphaItems.PICKAXE_DIAMOND, Models.HANDHELD);
            generator.register(AlphaItems.AXE_DIAMOND, Models.HANDHELD);
            generator.register(AlphaItems.STICK, Models.HANDHELD);
            generator.register(AlphaItems.BOWL_EMPTY, Models.GENERATED);
            generator.register(AlphaItems.BOWL_SOUP, Models.GENERATED);
            generator.register(AlphaItems.SWORD_GOLD, Models.HANDHELD);
            generator.register(AlphaItems.SHOVEL_GOLD, Models.HANDHELD);
            generator.register(AlphaItems.PICKAXE_GOLD, Models.HANDHELD);
            generator.register(AlphaItems.AXE_GOLD, Models.HANDHELD);
            generator.register(AlphaItems.SILK, Models.GENERATED);
            generator.register(AlphaItems.FEATHER, Models.GENERATED);
            generator.register(AlphaItems.GUNPOWDER, Models.GENERATED);
            generator.register(AlphaItems.HOE_WOOD, Models.HANDHELD);
            generator.register(AlphaItems.HOE_STONE, Models.HANDHELD);
            generator.register(AlphaItems.HOE_STEEL, Models.HANDHELD);
            generator.register(AlphaItems.HOE_DIAMOND, Models.HANDHELD);
            generator.register(AlphaItems.HOE_GOLD, Models.HANDHELD);
            generator.register(AlphaItems.SEEDS, Models.GENERATED);
            generator.register(AlphaItems.WHEAT, Models.GENERATED);
            generator.register(AlphaItems.BREAD, Models.GENERATED);
            generator.register(AlphaItems.HELMET_LEATHER, Models.GENERATED);
            generator.register(AlphaItems.PLATE_LEATHER, Models.GENERATED);
            generator.register(AlphaItems.LEGS_LEATHER, Models.GENERATED);
            generator.register(AlphaItems.BOOTS_LEATHER, Models.GENERATED);
            generator.register(AlphaItems.HELMET_CHAIN, Models.GENERATED);
            generator.register(AlphaItems.PLATE_CHAIN, Models.GENERATED);
            generator.register(AlphaItems.LEGS_CHAIN, Models.GENERATED);
            generator.register(AlphaItems.BOOTS_CHAIN, Models.GENERATED);
            generator.register(AlphaItems.HELMET_STEEL, Models.GENERATED);
            generator.register(AlphaItems.PLATE_STEEL, Models.GENERATED);
            generator.register(AlphaItems.LEGS_STEEL, Models.GENERATED);
            generator.register(AlphaItems.BOOTS_STEEL, Models.GENERATED);
            generator.register(AlphaItems.HELMET_DIAMOND, Models.GENERATED);
            generator.register(AlphaItems.PLATE_DIAMOND, Models.GENERATED);
            generator.register(AlphaItems.LEGS_DIAMOND, Models.GENERATED);
            generator.register(AlphaItems.BOOTS_DIAMOND, Models.GENERATED);
            generator.register(AlphaItems.HELMET_GOLD, Models.GENERATED);
            generator.register(AlphaItems.PLATE_GOLD, Models.GENERATED);
            generator.register(AlphaItems.LEGS_GOLD, Models.GENERATED);
            generator.register(AlphaItems.BOOTS_GOLD, Models.GENERATED);
            generator.register(AlphaItems.FLINT, Models.GENERATED);
            generator.register(AlphaItems.PORK_RAW, Models.GENERATED);
            generator.register(AlphaItems.PORK_COOKED, Models.GENERATED);
            generator.register(AlphaItems.PAINTING, Models.GENERATED);
            generator.register(AlphaItems.APPLE_GOLD, Models.GENERATED);
            generator.register(AlphaItems.SIGN, Models.GENERATED);
            generator.register(AlphaItems.DOOR_WOOD, Models.GENERATED);
            generator.register(AlphaItems.BUCKET_EMPTY, Models.GENERATED);
            generator.register(AlphaItems.BUCKET_WATER, Models.GENERATED);
            generator.register(AlphaItems.BUCKET_LAVA, Models.GENERATED);
            generator.register(AlphaItems.MINECART_EMPTY, Models.GENERATED);
            generator.register(AlphaItems.SADDLE, Models.GENERATED);
            generator.register(AlphaItems.DOOR_STEEL, Models.GENERATED);
            generator.register(AlphaItems.REDSTONE, Models.GENERATED);
            generator.register(AlphaItems.SNOWBALL, Models.GENERATED);
            generator.register(AlphaItems.BOAT, Models.GENERATED);
            generator.register(AlphaItems.LEATHER, Models.GENERATED);
            generator.register(AlphaItems.BUCKET_MILK, Models.GENERATED);
            generator.register(AlphaItems.BRICK, Models.GENERATED);
            generator.register(AlphaItems.CLAY, Models.GENERATED);
            generator.register(AlphaItems.REED, Models.GENERATED);
            generator.register(AlphaItems.PAPER, Models.GENERATED);
            generator.register(AlphaItems.BOOK, Models.GENERATED);
            generator.register(AlphaItems.SLIME_BALL, Models.GENERATED);
            generator.register(AlphaItems.MINECART_BOX, Models.GENERATED);
            generator.register(AlphaItems.MINECART_ENGINE, Models.GENERATED);
            generator.register(AlphaItems.EGG, Models.GENERATED);
            generator.register(AlphaItems.COMPASS, Models.GENERATED);
            generator.register(AlphaItems.FISHING_ROD, Models.GENERATED);
            generator.register(AlphaItems.RECORD_13, Models.GENERATED);
            generator.register(AlphaItems.RECORD_CAT, Models.GENERATED);
        }
    }

    private static class NostalgicVersionsBlockLootTableProvider extends FabricBlockLootTableProvider {
        protected NostalgicVersionsBlockLootTableProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            addDrop(AlphaBlocks.STONE, AlphaBlocks.COBBLESTONE);
            addDrop(AlphaBlocks.GRASS, AlphaBlocks.DIRT);
            addDrop(AlphaBlocks.DIRT);
            addDrop(AlphaBlocks.COBBLESTONE);
            addDrop(AlphaBlocks.PLANKS);
            addDrop(AlphaBlocks.SAPLING);
            addDrop(AlphaBlocks.SAND);
            addDrop(AlphaBlocks.GRAVEL, LootTable.builder().pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(AlphaBlocks.GRAVEL).weight(9))
                    .with(ItemEntry.builder(AlphaItems.FLINT).weight(1))
            ));
            addDrop(AlphaBlocks.ORE_GOLD);
            addDrop(AlphaBlocks.ORE_IRON);
            addDrop(AlphaBlocks.ORE_COAL, AlphaItems.COAL);
            addDrop(AlphaBlocks.WOOD);
            addDrop(AlphaBlocks.LEAVES, LootTable.builder().pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(AlphaBlocks.SAPLING))
                    .with(EmptyEntry.builder().weight(19))
            ));
            addDrop(AlphaBlocks.SAPLING);
            addDrop(AlphaBlocks.SPONGE);
            addDrop(AlphaBlocks.CLOTH);
            addDrop(AlphaBlocks.PLANT_YELLOW);
            addDrop(AlphaBlocks.PLANT_RED);
            addDrop(AlphaBlocks.MUSHROOM_BROWN);
            addDrop(AlphaBlocks.MUSHROOM_RED);
            addDrop(AlphaBlocks.BLOCK_GOLD);
            addDrop(AlphaBlocks.BLOCK_STEEL);
            addDrop(AlphaBlocks.STAIR_DOUBLE, AlphaBlocks.STAIR_SINGLE);
            addDrop(AlphaBlocks.STAIR_SINGLE);
            addDrop(AlphaBlocks.BRICK);
            addDrop(AlphaBlocks.COBBLESTONE_MOSSY);
            addDrop(AlphaBlocks.OBSIDIAN);
            addDrop(AlphaBlocks.TORCH);
            addDrop(AlphaBlocks.STAIR_COMPACT_WOOD, AlphaBlocks.PLANKS);
            addDrop(AlphaBlocks.CHEST);
            addDrop(AlphaBlocks.REDSTONE_WIRE, AlphaItems.REDSTONE);
            addDrop(AlphaBlocks.ORE_DIAMOND, AlphaItems.DIAMOND);
            addDrop(AlphaBlocks.BLOCK_DIAMOND);
            addDrop(AlphaBlocks.WORKBENCH);
            addDrop(AlphaBlocks.CROPS, LootTable.builder().pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(AlphaItems.WHEAT)
                            .conditionally(BlockStatePropertyLootCondition.builder(AlphaBlocks.CROPS)
                                    .properties(StatePredicate.Builder.create()
                                            .exactMatch(AlphaBlocks.CROPS.getStateManager().getProperty("age"), "7"))))
            ));
            addDrop(AlphaBlocks.TILLED_FIELD, AlphaBlocks.DIRT);
            addDrop(AlphaBlocks.STONE_OVEN_IDLE);
            addDrop(AlphaBlocks.STONE_OVEN_ACTIVE, AlphaBlocks.STONE_OVEN_IDLE);
            addDrop(AlphaBlocks.SIGN_STANDING, AlphaItems.SIGN);
            addDrop(AlphaBlocks.DOOR_WOOD, AlphaItems.DOOR_WOOD);
            addDrop(AlphaBlocks.LADDER);
            addDrop(AlphaBlocks.MINECART_TRACK);
            addDrop(AlphaBlocks.STAIR_COMPACT_STONE, AlphaBlocks.COBBLESTONE);
            addDrop(AlphaBlocks.SIGN_WALL, AlphaItems.SIGN);
            addDrop(AlphaBlocks.LEVER);
            addDrop(AlphaBlocks.PRESSURE_PLATE_STONE);
            addDrop(AlphaBlocks.DOOR_STEEL, AlphaItems.DOOR_STEEL);
            addDrop(AlphaBlocks.PRESSURE_PLATE_WOOD);
            addDrop(AlphaBlocks.ORE_REDSTONE, LootTable.builder().pool(LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(4, 5))
                    .with(ItemEntry.builder(AlphaItems.REDSTONE))
            ));
            addDrop(AlphaBlocks.ORE_REDSTONE_GLOWING, LootTable.builder().pool(LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(4, 5))
                    .with(ItemEntry.builder(AlphaItems.REDSTONE))
            ));
            addDrop(AlphaBlocks.TORCH_REDSTONE_IDLE, AlphaBlocks.TORCH_REDSTONE_ACTIVE);
            addDrop(AlphaBlocks.TORCH_REDSTONE_ACTIVE);
            addDrop(AlphaBlocks.BUTTON);
            addDrop(AlphaBlocks.BLOCK_SNOW, LootTable.builder().pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(4))
                    .with(ItemEntry.builder(AlphaItems.SNOWBALL))
            ));
            addDrop(AlphaBlocks.CACTUS);
            addDrop(AlphaBlocks.BLOCK_CLAY, LootTable.builder().pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(4))
                    .with(ItemEntry.builder(AlphaItems.CLAY))
            ));
            addDrop(AlphaBlocks.REED, AlphaItems.REED);
            addDrop(AlphaBlocks.JUKEBOX);
            addDrop(AlphaBlocks.FENCE);
        }
    }

    private static class NostalgicVersionsEnglishLangProvider extends FabricLanguageProvider {
        protected NostalgicVersionsEnglishLangProvider(FabricDataOutput dataOutput) {
            super(dataOutput, "en_us");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add("itemGroup.minecraft-alpha.alpha_items", "Minecraft Alpha 1.1.2_01");

            /*translationBuilder.add(AlphaBlocks.STONE, "Stone");
            translationBuilder.add(AlphaBlocks.GRASS, "Grass");
            translationBuilder.add(AlphaBlocks.DIRT, "Dirt");
            translationBuilder.add(AlphaBlocks.COBBLESTONE, "Cobblestone");
            translationBuilder.add(AlphaBlocks.PLANKS, "Planks");
            translationBuilder.add(AlphaBlocks.SAPLING, "Sapling");
            translationBuilder.add(AlphaBlocks.BEDROCK, "Bedrock");
            translationBuilder.add(AlphaBlocks.WATER_MOVING, "Water");
            translationBuilder.add(AlphaBlocks.WATER_STILL, "Water");
            translationBuilder.add(AlphaBlocks.LAVA_MOVING, "Lava");
            translationBuilder.add(AlphaBlocks.LAVA_STILL, "Lava");
            translationBuilder.add(AlphaBlocks.SAND, "Sand");
            translationBuilder.add(AlphaBlocks.GRAVEL, "Gravel");
            translationBuilder.add(AlphaBlocks.ORE_GOLD, "Gold Ore");
            translationBuilder.add(AlphaBlocks.ORE_IRON, "Iron Ore");
            translationBuilder.add(AlphaBlocks.ORE_COAL, "Coal Ore");
            translationBuilder.add(AlphaBlocks.WOOD, "Wood");
            translationBuilder.add(AlphaBlocks.LEAVES, "Leaves");
            translationBuilder.add(AlphaBlocks.SPONGE, "Sponge");
            translationBuilder.add(AlphaBlocks.GLASS, "Glass");
            translationBuilder.add(AlphaBlocks.CLOTH, "Cloth");
            translationBuilder.add(AlphaBlocks.PLANT_YELLOW, "Yellow Plant");
            translationBuilder.add(AlphaBlocks.PLANT_RED, "Red Plant");
            translationBuilder.add(AlphaBlocks.MUSHROOM_BROWN, "Brown Mushroom");
            translationBuilder.add(AlphaBlocks.MUSHROOM_RED, "Red Mushroom");
            translationBuilder.add(AlphaBlocks.BLOCK_GOLD, "Gold Block");
            translationBuilder.add(AlphaBlocks.BLOCK_STEEL, "Steel Block");
            translationBuilder.add(AlphaBlocks.STAIR_DOUBLE, "Double Stair");
            translationBuilder.add(AlphaBlocks.STAIR_SINGLE, "Single Stair");
            translationBuilder.add(AlphaBlocks.BRICK, "Brick");
            translationBuilder.add(AlphaBlocks.TNT, "TNT");
            translationBuilder.add(AlphaBlocks.BOOKSHELF, "Bookshelf");
            translationBuilder.add(AlphaBlocks.COBBLESTONE_MOSSY, "Mossy Cobblestone");
            translationBuilder.add(AlphaBlocks.OBSIDIAN, "Obsidian");
            translationBuilder.add(AlphaBlocks.TORCH, "Torch");
            translationBuilder.add(AlphaBlocks.FIRE, "Fire");
            translationBuilder.add(AlphaBlocks.MOB_SPAWNER, "Mob Spawner");
            translationBuilder.add(AlphaBlocks.STAIR_COMPACT_WOOD, "Compact Wood Stair");
            translationBuilder.add(AlphaBlocks.CHEST, "Chest");
            translationBuilder.add(AlphaBlocks.REDSTONE_WIRE, "Redstone Wire");
            translationBuilder.add(AlphaBlocks.ORE_DIAMOND, "Diamond Ore");
            translationBuilder.add(AlphaBlocks.BLOCK_DIAMOND, "Diamond Block");
            translationBuilder.add(AlphaBlocks.WORKBENCH, "Workbench");
            translationBuilder.add(AlphaBlocks.CROPS, "Crops");
            translationBuilder.add(AlphaBlocks.TILLED_FIELD, "Tilled Field");
            translationBuilder.add(AlphaBlocks.STONE_OVEN_IDLE, "Stone Oven");
            translationBuilder.add(AlphaBlocks.STONE_OVEN_ACTIVE, "Stone Oven");
            translationBuilder.add(AlphaBlocks.SIGN_STANDING, "Sign");
            translationBuilder.add(AlphaBlocks.DOOR_WOOD, "Wood Door");
            translationBuilder.add(AlphaBlocks.LADDER, "Ladder");
            translationBuilder.add(AlphaBlocks.MINECART_TRACK, "Minecart Track");
            translationBuilder.add(AlphaBlocks.STAIR_COMPACT_STONE, "Compact Stone Stair");
            translationBuilder.add(AlphaBlocks.SIGN_WALL, "Sign");
            translationBuilder.add(AlphaBlocks.LEVER, "Lever");
            translationBuilder.add(AlphaBlocks.PRESSURE_PLATE_STONE, "Stone Pressure Plate");
            translationBuilder.add(AlphaBlocks.DOOR_STEEL, "Steel Door");
            translationBuilder.add(AlphaBlocks.PRESSURE_PLATE_WOOD, "Wood Pressure Plate");
            translationBuilder.add(AlphaBlocks.ORE_REDSTONE, "Redstone Ore");
            translationBuilder.add(AlphaBlocks.ORE_REDSTONE_GLOWING, "Redstone Ore");
            translationBuilder.add(AlphaBlocks.TORCH_REDSTONE_IDLE, "Redstone Torch");
            translationBuilder.add(AlphaBlocks.TORCH_REDSTONE_ACTIVE, "Redstone Torch");
            translationBuilder.add(AlphaBlocks.BUTTON, "Button");
            translationBuilder.add(AlphaBlocks.SNOW, "Snow");
            translationBuilder.add(AlphaBlocks.ICE, "Ice");
            translationBuilder.add(AlphaBlocks.BLOCK_SNOW, "Snow");
            translationBuilder.add(AlphaBlocks.CACTUS, "Cactus");
            translationBuilder.add(AlphaBlocks.BLOCK_CLAY, "Clay");
            translationBuilder.add(AlphaBlocks.REED, "Reed");
            translationBuilder.add(AlphaBlocks.JUKEBOX, "Jukebox");
            translationBuilder.add(AlphaBlocks.FENCE, "Fence");

            translationBuilder.add(AlphaItems.SHOVEL, "Steel Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_STEEL, "Steel Pickaxe");
            translationBuilder.add(AlphaItems.AXE_STEEL, "Steel Axe");
            translationBuilder.add(AlphaItems.STRIKER, "Striker");
            translationBuilder.add(AlphaItems.APPLE_RED, "Red Apple");
            translationBuilder.add(AlphaItems.BOW, "Bow");
            translationBuilder.add(AlphaItems.ARROW, "Arrow");
            translationBuilder.add(AlphaItems.COAL, "Coal");
            translationBuilder.add(AlphaItems.DIAMOND, "Diamond");
            translationBuilder.add(AlphaItems.INGOT_IRON, "Iron Ingot");
            translationBuilder.add(AlphaItems.INGOT_GOLD, "Gold Ingot");
            translationBuilder.add(AlphaItems.SWORD_STEEL, "Steel Sword");
            translationBuilder.add(AlphaItems.SWORD_WOOD, "Wood Sword");
            translationBuilder.add(AlphaItems.SHOVEL_WOOD, "Wood Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_WOOD, "Wood Pickaxe");
            translationBuilder.add(AlphaItems.AXE_WOOD, "Wood Axe");
            translationBuilder.add(AlphaItems.SWORD_STONE, "Stone Sword");
            translationBuilder.add(AlphaItems.SHOVEL_STONE, "Stone Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_STONE, "Stone Pickaxe");
            translationBuilder.add(AlphaItems.AXE_STONE, "Stone Axe");
            translationBuilder.add(AlphaItems.SWORD_DIAMOND, "Diamond Sword");
            translationBuilder.add(AlphaItems.SHOVEL_DIAMOND, "Diamond Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_DIAMOND, "Diamond Pickaxe");
            translationBuilder.add(AlphaItems.AXE_DIAMOND, "Diamond Axe");
            translationBuilder.add(AlphaItems.STICK, "Stick");
            translationBuilder.add(AlphaItems.BOWL_EMPTY, "Empty Bowl");
            translationBuilder.add(AlphaItems.BOWL_SOUP, "Bowl of Soup");
            translationBuilder.add(AlphaItems.SWORD_GOLD, "Gold Sword");
            translationBuilder.add(AlphaItems.SHOVEL_GOLD, "Gold Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_GOLD, "Gold Pickaxe");
            translationBuilder.add(AlphaItems.AXE_GOLD, "Gold Axe");
            translationBuilder.add(AlphaItems.SILK, "Silk");
            translationBuilder.add(AlphaItems.FEATHER, "Feather");
            translationBuilder.add(AlphaItems.GUNPOWDER, "Gunpowder");
            translationBuilder.add(AlphaItems.HOE_WOOD, "Wood Hoe");
            translationBuilder.add(AlphaItems.HOE_STONE, "Stone Hoe");
            translationBuilder.add(AlphaItems.HOE_STEEL, "Steel Hoe");
            translationBuilder.add(AlphaItems.HOE_DIAMOND, "Diamond Hoe");
            translationBuilder.add(AlphaItems.HOE_GOLD, "Gold Hoe");
            translationBuilder.add(AlphaItems.SEEDS, "Seeds");
            translationBuilder.add(AlphaItems.WHEAT, "Wheat");
            translationBuilder.add(AlphaItems.BREAD, "Bread");
            translationBuilder.add(AlphaItems.HELMET_LEATHER, "Leather Helmet");
            translationBuilder.add(AlphaItems.PLATE_LEATHER, "Leather Plate");
            translationBuilder.add(AlphaItems.LEGS_LEATHER, "Leather Legs");
            translationBuilder.add(AlphaItems.BOOTS_LEATHER, "Leather Boots");
            translationBuilder.add(AlphaItems.HELMET_CHAIN, "Chain Helmet");
            translationBuilder.add(AlphaItems.PLATE_CHAIN, "Chain Plate");
            translationBuilder.add(AlphaItems.LEGS_CHAIN, "Chain Legs");
            translationBuilder.add(AlphaItems.BOOTS_CHAIN, "Chain Boots");
            translationBuilder.add(AlphaItems.HELMET_STEEL, "Steel Helmet");
            translationBuilder.add(AlphaItems.PLATE_STEEL, "Steel Plate");
            translationBuilder.add(AlphaItems.LEGS_STEEL, "Steel Legs");
            translationBuilder.add(AlphaItems.BOOTS_STEEL, "Steel Boots");
            translationBuilder.add(AlphaItems.HELMET_DIAMOND, "Diamond Helmet");
            translationBuilder.add(AlphaItems.PLATE_DIAMOND, "Diamond Plate");
            translationBuilder.add(AlphaItems.LEGS_DIAMOND, "Diamond Legs");
            translationBuilder.add(AlphaItems.BOOTS_DIAMOND, "Diamond Boots");
            translationBuilder.add(AlphaItems.HELMET_GOLD, "Gold Helmet");
            translationBuilder.add(AlphaItems.PLATE_GOLD, "Gold Plate");
            translationBuilder.add(AlphaItems.LEGS_GOLD, "Gold Legs");
            translationBuilder.add(AlphaItems.BOOTS_GOLD, "Gold Boots");
            translationBuilder.add(AlphaItems.FLINT, "Flint");
            translationBuilder.add(AlphaItems.PORK_RAW, "Raw Pork");
            translationBuilder.add(AlphaItems.PORK_COOKED, "Cooked Pork");
            translationBuilder.add(AlphaItems.PAINTING, "Painting");
            translationBuilder.add(AlphaItems.APPLE_GOLD, "Gold Apple");
            translationBuilder.add(AlphaItems.SIGN, "Sign");
            translationBuilder.add(AlphaItems.DOOR_WOOD, "Wood Door");
            translationBuilder.add(AlphaItems.BUCKET_EMPTY, "Empty Bucket");
            translationBuilder.add(AlphaItems.BUCKET_WATER, "Water Bucket");
            translationBuilder.add(AlphaItems.BUCKET_LAVA, "Lava Bucket");
            translationBuilder.add(AlphaItems.MINECART_EMPTY, "Empty Minecart");
            translationBuilder.add(AlphaItems.SADDLE, "Saddle");
            translationBuilder.add(AlphaItems.DOOR_STEEL, "Steel Door");
            translationBuilder.add(AlphaItems.REDSTONE, "Redstone");
            translationBuilder.add(AlphaItems.SNOWBALL, "Snowball");
            translationBuilder.add(AlphaItems.BOAT, "Boat");
            translationBuilder.add(AlphaItems.LEATHER, "Leather");
            translationBuilder.add(AlphaItems.BUCKET_MILK, "Milk Bucket");
            translationBuilder.add(AlphaItems.BRICK, "Brick");
            translationBuilder.add(AlphaItems.CLAY, "Clay");
            translationBuilder.add(AlphaItems.REED, "Reed");
            translationBuilder.add(AlphaItems.PAPER, "Paper");
            translationBuilder.add(AlphaItems.BOOK, "Book");
            translationBuilder.add(AlphaItems.SLIME_BALL, "Slime Ball");
            translationBuilder.add(AlphaItems.MINECART_BOX, "Minecart with Box");
            translationBuilder.add(AlphaItems.MINECART_ENGINE, "Minecart with Engine");
            translationBuilder.add(AlphaItems.EGG, "Egg");
            translationBuilder.add(AlphaItems.COMPASS, "Compass");
            translationBuilder.add(AlphaItems.FISHING_ROD, "Fishing Rod");
            translationBuilder.add(AlphaItems.RECORD_13, "Record (13)");
            translationBuilder.add(AlphaItems.RECORD_CAT, "Record (Cat)");*/
        }
    }

    private static class NostalgicVersionsBlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public NostalgicVersionsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(AlphaBlocks.COBBLESTONE)
                    .add(AlphaBlocks.STAIR_DOUBLE)
                    .add(AlphaBlocks.STAIR_SINGLE)
                    .add(AlphaBlocks.STONE)
                    .add(AlphaBlocks.COBBLESTONE_MOSSY)
                    .add(AlphaBlocks.ORE_IRON)
                    .add(AlphaBlocks.BLOCK_STEEL)
                    .add(AlphaBlocks.ORE_COAL)
                    .add(AlphaBlocks.BLOCK_GOLD)
                    .add(AlphaBlocks.ORE_DIAMOND)
                    .add(AlphaBlocks.BLOCK_DIAMOND)
                    .add(AlphaBlocks.ICE)
                    .add(AlphaBlocks.OBSIDIAN)
                    .add(AlphaBlocks.ORE_GOLD)
                    .add(AlphaBlocks.ORE_REDSTONE)
                    .add(AlphaBlocks.ORE_REDSTONE_GLOWING);
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                    .add(AlphaBlocks.PLANKS)
                    .add(AlphaBlocks.BOOKSHELF)
                    .add(AlphaBlocks.WOOD)
                    .add(AlphaBlocks.CHEST);
            getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                    .add(AlphaBlocks.GRASS)
                    .add(AlphaBlocks.DIRT)
                    .add(AlphaBlocks.SAND)
                    .add(AlphaBlocks.GRAVEL)
                    .add(AlphaBlocks.SNOW)
                    .add(AlphaBlocks.BLOCK_SNOW)
                    .add(AlphaBlocks.BLOCK_CLAY);
            getOrCreateTagBuilder(BlockTags.DIRT)
                    .add(AlphaBlocks.GRASS)
                    .add(AlphaBlocks.DIRT);
        }
    }
}
