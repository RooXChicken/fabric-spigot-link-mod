package com.rooxchicken.modtut.client;

import org.lwjgl.glfw.GLFW;

import com.rooxchicken.modtut.Event.DrawGUICallback;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.option.KeyBinding;

public class ModtutorialClient implements ClientModInitializer
{
    public static boolean enabled = false;
    private KeyBinding runCommand = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.tut.run", GLFW.GLFW_KEY_C, "key.category.tut"));

    @Override
    public void onInitializeClient()
    {
        HudRenderCallback.EVENT.register(new DrawGUICallback());
        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            if(runCommand.wasPressed())
            {
                sendChatCommand("wand");
            }
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
        {
            //disable the mod whenever the player leaves a server
            enabled = false;
        });
    }

    public static void sendChatCommand(String msg)
    {
        if(!ModtutorialClient.enabled)
            return;
            
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworkHandler handler = client.getNetworkHandler();

        if(handler == null)
            return;
        handler.sendChatCommand(msg);
    }
}
