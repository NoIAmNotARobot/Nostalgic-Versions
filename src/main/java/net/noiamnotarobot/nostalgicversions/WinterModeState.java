package net.noiamnotarobot.nostalgicversions;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class WinterModeState extends PersistentState {
    private boolean winterMode;

    private WinterModeState(boolean winterMode) {
        setWinterMode(winterMode);
    }

    public boolean isWinterMode() {
        return winterMode;
    }

    public void setWinterMode(boolean winterMode) {
        this.winterMode = winterMode;
        markDirty();
    }

    public WinterModeState withDirty() {
        markDirty();
        return this;
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("winterMode", winterMode);
        return nbt;
    }

    public static WinterModeState readNbt(NbtCompound nbt) {
        return new WinterModeState(nbt.getBoolean("winterMode"));
    }

    public static WinterModeState getServerState(MinecraftServer server) {
        ServerWorld world = server.getWorld(NostalgicVersions.ALPHA_DIM);
        return world.getPersistentStateManager().getOrCreate(WinterModeState::readNbt, () -> new WinterModeState(System.currentTimeMillis() % 4 == 0), "winterMode").withDirty();
    }
}