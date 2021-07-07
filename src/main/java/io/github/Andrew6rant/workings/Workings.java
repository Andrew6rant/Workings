package io.github.Andrew6rant.workings;

import io.github.Andrew6rant.workings.block.*;
import io.github.Andrew6rant.workings.block.pipe.LargeRodBlock;
import io.github.Andrew6rant.workings.block.pipe.MidRodBlock;
import io.github.Andrew6rant.workings.block.pipe.PipeBlock;
import io.github.Andrew6rant.workings.block.pipe.SmallRodBlock;
import io.github.Andrew6rant.workings.block.sign.RoadSignBlock;
import io.github.Andrew6rant.workings.block.trafficlight.AutoTrafficLight;
import io.github.Andrew6rant.workings.block.trafficlight.TrafficLight;
import io.github.Andrew6rant.workings.block.sign.WallSignBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Workings implements ModInitializer {

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier("workings", "general"),
			() -> new ItemStack(Workings.TRAFFIC_LIGHT_AUTO));

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
	public static final Item COPPER_NUGGET = new Item(new Item.Settings().group(Workings.ITEM_GROUP));
	public static final RoadSignBlock STOP_SIGN = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	//public static final WallSignBlock STOP_SIGN_WALL = new WallSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	// Wall Mounted Stop Sign disabled for now. I don't like that it is a separate block instead of a blockstate for the regular Stop Sign.
	public static final RoadSignBlock DIAMOND_SIGN_CROSS = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_SHOVEL = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final RoadSignBlock DIAMOND_SIGN_FLAG = new RoadSignBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));


	public static final Block PAVEMENT = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final AsphaltBlock ASPHALT = new AsphaltBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));

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
		Registry.register(Registry.ITEM, new Identifier("workings","copper_nugget"), COPPER_NUGGET);
		Registry.register(Registry.BLOCK, new Identifier("workings","stop_sign"), STOP_SIGN);
		Registry.register(Registry.ITEM, new Identifier("workings", "stop_sign"), new BlockItem(STOP_SIGN, new FabricItemSettings().group(Workings.ITEM_GROUP)));
		//Registry.register(Registry.BLOCK, new Identifier("workings","stop_sign_wall"), STOP_SIGN_WALL);
		//Registry.register(Registry.ITEM, new Identifier("workings", "stop_sign_wall"), new BlockItem(STOP_SIGN_WALL, new FabricItemSettings().group(Workings.ITEM_GROUP)));
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
