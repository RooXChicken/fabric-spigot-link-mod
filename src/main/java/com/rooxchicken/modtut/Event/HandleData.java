package com.rooxchicken.modtut.Event;

import com.rooxchicken.modtut.client.ModtutorialClient;

public class HandleData
{
    public static void parseData(String msg)
    {
        String[] data = msg.split("_");
        int mode = Integer.parseInt(data[1]);

        switch(mode)
        {
            case 0:
                ModtutorialClient.enabled = true;
                ModtutorialClient.sendChatCommand("verifymod");
                break;
            case 1:
                DrawGUICallback.manaMax = Integer.parseInt(data[2]);
                DrawGUICallback.currentMana = Integer.parseInt(data[3]);
                break;
        }
    }
}
