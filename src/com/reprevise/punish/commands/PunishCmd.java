package com.reprevise.punish.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.reprevise.punish.MainPunish;
import com.reprevise.punish.inventory.InventoryHandler;

public class PunishCmd implements CommandExecutor {
	
	private final HashMap<UUID, UUID> PUNISH_MAP = new HashMap<UUID, UUID>(); // creates the punishment hashmap
	private MainPunish mainClass; // create variable for main class

	public HashMap<UUID, UUID> getPunishMap() {
		return PUNISH_MAP;
	}
	
	// receives instance of main class
	public PunishCmd(MainPunish mainInstance) {
		mainClass = mainInstance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player plr = (Player) sender;
			
			if (label.equalsIgnoreCase("punish")) { // we don't need this! remove it
				
				if (!(args.length == 0)) {
					
					String typedsuspect = args[0];
					for (Player players : Bukkit.getOnlinePlayers()) {
						
						if (players.getName().equalsIgnoreCase(typedsuspect)) {
							// everything should be good, open the inventory
							Player suspect = mainClass.getServer().getPlayer(typedsuspect);
							
							PUNISH_MAP.put(suspect.getUniqueId(), plr.getUniqueId());
							
							new InventoryHandler(suspect, plr);
							
							return true;
						}
						
					}
					
				} else {
					plr.sendMessage(ChatColor.RED + "You must include a player's name. Usage: /punish <player name>!");
				}
				
			}
			
		} else {
			sender.sendMessage(ChatColor.RED + "You must be a player to execute the /punish command!");
		}
		
		return true;
	}

}
