package io.github.Andrew6rant.workings.block.trafficlight;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TrafficLight extends RedstoneLampBlock {
    public TrafficLight(Settings settings) {
        super(settings.emissiveLighting((state, world, pos) -> true));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(Properties.LIT);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.3125f, 0f, 0.3125f, 0.6875f, 1f, 0.6875f);
    }
}