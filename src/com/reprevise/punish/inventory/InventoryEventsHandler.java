package com.reprevise.punish.inventory;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.reprevise.punish.PunishmentManager;
import com.reprevise.punish.commands.PunishCmd;

public class InventoryEventsHandler implements Listener {

	String punType;
	String punReason;
	Integer offenses;

	private PunishCmd CmdClassInstance;
	private InventoryHandler handlerInstance;
	private HashMap<UUID, UUID> phm;

	// main constructor
	public InventoryEventsHandler(PunishCmd instance) {
		CmdClassInstance = instance;
		phm = CmdClassInstance.getPunishMap();
	}

	// inventory events' constructor
	public InventoryEventsHandler(InventoryHandler handlerClass) {
		handlerInstance = handlerClass;
	}

	// main class' constructor
	public InventoryEventsHandler() {

	}

	@EventHandler
	public void InvenClick(InventoryClickEvent event) {

		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		Player suspect = Bukkit.getServer().getPlayer(phm.get(player.getUniqueId()));

		Inventory clickedInv = event.getClickedInventory();

		if (event.getCurrentItem() == null) {
			return;
		}

		ItemStack clickedItem = event.getCurrentItem();

		if (!clickedItem.hasItemMeta()) {
			return;
		}

		ItemMeta meta = clickedItem.getItemMeta();

		if (!meta.hasDisplayName()) {
			return;
		}

		if (clickedInv.getName().contains("Punish")) {

			event.setCancelled(true);

			if (ChatColor.stripColor(meta.getDisplayName()).equals("Warn")) {

				handlerInstance.makeWarnInv(suspect, player);

			} else if (ChatColor.stripColor(meta.getDisplayName()).equals("Ban")) {

				punType = "Ban";
				handlerInstance.makeBanInv(suspect, player);

			} else if (ChatColor.stripColor(meta.getDisplayName()).equals("Mute")) {

				punType = "Mute";
				handlerInstance.makeMuteInv(suspect, player);

			} else if (ChatColor.stripColor(meta.getDisplayName()).equals("Quick Ban")) {

				Bukkit.dispatchCommand(player, "ban -s " + suspect.getName());
				player.closeInventory();

			}

		} else if (clickedInv.getName().contains("Warn")) {
			event.setCancelled(true);

			if (ChatColor.stripColor(meta.getDisplayName()).equals("Double Posting"))
				Bukkit.dispatchCommand(player, "warn -s " + suspect.getName() + " double posting");
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Alt Limit"))
				Bukkit.dispatchCommand(player, "warn -s " + suspect.getName() + " alt limit");
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("/report Abuse"))
				Bukkit.dispatchCommand(player, "warn -s " + suspect.getName() + " /report Abuse");
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("IRL Trading & Sales of Accounts"))
				Bukkit.dispatchCommand(player, "warn -s " + suspect.getName() + " Advertising Trade");

