package net.noiamnotarobot.nostalgicversions;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.block.enums.ChestType;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.noiamnotarobot.nostalgicversions.block.*;
import net.noiamnotarobot.nostalgicversions.item.AlphaItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NostalgicVersionsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(NostalgicVersionsModelProvider::new);
        pack.addProvider(NostalgicVersionsBlockLootTableProvider::new);
        pack.addProvider(NostalgicVersionsEnglishLangProvider::new);
        pack.addProvider(NostalgicVersionsBlockTagProvider::new);
        pack.addProvider(NostalgicVersionsRecipeProvider::new);
    }

    private static class NostalgicVersionsModelProvider extends FabricModelProvider {
        public NostalgicVersionsModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator generator) {
            generator.registerSimpleCubeAll(AlphaBlocks.STONE);
            // GRASS START
            generator.registerParentedItemModel(AlphaBlocks.GRASS, Models.CUBE_BOTTOM_TOP.upload(AlphaBlocks.GRASS,
                    TextureMap.sideAndTop(AlphaBlocks.GRASS).put(TextureKey.BOTTOM, NostalgicVersions.Util.id("block/dirt")),
                    generator.modelCollector));
            Models.CUBE_BOTTOM_TOP.upload(AlphaBlocks.GRASS, "_snowy",
                    new TextureMap()
                            .put(TextureKey.TOP, TextureMap.getSubId(AlphaBlocks.GRASS, "_top"))
                            .put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.GRASS, "_side_snowy"))
                            .put(TextureKey.BOTTOM, NostalgicVersions.Util.id("block/dirt")),
                    generator.modelCollector);
            generator.blockStateCollector.accept(
                    VariantsBlockStateSupplier.create(AlphaBlocks.GRASS)
                            .coordinate(BlockStateModelGenerator.createBooleanModelMap(
                                    AlphaGrassBlock.SNOWY,
                                    ModelIds.getBlockSubModelId(AlphaBlocks.GRASS, "_snowy"),
                                    ModelIds.getBlockModelId(AlphaBlocks.GRASS))));
            // GRASS END
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
            generator.registerParentedItemModel(AlphaBlocks.BLOCK_GOLD, Models.CUBE_BOTTOM_TOP.upload(AlphaBlocks.BLOCK_GOLD, TextureMap.sideTopBottom(AlphaBlocks.BLOCK_GOLD), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.BLOCK_GOLD);
            generator.registerParentedItemModel(AlphaBlocks.BLOCK_STEEL, Models.CUBE_BOTTOM_TOP.upload(AlphaBlocks.BLOCK_STEEL, TextureMap.sideTopBottom(AlphaBlocks.BLOCK_STEEL), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.BLOCK_STEEL);
            generator.registerSimpleCubeAll(AlphaBlocks.BRICK);
            generator.registerParentedItemModel(AlphaBlocks.BOOKSHELF, Models.CUBE_COLUMN.upload(AlphaBlocks.BOOKSHELF,
                    new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.BOOKSHELF, "_side"))
                            .put(TextureKey.END, NostalgicVersions.Util.id("block/planks")), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.BOOKSHELF);
            generator.registerSimpleCubeAll(AlphaBlocks.COBBLESTONE_MOSSY);
            generator.registerSimpleCubeAll(AlphaBlocks.OBSIDIAN);
            //generator.fire
            generator.registerSimpleCubeAll(AlphaBlocks.MOB_SPAWNER);
            // CHEST START
            generator.blockStateCollector.accept(
                    VariantsBlockStateSupplier.create(AlphaBlocks.CHEST)
                            .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                            .coordinate(BlockStateVariantMap.create(AlphaChestBlock.CHEST_TYPE)
                                    .register(ChestType.SINGLE, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(AlphaBlocks.CHEST)))
                                    .register(ChestType.LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(AlphaBlocks.CHEST).withSuffixedPath("_left")))
                                    .register(ChestType.RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(AlphaBlocks.CHEST).withSuffixedPath("_right")))
                            ));
            generator.registerParentedItemModel(AlphaBlocks.CHEST, Models.CUBE.upload(AlphaBlocks.CHEST,
                    new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.CHEST, "_side"))
                            .put(TextureKey.END, TextureMap.getSubId(AlphaBlocks.CHEST, "_end"))
                            .copy(TextureKey.END, TextureKey.UP).copy(TextureKey.END, TextureKey.DOWN)
                            .put(TextureKey.NORTH, TextureMap.getSubId(AlphaBlocks.CHEST, "_front"))
                            .copy(TextureKey.SIDE, TextureKey.SOUTH).copy(TextureKey.SIDE, TextureKey.EAST).copy(TextureKey.SIDE, TextureKey.WEST).copy(TextureKey.SIDE, TextureKey.PARTICLE), generator.modelCollector));
            Models.CUBE.upload(AlphaBlocks.CHEST, "_left",
                    new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.CHEST, "_side"))
                            .put(TextureKey.END, TextureMap.getSubId(AlphaBlocks.CHEST, "_end"))
                            .copy(TextureKey.END, TextureKey.UP).copy(TextureKey.END, TextureKey.DOWN)
                            .put(TextureKey.NORTH, TextureMap.getSubId(AlphaBlocks.CHEST, "_front_left"))
                            .put(TextureKey.SOUTH, TextureMap.getSubId(AlphaBlocks.CHEST, "_side_left"))
                            .copy(TextureKey.SIDE, TextureKey.EAST).copy(TextureKey.SIDE, TextureKey.WEST).copy(TextureKey.SIDE, TextureKey.PARTICLE), generator.modelCollector);
            Models.CUBE.upload(AlphaBlocks.CHEST, "_right",
                    new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.CHEST, "_side"))
                            .put(TextureKey.END, TextureMap.getSubId(AlphaBlocks.CHEST, "_end"))
                            .copy(TextureKey.END, TextureKey.UP).copy(TextureKey.END, TextureKey.DOWN)
                            .put(TextureKey.NORTH, TextureMap.getSubId(AlphaBlocks.CHEST, "_front_right"))
                            .put(TextureKey.SOUTH, TextureMap.getSubId(AlphaBlocks.CHEST, "_side_right"))
                            .copy(TextureKey.SIDE, TextureKey.EAST).copy(TextureKey.SIDE, TextureKey.WEST).copy(TextureKey.SIDE, TextureKey.PARTICLE), generator.modelCollector);
            // CHEST END
            generator.registerSimpleCubeAll(AlphaBlocks.ORE_DIAMOND);
            generator.registerParentedItemModel(AlphaBlocks.BLOCK_DIAMOND, Models.CUBE_BOTTOM_TOP.upload(AlphaBlocks.BLOCK_DIAMOND, TextureMap.sideTopBottom(AlphaBlocks.BLOCK_DIAMOND), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.BLOCK_DIAMOND);
            generator.registerParentedItemModel(AlphaBlocks.WORKBENCH, Models.CUBE.upload(AlphaBlocks.WORKBENCH,
                    new TextureMap().put(TextureKey.UP, TextureMap.getSubId(AlphaBlocks.WORKBENCH, "_top"))
                            .put(TextureKey.FRONT, TextureMap.getSubId(AlphaBlocks.WORKBENCH, "_front"))
                            .put(TextureKey.SIDE, TextureMap.getSubId(AlphaBlocks.WORKBENCH, "_side"))
                            .put(TextureKey.DOWN, TextureMap.getId(AlphaBlocks.PLANKS))
                            .copy(TextureKey.FRONT, TextureKey.NORTH).copy(TextureKey.FRONT, TextureKey.WEST)
                            .copy(TextureKey.SIDE, TextureKey.EAST).copy(TextureKey.SIDE, TextureKey.SOUTH)
                            .copy(TextureKey.SIDE, TextureKey.PARTICLE), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.WORKBENCH);
            generator.registerCrop(AlphaBlocks.CROPS, AlphaCropsBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
            // TILLED FIELD START
            generator.registerParentedItemModel(AlphaBlocks.TILLED_FIELD, Models.TEMPLATE_FARMLAND.upload(AlphaBlocks.TILLED_FIELD,
                    new TextureMap().put(TextureKey.DIRT, TextureMap.getId(AlphaBlocks.DIRT))
                            .put(TextureKey.TOP, TextureMap.getSubId(AlphaBlocks.TILLED_FIELD, "_top")), generator.modelCollector));
            Models.TEMPLATE_FARMLAND.upload(AlphaBlocks.TILLED_FIELD, "_wet",
                    new TextureMap().put(TextureKey.DIRT, TextureMap.getId(AlphaBlocks.DIRT))
                            .put(TextureKey.TOP, TextureMap.getSubId(AlphaBlocks.TILLED_FIELD, "_top_wet")), generator.modelCollector);
            generator.blockStateCollector.accept(
                    VariantsBlockStateSupplier.create(AlphaBlocks.TILLED_FIELD)
                            .coordinate(BlockStateVariantMap.create(AlphaFarmlandBlock.MOISTURE)
                                    .register((i) -> BlockStateVariant.create().put(VariantSettings.MODEL, i == 0
                                            ?  ModelIds.getBlockModelId(AlphaBlocks.TILLED_FIELD)
                                            : ModelIds.getBlockSubModelId(AlphaBlocks.TILLED_FIELD, "_wet")))
                            )
            );
            // TILLED FIELD END
            generator.registerSimpleCubeAll(AlphaBlocks.ICE);
            generator.registerParentedItemModel(AlphaBlocks.BLOCK_SNOW, Models.CUBE_ALL.upload(AlphaBlocks.BLOCK_SNOW, TextureMap.all(AlphaBlocks.SNOW), generator.modelCollector));
            generator.registerSimpleState(AlphaBlocks.BLOCK_SNOW);
            generator.registerSimpleCubeAll(AlphaBlocks.BLOCK_CLAY);
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
            generator.register(AlphaItems.TELEPORTER_ALPHA, Models.GENERATED);
            generator.register(AlphaItems.TELEPORTER_MODERN, Models.GENERATED);
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
            translationBuilder.add(NostalgicVersions.TranslationKeys.ALPHA_ITEMS, "Minecraft Alpha 1.1.2_01");

            translationBuilder.add(AlphaBlocks.STONE, "Stone");
            translationBuilder.add(AlphaBlocks.GRASS, "Grass");
            translationBuilder.add(AlphaBlocks.DIRT, "Dirt");
            translationBuilder.add(AlphaBlocks.COBBLESTONE, "Cobblestone");
            translationBuilder.add(AlphaBlocks.PLANKS, "Planks");
            translationBuilder.add(AlphaBlocks.SAPLING, "Sapling");
            translationBuilder.add(AlphaBlocks.BEDROCK, "Bedrock");
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
            //translationBuilder.add(AlphaBlocks.STAIR_DOUBLE, "Double Stair");
            //translationBuilder.add(AlphaBlocks.STAIR_SINGLE, "Single Stair");
            translationBuilder.add(AlphaBlocks.BRICK, "Brick");
            //translationBuilder.add(AlphaBlocks.TNT, "TNT");
            translationBuilder.add(AlphaBlocks.BOOKSHELF, "Bookshelf");
            translationBuilder.add(AlphaBlocks.COBBLESTONE_MOSSY, "Mossy Cobblestone");
            translationBuilder.add(AlphaBlocks.OBSIDIAN, "Obsidian");
            //translationBuilder.add(AlphaBlocks.TORCH, "Torch");
            translationBuilder.add(AlphaBlocks.FIRE, "Fire");
            translationBuilder.add(AlphaBlocks.MOB_SPAWNER, "Mob Spawner");
            //translationBuilder.add(AlphaBlocks.STAIR_COMPACT_WOOD, "Compact Wood Stair");
            translationBuilder.add(AlphaBlocks.CHEST, "Chest");
            //translationBuilder.add(AlphaBlocks.REDSTONE_WIRE, "Redstone Wire");
            translationBuilder.add(AlphaBlocks.ORE_DIAMOND, "Diamond Ore");
            translationBuilder.add(AlphaBlocks.BLOCK_DIAMOND, "Diamond Block");
            translationBuilder.add(AlphaBlocks.WORKBENCH, "Workbench");
            translationBuilder.add(AlphaBlocks.CROPS, "Crops");
            translationBuilder.add(AlphaBlocks.TILLED_FIELD, "Tilled Field");
            //translationBuilder.add(AlphaBlocks.STONE_OVEN_IDLE, "Stone Oven");
            //translationBuilder.add(AlphaBlocks.STONE_OVEN_ACTIVE, "Stone Oven");
            //translationBuilder.add(AlphaBlocks.SIGN_STANDING, "Sign");
            //translationBuilder.add(AlphaBlocks.DOOR_WOOD, "Wood Door");
            //translationBuilder.add(AlphaBlocks.LADDER, "Ladder");
            //translationBuilder.add(AlphaBlocks.MINECART_TRACK, "Minecart Track");
            //translationBuilder.add(AlphaBlocks.STAIR_COMPACT_STONE, "Compact Stone Stair");
            //translationBuilder.add(AlphaBlocks.SIGN_WALL, "Sign");
            //translationBuilder.add(AlphaBlocks.LEVER, "Lever");
            //translationBuilder.add(AlphaBlocks.PRESSURE_PLATE_STONE, "Stone Pressure Plate");
            //translationBuilder.add(AlphaBlocks.DOOR_STEEL, "Steel Door");
            //translationBuilder.add(AlphaBlocks.PRESSURE_PLATE_WOOD, "Wood Pressure Plate");
            //translationBuilder.add(AlphaBlocks.ORE_REDSTONE, "Redstone Ore");
            //translationBuilder.add(AlphaBlocks.ORE_REDSTONE_GLOWING, "Redstone Ore");
            //translationBuilder.add(AlphaBlocks.TORCH_REDSTONE_IDLE, "Redstone Torch");
            //translationBuilder.add(AlphaBlocks.TORCH_REDSTONE_ACTIVE, "Redstone Torch");
            //translationBuilder.add(AlphaBlocks.BUTTON, "Button");
            //translationBuilder.add(AlphaBlocks.SNOW, "Snow");
            translationBuilder.add(AlphaBlocks.ICE, "Ice");
            translationBuilder.add(AlphaBlocks.BLOCK_SNOW, "Snow");
            //translationBuilder.add(AlphaBlocks.CACTUS, "Cactus");
            translationBuilder.add(AlphaBlocks.BLOCK_CLAY, "Clay");
            //translationBuilder.add(AlphaBlocks.REED, "Reed");
            //translationBuilder.add(AlphaBlocks.JUKEBOX, "Jukebox");
            //translationBuilder.add(AlphaBlocks.FENCE, "Fence");

            translationBuilder.add(NostalgicVersions.TranslationKeys.CHEST_NAME, "Chest");
            translationBuilder.add(NostalgicVersions.TranslationKeys.LARGE_CHEST_NAME, "Large chest");

            translationBuilder.add(AlphaItems.SHOVEL, "Steel Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_STEEL, "Steel Pickaxe");
            translationBuilder.add(AlphaItems.AXE_STEEL, "Steel Axe");
            translationBuilder.add(AlphaItems.STRIKER, "Striker");
            translationBuilder.add(AlphaItems.APPLE_RED, "Red Apple");
            translationBuilder.add(AlphaItems.BOW, "Bow");
            //translationBuilder.add(AlphaItems.ARROW, "Arrow");
            translationBuilder.add(AlphaItems.COAL, "Coal");
            translationBuilder.add(AlphaItems.DIAMOND, "Diamond");
            translationBuilder.add(AlphaItems.INGOT_IRON, "Iron Ingot");
            translationBuilder.add(AlphaItems.INGOT_GOLD, "Gold Ingot");
            //translationBuilder.add(AlphaItems.SWORD_STEEL, "Steel Sword");
            //translationBuilder.add(AlphaItems.SWORD_WOOD, "Wood Sword");
            translationBuilder.add(AlphaItems.SHOVEL_WOOD, "Wood Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_WOOD, "Wood Pickaxe");
            translationBuilder.add(AlphaItems.AXE_WOOD, "Wood Axe");
            //translationBuilder.add(AlphaItems.SWORD_STONE, "Stone Sword");
            translationBuilder.add(AlphaItems.SHOVEL_STONE, "Stone Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_STONE, "Stone Pickaxe");
            translationBuilder.add(AlphaItems.AXE_STONE, "Stone Axe");
            //translationBuilder.add(AlphaItems.SWORD_DIAMOND, "Diamond Sword");
            translationBuilder.add(AlphaItems.SHOVEL_DIAMOND, "Diamond Shovel");
            translationBuilder.add(AlphaItems.PICKAXE_DIAMOND, "Diamond Pickaxe");
            translationBuilder.add(AlphaItems.AXE_DIAMOND, "Diamond Axe");
            translationBuilder.add(AlphaItems.STICK, "Stick");
            translationBuilder.add(AlphaItems.BOWL_EMPTY, "Empty Bowl");
            translationBuilder.add(AlphaItems.BOWL_SOUP, "Bowl of Soup");
            //translationBuilder.add(AlphaItems.SWORD_GOLD, "Gold Sword");
            //translationBuilder.add(AlphaItems.SHOVEL_GOLD, "Gold Shovel");
            //translationBuilder.add(AlphaItems.PICKAXE_GOLD, "Gold Pickaxe");
            //translationBuilder.add(AlphaItems.AXE_GOLD, "Gold Axe");
            translationBuilder.add(AlphaItems.SILK, "Silk");
            translationBuilder.add(AlphaItems.FEATHER, "Feather");
            translationBuilder.add(AlphaItems.GUNPOWDER, "Gunpowder");
            //translationBuilder.add(AlphaItems.HOE_WOOD, "Wood Hoe");
            //translationBuilder.add(AlphaItems.HOE_STONE, "Stone Hoe");
            //translationBuilder.add(AlphaItems.HOE_STEEL, "Steel Hoe");
            //translationBuilder.add(AlphaItems.HOE_DIAMOND, "Diamond Hoe");
            //translationBuilder.add(AlphaItems.HOE_GOLD, "Gold Hoe");
            translationBuilder.add(AlphaItems.SEEDS, "Seeds");
            translationBuilder.add(AlphaItems.WHEAT, "Wheat");
            translationBuilder.add(AlphaItems.BREAD, "Bread");
            //translationBuilder.add(AlphaItems.HELMET_LEATHER, "Leather Helmet");
            //translationBuilder.add(AlphaItems.PLATE_LEATHER, "Leather Plate");
            //translationBuilder.add(AlphaItems.LEGS_LEATHER, "Leather Legs");
            //translationBuilder.add(AlphaItems.BOOTS_LEATHER, "Leather Boots");
            //translationBuilder.add(AlphaItems.HELMET_CHAIN, "Chain Helmet");
            //translationBuilder.add(AlphaItems.PLATE_CHAIN, "Chain Plate");
            //translationBuilder.add(AlphaItems.LEGS_CHAIN, "Chain Legs");
            //translationBuilder.add(AlphaItems.BOOTS_CHAIN, "Chain Boots");
            //translationBuilder.add(AlphaItems.HELMET_STEEL, "Steel Helmet");
            //translationBuilder.add(AlphaItems.PLATE_STEEL, "Steel Plate");
            //translationBuilder.add(AlphaItems.LEGS_STEEL, "Steel Legs");
            //translationBuilder.add(AlphaItems.BOOTS_STEEL, "Steel Boots");
            //translationBuilder.add(AlphaItems.HELMET_DIAMOND, "Diamond Helmet");
            //translationBuilder.add(AlphaItems.PLATE_DIAMOND, "Diamond Plate");
            //translationBuilder.add(AlphaItems.LEGS_DIAMOND, "Diamond Legs");
            //translationBuilder.add(AlphaItems.BOOTS_DIAMOND, "Diamond Boots");
            //translationBuilder.add(AlphaItems.HELMET_GOLD, "Gold Helmet");
            //translationBuilder.add(AlphaItems.PLATE_GOLD, "Gold Plate");
            //translationBuilder.add(AlphaItems.LEGS_GOLD, "Gold Legs");
            //translationBuilder.add(AlphaItems.BOOTS_GOLD, "Gold Boots");
            translationBuilder.add(AlphaItems.FLINT, "Flint");
            translationBuilder.add(AlphaItems.PORK_RAW, "Raw Pork");
            translationBuilder.add(AlphaItems.PORK_COOKED, "Cooked Pork");
            //translationBuilder.add(AlphaItems.PAINTING, "Painting");
            translationBuilder.add(AlphaItems.APPLE_GOLD, "Gold Apple");
            //translationBuilder.add(AlphaItems.SIGN, "Sign");
            //translationBuilder.add(AlphaItems.DOOR_WOOD, "Wood Door");
            translationBuilder.add(AlphaItems.BUCKET_EMPTY, "Empty Bucket");
            translationBuilder.add(AlphaItems.BUCKET_WATER, "Water Bucket");
            translationBuilder.add(AlphaItems.BUCKET_LAVA, "Lava Bucket");
            //translationBuilder.add(AlphaItems.MINECART_EMPTY, "Empty Minecart");
            //translationBuilder.add(AlphaItems.SADDLE, "Saddle");
            //translationBuilder.add(AlphaItems.DOOR_STEEL, "Steel Door");
            //translationBuilder.add(AlphaItems.REDSTONE, "Redstone");
            //translationBuilder.add(AlphaItems.SNOWBALL, "Snowball");
            //translationBuilder.add(AlphaItems.BOAT, "Boat");
            translationBuilder.add(AlphaItems.LEATHER, "Leather");
            translationBuilder.add(AlphaItems.BUCKET_MILK, "Milk Bucket");
            translationBuilder.add(AlphaItems.BRICK, "Brick");
            translationBuilder.add(AlphaItems.CLAY, "Clay");
            //translationBuilder.add(AlphaItems.REED, "Reed");
            translationBuilder.add(AlphaItems.PAPER, "Paper");
            translationBuilder.add(AlphaItems.BOOK, "Book");
            translationBuilder.add(AlphaItems.SLIME_BALL, "Slime Ball");
            //translationBuilder.add(AlphaItems.MINECART_BOX, "Minecart with Box");
            //translationBuilder.add(AlphaItems.MINECART_ENGINE, "Minecart with Engine");
            translationBuilder.add(AlphaItems.EGG, "Egg");
            translationBuilder.add(AlphaItems.COMPASS, "Compass");
            translationBuilder.add(AlphaItems.FISHING_ROD, "Fishing Rod");
            //translationBuilder.add(AlphaItems.RECORD_13, "Record");
            //translationBuilder.add(AlphaItems.RECORD_CAT, "Record");
            translationBuilder.add(AlphaItems.TELEPORTER_ALPHA, "Alpha Teleporter");
            translationBuilder.add(AlphaItems.TELEPORTER_MODERN, "Modern Teleporter");

            translationBuilder.add(NostalgicVersions.TranslationKeys.MODERN_TELEPORT_ERROR, "Must be in Minecraft Alpha 1.1.2_01!");
            translationBuilder.add(NostalgicVersions.TranslationKeys.ALPHA_TELEPORT_ERROR_OLD, "Must be in Minecraft 1.20.1!");
            translationBuilder.add(NostalgicVersions.TranslationKeys.ALPHA_TELEPORT_ERROR_ELSE, "Must be in Overworld!");
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
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                    .add(AlphaBlocks.ORE_IRON)
                    .add(AlphaBlocks.BLOCK_STEEL);
            getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                    .add(AlphaBlocks.ORE_GOLD)
                    .add(AlphaBlocks.BLOCK_GOLD)
                    .add(AlphaBlocks.ORE_DIAMOND)
                    .add(AlphaBlocks.BLOCK_DIAMOND)
                    .add(AlphaBlocks.ORE_REDSTONE)
                    .add(AlphaBlocks.ORE_REDSTONE_GLOWING);
            getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                    .add(AlphaBlocks.OBSIDIAN);
            getOrCreateTagBuilder(BlockTags.DIRT)
                    .add(AlphaBlocks.GRASS)
                    .add(AlphaBlocks.DIRT);
            getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
                    .add(AlphaBlocks.CROPS);
        }
    }

    private static class NostalgicVersionsRecipeProvider extends FabricRecipeProvider {
        public NostalgicVersionsRecipeProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> consumer) {
            ItemConvertible[][] matrix = new ItemConvertible[][]{
                    {AlphaBlocks.PLANKS, AlphaBlocks.COBBLESTONE, AlphaItems.INGOT_IRON, AlphaItems.DIAMOND, AlphaItems.INGOT_GOLD},
                    {AlphaItems.PICKAXE_WOOD, AlphaItems.PICKAXE_STONE, AlphaItems.PICKAXE_STEEL, AlphaItems.PICKAXE_DIAMOND, AlphaItems.PICKAXE_GOLD},
                    {AlphaItems.SHOVEL_WOOD, AlphaItems.SHOVEL_STONE, AlphaItems.SHOVEL, AlphaItems.SHOVEL_DIAMOND, AlphaItems.SHOVEL_GOLD},
                    {AlphaItems.AXE_WOOD, AlphaItems.AXE_STONE, AlphaItems.AXE_STEEL, AlphaItems.AXE_DIAMOND, AlphaItems.AXE_GOLD},
                    {AlphaItems.HOE_WOOD, AlphaItems.HOE_STONE, AlphaItems.HOE_STEEL, AlphaItems.HOE_DIAMOND, AlphaItems.HOE_GOLD}};
            for (int i = 0; i < matrix[0].length; i++) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[1][i])
                        .pattern("XXX")
                        .pattern(" # ")
                        .pattern(" # ")
                        .input('X', matrix[0][i])
                        .input('#', AlphaItems.STICK)
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[2][i])
                        .pattern(" X ")
                        .pattern(" # ")
                        .pattern(" # ")
                        .input('X', matrix[0][i])
                        .input('#', AlphaItems.STICK)
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[3][i])
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .input('X', matrix[0][i])
                        .input('#', AlphaItems.STICK)
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[4][i])
                        .pattern("XX")
                        .pattern(" #")
                        .pattern(" #")
                        .input('X', matrix[0][i])
                        .input('#', AlphaItems.STICK)
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
            }

            matrix = new ItemConvertible[][]{
                    {AlphaBlocks.PLANKS, AlphaBlocks.COBBLESTONE, AlphaItems.INGOT_IRON, AlphaItems.DIAMOND, AlphaItems.INGOT_GOLD},
                    {AlphaItems.SWORD_WOOD, AlphaItems.SWORD_STONE, AlphaItems.SWORD_STEEL, AlphaItems.SWORD_DIAMOND, AlphaItems.SWORD_GOLD}};
            for (int i = 0; i < 5; i++) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[1][i])
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .input('#', AlphaItems.STICK)
                        .input('X', matrix[0][i])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
            }
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, AlphaItems.BOW)
                    .pattern(" #X")
                    .pattern("# X")
                    .pattern(" #X")
                    .input('#', AlphaItems.STICK)
                    .input('X', AlphaItems.SILK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, AlphaItems.ARROW, 4)
                    .pattern("X")
                    .pattern("#")
                    .pattern("Y")
                    .input('Y', AlphaItems.FEATHER)
                    .input('X', AlphaItems.FLINT)
                    .input('#', AlphaItems.STICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);

            for (ItemConvertible[] pair : new ItemConvertible[][]{
                    {AlphaBlocks.BLOCK_GOLD, AlphaItems.INGOT_GOLD},
                    {AlphaBlocks.BLOCK_STEEL, AlphaItems.INGOT_IRON},
                    {AlphaBlocks.BLOCK_DIAMOND, AlphaItems.DIAMOND}
            }) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, pair[0])
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .input('#', pair[1])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, pair[1], 9)
                        .input(pair[0])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
            }

            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, AlphaItems.BOWL_SOUP)
                    .pattern("Y")
                    .pattern("X")
                    .pattern("#")
                    .input('Y', AlphaBlocks.MUSHROOM_BROWN)
                    .input('X', AlphaBlocks.MUSHROOM_RED)
                    .input('#', AlphaItems.BOWL_EMPTY)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer, NostalgicVersions.Util.id("bowl_soup1"));
            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, AlphaItems.BOWL_SOUP)
                    .pattern("Y")
                    .pattern("X")
                    .pattern("#")
                    .input('Y', AlphaBlocks.MUSHROOM_RED)
                    .input('X', AlphaBlocks.MUSHROOM_BROWN)
                    .input('#', AlphaItems.BOWL_EMPTY)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer, NostalgicVersions.Util.id("bowl_soup2"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaBlocks.CHEST)
                    .pattern("###")
                    .pattern("# #")
                    .pattern("###")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaBlocks.STONE_OVEN_IDLE)
                    .pattern("###")
                    .pattern("# #")
                    .pattern("###")
                    .input('#', AlphaBlocks.COBBLESTONE)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaBlocks.WORKBENCH)
                    .pattern("##")
                    .pattern("##")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);

            matrix = new ItemConvertible[][]{
                    {AlphaItems.LEATHER, AlphaBlocks.FIRE, AlphaItems.INGOT_IRON, AlphaItems.DIAMOND, AlphaItems.INGOT_GOLD},
                    {AlphaItems.HELMET_LEATHER, AlphaItems.HELMET_CHAIN, AlphaItems.HELMET_STEEL, AlphaItems.HELMET_DIAMOND, AlphaItems.HELMET_GOLD},
                    {AlphaItems.PLATE_LEATHER, AlphaItems.PLATE_CHAIN, AlphaItems.PLATE_STEEL, AlphaItems.PLATE_DIAMOND, AlphaItems.PLATE_GOLD},
                    {AlphaItems.LEGS_LEATHER, AlphaItems.LEGS_CHAIN, AlphaItems.LEGS_STEEL, AlphaItems.LEGS_DIAMOND, AlphaItems.LEGS_GOLD},
                    {AlphaItems.BOOTS_LEATHER, AlphaItems.BOOTS_CHAIN, AlphaItems.BOOTS_STEEL, AlphaItems.BOOTS_DIAMOND, AlphaItems.BOOTS_GOLD}};
            for (int i = 0; i < matrix[0].length; i++) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[1][i])
                        .pattern("###")
                        .pattern("# #")
                        .input('#', matrix[0][i])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[2][i])
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .input('#', matrix[0][i])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[3][i])
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .input('#', matrix[0][i])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
                ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, matrix[4][i])
                        .pattern("# #")
                        .pattern("# #")
                        .input('#', matrix[0][i])
                        .criterion("impossible", new ImpossibleCriterion.Conditions())
                        .offerTo(consumer);
            }

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaItems.PAPER, 3)
                    .pattern("###")
                    .input('#', AlphaItems.REED)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaItems.BOOK)
                    .pattern("#")
                    .pattern("#")
                    .pattern("#")
                    .input('#', AlphaItems.PAPER)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.FENCE, 2)
                    .pattern("###")
                    .pattern("###")
                    .input('#', AlphaItems.STICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaBlocks.JUKEBOX)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .input('#', AlphaBlocks.PLANKS)
                    .input('X', AlphaItems.DIAMOND)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.BOOKSHELF)
                    .pattern("###")
                    .pattern("XXX")
                    .pattern("###")
                    .input('#', AlphaBlocks.PLANKS)
                    .input('X', AlphaItems.BOOK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.BLOCK_SNOW)
                    .pattern("##")
                    .pattern("##")
                    .input('#', AlphaItems.SNOWBALL)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.BLOCK_CLAY)
                    .pattern("##")
                    .pattern("##")
                    .input('#', AlphaItems.CLAY)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.BRICK)
                    .pattern("##")
                    .pattern("##")
                    .input('#', AlphaItems.BRICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.CLOTH)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .input('#', AlphaItems.SILK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, AlphaBlocks.TNT)
                    .pattern("X#X")
                    .pattern("#X#")
                    .pattern("X#X")
                    .input('X', AlphaItems.GUNPOWDER)
                    .input('#', AlphaBlocks.SAND)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.STAIR_SINGLE, 3)
                    .pattern("###")
                    .input('#', AlphaBlocks.COBBLESTONE)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaBlocks.LADDER)
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .input('#', AlphaItems.STICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaItems.DOOR_WOOD)
                    .pattern("##")
                    .pattern("##")
                    .pattern("##")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaItems.DOOR_STEEL)
                    .pattern("##")
                    .pattern("##")
                    .pattern("##")
                    .input('#', AlphaItems.INGOT_IRON)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, AlphaItems.SIGN)
                    .pattern("###")
                    .pattern("###")
                    .pattern(" X ")
                    .input('#', AlphaBlocks.PLANKS)
                    .input('X', AlphaItems.STICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.PLANKS, 4)
                    .input(AlphaBlocks.WOOD)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaItems.STICK, 4)
                    .pattern("#")
                    .pattern("#")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, AlphaBlocks.TORCH, 4)
                    .pattern("X")
                    .pattern("#")
                    .input('X', AlphaItems.COAL)
                    .input('#', AlphaItems.STICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaItems.BOWL_EMPTY, 4)
                    .pattern("# #")
                    .pattern(" # ")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaBlocks.MINECART_TRACK, 16)
                    .pattern("X X")
                    .pattern("X#X")
                    .pattern("X X")
                    .input('#', AlphaItems.STICK)
                    .input('X', AlphaItems.INGOT_IRON)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaItems.MINECART_EMPTY)
                    .pattern("# #")
                    .pattern("###")
                    .input('#', AlphaItems.INGOT_IRON)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaItems.MINECART_BOX)
                    .pattern("A")
                    .pattern("B")
                    .input('A', AlphaBlocks.CHEST)
                    .input('B', AlphaItems.MINECART_EMPTY)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaItems.MINECART_ENGINE)
                    .pattern("A")
                    .pattern("B")
                    .input('A', AlphaBlocks.STONE_OVEN_IDLE)
                    .input('B', AlphaItems.MINECART_EMPTY)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaItems.BOAT)
                    .pattern("# #")
                    .pattern("###")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, AlphaItems.BUCKET_EMPTY)
                    .pattern("# #")
                    .pattern(" # ")
                    .input('#', AlphaItems.INGOT_IRON)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, AlphaItems.STRIKER)
                    .pattern("A ")
                    .pattern(" B")
                    .input('A', AlphaItems.INGOT_IRON)
                    .input('B', AlphaItems.FLINT)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AlphaItems.BREAD)
                    .pattern("###")
                    .input('#', AlphaItems.WHEAT)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.STAIR_COMPACT_WOOD, 4)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, AlphaItems.FISHING_ROD)
                    .pattern("  #")
                    .pattern(" #X")
                    .pattern("# X")
                    .input('#', AlphaItems.STICK)
                    .input('X', AlphaItems.SILK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, AlphaBlocks.STAIR_COMPACT_STONE, 4)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .input('#', AlphaBlocks.COBBLESTONE)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AlphaItems.PAINTING)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .input('#', AlphaItems.STICK)
                    .input('X', AlphaBlocks.CLOTH)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, AlphaItems.APPLE_GOLD)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .input('#', AlphaBlocks.BLOCK_GOLD)
                    .input('X', AlphaItems.APPLE_RED)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AlphaBlocks.LEVER)
                    .pattern("X")
                    .pattern("#")
                    .input('X', AlphaItems.STICK)
                    .input('#', AlphaBlocks.COBBLESTONE)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AlphaBlocks.TORCH_REDSTONE_ACTIVE)
                    .pattern("X")
                    .pattern("#")
                    .input('X', AlphaItems.REDSTONE)
                    .input('#', AlphaItems.STICK)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AlphaItems.COMPASS)
                    .pattern(" # ")
                    .pattern("#X#")
                    .pattern(" # ")
                    .input('X', AlphaItems.REDSTONE)
                    .input('#', AlphaItems.INGOT_IRON)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AlphaBlocks.BUTTON)
                    .pattern("#")
                    .pattern("#")
                    .input('#', AlphaBlocks.STONE)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AlphaBlocks.PRESSURE_PLATE_STONE)
                    .pattern("###")
                    .input('#', AlphaBlocks.STONE)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AlphaBlocks.PRESSURE_PLATE_WOOD)
                    .pattern("###")
                    .input('#', AlphaBlocks.PLANKS)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);

            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, AlphaItems.TELEPORTER_ALPHA)
                    .pattern("IRI")
                    .pattern("RDR")
                    .pattern("IRI")
                    .input('I', Items.IRON_INGOT)
                    .input('R', Items.REDSTONE)
                    .input('D', Items.DIAMOND)
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, AlphaItems.TELEPORTER_MODERN)
                    .pattern("IRI")
                    .pattern("RDR")
                    .pattern("IRI")
                    .input('I', AlphaItems.INGOT_IRON)
                    .input('R', AlphaItems.REDSTONE)
                    .input('D', AlphaItems.DIAMOND)
                    .criterion("impossible", new ImpossibleCriterion.Conditions())
                    .offerTo(consumer);
        }
    }
}