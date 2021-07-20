package io.github.Andrew6rant.workings.block;

import io.github.Andrew6rant.workings.Workings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Pallet extends Block {
    public Pallet(Settings settings) {
        super(Settings.of(Material.WOOD).nonOpaque());
    }

    public static final IntProperty LEVEL = IntProperty.of("level", 1, 4);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(LEVEL);
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return this.getDefaultState().with(LEVEL, 1).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        int level = blockState.get(LEVEL);
        ItemStack stack = player.getStackInHand(hand);
        if (level!=4){
            if(stack.getItem().equals(Workings.PALLET.asItem())) {
                if(!player.isCreative()){
                    stack.setCount(stack.getCount()-1);
                }
                world.setBlockState(pos, blockState.with(LEVEL, level+1));
                player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 1);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        int level = blockState.get(LEVEL);
        return switch (level) {
            case 1 -> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.25f, 1f);
            case 2 -> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.5f, 1f);
            case 3 -> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.75f, 1f);
            case 4 -> VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1f);
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }
}