			player.closeInventory();

		} else if (clickedInv.getName().contains("Mute")) {
			event.setCancelled(true);

			if (ChatColor.stripColor(meta.getDisplayName()).equals("Double Posting"))
				punReason = "Double Posting";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Spamming"))
				punReason = "Spamming";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Staff Disrespect"))
				punReason = "Staff Disrespect";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("IRL Trading & Sales of Accounts"))
				punReason = "IRL trading & sale of account";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Racism and Discrimination"))
				punReason = "racism or discrimination";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Innapropriate Chat Content"))
				punReason = "innap chat content";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Death Threats"))
				punReason = "death threats";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Staff Impersonation"))
				punReason = "staff imper";

			handlerInstance.makeOffensesInv(suspect, player);

		} else if (clickedInv.getName().contains("Ban")) {
			event.setCancelled(true);

			if (ChatColor.stripColor(meta.getDisplayName()).equals("Exploit / Bug Abuse"))
				punReason = "Exploit or Bug Abuse";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Xray"))
				punReason = "Xray";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Staff Disrespect"))
				punReason = "Staff Disrespect";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("IRL Trading & Sales of Accounts"))
				punReason = "IRL trading & sale of account";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Alt Limit"))
				punReason = "alt limit";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("/report Abuse"))
				punReason = "/report Abuse";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("DDoS or Dox Threats"))
				punReason = "ddos or dox threat";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Cheating"))
				punReason = "cheating";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Auction House Scamming"))
				punReason = "/ah scam";
			else if (ChatColor.stripColor(meta.getDisplayName()).equals("Scamming"))
				punReason = "scamming";

			handlerInstance.makeOffensesInv(suspect, player);

		} else if (clickedInv.getName().equals("Prior Offenses")) {

			event.setCancelled(true);

			if (ChatColor.stripColor(meta.getDisplayName()).equals("First Offense"))
				offenses = 1;
			if (ChatColor.stripColor(meta.getDisplayName()).equals("Second Offense"))
				offenses = 2;
			if (ChatColor.stripColor(meta.getDisplayName()).equals("Third+ Offense"))
				offenses = 3;

			if (punType == "Ban") {

				calban(suspect, player);

			} else if (punType == "Mute") {

				calmute(suspect, player);

			}

			player.closeInventory();

		}

	}

	private void calban(Player suspect, Player moderator) {

		if (punReason == "Exploit or Bug Abuse") {

			if (offenses == 1)
				PunishmentManager.banPlayer(moderator, suspect, "Exploit / Bug Abuse", 1, 'd');
			else
				PunishmentManager.permbanPlayer(moderator, suspect, "Exploit / Bug Abuse");

		} else if (punReason == "Xray") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect.getName() + " 2w X-Ray");
			else
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect + " X-Ray");

		} else if (punReason == "Staff Disrespect") {

			Bukkit.dispatchCommand(moderator, "ban -s " + suspect.getName() + " 1d Staff Disrespect");

		} else if (punReason == "alt limit") {

			if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect.getName() + " 1h Alt Limit");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect.getName() + " Alt Limit");

		} else if (punReason == "/report Abuse") {

			Bukkit.dispatchCommand(moderator, "ban -s " + suspect.getName() + " 30m /report Abuse");

		} else if (punReason == "ddos or dox threat") {

			Bukkit.dispatchCommand(moderator, "ban -s " + suspect + " DDoS / Dox Threats");

		} else if (punReason == "cheating") {

			Bukkit.dispatchCommand(moderator, "ban -s " + suspect + " Cheating");

		} else if (punReason == "/ah scam") {

			Bukkit.dispatchCommand(moderator, "ban -s " + suspect + "1h /ah Scam");

		} else if (punReason == "scamming") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect + "1w Scamming");
			else if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect + "2w Scamming");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "ban -s " + suspect + " Scamming");

		} else if (punReason == "IRL trading & sale of account") {
			Bukkit.dispatchCommand(moderator, "ban -s " + suspect.getName() + " Advertising Trade/Sale of Account");
		}

		System.out.println(offenses + " " + punReason + " " + punType);

		punReason = "";
		punType = "";
		offenses = 0;

		phm.remove(moderator.getUniqueId());

	}

	private void calmute(Player suspect, Player moderator) {

		if (punReason == "Spamming") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 10m Spamming");
			else if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 30m Spamming");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 1h Spamming");

		} else if (punReason == "Double Posting") {

			if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 30m Double Posting");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 1h Double Posting");

		} else if (punReason == "Staff Disrespect") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 1h Staff Disrespect");
			else if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 3h Staff Disrespect");

		} else if (punReason == "IRL Trading & Sales of Accounts") {

			if (offenses == 2)
				PunishmentManager.mutePlayer(moderator, suspect, "Advertising IRL Trading / Sale of Account", 1, 'd');

		} else if (punReason == "Racism and Discrimination") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 6h Racism / Discrimination");
			else if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 12h Racism / Discrimination");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " Racism / Discrimination");

		} else if (punReason == "Innapropriate Chat Content") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 1h Innapropriate Chat Content");
			else if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 3h Innapropriate Chat Content");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 12h Innapropriate Chat Content");

		} else if (punReason == "Death Threats") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 6h Death Threats");
			else if (offenses == 2)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 12h Death Threats");
			else if (offenses == 3)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " Death Threats");

		} else if (punReason == "Staff Impersonation") {

			if (offenses == 1)
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 1h Staff Impersonation");
			else
				Bukkit.dispatchCommand(moderator, "mute -s " + suspect.getName() + " 12 Staff Impersonation");
		}

		System.out.println(offenses + " " + punReason + " " + punType);

		punReason = "";
		punType = "";
		offenses = 0;

		phm.remove(moderator.getUniqueId());

	}

}
