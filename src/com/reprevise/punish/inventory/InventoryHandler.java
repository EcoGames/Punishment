package com.reprevise.punish.inventory;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.reprevise.punish.MainPunish;

public class InventoryHandler {

	private MainPunish mainClass;

	// receives instance of main class and gives event listener an instance of this
	// class
	public InventoryHandler(MainPunish mainInstance) {
		mainClass = mainInstance;
		new InventoryEventsHandler(this);
	}

	public InventoryHandler(Player suspect, Player cmdSender) {

		Inventory startPunishInv = mainClass.getServer().createInventory(null, 18, "Punish " + suspect.getName()); // creates
																													// the
																													// inventory

		// creates the wools
		ItemStack warnWool = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemStack muteWool = new ItemStack(Material.WOOL, 1, (byte) 4);
		ItemStack banWool = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemStack quickBanClay = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);

		ItemMeta warnMeta = warnWool.getItemMeta();
		ItemMeta muteMeta = muteWool.getItemMeta();
		ItemMeta banMeta = banWool.getItemMeta();
		ItemMeta quickBanMeta = quickBanClay.getItemMeta();

		// configurating the warn wool meta
		warnMeta.setDisplayName(ChatColor.GREEN + "Warn");
		ArrayList<String> warnLore = new ArrayList<String>();
		warnLore.add(ChatColor.GRAY + "Click this wool if you wish to warn " + suspect.getName() + ".");

		// configurating the mute wool meta
		muteMeta.setDisplayName(ChatColor.YELLOW + "Mute");
		ArrayList<String> muteLore = new ArrayList<String>();
		muteLore.add(ChatColor.GRAY + "Click this wool if you wish to mute " + suspect.getName() + ".");

		// configurating the ban wool meta
		banMeta.setDisplayName(ChatColor.RED + "Ban");
		ArrayList<String> banLore = new ArrayList<String>();
		banLore.add(ChatColor.GRAY + "Click this wool if you wish to ban " + suspect.getName() + ".");

		// configurating the ban wool meta
		quickBanMeta.setDisplayName(ChatColor.DARK_RED + "Quick Ban");
		ArrayList<String> quickBanLore = new ArrayList<String>();
		quickBanLore.add(ChatColor.GRAY + "Click this wool if you wish to quickly ban " + suspect.getName() + ".");

		// setting the lore of all the wool blocks
		warnMeta.setLore(warnLore);
		muteMeta.setLore(muteLore);
		banMeta.setLore(banLore);
		quickBanMeta.setLore(quickBanLore);

		// setting the new item meta of all the wool blocks
		warnWool.setItemMeta(warnMeta);
		muteWool.setItemMeta(muteMeta);
		banWool.setItemMeta(banMeta);
		quickBanClay.setItemMeta(quickBanMeta);

		// adds the wool's to the punishment select inventory
		startPunishInv.setItem(0, warnWool);
		startPunishInv.setItem(4, muteWool);
		startPunishInv.setItem(8, banWool);
		startPunishInv.setItem(13, quickBanClay);

