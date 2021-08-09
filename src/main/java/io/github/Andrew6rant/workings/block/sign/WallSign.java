package io.github.Andrew6rant.workings.block.sign;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
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

import static net.minecraft.block.RedstoneLampBlock.LIT;

public class WallSign extends WallTorchBlock {
    public WallSign(Settings settings, ParticleEffect particleEffect) {
        super(settings, particleEffect);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random){
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(Properties.LIT);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = blockState.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case NORTH-> VoxelShapes.cuboid(0f, 0f, 0.8125f, 1f, 1f, 1f);
            case SOUTH-> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 0.1875f);
            case EAST -> VoxelShapes.cuboid(0f, 0f, 0f, 0.1875f, 1f, 1f);
            case WEST -> VoxelShapes.cuboid(0.8125f, 0f, 0f, 1f, 1f, 1f);
            default -> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1f);
        };
    }
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean bl = state.get(LIT);
            if (bl != world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.getBlockTickScheduler().schedule(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(LIT), 2);
                }
            }
        }
    }
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT), 2);
        }
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }
}
