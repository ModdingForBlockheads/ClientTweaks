package net.blay09.mods.clienttweaks.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.clienttweaks.ClientTweaks;
import net.fabricmc.api.ClientModInitializer;

public class FabricClientTweaksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BalmClient.initialize(ClientTweaks.MOD_ID, ClientTweaks::initializeClient);
    }
}
