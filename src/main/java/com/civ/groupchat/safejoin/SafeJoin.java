package com.civ.groupchat.safejoin;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.civ.groupchat.safejoin.config.ModConfig;

public class SafeJoin implements ClientModInitializer {

    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir();
    public static final String MODID = "SafeJoin";	

	@Override
	public void onInitializeClient() {
		
	    ModConfig.getConfig().load();
		
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {

			client.player.sendChatMessage("/gc " + ModConfig.getConfig().getChatGroupToJoin());

		});
	}
}