package net.noiamnotarobot.nostalgicversions.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AlphaFoodItem extends Item {
    private final int healAmount;

    public AlphaFoodItem(Settings settings, int healAmount) {
        super(settings);
        this.healAmount = healAmount;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getStackInHand(hand).setCount(user.getStackInHand(hand).getCount() - 1);
        user.heal(this.healAmount);
        return super.use(world, user, hand);
    }
}
