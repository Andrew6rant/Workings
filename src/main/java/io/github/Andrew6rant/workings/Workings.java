package io.github.Andrew6rant.workings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
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


public class Workings implements ModInitializer {

	public static final PillarBlock BLOCK_OF_STICKS = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f));
	public static final Item BUNDLE_OF_STICKS = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Pallet PALLET = new Pallet(FabricBlockSettings.of(Material.WOOD).strength(2.0f));
	public static final Block PAVEMENT = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final Block ASPHALT = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final FenceBlock JERSEY_WALL = new FenceBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final FenceBlock HIGHWAY_WALL = new FenceBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final ConeBlock TRAFFIC_POLE = new ConeBlock(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final TrafficLight TRAFFIC_LIGHT_AUTO = new TrafficLight(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final DrumBlock TRAFFIC_DRUM = new DrumBlock(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final TrafficLight TRAFFIC_LIGHT = new TrafficLight(FabricBlockSettings.of(Material.DECORATION).strength(1.0f).emissiveLighting((state, world, pos) -> true));
	public static final Block EMISSIVE_TEXTURE_TEST_BLOCK = new Block(FabricBlockSettings.of(Material.DECORATION).strength(2.0f).luminance(15).emissiveLighting((state, world, pos) -> true));
	public static final PipeBlock BLOCK_OF_PIPES = new PipeBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final SmallRodBlock IRON_PIPE_SMALL = new SmallRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final MidRodBlock IRON_PIPE = new MidRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final LargeRodBlock IRON_PIPE_LARGE = new LargeRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final PipeBlock BLOCK_OF_COPPER_PIPES = new PipeBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final SmallRodBlock COPPER_PIPE_SMALL = new SmallRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final MidRodBlock COPPER_PIPE = new MidRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final LargeRodBlock COPPER_PIPE_LARGE = new LargeRodBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));

	public static class SmallRodBlock extends LightningRodBlock {
		public SmallRodBlock(AbstractBlock.Settings settings) {
			super(AbstractBlock.Settings.of(Material.METAL).nonOpaque());
		}
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			Direction dir = blockState.get(FACING);
			return switch (dir) {
				case UP, DOWN -> VoxelShapes.cuboid(0.40625f, 0f, 0.40625f, 0.59375f, 1f, 0.59375f);
				case NORTH, SOUTH -> VoxelShapes.cuboid(0.40625f, 0.40625f, 0f, 0.59375f, 0.59375f, 1f);
				case EAST, WEST -> VoxelShapes.cuboid(0f, 0.40625f, 0.40625f, 1f, 0.59375f, 0.59375f);
				default -> VoxelShapes.fullCube();
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
				default -> VoxelShapes.fullCube();
			};
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
				default -> VoxelShapes.fullCube();
			};
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
		//public static final void IntProperty(String stack, 1, 4) {
		public static final BooleanProperty SECOND = BooleanProperty.of("second");
		public static final BooleanProperty THIRD = BooleanProperty.of("third");
		public static final BooleanProperty FOURTH = BooleanProperty.of("fourth");

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
			stateManager.add(SECOND);
			stateManager.add(THIRD);
			stateManager.add(FOURTH);
		}

		public Pallet(Settings settings) {
			super(Settings.of(Material.WOOD).nonOpaque());
			setDefaultState(getStateManager().getDefaultState().with(SECOND, false).with(THIRD, false).with(FOURTH, false));
		}
		@Override
		public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
			boolean second = blockState.get(SECOND);
			boolean third = blockState.get(THIRD);
			boolean fourth = blockState.get(FOURTH);
			ItemStack stack = player.getStackInHand(hand);
			if (!fourth){
				if(stack.getItem().equals(Workings.PALLET.asItem())) {
					if(!player.isCreative()){
						stack.setCount(stack.getCount()-1);
					}
					if(third) world.setBlockState(pos, blockState.with(FOURTH, true));
					else if(second) world.setBlockState(pos, blockState.with(THIRD, true));
					else world.setBlockState(pos, blockState.with(SECOND, true));
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.FAIL;
		}
		@Override
		public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
			boolean second = blockState.get(SECOND);
			boolean third = blockState.get(THIRD);
			boolean fourth = blockState.get(FOURTH);
			if(fourth) return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1f);
			else if(third) return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.75f, 1f);
			else if(second) return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.5f, 1f);
			else return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.25f, 1f);
		}
	}

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("workings", "block_of_sticks"), BLOCK_OF_STICKS);
		Registry.register(Registry.ITEM, new Identifier("workings", "block_of_sticks"), new BlockItem(BLOCK_OF_STICKS, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier("workings","bundle_of_sticks"), BUNDLE_OF_STICKS);
		FuelRegistry.INSTANCE.add(BUNDLE_OF_STICKS, 1000);
		FuelRegistry.INSTANCE.add(BLOCK_OF_STICKS, 3000);
		Registry.register(Registry.BLOCK, new Identifier("workings", "pallet"), PALLET);
		Registry.register(Registry.ITEM, new Identifier("workings", "pallet"), new BlockItem(PALLET, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings", "pavement"), PAVEMENT);
		Registry.register(Registry.ITEM, new Identifier("workings", "pavement"), new BlockItem(PAVEMENT, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings", "asphalt"), ASPHALT);
		Registry.register(Registry.ITEM, new Identifier("workings", "asphalt"), new BlockItem(ASPHALT, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","jersey_wall"), JERSEY_WALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "jersey_wall"), new BlockItem(JERSEY_WALL, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","highway_wall"), HIGHWAY_WALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "highway_wall"), new BlockItem(HIGHWAY_WALL, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_pole"), TRAFFIC_POLE);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_pole"), new BlockItem(TRAFFIC_POLE, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_light_auto"), TRAFFIC_LIGHT_AUTO);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_light_auto"), new BlockItem(TRAFFIC_LIGHT_AUTO, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_drum"), TRAFFIC_DRUM);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_drum"), new BlockItem(TRAFFIC_DRUM, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","traffic_light"), TRAFFIC_LIGHT);
		Registry.register(Registry.ITEM, new Identifier("workings", "traffic_light"), new BlockItem(TRAFFIC_LIGHT, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","emissive_texture_test_block"), EMISSIVE_TEXTURE_TEST_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("workings", "emissive_texture_test_block"), new BlockItem(EMISSIVE_TEXTURE_TEST_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","block_of_pipes"), BLOCK_OF_PIPES);
		Registry.register(Registry.ITEM, new Identifier("workings", "block_of_pipes"), new BlockItem(BLOCK_OF_PIPES, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","iron_pipe_small"), IRON_PIPE_SMALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "iron_pipe_small"), new BlockItem(IRON_PIPE_SMALL, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","iron_pipe"), IRON_PIPE);
		Registry.register(Registry.ITEM, new Identifier("workings", "iron_pipe"), new BlockItem(IRON_PIPE, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","iron_pipe_large"), IRON_PIPE_LARGE);
		Registry.register(Registry.ITEM, new Identifier("workings", "iron_pipe_large"), new BlockItem(IRON_PIPE_LARGE, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","block_of_copper_pipes"), BLOCK_OF_COPPER_PIPES);
		Registry.register(Registry.ITEM, new Identifier("workings", "block_of_copper_pipes"), new BlockItem(BLOCK_OF_COPPER_PIPES, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","copper_pipe_small"), COPPER_PIPE_SMALL);
		Registry.register(Registry.ITEM, new Identifier("workings", "copper_pipe_small"), new BlockItem(COPPER_PIPE_SMALL, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","copper_pipe"), COPPER_PIPE);
		Registry.register(Registry.ITEM, new Identifier("workings", "copper_pipe"), new BlockItem(COPPER_PIPE, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("workings","copper_pipe_large"), COPPER_PIPE_LARGE);
		Registry.register(Registry.ITEM, new Identifier("workings", "copper_pipe_large"), new BlockItem(COPPER_PIPE_LARGE, new FabricItemSettings().group(ItemGroup.MISC)));
	}
}
