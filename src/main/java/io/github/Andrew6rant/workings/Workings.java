package io.github.Andrew6rant.workings;

import io.github.Andrew6rant.workings.block.*;
import io.github.Andrew6rant.workings.block.pipe.LargeRodBlock;
import io.github.Andrew6rant.workings.block.pipe.MidRodBlock;
import io.github.Andrew6rant.workings.block.pipe.PipeBlock;
import io.github.Andrew6rant.workings.block.pipe.SmallRodBlock;
import io.github.Andrew6rant.workings.block.sign.RoadSignBlock;
import io.github.Andrew6rant.workings.block.trafficlight.AutoTrafficLight;
import io.github.Andrew6rant.workings.block.trafficlight.TrafficLight;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Workings implements ModInitializer {

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier("workings", "general"),
			() -> new ItemStack(Workings.TRAFFIC_LIGHT_AUTO));

	public static void registerItem(String itemName, Item item) {
		Registry.register(Registry.ITEM, new Identifier("workings", itemName), item);
	}
	public static void registerBlock(String blockName, Block block) {
		Registry.register(Registry.BLOCK, new Identifier("workings", blockName), block);
		Registry.register(Registry.ITEM, new Identifier("workings", blockName), new BlockItem(block, new FabricItemSettings().group(Workings.ITEM_GROUP)));
	}
	public static void registerFuel(Object blockOrItem, int value) {
		FuelRegistry.INSTANCE.add((ItemConvertible) blockOrItem, value);
	}

	public static final PillarBlock BLOCK_OF_STICKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).sounds(BlockSoundGroup.WOOD));
	public static final Item BUNDLE_OF_STICKS = new Item(new Item.Settings().group(Workings.ITEM_GROUP));
	public static final Pallet PALLET = new Pallet(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
	public static final FenceBlock JERSEY_WALL = new FenceBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
	public static final FenceBlock HIGHWAY_WALL = new FenceBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
	public static final ConeBlock TRAFFIC_POLE = new ConeBlock(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final DrumBlock TRAFFIC_DRUM = new DrumBlock(FabricBlockSettings.of(Material.DECORATION).strength(1.0f));
	public static final TrafficLight TRAFFIC_LIGHT = new TrafficLight(FabricBlockSettings.of(Material.DECORATION).strength(1.0f).emissiveLighting((state, world, pos) -> true).luminance(15));
	public static final AutoTrafficLight TRAFFIC_LIGHT_AUTO = new AutoTrafficLight(FabricBlockSettings.of(Material.DECORATION).strength(1.0f).emissiveLighting((state, world, pos) -> true).luminance(15));
	public static final PipeBlock BLOCK_OF_PIPES = new PipeBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	public static final SmallRodBlock IRON_PIPE_SMALL = new SmallRodBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
	public static final MidRodBlock IRON_PIPE = new MidRodBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
	public static final LargeRodBlock IRON_PIPE_LARGE = new LargeRodBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
	public static final PipeBlock BLOCK_OF_COPPER_PIPES = new PipeBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK));
	public static final SmallRodBlock COPPER_PIPE_SMALL = new SmallRodBlock(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final MidRodBlock COPPER_PIPE = new MidRodBlock(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final LargeRodBlock COPPER_PIPE_LARGE = new LargeRodBlock(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final Item COPPER_NUGGET = new Item(new Item.Settings().group(Workings.ITEM_GROUP));
	public static final RoadSignBlock STOP_SIGN = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	//public static final WallSignBlock STOP_SIGN_WALL = new WallSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	// Wall Mounted Stop Sign disabled for now. I don't like that it is a separate block instead of a blockstate for the regular Stop Sign.
	public static final RoadSignBlock DIAMOND_SIGN_CROSS = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_SHOVEL = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_FLAG = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));

	public static final Block PAVEMENT = new Block(FabricBlockSettings.copy(Blocks.STONE));
	public static final AsphaltBlock ASPHALT = new AsphaltBlock(FabricBlockSettings.copy(Blocks.STONE));

	@Override
	public void onInitialize() {
		registerBlock(Names.BLOCK_OF_STICKS, BLOCK_OF_STICKS);   registerFuel(BLOCK_OF_STICKS, 3000);
		registerItem (Names.BUNDLE_OF_STICKS, BUNDLE_OF_STICKS); registerFuel(BUNDLE_OF_STICKS, 1000);
		registerBlock(Names.PALLET, PALLET);                     registerFuel(PALLET, 250);
		registerBlock(Names.JERSEY_WALL, JERSEY_WALL);
		registerBlock(Names.HIGHWAY_WALL, HIGHWAY_WALL);
		registerBlock(Names.TRAFFIC_POLE, TRAFFIC_POLE);
		registerBlock(Names.TRAFFIC_DRUM, TRAFFIC_DRUM);
		registerBlock(Names.TRAFFIC_LIGHT, TRAFFIC_LIGHT);
		registerBlock(Names.TRAFFIC_LIGHT_AUTO, TRAFFIC_LIGHT_AUTO);
		registerBlock(Names.BLOCK_OF_PIPES, BLOCK_OF_PIPES);
		registerBlock(Names.IRON_PIPE_SMALL, IRON_PIPE_SMALL);
		registerBlock(Names.IRON_PIPE, IRON_PIPE);
		registerBlock(Names.IRON_PIPE_LARGE, IRON_PIPE_LARGE);
		registerBlock(Names.BLOCK_OF_COPPER_PIPES, BLOCK_OF_COPPER_PIPES);
		registerBlock(Names.COPPER_PIPE_SMALL, COPPER_PIPE_SMALL);
		registerBlock(Names.COPPER_PIPE, COPPER_PIPE);
		registerBlock(Names.COPPER_PIPE_LARGE, COPPER_PIPE_LARGE);
		registerItem (Names.COPPER_NUGGET, COPPER_NUGGET);
		registerBlock(Names.STOP_SIGN, STOP_SIGN);
		registerBlock(Names.DIAMOND_SIGN_SHOVEL, DIAMOND_SIGN_SHOVEL);
		registerBlock(Names.DIAMOND_SIGN_FLAG, DIAMOND_SIGN_FLAG);
		registerBlock(Names.DIAMOND_SIGN_CROSS, DIAMOND_SIGN_CROSS);
		registerBlock(Names.PAVEMENT, PAVEMENT);
		registerBlock(Names.ASPHALT, ASPHALT);
	}
}
