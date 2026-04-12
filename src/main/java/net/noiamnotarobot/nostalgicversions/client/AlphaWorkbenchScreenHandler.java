package net.noiamnotarobot.nostalgicversions.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.*;

public class AlphaWorkbenchScreenHandler extends CraftingScreenHandler {
    public AlphaWorkbenchScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(syncId, playerInventory, context);
    }

    public boolean canUse(PlayerEntity player) {
        return player.isAlive();
    }

    public RecipeBookCategory getCategory() {
        return null;
    }
}