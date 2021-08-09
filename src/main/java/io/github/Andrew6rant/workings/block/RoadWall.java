package io.github.Andrew6rant.workings.block;

import io.github.Andrew6rant.workings.Workings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        int flag = blockState.get(FLAG);
        ItemStack stack = player.getStackInHand(hand);
        if (flag!=0) {
            switch(flag){
                case 1 -> world.setBlockState(pos, blockState.with(FLAG, 2));
                case 2 -> world.setBlockState(pos, blockState.with(FLAG, 1));
            }
            return ActionResult.SUCCESS;
        }
        else {
            if(stack.getItem().equals(Workings.FLAG)) {
                if(!player.isCreative()){
                    stack.setCount(stack.getCount()-1);
                }
                world.setBlockState(pos, blockState.with(FLAG, 1));
                player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 1);
            }
        }
        return ActionResult.SUCCESS;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx){
        return this.getDefaultState().with(FLAG, 0).with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false).with(WATERLOGGED, false);
    }
}
