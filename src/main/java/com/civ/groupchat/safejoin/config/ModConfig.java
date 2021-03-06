package com.civ.groupchat.safejoin.config;

import com.civ.groupchat.safejoin.SafeJoin;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModConfig {

	private static ModConfig INSTANCE = null;
	private final File file;
	
	private String defaultGroup;

	public static final Logger LOGGER = LoggerFactory.getLogger(SafeJoin.MODID);

    public ModConfig() {
        this.file = FabricLoader.getInstance().getConfigDir().resolve("civgroupsafejoin.json").toFile();
        LOGGER.info(file.toString());
        this.defaultGroup = "your group";
    }

    public static ModConfig getConfig() {
        if (INSTANCE == null) INSTANCE = new ModConfig();
        return INSTANCE;
    }
    

    public void load() {
        try {
            String json = new String(Files.readAllBytes(this.file.toPath()));
            
            LOGGER.info(json);
            
            if (!json.equals("")) {
                
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();                
                
                this.defaultGroup = jsonObject.getAsJsonPrimitive("defaultGroup").getAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("defaultGroup", this.defaultGroup);

        try (PrintWriter out = new PrintWriter(file)) {
            out.println(jsonObject.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getChatGroupToJoin() {
        return defaultGroup;
    }

    public void setChatGroupToJoin(String defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

}