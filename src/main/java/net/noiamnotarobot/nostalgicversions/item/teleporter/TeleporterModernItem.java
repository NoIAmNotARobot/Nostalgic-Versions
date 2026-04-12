package net.noiamnotarobot.nostalgicversions.item.teleporter;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;

public class TeleporterModernItem extends TeleporterItem {
    public TeleporterModernItem(Settings settings) {
        super(settings);
    }

    public RegistryKey<World>[] getValidSourceWorlds() {
        return new RegistryKey[] {RegistryKey.of(RegistryKeys.WORLD, NostalgicVersions.Util.id("alpha_dim"))};
    }

    public RegistryKey<World> getDestinationWorld(RegistryKey<World> sourceWorld) {
        return World.OVERWORLD;
    }

    public Text showErrorMessage(RegistryKey<World> sourceWorld) {
        return Text.translatable(NostalgicVersions.TranslationKeys.MODERN_TELEPORT_ERROR);
    }
}
