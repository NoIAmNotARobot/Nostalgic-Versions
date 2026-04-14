package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.AlphaWorkbenchScreenHandler;

public class AlphaWorkbenchBlock extends CraftingTableBlock {
    public AlphaWorkbenchBlock(Settings settings) {
        super(settings);
    }

    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                new AlphaWorkbenchScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)),
                Text.translatable("container.crafting"));
    }
}