		// opens the punishment inventory
		cmdSender.openInventory(startPunishInv);

	}

	public void makeWarnInv(Player suspect, Player moderator) {

		Inventory warnInv = mainClass.getServer().createInventory(null, 9, "Warn " + suspect.getName()); // create
																											// inventory
		ItemStack doublePostBlock = new ItemStack(Material.SIGN, 1);
		{
			ItemMeta doublePostMeta = doublePostBlock.getItemMeta();
			doublePostMeta.setDisplayName(ChatColor.GRAY + "Double Posting");
			doublePostBlock.setItemMeta(doublePostMeta);
		}
		ItemStack altLimitBlock = new ItemStack(Material.SKULL_ITEM, 1);
		{
			ItemMeta altLimitMeta = altLimitBlock.getItemMeta();
			altLimitMeta.setDisplayName(ChatColor.GRAY + "Alt Limit");
			altLimitBlock.setItemMeta(altLimitMeta);
		}
		ItemStack reportAbuseBlock = new ItemStack(Material.BARRIER, 1);
		{
			ItemMeta reportAbuseMeta = reportAbuseBlock.getItemMeta();
			reportAbuseMeta.setDisplayName(ChatColor.GRAY + "/report Abuse");
			reportAbuseBlock.setItemMeta(reportAbuseMeta);
		}
		ItemStack advertisingTradingBlock = new ItemStack(Material.BOOK_AND_QUILL, 1);
		{
			ItemMeta advertisingTradingMeta = advertisingTradingBlock.getItemMeta();
			advertisingTradingMeta.setDisplayName(ChatColor.GRAY + "IRL Trading & Sales of Accounts");
			advertisingTradingBlock.setItemMeta(advertisingTradingMeta);
		}

		warnInv.setItem(0, doublePostBlock);
		warnInv.setItem(2, altLimitBlock);
		warnInv.setItem(6, reportAbuseBlock);
		warnInv.setItem(8, advertisingTradingBlock);

		moderator.openInventory(warnInv);

	}

	public void makeMuteInv(Player suspect, Player moderator) {

		Inventory muteInv = mainClass.getServer().createInventory(null, 36, "Mute " + suspect.getName());

		ItemStack spamBlock = new ItemStack(Material.WEB, 1);
		{
			ItemMeta meta = spamBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Spamming");
			spamBlock.setItemMeta(meta);
		}
		ItemStack dpBlock = new ItemStack(Material.SIGN, 1);
		{
			ItemMeta meta = dpBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Double Posting");
			dpBlock.setItemMeta(meta);
		}
		ItemStack sdBlock = new ItemStack(Material.WOOL, 1, (byte) 7);
		{
			ItemMeta meta = sdBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Staff Disrespect");
			sdBlock.setItemMeta(meta);
		}
		ItemStack rdBlock = new ItemStack(Material.WOOL, 1, (byte) 12);
		{
			ItemMeta meta = rdBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Racism and Discrimination");
			rdBlock.setItemMeta(meta);
		}
		ItemStack deathThrBlock = new ItemStack(Material.WOOL, 1, (byte) 15);
		{
			ItemMeta meta = deathThrBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Death Threats");
			deathThrBlock.setItemMeta(meta);
		}
		ItemStack InnapChatContBlock = new ItemStack(Material.BARRIER, 1);
		{
			ItemMeta meta = InnapChatContBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Innapropriate Chat Content");
			InnapChatContBlock.setItemMeta(meta);
		}
		ItemStack irlTradeAndAccountBlock = new ItemStack(Material.BOOK_AND_QUILL, 1);
		{
			ItemMeta meta = irlTradeAndAccountBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "IRL Trading or Sale of Accounts Advert");
			irlTradeAndAccountBlock.setItemMeta(meta);
		}
		ItemStack staffImpBlock = new ItemStack(Material.TNT, 1);
		{
			ItemMeta meta = staffImpBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Staff Impersonation");
			staffImpBlock.setItemMeta(meta);
		}

		muteInv.setItem(10, spamBlock);
		muteInv.setItem(11, dpBlock);
		muteInv.setItem(12, sdBlock);
		muteInv.setItem(13, rdBlock);
		muteInv.setItem(14, deathThrBlock);
		muteInv.setItem(15, InnapChatContBlock);
		muteInv.setItem(16, irlTradeAndAccountBlock);
		muteInv.setItem(22, staffImpBlock);

		moderator.openInventory(muteInv);

	}

	public void makeBanInv(Player suspect, Player moderator) {

		Inventory banInv = mainClass.getServer().createInventory(null, 36, "Ban " + suspect.getName());

		ItemStack exploitOrBugAbuseBlock = new ItemStack(Material.EGG, 1);
		{
			ItemMeta meta = exploitOrBugAbuseBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Exploit / Bug Abuse");
			exploitOrBugAbuseBlock.setItemMeta(meta);
		}
		ItemStack XrayBlock = new ItemStack(Material.GLASS, 1);
		{
			ItemMeta meta = XrayBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Xray");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "NOTE: This is a two week ban!");
			meta.setLore(lore);
			XrayBlock.setItemMeta(meta);
		}
		ItemStack sdBlock = new ItemStack(Material.WOOL, 1, (byte) 7);
		{
			ItemMeta meta = sdBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Staff Disrespect");
			sdBlock.setItemMeta(meta);
		}
		ItemStack AltLimit = new ItemStack(Material.SKULL_ITEM, 1);
		{
			ItemMeta meta = AltLimit.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Alt Limit");
			AltLimit.setItemMeta(meta);
		}
		ItemStack ReportAbuse = new ItemStack(Material.WOOL, 1, (byte) 15);
		{
			ItemMeta meta = ReportAbuse.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "/report Abuse");
			ReportAbuse.setItemMeta(meta);
		}
		ItemStack ddosdoxthreat = new ItemStack(Material.BARRIER, 1);
		{
			ItemMeta meta = ddosdoxthreat.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "DDoS or Dox Threats");
			ddosdoxthreat.setItemMeta(meta);
		}
		ItemStack irlTradeAndAccountBlock = new ItemStack(Material.BOOK_AND_QUILL, 1);
		{
			ItemMeta meta = irlTradeAndAccountBlock.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "IRL Trading or Sale of Accounts");
			irlTradeAndAccountBlock.setItemMeta(meta);
		}
		ItemStack cheating = new ItemStack(Material.TNT, 1);
		{
			ItemMeta meta = cheating.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Cheating");
			cheating.setItemMeta(meta);
		}
		ItemStack ahscam = new ItemStack(Material.ACACIA_DOOR_ITEM, 1);
		{
			ItemMeta meta = ahscam.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Auction House Scamming");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "NOTE: This is a one hour temp ban!");
			meta.setLore(lore);
			ahscam.setItemMeta(meta);
		}
		ItemStack scam = new ItemStack(Material.ANVIL, 1);
		{
			ItemMeta meta = scam.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Scamming");
			scam.setItemMeta(meta);
		}

		banInv.setItem(10, exploitOrBugAbuseBlock);
		banInv.setItem(11, XrayBlock);
		banInv.setItem(12, sdBlock);
		banInv.setItem(13, AltLimit);
		banInv.setItem(14, ReportAbuse);
		banInv.setItem(15, ddosdoxthreat);
		banInv.setItem(16, irlTradeAndAccountBlock);
		banInv.setItem(21, cheating);
		banInv.setItem(22, ahscam);
		banInv.setItem(23, scam);

		moderator.openInventory(banInv);

	}

	public void makeOffensesInv(Player suspect, Player moderator) {

		Inventory offenseInv = mainClass.getServer().createInventory(null, 9, "Prior Offenses");

		ItemStack First = new ItemStack(Material.WOOL, 1, (byte) 5);
		{
			ItemMeta meta = First.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "First Offense");
			First.setItemMeta(meta);
		}
		ItemStack Second = new ItemStack(Material.WOOL, 1, (byte) 4);
		{
			ItemMeta meta = Second.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Second Offense");
			Second.setItemMeta(meta);
		}
		ItemStack ThirdPlus = new ItemStack(Material.WOOL, 1, (byte) 14);
		{
			ItemMeta meta = ThirdPlus.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Third+ Offense");
			ThirdPlus.setItemMeta(meta);
		}

		offenseInv.setItem(0, First);
		offenseInv.setItem(4, Second);
		offenseInv.setItem(8, ThirdPlus);

		moderator.openInventory(offenseInv);

	}

}
