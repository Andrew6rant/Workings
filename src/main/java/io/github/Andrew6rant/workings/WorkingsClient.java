package io.github.Andrew6rant.workings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static io.github.Andrew6rant.workings.Workings.*;

public class WorkingsClient implements ClientModInitializer {

    public static void registerCutout(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }

    @Override
    public void onInitializeClient() {
        registerCutout(STOP_SIGN);
        registerCutout(DIAMOND_SIGN_SHOVEL);
        registerCutout(DIAMOND_SIGN_FLAG);
        registerCutout(DIAMOND_SIGN_CROSS);
    }
}
