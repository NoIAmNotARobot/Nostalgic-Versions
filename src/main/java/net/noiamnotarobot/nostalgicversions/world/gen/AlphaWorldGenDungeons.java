package net.noiamnotarobot.nostalgicversions.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaChestBlockEntity;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaMobSpawnerBlockEntity;
import net.noiamnotarobot.nostalgicversions.item.AlphaItems;

import java.util.Random;

public class AlphaWorldGenDungeons extends AlphaWorldGenerator {
    public boolean generate(WorldAccess world, Random rand, BlockPos pos) {
        byte var6 = 3;
        int var7 = rand.nextInt(2) + 2;
        int var8 = rand.nextInt(2) + 2;
        int count = 0;

        int newX;
        int newY;
        int newZ;
        BlockPos pos2;
        for(newX = pos.getX() - var7 - 1; newX <= pos.getX() + var7 + 1; ++newX) {
            for(newY = pos.getY() - 1; newY <= pos.getY() + var6 + 1; ++newY) {
                for(newZ = pos.getZ() - var8 - 1; newZ <= pos.getZ() + var8 + 1; ++newZ) {
                    pos2 = new BlockPos(newX, newY, newZ);
                    BlockState var13 = world.getBlockState(pos2);
                    if (newY == pos.getY() - 1 && !var13.isSolidBlock(world, pos2)) {
                        return false;
                    }

                    if (newY == pos.getY() + var6 + 1 && !var13.isSolidBlock(world, pos2)) {
                        return false;
                    }

                    if ((newX == pos.getX() - var7 - 1 || newX == pos.getX() + var7 + 1 || newZ == pos.getZ() - var8 - 1 || newZ == pos.getZ() + var8 + 1) && newY == pos.getY() && world.getBlockState(pos2).isAir() && world.getBlockState(pos2.up()).isAir()) {
                        count++;
                    }
                }
            }
        }

        if (count >= 1 && count <= 5) {
            for(newX = pos.getX() - var7 - 1; newX <= pos.getX() + var7 + 1; ++newX) {
                for(newY = pos.getY() + var6; newY >= pos.getY() - 1; --newY) {
                    for(newZ = pos.getZ() - var8 - 1; newZ <= pos.getZ() + var8 + 1; ++newZ) {
                        pos2 = new BlockPos(newX, newY, newZ);
                        if (newX != pos.getX() - var7 - 1 && newY != pos.getY() - 1 && newZ != pos.getZ() - var8 - 1 && newX != pos.getX() + var7 + 1 && newY != pos.getY() + var6 + 1 && newZ != pos.getZ() + var8 + 1) {
                            world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 3);
                        } else if (newY >= 0 && !world.getBlockState(pos2.down()).isSolidBlock(world, pos2.down())) {
                            world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 3);
                        } else if (world.getBlockState(pos2).isSolidBlock(world, pos2)) {
                            if (newY == pos.getY() - 1 && rand.nextInt(4) != 0) {
                                world.setBlockState(pos2, AlphaBlocks.COBBLESTONE_MOSSY.getDefaultState(), 3);
                            } else {
                                world.setBlockState(pos2, AlphaBlocks.COBBLESTONE.getDefaultState(), 3);
                            }
                        }
                    }
                }
            }

            label110:
            for(newX = 0; newX < 2; ++newX) {
                for(newY = 0; newY < 3; ++newY) {
                    newZ = pos.getX() + rand.nextInt(var7 * 2 + 1) - var7;
                    int var14 = pos.getZ() + rand.nextInt(var8 * 2 + 1) - var8;
                    pos2 = new BlockPos(newZ, pos.getY(), var14);
                    if (world.getBlockState(pos2).isAir()) {
                        int var15 = 0;
                        if (world.getBlockState(pos2.north()).isSolidBlock(world, pos2.north())) {
                            ++var15;
                        }

                        if (world.getBlockState(pos2.south()).isSolidBlock(world, pos2.south())) {
                            ++var15;
                        }

                        if (world.getBlockState(pos2.east()).isSolidBlock(world, pos2.east())) {
                            ++var15;
                        }

                        if (world.getBlockState(pos2.west()).isSolidBlock(world, pos2.west())) {
                            ++var15;
                        }

                        if (var15 == 1) {
                            world.setBlockState(pos2, AlphaBlocks.CHEST.getDefaultState(), 3);
                            BlockEntity var16 = world.getBlockEntity(pos2);
                            int var17 = 0;

                            while(true) {
                                if (var17 >= 8) {
                                    continue label110;
                                }

                                ItemStack var18 = this.pickCheckLootItem(rand);
                                if (var16 instanceof AlphaChestBlockEntity) {
                                    ((AlphaChestBlockEntity)var16).setStack(rand.nextInt(((AlphaChestBlockEntity)var16).size()), var18);
                                }

                                ++var17;
                            }
                        }
                    }
                }
            }

            world.setBlockState(pos, AlphaBlocks.MOB_SPAWNER.getDefaultState(), 3);
            BlockEntity spawner = world.getBlockEntity(pos);
            if (spawner instanceof AlphaMobSpawnerBlockEntity) {
                ((AlphaMobSpawnerBlockEntity)spawner).mobID = pickMobSpawner(rand);
            }
            return true;
        } else {
            return false;
        }
    }

    private ItemStack pickCheckLootItem(Random rand) {
        return switch (rand.nextInt(11)) {
            case 0 -> new ItemStack(AlphaItems.SADDLE);
            case 1 -> new ItemStack(AlphaItems.INGOT_IRON, rand.nextInt(4) + 1);
            case 2 -> new ItemStack(AlphaItems.BREAD);
            case 3 -> new ItemStack(AlphaItems.WHEAT, rand.nextInt(4) + 1);
            case 4 -> new ItemStack(AlphaItems.GUNPOWDER, rand.nextInt(4) + 1);
            case 5 -> new ItemStack(AlphaItems.SILK, rand.nextInt(4) + 1);
            case 6 -> new ItemStack(AlphaItems.BUCKET_EMPTY);
            case 7 -> (rand.nextInt(100) == 0) ? new ItemStack(AlphaItems.APPLE_GOLD) : ItemStack.EMPTY;
            case 8 -> (rand.nextInt(2) == 0)
                    ? new ItemStack(AlphaItems.REDSTONE, rand.nextInt(4) + 1)
                    : ItemStack.EMPTY;
            case 9 -> (rand.nextInt(10) == 0)
                    ? new ItemStack(rand.nextBoolean() ? AlphaItems.RECORD_13 : AlphaItems.RECORD_CAT)
                    : ItemStack.EMPTY;
            default -> ItemStack.EMPTY;
        };
    }

    private String pickMobSpawner(Random rand) {
        return switch (rand.nextInt(4)) {
            case 0 -> "minecraft:skeleton";
            case 1, 2 -> "minecraft:zombie";
            case 3 -> "minecraft:spider";
            default -> "this should probably crash but its fine just make a blank one";
        };
    }
}