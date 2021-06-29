package io.github.Andrew6rant.workings.block.sign;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class WallSignBlock extends RoadSignBlock {
    public WallSignBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = blockState.get(FACING);
        return switch (dir) {
            case EAST -> VoxelShapes.cuboid(0f, 0f, 0f, 0.1875f, 1f, 1f);
            case WEST -> VoxelShapes.cuboid(0.8125f, 0f, 0f, 1f, 1f, 1f);
            case NORTH -> VoxelShapes.cuboid(0f, 0f, 0.8125f, 1f, 1f, 1f);
            case SOUTH -> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 0.1875f);
            default -> VoxelShapes.fullCube();
        };
    }
}