package net.noiamnotarobot.nostalgicversions.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;
import net.noiamnotarobot.nostalgicversions.block.AlphaCropsBlock;

public class AlphaSeedsItem extends Item {
    public AlphaSeedsItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        super.useOnBlock(context);

        if(context.getSide() != Direction.UP) {
            return ActionResult.PASS;
        } else {
            if(((AlphaCropsBlock)AlphaBlocks.CROPS).canThisPlantGrowOnThisBlock(context.getWorld().getBlockState(context.getBlockPos()))) {
                context.getWorld().setBlockState(context.getBlockPos().up(), AlphaBlocks.CROPS.getDefaultState());
                context.getStack().setCount(context.getStack().getCount() - 1);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS;
            }
        }
    }
}