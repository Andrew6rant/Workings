package io.github.Andrew6rant.workings.block.pipe;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PipeBlock extends HorizontalFacingBlock {
    public PipeBlock(Settings settings) {
        super(Settings.of(Material.METAL).nonOpaque());
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = blockState.get(FACING);
        return switch (dir) {
            case NORTH -> VoxelShapes.cuboid(-0.0625f, -0.0625f, 0f, 1f, 1f, 1f);
            case SOUTH -> VoxelShapes.cuboid(0f, -0.0625f, 0f, 1.0625f, 1f, 1f);
            case EAST -> VoxelShapes.cuboid(0f, -0.0625f, -0.0625f, 1f, 1f, 1f);
            case WEST -> VoxelShapes.cuboid(0f, -0.0625f, 0f, 1f, 1f, 1.0625f);
            default -> VoxelShapes.fullCube();
        };
    }
}