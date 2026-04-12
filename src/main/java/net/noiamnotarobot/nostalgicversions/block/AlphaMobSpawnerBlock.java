package net.noiamnotarobot.nostalgicversions.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaBlockEntityTypes;
import net.noiamnotarobot.nostalgicversions.block.entity.AlphaMobSpawnerBlockEntity;

public class AlphaMobSpawnerBlock extends BlockWithEntity {
    protected AlphaMobSpawnerBlock(Settings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlphaMobSpawnerBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, AlphaBlockEntityTypes.MOB_SPAWNER, AlphaMobSpawnerBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
