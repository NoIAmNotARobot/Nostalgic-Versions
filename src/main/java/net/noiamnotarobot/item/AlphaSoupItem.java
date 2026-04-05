package net.noiamnotarobot.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AlphaSoupItem extends AlphaFoodItem {
    public AlphaSoupItem(Settings settings, int healAmount) {
        super(settings, healAmount);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        super.use(world, user, hand);
        return TypedActionResult.pass(new ItemStack(AlphaItems.BOWL_EMPTY));
    }
}
