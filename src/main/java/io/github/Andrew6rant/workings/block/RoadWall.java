package io.github.Andrew6rant.workings.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class RoadWall extends FenceBlock {
    public RoadWall(Settings settings) {
        super(settings);
    }
    public static final IntProperty FLAG = IntProperty.of("flag", 0, 2);

    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FLAG);
        stateManager.add(NORTH);
        stateManager.add(SOUTH);
        stateManager.add(EAST);
        stateManager.add(WEST);
        stateManager.add(WATERLOGGED);

    }
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return this.getDefaultState().with(FLAG, 0).with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false).with(WATERLOGGED, false);
    }
}
