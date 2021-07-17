package io.github.Andrew6rant.workings.block;

import io.github.Andrew6rant.workings.Workings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Pallet extends Block{
    public Pallet(Settings settings) {
        super(Settings.of(Material.WOOD).nonOpaque());
        setDefaultState(stateManager.getDefaultState().with(LVL1NORTH, 1).with(LVL2NORTH, 0).with(LVL3NORTH, 0).with(LVL4NORTH, 0));
    }

    public static final IntProperty LVL1NORTH = IntProperty.of("lvl1", 1, 2);
    public static final IntProperty LVL2NORTH = IntProperty.of("lvl2", 0, 2);
    public static final IntProperty LVL3NORTH = IntProperty.of("lvl3", 0, 2);
    public static final IntProperty LVL4NORTH = IntProperty.of("lvl4", 0, 2);


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(LVL1NORTH);
        stateManager.add(LVL2NORTH);
        stateManager.add(LVL3NORTH);
        stateManager.add(LVL4NORTH);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx, PlayerEntity player){
        Direction facing = player.getHorizontalFacing();
        return switch (facing){
            case NORTH, SOUTH, UP -> (BlockState)this.getDefaultState().with(LVL1NORTH, 1).with(LVL2NORTH, 0).with(LVL3NORTH, 0).with(LVL4NORTH, 0);
            case EAST, WEST, DOWN -> (BlockState)this.getDefaultState().with(LVL1NORTH, 2).with(LVL2NORTH, 0).with(LVL3NORTH, 0).with(LVL4NORTH, 0);
        };
    }
    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        int lvl2 = blockState.get(LVL2NORTH);
        int lvl3 = blockState.get(LVL3NORTH);
        int lvl4 = blockState.get(LVL4NORTH);
        ItemStack stack = player.getStackInHand(hand);
        Direction facing = player.getHorizontalFacing();
        if (lvl4 == 0){
            if(stack.getItem().equals(Workings.PALLET.asItem())) {
                if(!player.isCreative()){
                    stack.setCount(stack.getCount()-1);
                }
                    if (lvl2 == 0) {
                        switch (facing){
                            case NORTH, SOUTH -> world.setBlockState(pos, blockState.with(LVL2NORTH, 1));
                            case EAST, WEST -> world.setBlockState(pos, blockState.with(LVL2NORTH, 2));
                        };
                    }
                    else {
                        if (lvl3 == 0) {
                            switch (facing){
                                case NORTH, SOUTH -> world.setBlockState(pos, blockState.with(LVL3NORTH, 1));
                                case EAST, WEST -> world.setBlockState(pos, blockState.with(LVL3NORTH, 2));
                            };
                        }
                        else{
                            switch (facing){
                                case NORTH, SOUTH -> world.setBlockState(pos, blockState.with(LVL4NORTH, 1));
                                case EAST, WEST -> world.setBlockState(pos, blockState.with(LVL4NORTH, 2));
                            };
                        }
                    }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        int lvl2 = blockState.get(LVL2NORTH);
        int lvl3 = blockState.get(LVL3NORTH);
        int lvl4 = blockState.get(LVL4NORTH);
        if (lvl4 != 0) {
            return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1f);
        }
        else if (lvl3 != 0){
            return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.75f, 1f);
        }
        else if (lvl2 != 0){
            return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.5f, 1f);
        }
        else {
            return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.25f, 1f);
        }
    }
}