package com.civ.groupchat.safejoin.config;

import com.civ.groupchat.safejoin.SafeJoin;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {
  
	 @Override
	    public ConfigScreenFactory<?> getModConfigScreenFactory() {
	        return (ConfigScreenFactory<Screen>) screen -> {
	            ConfigBuilder builder = ConfigBuilder.create();
	            
	    		builder.setTitle(new TranslatableText("title." + SafeJoin.MODID + ".config"));
	    		builder.setSavingRunnable(() -> ModConfig.getConfig().save());
	    		
	    		ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("key." + SafeJoin.MODID + ".options"));

	    		
	    		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
	    		
	    		general.addEntry(
	    				entryBuilder
	    						.startStrField(new TranslatableText("config." + SafeJoin.MODID + ".chatGroupToJoin"),
	    								 ModConfig.getConfig().getChatGroupToJoin())
	    						.setDefaultValue("your group")
	    						.setTooltip(new TranslatableText("config." + SafeJoin.MODID + ".chatGroupToJoin.tooltip"))
	    						.setSaveConsumer(newValue -> ModConfig.getConfig().setChatGroupToJoin(newValue))
	    						.build());
	            
	            return builder.build();
	        };
	    }
}