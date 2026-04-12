package net.noiamnotarobot.nostalgicversions.item.teleporter;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;

import java.util.List;

public abstract class TeleporterItem extends Item {
    public TeleporterItem(Settings settings) {
        super(settings);
    }

    public abstract RegistryKey<World>[] getValidSourceWorlds();
    public abstract RegistryKey<World> getDestinationWorld(RegistryKey<World> sourceWorld);
    public abstract Text showErrorMessage(RegistryKey<World> sourceWorld);

    public TypedActionResult<ItemStack> use(World sourceWorld, PlayerEntity user, Hand hand) {
        if (sourceWorld instanceof ServerWorld) {
            for (RegistryKey<World> world2 : getValidSourceWorlds()) {
                if (sourceWorld.getRegistryKey() == world2) {
                    ServerWorld destWorld = ((ServerWorld) sourceWorld).getServer().getWorld(getDestinationWorld(world2));
                    if (destWorld != null) {
                        user.getStackInHand(hand).setCount(user.getStackInHand(hand).getCount() - 1);
                        int yOffset = destWorld.getChunk(user.getChunkPos().x, user.getChunkPos().z)
                                .sampleHeightmap(Heightmap.Type.WORLD_SURFACE, user.getBlockX(), user.getBlockZ()) + 1 - user.getBlockY();
                        for (int x = -NostalgicVersions.TELEPORTER_RANGE; x <= NostalgicVersions.TELEPORTER_RANGE; x++) {
                            for (int y = -NostalgicVersions.TELEPORTER_RANGE; y <= +NostalgicVersions.TELEPORTER_RANGE; y++) {
                                for (int z = -NostalgicVersions.TELEPORTER_RANGE; z <= +NostalgicVersions.TELEPORTER_RANGE; z++) {
                                    if (x * x + y * y + z * z <= Math.pow(NostalgicVersions.TELEPORTER_RANGE, 2)) {
                                        BlockPos newPos = user.getBlockPos().add(new Vec3i(x, y, z));
                                        swapBlock(sourceWorld, destWorld, newPos, newPos.add(0, yOffset, 0));
                                    }
                                }
                            }
                        }
                        List<Entity> sourceEntities = getEntitiesToTransport(sourceWorld, user.getPos());
                        List<Entity> destEntities = getEntitiesToTransport(destWorld, user.getPos().add(0, yOffset, 0));
                        transportEntities(destWorld, sourceEntities, yOffset);
                        transportEntities(((ServerWorld) sourceWorld).toServerWorld(), destEntities, -yOffset);
                    }
                    return TypedActionResult.success(user.getStackInHand(hand));
                }
            }
            user.sendMessage(showErrorMessage(sourceWorld.getRegistryKey()), true);
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    private void swapBlock(World sourceWorld, World destWorld, BlockPos sourcePos, BlockPos destPos) {
        if (sourceWorld.getBlockEntity(sourcePos) != null) return;
        if (destWorld.getBlockEntity(destPos) != null) return;
        BlockState destState = destWorld.getBlockState(destPos);
        destWorld.setBlockState(destPos, sourceWorld.getBlockState(sourcePos));
        sourceWorld.setBlockState(sourcePos, destState);
    }

    private List<Entity> getEntitiesToTransport(World world, Vec3d pos) {
        return world.getOtherEntities(null, new Box(
                pos.x - NostalgicVersions.TELEPORTER_RANGE,
                pos.y - NostalgicVersions.TELEPORTER_RANGE,
                pos.z - NostalgicVersions.TELEPORTER_RANGE,
                pos.x + NostalgicVersions.TELEPORTER_RANGE,
                pos.y + NostalgicVersions.TELEPORTER_RANGE,
                pos.z + NostalgicVersions.TELEPORTER_RANGE
        ), (entity) -> entity.squaredDistanceTo(pos) < NostalgicVersions.TELEPORTER_RANGE);
    }

    private void transportEntities(ServerWorld world, List<Entity> entities, int yOffset) {
        entities.forEach((entity) -> FabricDimensions.teleport(entity, world, new TeleportTarget(
                new Vec3d(
                        entity.getX(),
                        entity.getY() + yOffset,
                        entity.getZ()),
                entity.getVelocity(),
                entity.getYaw(),
                entity.getPitch())
        ));
    }
}