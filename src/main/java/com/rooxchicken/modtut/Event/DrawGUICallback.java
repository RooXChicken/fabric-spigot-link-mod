package com.rooxchicken.modtut.Event;

import com.rooxchicken.modtut.client.ModtutorialClient;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class DrawGUICallback implements HudRenderCallback
{
    //values changed by the HandleData class
    //static isn't required, i'm just a lazy dev :3
    public static int manaMax = 400;
    public static int currentMana = 400;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter)
    {
        if(!ModtutorialClient.enabled)
            return;
            
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        int x = 200;
        int y = 200;

        drawContext.fill(x, y, x + (int) (100 * (1.0*currentMana/manaMax)), y+8, 0xFF0000FF);
        drawContext.drawCenteredTextWithShadow(textRenderer, (currentMana/20) + "/" + (manaMax/20), x+50, y-10, 0xFF0000FF);
    }
}
