package net.noiamnotarobot.nostalgicversions.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.sound.AlphaSounds;

import java.util.function.Predicate;

public class AlphaBowItem extends RangedWeaponItem {
    public AlphaBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack arrow = user.getProjectileType(user.getStackInHand(hand));
        if(arrow.getItem() instanceof ArrowItem) {
            world.playSound(null, user.getBlockPos(), AlphaSounds.SOUND_BOW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.random.nextFloat() * 0.4F + 0.8F));
            PersistentProjectileEntity arrowEntity = ((ArrowItem) arrow.getItem()).createArrow(world, arrow, user);
            arrowEntity.setVelocity(
                    -MathHelper.sin(user.getYaw() / 180.0F * (float)Math.PI) * MathHelper.cos(user.getPitch() / 180.0F * (float)Math.PI),
                    -MathHelper.sin(user.getPitch() / 180.0F * (float)Math.PI),
                    MathHelper.cos(user.getYaw() / 180.0F * (float)Math.PI) * MathHelper.cos(user.getPitch() / 180.0F * (float)Math.PI)
            );
            world.spawnEntity(arrowEntity);
            arrow.setCount(arrow.getCount() - 1);

            return TypedActionResult.success(user.getStackInHand(hand));
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 15;
    }
}
