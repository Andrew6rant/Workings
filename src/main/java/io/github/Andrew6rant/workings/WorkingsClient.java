package io.github.Andrew6rant.workings;

import io.github.Andrew6rant.workings.block.sign.RoadSign;
import io.github.Andrew6rant.workings.block.sign.WallSign;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static io.github.Andrew6rant.workings.Workings.*;

public class WorkingsClient implements ClientModInitializer {

    public static void cutoutSign(RoadSign sign, WallSign wallSign) {
        BlockRenderLayerMap.INSTANCE.putBlock(sign, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(wallSign, RenderLayer.getCutout());
    }

    @Override
    public void onInitializeClient() {
        cutoutSign(STOP_SIGN, STOP_SIGN_WALL);
        cutoutSign(DIAMOND_SIGN_SHOVEL, DIAMOND_SIGN_SHOVEL_WALL);
        cutoutSign(DIAMOND_SIGN_FLAG, DIAMOND_SIGN_FLAG_WALL);
        cutoutSign(DIAMOND_SIGN_CROSS, DIAMOND_SIGN_CROSS_WALL);
    }
}
