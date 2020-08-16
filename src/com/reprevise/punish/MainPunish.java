package com.reprevise.punish;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.reprevise.punish.commands.PunishCmd;
import com.reprevise.punish.inventory.InventoryEventsHandler;
import com.reprevise.punish.inventory.InventoryHandler;

public class MainPunish extends JavaPlugin {

	// on enable, calls all events in the class
	@Override
	public void onEnable() {
		registerListeners();
		registerCmds();
		giveInstance();
	}

	/*
	 * @Override public void onDisable() {
	 * 
	 * }
	 */

	// registers all the listeners
	private void registerListeners() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new InventoryEventsHandler(), this);
	}

	// registers all the commands
	private void registerCmds() {
		this.getCommand("punish").setExecutor(new PunishCmd(this));
	}

	// gives inventory handler an instance of this class
	private void giveInstance() {
		new InventoryHandler(this);
	}

}
