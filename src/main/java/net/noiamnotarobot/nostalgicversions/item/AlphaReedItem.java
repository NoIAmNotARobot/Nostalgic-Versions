package net.noiamnotarobot.nostalgicversions.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

public class AlphaReedItem extends Item {
    public AlphaReedItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        if (!context.getWorld().getBlockState(context.getBlockPos()).isOf(AlphaBlocks.SNOW)) {
            pos = pos.offset(context.getSide());
        }

        if (context.getStack().getCount() == 0) {
            return ActionResult.PASS;
        } else {
            if (AlphaBlocks.REED.canPlaceAt(context.getWorld().getBlockState(pos), context.getWorld(), pos)) {
                if (context.getWorld().setBlockState(pos, AlphaBlocks.REED.getDefaultState())) {
                    AlphaBlocks.REED.onPlaced(context.getWorld(), pos, context.getWorld().getBlockState(pos), context.getPlayer(), context.getStack());
                    context.getWorld().playSound(null, context.getBlockPos(), AlphaBlocks.REED.getSoundGroup(AlphaBlocks.REED.getDefaultState()).getStepSound(), SoundCategory.PLAYERS, (AlphaBlocks.REED.getSoundGroup(AlphaBlocks.REED.getDefaultState()).getVolume() + 1.0F) / 2.0F, AlphaBlocks.REED.getSoundGroup(AlphaBlocks.REED.getDefaultState()).getPitch() * 0.8F);
                    context.getStack().setCount(context.getStack().getCount() - 1);
                }
            }

            return ActionResult.SUCCESS;
        }
    }
}