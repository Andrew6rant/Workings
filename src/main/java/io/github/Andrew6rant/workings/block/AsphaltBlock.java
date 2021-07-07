package io.github.Andrew6rant.workings.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class AsphaltBlock extends Block {

    public AsphaltBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity){
        if (entity instanceof LivingEntity){
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 1));
        }
        super.onSteppedOn(world, pos, state, entity);
    }
    @Override
    public void appendTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("block.workings.asphalt.tooltip").formatted(Formatting.GRAY));
    }
}
