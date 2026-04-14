package net.noiamnotarobot.nostalgicversions.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.noiamnotarobot.nostalgicversions.block.AlphaBlocks;

public class AlphaHoeItem extends Item {
    public AlphaHoeItem(Settings settings, int material) {
        super(settings.maxDamage(32 << material));
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState var8 = context.getWorld().getBlockState(context.getBlockPos());
        BlockState var9 = context.getWorld().getBlockState(context.getBlockPos().up());
        if ((var9.isSolidBlock(context.getWorld(), context.getBlockPos().up()) || !var8.isIn(BlockTags.DIRT))) {
            return ActionResult.PASS;
        } else {
            Block var10 = AlphaBlocks.TILLED_FIELD;
            context.getWorld().playSound(null, context.getBlockPos(), var10.getSoundGroup(var10.getDefaultState()).getStepSound(), SoundCategory.PLAYERS, (var10.getSoundGroup(var10.getDefaultState()).getVolume() + 1.0F) / 2.0F, var10.getSoundGroup(var10.getDefaultState()).getPitch() * 0.8F);
            context.getWorld().setBlockState(context.getBlockPos(), var10.getDefaultState());
            if (context.getPlayer() != null) {
                context.getStack().damage(1, context.getPlayer(), (entity) -> {
                });
            }
            if (context.getWorld().random.nextInt(8) == 0 && var8.isOf(AlphaBlocks.GRASS)) {
                float x = context.getWorld().getRandom().nextFloat() * 0.7F + 0.15F;
                float y = 1.2F;
                float z = context.getWorld().getRandom().nextFloat() * 0.7F + 0.15F;
                ItemEntity item = new ItemEntity(context.getWorld(), (float) context.getBlockPos().getX() + x, (float) context.getBlockPos().getY() + y, (float) context.getBlockPos().getZ() + z, new ItemStack(AlphaItems.SEEDS));
                item.setPickupDelay(10);
                context.getWorld().spawnEntity(item);
            }

            return ActionResult.SUCCESS;
        }
    }
}