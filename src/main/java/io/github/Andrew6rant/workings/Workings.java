package io.github.Andrew6rant.workings;

import io.github.Andrew6rant.workings.block.*;
import io.github.Andrew6rant.workings.block.pipe.LargeRodBlock;
import io.github.Andrew6rant.workings.block.pipe.MidRodBlock;
import io.github.Andrew6rant.workings.block.pipe.PipeBlock;
import io.github.Andrew6rant.workings.block.pipe.SmallRodBlock;
import io.github.Andrew6rant.workings.block.sign.RoadSign;
import io.github.Andrew6rant.workings.block.sign.WallSign;
import io.github.Andrew6rant.workings.block.trafficlight.AutoTrafficLight;
import io.github.Andrew6rant.workings.block.trafficlight.TrafficLight;
import io.github.Andrew6rant.workings.item.FlagItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
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
	public static void registerSign(String signName, Block sign, String wallSignName, Block wallSign) {
		Registry.register(Registry.BLOCK, new Identifier("workings", signName), sign);
		Registry.register(Registry.BLOCK, new Identifier("workings", wallSignName), wallSign);
		Registry.register(Registry.ITEM, new Identifier("workings", signName), new WallStandingBlockItem(sign, wallSign, new Item.Settings().group(Workings.ITEM_GROUP)));
	}
	public static void registerFuel(Object blockOrItem, int value) {
		FuelRegistry.INSTANCE.add((ItemConvertible) blockOrItem, value);
	}

	public static final PillarBlock BLOCK_OF_STICKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).sounds(BlockSoundGroup.WOOD));
	public static final Item BUNDLE_OF_STICKS = new Item(new Item.Settings().group(Workings.ITEM_GROUP));
	public static final FlagItem FLAG = new FlagItem(new Item.Settings().group(Workings.ITEM_GROUP));
	public static final Pallet PALLET = new Pallet(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
	public static final RoadWall JERSEY_WALL = new RoadWall(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
	public static final RoadWall HIGHWAY_WALL = new RoadWall(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
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
	public static final RoadSign STOP_SIGN = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(3.0f), ParticleTypes.ELECTRIC_SPARK);
	public static final WallSign STOP_SIGN_WALL = new WallSign(FabricBlockSettings.of(Material.METAL).strength(3.0f).nonOpaque(), ParticleTypes.ELECTRIC_SPARK);
	public static final RoadSign DIAMOND_SIGN_CROSS = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(3.0f), ParticleTypes.ELECTRIC_SPARK);
	public static final WallSign DIAMOND_SIGN_CROSS_WALL = new WallSign(FabricBlockSettings.of(Material.METAL).strength(3.0f).nonOpaque(), ParticleTypes.ELECTRIC_SPARK);
	public static final RoadSign DIAMOND_SIGN_SHOVEL = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(3.0f), ParticleTypes.ELECTRIC_SPARK);
	public static final WallSign DIAMOND_SIGN_SHOVEL_WALL = new WallSign(FabricBlockSettings.of(Material.METAL).strength(3.0f).nonOpaque(), ParticleTypes.ELECTRIC_SPARK);
	public static final RoadSign DIAMOND_SIGN_FLAG = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(3.0f), ParticleTypes.ELECTRIC_SPARK);
	public static final WallSign DIAMOND_SIGN_FLAG_WALL = new WallSign(FabricBlockSettings.of(Material.METAL).strength(3.0f).nonOpaque(), ParticleTypes.ELECTRIC_SPARK);

	public static final Block PAVEMENT = new Block(FabricBlockSettings.copy(Blocks.STONE));
	public static final AsphaltBlock ASPHALT = new AsphaltBlock(FabricBlockSettings.copy(Blocks.STONE));

	@Override
	public void onInitialize() {
		registerBlock(Names.BLOCK_OF_STICKS, BLOCK_OF_STICKS);		registerFuel(BLOCK_OF_STICKS, 3000);
		registerItem (Names.BUNDLE_OF_STICKS, BUNDLE_OF_STICKS);	registerFuel(BUNDLE_OF_STICKS, 1000);
		registerBlock(Names.PALLET, PALLET);						registerFuel(PALLET, 250);
		registerBlock(Names.JERSEY_WALL, JERSEY_WALL);				registerFuel(FLAG, 30);
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
		registerSign(Names.STOP_SIGN, STOP_SIGN, Names.STOP_SIGN_WALL, STOP_SIGN_WALL);
		registerSign(Names.DIAMOND_SIGN_SHOVEL, DIAMOND_SIGN_SHOVEL, Names.DIAMOND_SIGN_SHOVEL_WALL, DIAMOND_SIGN_SHOVEL_WALL);
		registerSign(Names.DIAMOND_SIGN_FLAG, DIAMOND_SIGN_FLAG, Names.DIAMOND_SIGN_FLAG_WALL, DIAMOND_SIGN_FLAG_WALL);
		registerSign(Names.DIAMOND_SIGN_CROSS, DIAMOND_SIGN_CROSS, Names.DIAMOND_SIGN_CROSS_WALL, DIAMOND_SIGN_CROSS_WALL);
		registerBlock(Names.PAVEMENT, PAVEMENT);
		registerBlock(Names.ASPHALT, ASPHALT);
		registerItem (Names.FLAG, FLAG);

		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
		flammableRegistry.add(BLOCK_OF_STICKS, 60, 100);
	}
}
