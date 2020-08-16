package com.reprevise.punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishmentManager {

	// bans a player using the arguments passed through the method
	public static void banPlayer(Player cmdSender, Player plrToBan, String reason, Integer time, Character format) {

		Bukkit.dispatchCommand(cmdSender, "ban -s " + plrToBan.getName() + " " + time + format + " " + reason);

	}

	// perm-bans a player using the arguments passed through the method
	public static void permbanPlayer(Player cmdSender, Player plrToBan, String reason) {

		Bukkit.dispatchCommand(cmdSender, "ban -s " + plrToBan.getName() + " " + reason);

	}

	// mutes a player using the arguments passed through the method
	public static void mutePlayer(Player cmdSender, Player plrToMute, String reason, Integer time, Character format) {

		Bukkit.dispatchCommand(cmdSender, "mute -s " + plrToMute.getName() + " " + time + format + " " + reason);

	}

}
