package net.noiamnotarobot.nostalgicversions.item.teleporter;

import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;

public class TeleporterAlphaItem extends TeleporterItem {
    public TeleporterAlphaItem(Settings settings) {
        super(settings);
    }

    public RegistryKey<World>[] getValidSourceWorlds() {
        return new RegistryKey[] {World.OVERWORLD};
    }

    public RegistryKey<World> getDestinationWorld(RegistryKey<World> sourceWorld) {
        return NostalgicVersions.ALPHA_DIM;
    }

    public Text showErrorMessage(RegistryKey<World> sourceWorld) {
        return sourceWorld == NostalgicVersions.ALPHA_DIM
                ? Text.translatable(NostalgicVersions.TranslationKeys.ALPHA_TELEPORT_ERROR_OLD)
                : Text.translatable(NostalgicVersions.TranslationKeys.ALPHA_TELEPORT_ERROR_ELSE);
    }
}