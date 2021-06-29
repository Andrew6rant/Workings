package io.github.Andrew6rant.workings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;


public class Workings implements ModInitializer {

	public static final PillarBlock BLOCK_OF_STICKS = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f));
	public static final Item BUNDLE_OF_STICKS = new Item(new Item.Settings().group(Workings.ITEM_GROUP));
	public static final Pallet PALLET = new Pallet(FabricBlockSettings.of(Material.WOOD).strength(2.0f));
	public static final FenceBlock JERSEY_WALL = new FenceBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final FenceBlock HIGHWAY_WALL = new FenceBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final ConeBlock TRAFFIC_POLE = new ConeBlock(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final DrumBlock TRAFFIC_DRUM = new DrumBlock(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final TrafficLight TRAFFIC_LIGHT = new TrafficLight(FabricBlockSettings.of(Material.DECORATION).strength(1.0f).emissiveLighting((state, world, pos) -> true).luminance(15));
	public static final AutoTrafficLight TRAFFIC_LIGHT_AUTO = new AutoTrafficLight(FabricBlockSettings.of(Material.DECORATION).strength(1.0f).emissiveLighting((state, world, pos) -> true).luminance(15));
	public static final PipeBlock BLOCK_OF_PIPES = new PipeBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final SmallRodBlock IRON_PIPE_SMALL = new SmallRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final MidRodBlock IRON_PIPE = new MidRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final LargeRodBlock IRON_PIPE_LARGE = new LargeRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final PipeBlock BLOCK_OF_COPPER_PIPES = new PipeBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final SmallRodBlock COPPER_PIPE_SMALL = new SmallRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final MidRodBlock COPPER_PIPE = new MidRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final LargeRodBlock COPPER_PIPE_LARGE = new LargeRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock STOP_SIGN = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final WallSignBlock STOP_SIGN_WALL = new WallSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_CROSS = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_SHOVEL = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_FLAG = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));

	public static final Block PAVEMENT = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final Block ASPHALT = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier("workings", "general"),
			() -> new ItemStack(Workings.TRAFFIC_LIGHT_AUTO));

	public static class AutoTrafficLight extends TrafficLight {
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

	public static class WallSignBlock extends RoadSignBlock {
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
	public static class RoadSignBlock extends HorizontalFacingBlock {
		public RoadSignBlock(Settings settings) {
			super(Settings.of(Material.METAL).nonOpaque());
		}
		protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
			stateManager.add(Properties.HORIZONTAL_FACING);
		}
		public BlockState getPlacementState(ItemPlacementContext ctx){
			return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			Direction dir = blockState.get(FACING);
			return switch (dir) {
				case EAST, WEST -> VoxelShapes.cuboid(0.40625f, 0f, 0f, 0.59375f, 1f, 1f);
				case NORTH, SOUTH -> VoxelShapes.cuboid(0f, 0f, 0.40625f, 1f, 1f, 0.59375f);
				default -> VoxelShapes.fullCube();
			};
		}
	}

	public static class SmallRodBlock extends LightningRodBlock {
		public SmallRodBlock(AbstractBlock.Settings settings) {
			super(AbstractBlock.Settings.of(Material.METAL).nonOpaque());
		}
		@Override
		public void appendTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
			tooltip.add(new TranslatableText("item.workings.pipe.tooltip"));
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			Direction dir = blockState.get(FACING);
			return switch (dir) {
				case UP, DOWN -> VoxelShapes.cuboid(0.40625f, 0f, 0.40625f, 0.59375f, 1f, 0.59375f);
				case NORTH, SOUTH -> VoxelShapes.cuboid(0.40625f, 0.40625f, 0f, 0.59375f, 0.59375f, 1f);
				case EAST, WEST -> VoxelShapes.cuboid(0f, 0.40625f, 0.40625f, 1f, 0.59375f, 0.59375f);
			};
		}
	}
	public static class MidRodBlock extends LightningRodBlock {
		public MidRodBlock(AbstractBlock.Settings settings) {
			super(AbstractBlock.Settings.of(Material.METAL).nonOpaque());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			Direction dir = blockState.get(FACING);
			return switch (dir) {
				case UP, DOWN -> VoxelShapes.cuboid(0.28125f, 0f, 0.28125f, 0.71875f, 1f, 0.71875f);
				case NORTH, SOUTH -> VoxelShapes.cuboid(0.28125f, 0.28125f, 0f, 0.71875f, 0.71875f, 1f);
				case EAST, WEST -> VoxelShapes.cuboid(0f, 0.28125f, 0.28125f, 1f, 0.71875f, 0.71875f);
			};
		}
		@Override
		public void appendTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
			tooltip.add(new TranslatableText("item.workings.pipe.tooltip"));
		}
	}
	public static class LargeRodBlock extends LightningRodBlock {
		public LargeRodBlock(AbstractBlock.Settings settings) {
			super(AbstractBlock.Settings.of(Material.METAL).nonOpaque());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			Direction dir = blockState.get(FACING);
			return switch (dir) {
				case UP, DOWN -> VoxelShapes.cuboid(0.21875f, 0f, 0.21875f, 0.78125f, 1f, 0.78125f);
				case NORTH, SOUTH -> VoxelShapes.cuboid(0.21875f, 0.21875f, 0f, 0.78125f, 0.78125f, 1f);
				case EAST, WEST -> VoxelShapes.cuboid(0f, 0.21875f, 0.21875f, 1f, 0.78125f, 0.78125f);
			};
		}
		@Override
		public void appendTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
			tooltip.add(new TranslatableText("item.workings.pipe.tooltip"));
		}
	}

	public static class PipeBlock extends HorizontalFacingBlock {
		public PipeBlock(Settings settings) {
			super(Settings.of(Material.METAL).nonOpaque());
		}
		protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
			stateManager.add(Properties.HORIZONTAL_FACING);
		}
		public BlockState getPlacementState(ItemPlacementContext ctx){
			return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			Direction dir = blockState.get(FACING);
			return switch (dir) {
				case NORTH -> VoxelShapes.cuboid(-0.0625f, -0.0625f, 0f, 1f, 1f, 1f);
				case SOUTH -> VoxelShapes.cuboid(0f, -0.0625f, 0f, 1.0625f, 1f, 1f);
				case EAST -> VoxelShapes.cuboid(0f, -0.0625f, -0.0625f, 1f, 1f, 1f);
				case WEST -> VoxelShapes.cuboid(0f, -0.0625f, 0f, 1f, 1f, 1.0625f);
				default -> VoxelShapes.fullCube();
			};

		}

	}

	public static class TrafficLight extends RedstoneLampBlock {
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

	public static class DrumBlock extends Block {
		public DrumBlock(Settings settings) {
			super(Settings.of(Material.DECORATION).nonOpaque());
		}
		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
			stateManager.add(Properties.HORIZONTAL_FACING);
		}
		public BlockState getPlacementState(ItemPlacementContext ctx){
			return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			return VoxelShapes.cuboid(0.125f, 0f, 0.125f, 0.875f, 1.0625f, 0.875f);
		}
	}

	public static class ConeBlock extends Block {
		public ConeBlock(Settings settings) {
			super(Settings.of(Material.DECORATION).nonOpaque());
		}
		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
			stateManager.add(Properties.HORIZONTAL_FACING);
		}
		public BlockState getPlacementState(ItemPlacementContext ctx){
			return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			return VoxelShapes.cuboid(0.40625f, 0f, 0.40625f, 0.59375f, 1.25f, 0.59375f);
		}
	}

	public static class Pallet extends Block {
		public Pallet(Settings settings) {
			super(Settings.of(Material.WOOD).nonOpaque());
			setDefaultState(getStateManager().getDefaultState().with(LEVEL, 1));
		}

		public static final IntProperty LEVEL = IntProperty.of("level", 1, 4);

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
			stateManager.add(LEVEL);
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

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("workings", "block_of_sticks"), BLOCK_OF_STICKS);
		Registry.register(Registry.ITEM, new Identifier("workings", "block_of_sticks"), new BlockItem(BLOCK_OF_STICKS, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.ITEM, new Identifier("workings","bundle_of_sticks"), BUNDLE_OF_STICKS);
		FuelRegistry.INSTANCE.add(BUNDLE_OF_STICKS, 1000);
		FuelRegistry.INSTANCE.add(BLOCK_OF_STICKS, 3000);
		Registry.register(Registry.BLOCK, new Identifier("workings", "pallet"), PALLET);
		Registry.register(Registry.ITEM, new Identifier("workings", "pallet"), new BlockItem(PALLET, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","jersey_wall"), JERSEY_WALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "jersey_wall"), new BlockItem(JERSEY_WALL, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","highway_wall"), HIGHWAY_WALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "highway_wall"), new BlockItem(HIGHWAY_WALL, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_pole"), TRAFFIC_POLE);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_pole"), new BlockItem(TRAFFIC_POLE, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_drum"), TRAFFIC_DRUM);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_drum"), new BlockItem(TRAFFIC_DRUM, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_light"), TRAFFIC_LIGHT);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_light"), new BlockItem(TRAFFIC_LIGHT, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_light_auto"), TRAFFIC_LIGHT_AUTO);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_light_auto"), new BlockItem(TRAFFIC_LIGHT_AUTO, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		//Registry.register(Registry.BLOCK, new Identifier("workings","emissive_texture_test_block"), EMISSIVE_TEXTURE_TEST_BLOCK);
		//Registry.register(Registry.ITEM, new Identifier("workings", "emissive_texture_test_block"), new BlockItem(EMISSIVE_TEXTURE_TEST_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","block_of_pipes"), BLOCK_OF_PIPES);
		Registry.register(Registry.ITEM, new Identifier("workings", "block_of_pipes"), new BlockItem(BLOCK_OF_PIPES, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","iron_pipe_small"), IRON_PIPE_SMALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "iron_pipe_small"), new BlockItem(IRON_PIPE_SMALL, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","iron_pipe"), IRON_PIPE);
		Registry.register(Registry.ITEM, new Identifier("workings", "iron_pipe"), new BlockItem(IRON_PIPE, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","iron_pipe_large"), IRON_PIPE_LARGE);
		Registry.register(Registry.ITEM, new Identifier("workings", "iron_pipe_large"), new BlockItem(IRON_PIPE_LARGE, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","block_of_copper_pipes"), BLOCK_OF_COPPER_PIPES);
		Registry.register(Registry.ITEM, new Identifier("workings", "block_of_copper_pipes"), new BlockItem(BLOCK_OF_COPPER_PIPES, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","copper_pipe_small"), COPPER_PIPE_SMALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "copper_pipe_small"), new BlockItem(COPPER_PIPE_SMALL, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","copper_pipe"), COPPER_PIPE);
		Registry.register(Registry.ITEM, new Identifier("workings", "copper_pipe"), new BlockItem(COPPER_PIPE, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","copper_pipe_large"), COPPER_PIPE_LARGE);
		Registry.register(Registry.ITEM, new Identifier("workings", "copper_pipe_large"), new BlockItem(COPPER_PIPE_LARGE, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","stop_sign"), STOP_SIGN);
		Registry.register(Registry.ITEM, new Identifier("workings", "stop_sign"), new BlockItem(STOP_SIGN, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","stop_sign_wall"), STOP_SIGN_WALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "stop_sign_wall"), new BlockItem(STOP_SIGN_WALL, new FabricItemSettings().group(Workings.ITEM_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("workings","diamond_sign_shovel"), DIAMOND_SIGN_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("workings", "diamond_sign_shovel"), new BlockItem(DIAMOND_SIGN_SHOVEL, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","diamond_sign_flag"), DIAMOND_SIGN_FLAG);
		Registry.register(Registry.ITEM, new Identifier("workings", "diamond_sign_flag"), new BlockItem(DIAMOND_SIGN_FLAG, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings","diamond_sign_cross"), DIAMOND_SIGN_CROSS);
		Registry.register(Registry.ITEM, new Identifier("workings", "diamond_sign_cross"), new BlockItem(DIAMOND_SIGN_CROSS, new FabricItemSettings().group(Workings.ITEM_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("workings", "pavement"), PAVEMENT);
		Registry.register(Registry.ITEM, new Identifier("workings", "pavement"), new BlockItem(PAVEMENT, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("workings", "asphalt"), ASPHALT);
		Registry.register(Registry.ITEM, new Identifier("workings", "asphalt"), new BlockItem(ASPHALT, new FabricItemSettings().group(Workings.ITEM_GROUP)));
	}
}
