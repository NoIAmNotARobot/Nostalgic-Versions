package net.noiamnotarobot.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.noiamnotarobot.block.AlphaBlocks;
import net.noiamnotarobot.sound.AlphaSounds;

public class AlphaFlintAndSteelItem extends Item {
    public AlphaFlintAndSteelItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos().offset(context.getSide());

        if (world.getBlockState(pos).isAir()) {
            world.playSound(
                    null,
                    (double) pos.getX() + 0.5D,
                    (double) pos.getY() + 0.5D,
                    (double) pos.getZ() + 0.5D,
                    AlphaSounds.SOUND_FIRE_IGNITE,
                    SoundCategory.PLAYERS,
                    1.0F,
                    world.random.nextFloat() * 0.4F + 0.8F
            );
            world.setBlockState(pos, AlphaBlocks.FIRE.getDefaultState());
        }

        if (player != null) context.getStack().damage(1, player, (player2) -> {
        });
        return ActionResult.SUCCESS;
    }
}