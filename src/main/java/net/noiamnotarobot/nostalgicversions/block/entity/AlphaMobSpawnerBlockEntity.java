package net.noiamnotarobot.nostalgicversions.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AlphaMobSpawnerBlockEntity extends BlockEntity {
    public int delay = 20;
    public String mobID = "minecraft:pig";
    public double yaw;
    public double prevYaw = 0.0D;

    public AlphaMobSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaBlockEntityTypes.MOB_SPAWNER, pos, state);
    }

    public boolean anyPlayerInRange() {
        if (world != null) {
            return world.getClosestPlayer((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 16.0D, false) != null;
        } else return false;
    }

    public static void tick(World world, BlockPos pos, BlockState state, AlphaMobSpawnerBlockEntity blockEntity) {
        blockEntity.prevYaw = blockEntity.yaw;
        if (blockEntity.anyPlayerInRange()) {
            double var1 = (float) pos.getX() + world.random.nextFloat();
            double var3 = (float) pos.getY() + world.random.nextFloat();
            double var5 = (float) pos.getZ() + world.random.nextFloat();
            world.addParticle(ParticleTypes.SMOKE, var1, var3, var5, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, var1, var3, var5, 0.0D, 0.0D, 0.0D);

            for (blockEntity.yaw += 1000.0F / ((float) blockEntity.delay + 200.0F); blockEntity.yaw > 360.0D; blockEntity.prevYaw -= 360.0D) {
                blockEntity.yaw -= 360.0D;
            }

            if (blockEntity.delay == -1) {
                blockEntity.updateDelay();
            }

            if (blockEntity.delay > 0) {
                --blockEntity.delay;
            } else {
                byte var7 = 4;

                for (int var8 = 0; var8 < var7; ++var8) {
                    LivingEntity entity;
                    if (EntityType.get(blockEntity.mobID).isPresent())
                        entity = (LivingEntity) EntityType.get(blockEntity.mobID).get().create(world);
                    else return;
                    if (entity == null) return;

                    int var10 = world.getOtherEntities(null, new Box(blockEntity.pos, blockEntity.pos.add(1, 1, 1)).expand(8.0D, 4.0D, 8.0D), (otherEntity) -> entity.getClass().isInstance(otherEntity)).size();
                    if (var10 >= 6) {
                        blockEntity.updateDelay();
                        return;
                    }

                    double var11 = (double) pos.getX() + (world.random.nextDouble() - world.random.nextDouble()) * 4.0D;
                    double var13 = pos.getY() + world.random.nextInt(3) - 1;
                    double var15 = (double) pos.getZ() + (world.random.nextDouble() - world.random.nextDouble()) * 4.0D;
                    entity.setPos(var11, var13, var15);
                    entity.setYaw(world.random.nextFloat() * 360.0F);
                    entity.setPitch(0.0F);
                    if (!world.getBlockState(entity.getBlockPos()).isSolidBlock(world, entity.getBlockPos()) && !world.getCollisions(entity, entity.getBoundingBox()).iterator().hasNext() && !world.containsFluid(entity.getBoundingBox())) {
                        world.spawnEntity(entity);

                        for (int var17 = 0; var17 < 20; ++var17) {
                            var1 = pos.getX() + 0.5D + ((double) world.random.nextFloat() - 0.5D) * 2.0D;
                            var3 = pos.getY() + 0.5D + ((double) world.random.nextFloat() - 0.5D) * 2.0D;
                            var5 = pos.getZ() + 0.5D + ((double) world.random.nextFloat() - 0.5D) * 2.0D;
                            world.addParticle(ParticleTypes.SMOKE, var1, var3, var5, 0.0D, 0.0D, 0.0D);
                            world.addParticle(ParticleTypes.FLAME, var1, var3, var5, 0.0D, 0.0D, 0.0D);
                        }

                        for (int i = 0; i < 20; ++i) {
                            double var2 = world.random.nextGaussian() * 0.02D;
                            double var4 = world.random.nextGaussian() * 0.02D;
                            double var6 = world.random.nextGaussian() * 0.02D;
                            double var80 = 10.0D;
                            world.addParticle(ParticleTypes.POOF, entity.getX() + (double) (world.random.nextFloat() * entity.getWidth() * 2.0F) - (double) entity.getWidth() - var2 * var80, entity.getY() + (double) (world.random.nextFloat() * entity.getHeight()) - var4 * var80, entity.getZ() + (double) (world.random.nextFloat() * entity.getWidth() * 2.0F) - (double) entity.getWidth() - var6 * var80, var2, var4, var6);
                        }
                        blockEntity.updateDelay();
                    }
                }
            }
        }
    }

    private void updateDelay() {
        if (world != null) {
            this.delay = 200 + world.random.nextInt(600);
        }
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.mobID = nbt.getString("EntityId");
        this.delay = nbt.getShort("Delay");
    }

    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("EntityId", this.mobID);
        nbt.putShort("Delay", (short) this.delay);
    }
}