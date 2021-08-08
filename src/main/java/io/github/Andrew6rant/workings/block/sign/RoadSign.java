package io.github.Andrew6rant.workings.block.sign;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TorchBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;


public class RoadSign extends TorchBlock {

    public RoadSign(Settings settings, ParticleEffect particle) {
        super(settings, particle);
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random){
    }
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = blockState.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case EAST, WEST, UP -> VoxelShapes.cuboid(0.40625f, 0f, 0f, 0.59375f, 1f, 1f);
            case NORTH, SOUTH, DOWN -> VoxelShapes.cuboid(0f, 0f, 0.40625f, 1f, 1f, 0.59375f);
        };
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }
}
