package com.rooxchicken.modtut.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.rooxchicken.modtut.Event.HandleData;
import com.rooxchicken.modtut.client.ModtutorialClient;

import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;

@Mixin(MessageHandler.class)
public class OnChatMessage
{
    @Inject(method = "onGameMessage(Lnet/minecraft/text/Text;Z)V", at = @At("HEAD"), cancellable = true)
    public void onGameMessage(Text message, boolean overlay, CallbackInfo info)
    {
        String content = message.getString();
        if(content.length() < 7)
            return;

        if(content.substring(0, 6).equals("tut012"))
        {
            HandleData.parseData(content);
            info.cancel();
        }
    }
}
