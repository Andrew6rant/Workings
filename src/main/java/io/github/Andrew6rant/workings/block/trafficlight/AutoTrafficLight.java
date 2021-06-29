package io.github.Andrew6rant.workings.block.trafficlight;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class AutoTrafficLight extends TrafficLight {
    public AutoTrafficLight(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(SPEED, 4).with(LIT, false));
    }
    public static final IntProperty SPEED = IntProperty.of("speed", 0, 8);
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(SPEED);
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(LIT);
    }
    @Override
    public void appendTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.workings.auto_traffic_light.tooltip_1"));
        tooltip.add(new TranslatableText("item.workings.auto_traffic_light.tooltip_2"));
        tooltip.add(new TranslatableText("item.workings.auto_traffic_light.tooltip_3"));
        tooltip.add(new TranslatableText("item.workings.auto_traffic_light.tooltip_4"));
    }
    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        int speed = blockState.get(SPEED);
        ItemStack stack = player.getStackInHand(hand);
        if (speed!=8){											//Yes, I know that this code looks like it does the opposite of what
            if(stack.getItem() == Items.FERMENTED_SPIDER_EYE) { //it is supposed to do, but I've accidentally made the .png's in
                if(!player.isCreative()){						//reverse order and would rather not take the time to swap them out
                    stack.setCount(stack.getCount()-1);
                }
                world.setBlockState(pos, blockState.with(SPEED, speed+1));
                return ActionResult.SUCCESS;
            }
        }
        if (speed!=0){
            if(stack.getItem() == Items.SUGAR) {
                if(!player.isCreative()){
                    stack.setCount(stack.getCount()-1);
                }
                world.setBlockState(pos, blockState.with(SPEED, speed-1));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}