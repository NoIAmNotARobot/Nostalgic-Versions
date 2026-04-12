package net.noiamnotarobot.nostalgicversions.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.noiamnotarobot.nostalgicversions.WinterModeState;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.block.AlphaGrassBlock;
import net.noiamnotarobot.nostalgicversions.world.gen.noise.NoiseGeneratorOctaves;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AlphaChunkGenerator extends ChunkGenerator {
    private long seed;
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    private NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves noiseGen7;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private double[] sandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] stoneNoise = new double[256];
    private AlphaMapGenCaves caveGenerator = new AlphaMapGenCaves();
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise6;
    double[] noise7;
    boolean winterMode;

    public static final Codec<AlphaChunkGenerator> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(AlphaChunkGenerator::getBiomeSource)
            ).apply(instance, AlphaChunkGenerator::new));

    public AlphaChunkGenerator(BiomeSource biomeSource) {
        super(biomeSource);
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    public void init(long seed, WinterModeState winterState) {
        rand = new Random(seed);
        noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 10);
        noiseGen7 = new NoiseGeneratorOctaves(rand, 16);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
        winterMode = winterState.isWinterMode();
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {
        caveGenerator.rand.setSeed(seed);
        long salt1 = caveGenerator.rand.nextLong() / 2L * 2L + 1L;
        long salt2 = caveGenerator.rand.nextLong() / 2L * 2L + 1L;

        for (int x = chunk.getPos().x - caveGenerator.range; x <= chunk.getPos().x + caveGenerator.range; ++x) {
            for (int z = chunk.getPos().z - caveGenerator.range; z <= chunk.getPos().z + caveGenerator.range; ++z) {
                caveGenerator.rand.setSeed((long) x * salt1 + (long) z * salt2 ^ seed);
                caveGenerator.recursiveGenerate(chunk, x, z, chunk.getPos().x, chunk.getPos().z);
            }
        }
    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        byte var4 = 64;
        double var5 = 1.0D / 32.0D;
        sandNoise = noiseGen4.generateNoiseOctaves(sandNoise, chunk.getPos().x * 16, chunk.getPos().z * 16, 0.0D, 16, 16, 1, var5, var5, 1.0D);
        gravelNoise = noiseGen4.generateNoiseOctaves(gravelNoise, chunk.getPos().z * 16, 109.0134D, chunk.getPos().x * 16, 16, 1, 16, var5, 1.0D, var5);
        stoneNoise = noiseGen5.generateNoiseOctaves(stoneNoise, chunk.getPos().x * 16, chunk.getPos().z * 16, 0.0D, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                boolean var9 = sandNoise[x + z * 16] + rand.nextDouble() * 0.2D > 0.0D;
                boolean var10 = gravelNoise[x + z * 16] + rand.nextDouble() * 0.2D > 3.0D;
                int var11 = (int) (stoneNoise[x + z * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
                int var12 = -1;
                Block topLayer = AlphaBlocks.GRASS;
                Block middleLayer = AlphaBlocks.DIRT;

                for (int y = 127; y >= 0; --y) {
                    if (y <= rand.nextInt(6) - 1) {
                        chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, AlphaBlocks.BEDROCK.getDefaultState(), false);
                    } else {
                        Block var17 = chunk.getSection(chunk.getSectionIndex(y)).getBlockState(x, y % 16, z).getBlock();
                        if (var17 == Blocks.AIR) {
                            var12 = -1;
                        } else if (var17 == AlphaBlocks.STONE) {
                            if (var12 == -1) {
                                if (var11 <= 0) {
                                    topLayer = Blocks.AIR;
                                    middleLayer = AlphaBlocks.STONE;
                                } else if (y >= var4 - 4 && y <= var4 + 1) {
                                    topLayer = AlphaBlocks.GRASS;
                                    middleLayer = AlphaBlocks.DIRT;
                                    if (var10) {
                                        topLayer = Blocks.AIR;
                                    }

                                    if (var10) {
                                        middleLayer = AlphaBlocks.GRAVEL;
                                    }

                                    if (var9) {
                                        topLayer = AlphaBlocks.SAND;
                                    }

                                    if (var9) {
                                        middleLayer = AlphaBlocks.SAND;
                                    }
                                }

                                if (y < var4 && topLayer == Blocks.AIR) {
                                    topLayer = Blocks.WATER;
                                }

                                var12 = var11;
                                if (y >= var4 - 1) {
                                    chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, topLayer.getDefaultState(), false);
                                } else {
                                    chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, middleLayer.getDefaultState(), false);
                                }
                            } else if (var12 > 0) {
                                --var12;
                                chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, middleLayer.getDefaultState(), false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 128;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        byte var7 = 17;
        int var8 = 5;
        double[] noiseArray = initializeNoiseField(chunk.getPos().x * 4, chunk.getPos().z * 4, 5, var7, var8);

        for (int i = 0; i < 4; ++i) {
            for (int var10 = 0; var10 < 4; ++var10) {
                for (int var11 = 0; var11 < 16; ++var11) {
                    double var14 = noiseArray[((i) * var8 + var10) * var7 + var11];
                    double var16 = noiseArray[((i) * var8 + var10 + 1) * var7 + var11];
                    double var18 = noiseArray[((i + 1) * var8 + var10) * var7 + var11];
                    double var20 = noiseArray[((i + 1) * var8 + var10 + 1) * var7 + var11];
                    double var22 = (noiseArray[(i * var8 + var10) * var7 + var11 + 1] - var14) / 8;
                    double var24 = (noiseArray[(i * var8 + var10 + 1) * var7 + var11 + 1] - var16) / 8;
                    double var26 = (noiseArray[((i + 1) * var8 + var10) * var7 + var11 + 1] - var18) / 8;
                    double var28 = (noiseArray[((i + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) / 8;

                    for (int var30 = 0; var30 < 8; ++var30) {
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) / 4;
                        double var39 = (var20 - var16) / 4;

                        for (int var41 = 0; var41 < 4; ++var41) {
                            double var46 = var33;
                            double var48 = (var35 - var33) / 4;

                            for (int var50 = 0; var50 < 4; ++var50) {
                                Block block = Blocks.AIR;
                                if (var11 * 8 + var30 < getSeaLevel()) {
                                    if (winterMode && var11 * 8 + var30 >= getSeaLevel() - 1) {
                                        block = AlphaBlocks.ICE;
                                    } else {
                                        block = Blocks.WATER;
                                    }
                                }

                                if (var46 > 0.0D) {
                                    block = AlphaBlocks.STONE;
                                }

                                chunk.getSection(chunk.getSectionIndex(var11 * 8 + var30))
                                        .setBlockState(
                                                var41 + i * 4,
                                                (var11 * 8 + var30) % 16,
                                                var50 + var10 * 4,
                                                block.getDefaultState(),
                                                false
                                        );
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        var14 += var22;
                        var16 += var24;
                        var18 += var26;
                        var20 += var28;
                    }
                }
            }
        }

        return CompletableFuture.completedFuture(chunk);
    }

    private double[] initializeNoiseField(int var2, int var4, int var5, int var6, int var7) {
        double[] noiseArray = new double[var5 * var6 * var7];
        double scale = 684.412D;
        noise6 = noiseGen6.generateNoiseOctaves(noise6, var2, 0, var4, var5, 1, var7, 1.0D, 0.0D, 1.0D);
        noise7 = noiseGen7.generateNoiseOctaves(noise7, var2, 0, var4, var5, 1, var7, 100.0D, 0.0D, 100.0D);
        noise3 = noiseGen3.generateNoiseOctaves(noise3, var2, 0, var4, var5, var6, var7, scale / 80.0D, scale / 160.0D, scale / 80.0D);
        noise1 = noiseGen1.generateNoiseOctaves(noise1, var2, 0, var4, var5, var6, var7, scale, scale, scale);
        noise2 = noiseGen2.generateNoiseOctaves(noise2, var2, 0, var4, var5, var6, var7, scale, scale, scale);
        int var12 = 0;
        int var13 = 0;

        for (int x = 0; x < var5; ++x) {
            for (int z = 0; z < var7; ++z) {
                double var16 = (noise6[var13] + 256.0D) / 512.0D;
                if (var16 > 1.0D) {
                    var16 = 1.0D;
                }

                double var20 = noise7[var13] / 8000.0D;
                if (var20 < 0.0D) {
                    var20 = -var20;
                }

                var20 = var20 * 3.0D - 3.0D;
                if (var20 < 0.0D) {
                    var20 /= 2.0D;
                    if (var20 < -1.0D) {
                        var20 = -1.0D;
                    }

                    var20 /= 1.4D;
                    var20 /= 2.0D;
                    var16 = 0.0D;
                } else {
                    if (var20 > 1.0D) {
                        var20 = 1.0D;
                    }

                    var20 /= 6.0D;
                }

                var16 += 0.5D;
                var20 = var20 * (double) var6 / 16.0D;
                double var22 = (double) var6 / 2.0D + var20 * 4.0D;
                ++var13;

                for (int var24 = 0; var24 < var6; ++var24) {
                    double var25;
                    double var27 = ((double) var24 - var22) * 12.0D / var16;
                    if (var27 < 0.0D) {
                        var27 *= 4.0D;
                    }

                    double var29 = noise1[var12] / 512.0D;
                    double var31 = noise2[var12] / 512.0D;
                    double var33 = (noise3[var12] / 10.0D + 1.0D) / 2.0D;
                    if (var33 < 0.0D) {
                        var25 = var29;
                    } else if (var33 > 1.0D) {
                        var25 = var31;
                    } else {
                        var25 = var29 + (var31 - var29) * var33;
                    }

                    var25 -= var27;
                    double var35;
                    if (var24 > var6 - 4) {
                        var35 = ((float) (var24 - (var6 - 4)) / 3.0F);
                        var25 = var25 * (1.0D - var35) + -10.0D * var35;
                    }

                    noiseArray[var12] = var25;
                    ++var12;
                }
            }
        }

        return noiseArray;
    }

    @Override
    public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor) {
        int baseX = chunk.getPos().x * 16;
        int baseZ = chunk.getPos().z * 16;
        long salt1 = rand.nextLong() / 2L * 2L + 1L;
        long salt2 = rand.nextLong() / 2L * 2L + 1L;
        rand.setSeed((long) chunk.getPos().x * salt1 + (long) chunk.getPos().z * salt2 ^ seed);
        Heightmap.populateHeightmaps(chunk, EnumSet.of(Heightmap.Type.WORLD_SURFACE_WG));

        int var12;
        int var13;
        int var14;
        int var15;
        for (var12 = 0; var12 < 8; ++var12) {
            var13 = baseX + rand.nextInt(16) + 8;
            var14 = rand.nextInt(128);
            var15 = baseZ + rand.nextInt(16) + 8;
            (new AlphaWorldGenDungeons()).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 10; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(128);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenClay(32)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 20; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(128);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.DIRT, 32)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 10; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(128);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.GRAVEL, 32)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 20; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(128);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.ORE_COAL, 16)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 20; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(64);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.ORE_IRON, 8)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 2; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(32);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.ORE_GOLD, 8)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 8; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(16);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.ORE_REDSTONE, 7)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        for (var12 = 0; var12 < 1; ++var12) {
            var13 = baseX + rand.nextInt(16);
            var14 = rand.nextInt(16);
            var15 = baseZ + rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlocks.ORE_DIAMOND, 7)).generate(world, rand, new BlockPos(var13, var14, var15));
        }

        var12 = (int) ((mobSpawnerNoise.generateNoiseOctaves((double) baseX / 2.0D, (double) baseZ / 2.0D) / 8.0D + rand.nextDouble() * 4.0D + 4.0D) / 3.0D);
        if (var12 < 0) {
            var12 = 0;
        }

        if (rand.nextInt(10) == 0) {
            ++var12;
        }

        AlphaWorldGenerator treeGen = new AlphaWorldGenTrees();
        if (rand.nextInt(10) == 0) {
            treeGen = new AlphaWorldGenBigTree();
        }

        int var16;
        for (var14 = 0; var14 < var12; ++var14) {
            var15 = baseX + rand.nextInt(16) + 8;
            var16 = baseZ + rand.nextInt(16) + 8;
            treeGen.setScale(1.0D, 1.0D, 1.0D);
            treeGen.generate(world, rand, new BlockPos(var15, chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, var15, var16) + 1, var16));
        }

        int var17;
        for (var14 = 0; var14 < 2; ++var14) {
            var15 = baseX + rand.nextInt(16) + 8;
            var16 = rand.nextInt(128);
            var17 = baseZ + rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlocks.PLANT_YELLOW)).generate(world, rand, new BlockPos(var15, var16, var17));
        }

        if (rand.nextInt(2) == 0) {
            var14 = baseX + rand.nextInt(16) + 8;
            var15 = rand.nextInt(128);
            var16 = baseZ + rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlocks.PLANT_RED)).generate(world, rand, new BlockPos(var14, var15, var16));
        }

        if (rand.nextInt(4) == 0) {
            var14 = baseX + rand.nextInt(16) + 8;
            var15 = rand.nextInt(128);
            var16 = baseZ + rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlocks.MUSHROOM_BROWN)).generate(world, rand, new BlockPos(var14, var15, var16));
        }

        if (rand.nextInt(8) == 0) {
            var14 = baseX + rand.nextInt(16) + 8;
            var15 = rand.nextInt(128);
            var16 = baseZ + rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlocks.MUSHROOM_RED)).generate(world, rand, new BlockPos(var14, var15, var16));
        }

        for (var14 = 0; var14 < 10; ++var14) {
            var15 = baseX + rand.nextInt(16) + 8;
            var16 = rand.nextInt(128);
            var17 = baseZ + rand.nextInt(16) + 8;
            //(new WorldGenReed()).generate(worldObj, rand, var15, var16, var17);
        }

        for (var14 = 0; var14 < 1; ++var14) {
            var15 = baseX + rand.nextInt(16) + 8;
            var16 = rand.nextInt(128);
            var17 = baseZ + rand.nextInt(16) + 8;
            //(new WorldGenCactus()).generate(worldObj, rand, var15, var16, var17);
        }

        for (var14 = 0; var14 < 50; ++var14) {
            var15 = baseX + rand.nextInt(16) + 8;
            var16 = rand.nextInt(rand.nextInt(120) + 8);
            var17 = baseZ + rand.nextInt(16) + 8;
            //(new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, rand, var15, var16, var17);
        }

        for (var14 = 0; var14 < 20; ++var14) {
            var15 = baseX + rand.nextInt(16) + 8;
            var16 = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
            var17 = baseZ + rand.nextInt(16) + 8;
            //(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj, rand, var15, var16, var17);
        }

        for (var14 = baseX; var14 < baseX + 16; ++var14) {
            for (var15 = baseZ; var15 < baseZ + 16; ++var15) {
                var16 = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, var14, var15) + 1;
                BlockPos pos = new BlockPos(var14, var16, var15);
                if (winterMode
                        && var16 > 0
                        && var16 < 128
                        && world.getBlockState(pos).isAir()
                        && world.getBlockState(pos.down()).isSolidBlock(world, pos.down())
                        && !world.getBlockState(pos.down()).isIn(BlockTags.ICE)
                ) {
                    world.setBlockState(pos, AlphaBlocks.SNOW.getDefaultState(), 3);
                    if (world.getBlockState(pos.down()).isOf(AlphaBlocks.GRASS))
                        world.setBlockState(pos.down(), AlphaBlocks.GRASS.getDefaultState().with(AlphaGrassBlock.SNOWY, true), 3);
                }
            }
        }
    }

    @Override
    public int getSeaLevel() {
        return 64;
    }

    @Override
    public int getMinimumY() {
        return 0;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        /*int y = 127;
        while (y >= 0) {
            if (heightmap.getBlockPredicate().test(world.))
            y--;
        }*/
        return 67;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        return null;
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}