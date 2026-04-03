package net.noiamnotarobot.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.noiamnotarobot.MinecraftAlpha;
import net.noiamnotarobot.block.AlphaBlocks;
import net.noiamnotarobot.world.gen.noise.NoiseGeneratorOctaves;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AlphaChunkGenerator extends ChunkGenerator {
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
    //private MapGenBase caveGenerator = new MapGenCaves();
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise6;
    double[] noise7;

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

    private void initialize(long seed) {
        rand = new Random(seed);
        noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 10);
        noiseGen7 = new NoiseGeneratorOctaves(rand, 16);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        int var1 = chunk.getPos().x;
        int var2 = chunk.getPos().z;
        byte var4 = 64;
        double var5 = 1.0D / 32.0D;
        this.sandNoise = this.noiseGen4.generateNoiseOctaves(this.sandNoise, var1 * 16, var2 * 16, 0.0D, 16, 16, 1, var5, var5, 1.0D);
        this.gravelNoise = this.noiseGen4.generateNoiseOctaves(this.gravelNoise, var2 * 16, 109.0134D, var1 * 16, 16, 1, 16, var5, 1.0D, var5);
        this.stoneNoise = this.noiseGen5.generateNoiseOctaves(this.stoneNoise, var1 * 16, var2 * 16, 0.0D, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);

        for(int x = 0; x < 16; ++x) {
            for(int z = 0; z < 16; ++z) {
                boolean var9 = this.sandNoise[x + z * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                boolean var10 = this.gravelNoise[x + z * 16] + this.rand.nextDouble() * 0.2D > 3.0D;
                int var11 = (int)(this.stoneNoise[x + z * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var12 = -1;
                Block topLayer = AlphaBlocks.GRASS;
                Block middleLayer = AlphaBlocks.DIRT;

                for(int y = 127; y >= 0; --y) {
                    if(y <= this.rand.nextInt(6) - 1) {
                        chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, AlphaBlocks.BEDROCK.getDefaultState(), false);
                    } else {
                        Block var17 = chunk.getSection(chunk.getSectionIndex(y)).getBlockState(x, y % 16, z).getBlock();
                        if(var17 == Blocks.AIR) {
                            var12 = -1;
                        } else if(var17 == AlphaBlocks.STONE) {
                            if(var12 == -1) {
                                if(var11 <= 0) {
                                    topLayer = Blocks.AIR;
                                    middleLayer = AlphaBlocks.STONE;
                                } else if(y >= var4 - 4 && y <= var4 + 1) {
                                    topLayer = AlphaBlocks.GRASS;
                                    middleLayer = AlphaBlocks.DIRT;
                                    if(var10) {
                                        topLayer = Blocks.AIR;
                                    }

                                    if(var10) {
                                        middleLayer = AlphaBlocks.GRAVEL;
                                    }

                                    if(var9) {
                                        topLayer = AlphaBlocks.SAND;
                                    }

                                    if(var9) {
                                        middleLayer = AlphaBlocks.SAND;
                                    }
                                }

                                if(y < var4 && topLayer == Blocks.AIR) {
                                    topLayer = AlphaBlocks.WATER_STILL;
                                }

                                var12 = var11;
                                if(y >= var4 - 1) {
                                    chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, topLayer.getDefaultState(), false);
                                } else {
                                    chunk.getSection(chunk.getSectionIndex(y)).setBlockState(x, y % 16, z, middleLayer.getDefaultState(), false);
                                }
                            } else if(var12 > 0) {
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
        initialize(478868574082066804L);
        byte var5 = 64;
        int var6 = 5;
        byte var7 = 17;
        int var8 = 5;
        double[] noiseArray = this.initializeNoiseField(chunk.getPos().x * 4, chunk.getPos().z * 4, var6, var7, var8);

        for(int i = 0; i < 4; ++i) {
            for(int var10 = 0; var10 < 4; ++var10) {
                for(int var11 = 0; var11 < 16; ++var11) {
                    double var12 = 0.125D;
                    double var14 = noiseArray[((i) * var8 + var10) * var7 + var11];
                    double var16 = noiseArray[((i) * var8 + var10 + 1) * var7 + var11];
                    double var18 = noiseArray[((i + 1) * var8 + var10) * var7 + var11];
                    double var20 = noiseArray[((i + 1) * var8 + var10 + 1) * var7 + var11];
                    double var22 = (noiseArray[(i * var8 + var10) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (noiseArray[(i * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (noiseArray[((i + 1) * var8 + var10) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (noiseArray[((i + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

                    for(int var30 = 0; var30 < 8; ++var30) {
                        double var31 = 0.25D;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for(int var41 = 0; var41 < 4; ++var41) {
                            int var42 = var41 + i * 4 << 11 | var10 * 4 << 7 | var11 * 8 + var30;
                            short var43 = 128;
                            double var44 = 0.25D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for(int var50 = 0; var50 < 4; ++var50) {
                                Block var51 = Blocks.AIR;
                                if(var11 * 8 + var30 < var5) {
                                    if(MinecraftAlpha.winterMode && var11 * 8 + var30 >= var5 - 1) {
                                        var51 = AlphaBlocks.ICE;
                                    } else {
                                        var51 = AlphaBlocks.WATER_STILL;
                                    }
                                }

                                if(var46 > 0.0D) {
                                    var51 = AlphaBlocks.STONE;
                                }

                                chunk.getSection(chunk.getSectionIndex(var11 * 8 + var30))
                                        .setBlockState(
                                                var41 + i * 4,
                                                (var11 * 8 + var30) % 16,
                                                var50 + var10 * 4,
                                                var51.getDefaultState(),
                                                false
                                        );
                                var42 += var43;
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
        double[] var1 = new double[var5 * var6 * var7];
        double var8 = 684.412D;
        double var10 = 684.412D;
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, var2, 0, var4, var5, 1, var7, 1.0D, 0.0D, 1.0D);
        this.noise7 = this.noiseGen7.generateNoiseOctaves(this.noise7, var2, 0, var4, var5, 1, var7, 100.0D, 0.0D, 100.0D);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, var2, 0, var4, var5, var6, var7, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, var2, 0, var4, var5, var6, var7, var8, var10, var8);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, var2, 0, var4, var5, var6, var7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;

        for(int var14 = 0; var14 < var5; ++var14) {
            for(int var15 = 0; var15 < var7; ++var15) {
                double var16 = (this.noise6[var13] + 256.0D) / 512.0D;
                if(var16 > 1.0D) {
                    var16 = 1.0D;
                }

                double var20 = this.noise7[var13] / 8000.0D;
                if(var20 < 0.0D) {
                    var20 = -var20;
                }

                var20 = var20 * 3.0D - 3.0D;
                if(var20 < 0.0D) {
                    var20 /= 2.0D;
                    if(var20 < -1.0D) {
                        var20 = -1.0D;
                    }

                    var20 /= 1.4D;
                    var20 /= 2.0D;
                    var16 = 0.0D;
                } else {
                    if(var20 > 1.0D) {
                        var20 = 1.0D;
                    }

                    var20 /= 6.0D;
                }

                var16 += 0.5D;
                var20 = var20 * (double)var6 / 16.0D;
                double var22 = (double)var6 / 2.0D + var20 * 4.0D;
                ++var13;

                for(int var24 = 0; var24 < var6; ++var24) {
                    double var25;
                    double var27 = ((double)var24 - var22) * 12.0D / var16;
                    if(var27 < 0.0D) {
                        var27 *= 4.0D;
                    }

                    double var29 = this.noise1[var12] / 512.0D;
                    double var31 = this.noise2[var12] / 512.0D;
                    double var33 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;
                    if(var33 < 0.0D) {
                        var25 = var29;
                    } else if(var33 > 1.0D) {
                        var25 = var31;
                    } else {
                        var25 = var29 + (var31 - var29) * var33;
                    }

                    var25 -= var27;
                    double var35;
                    if(var24 > var6 - 4) {
                        var35 = ((float)(var24 - (var6 - 4)) / 3.0F);
                        var25 = var25 * (1.0D - var35) + -10.0D * var35;
                    }

                    var1[var12] = var25;
                    ++var12;
                }
            }
        }

        return var1;
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
        return 0;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        return null;
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}