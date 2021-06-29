package io.github.Andrew6rant.workings.block.pipe;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.List;

public class LargeRodBlock extends LightningRodBlock {
    public LargeRodBlock(AbstractBlock.Settings settings) {
        super(AbstractBlock.Settings.of(Material.METAL).nonOpaque());
    }
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = blockState.get(FACING);
        return switch (dir) {
            case UP, DOWN -> VoxelShapes.cuboid(0.21875f, 0f, 0.21875f, 0.78125f, 1f, 0.78125f);
            case NORTH, SOUTH -> VoxelShapes.cuboid(0.21875f, 0.21875f, 0f, 0.78125f, 0.78125f, 1f);
            case EAST, WEST -> VoxelShapes.cuboid(0f, 0.21875f, 0.21875f, 1f, 0.78125f, 0.78125f);
        };
    }
    @Override
    public void appendTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.workings.pipe.tooltip"));
    }
}