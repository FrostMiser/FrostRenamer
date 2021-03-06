package com.frostcraft.frostrenamer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;


public class FrostRenamer extends JavaPlugin {
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostRenamer] Plugin Enabled.");
		return;
	}

	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostRenamer] Plugin Disabled.");
	}
	
	private void playMenu(Player p) {
		p.sendMessage(ChatColor.GOLD+ "                      FrostRenamer Menu");
		p.sendMessage(ChatColor.GOLD + "You may use /Rename (name), or /describe (descrption) to rename or describe an item");				
		p.sendMessage(ChatColor.BLUE + "=-=-=-=-=-=-=-=-=-=FrostCraft.com=-=-=-=-=-=-=-=-=-=-=");			
		p.sendMessage(ChatColor.BLACK + "&0 - Black");
		p.sendMessage(ChatColor.DARK_BLUE + "&1 - Dark Blue");
		p.sendMessage(ChatColor.DARK_GREEN + "&2 - Dark Green");
		p.sendMessage(ChatColor.DARK_AQUA + "&3 - Dark Aqua");
		p.sendMessage(ChatColor.DARK_RED + "&4 - Dark Red");
		p.sendMessage(ChatColor.DARK_PURPLE + "&5 - Dark Purple");
		p.sendMessage(ChatColor.GOLD + "&6 - Gold");
		p.sendMessage(ChatColor.GRAY + "&7 - Gray");
		p.sendMessage(ChatColor.DARK_GRAY + "&8 - Dark Gray");
		p.sendMessage(ChatColor.BLUE + "&9 - Blue");
		p.sendMessage(ChatColor.GREEN + "&a - Green");
		p.sendMessage(ChatColor.AQUA + "&b - Aqua");
		p.sendMessage(ChatColor.RED + "&c - Red");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "&d - Light Purple");
		p.sendMessage(ChatColor.YELLOW + "&e- Yellow");
		p.sendMessage(ChatColor.WHITE + "&f - White");
	}	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		Player p;
		try {
			p = this.getServer().getPlayer(sender.getName());	
		}
		catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("rename") ) 
		{	
			if (args.length == 0) {
				playMenu(p);
				return true;
			}
			ItemStack item = p.getInventory().getItemInMainHand();
			ItemMeta itemMeta = item.getItemMeta();
			String message = args[0];
			for (int i=1;i<args.length;i++)
			{
				message = message + " " + args[i]; 
			}
			message = ChatColor.translateAlternateColorCodes('&', message);
			
			itemMeta.setDisplayName(message);
			p.getInventory().getItemInMainHand().setItemMeta(itemMeta);
			p.sendMessage(ChatColor.AQUA + "[FrostRenamer] " + ChatColor.GREEN + "Your Item has been renamed to:  " + message);
			
			return true;
		}
		else if (cmd.getName().equalsIgnoreCase("describe") ) {
			if (args.length < 2) {
				playMenu(p);
				return true;
			}

			String message = args[1];

			for (int i=2;i<args.length;i++)
			{
				message = message + " " + args[i]; 
			}

			message = ChatColor.translateAlternateColorCodes('&', message);
			
			List<String> itemLore;
			ItemStack item = p.getInventory().getItemInMainHand();
			ItemMeta itemMeta = item.getItemMeta();
			
			//add to lore
			if (args[0].equalsIgnoreCase("add")) {
				if (itemMeta.hasLore()) {
					itemLore = itemMeta.getLore();
				}
				else {
					itemLore = new ArrayList<String>();
				}
				itemLore.add(message);
			}
			//clear lore
			else if (args[0].equalsIgnoreCase("clear")) {
				itemLore = new ArrayList<String>();
			}
			//set a specific lore line
			else if (StringUtils.isNumeric(args[0])){

				if (itemMeta.hasLore()) {
					itemLore = itemMeta.getLore();
				}
				else {
					itemLore = new ArrayList<String>();
				}
				while (itemLore.size() < Integer.parseInt(args[0])) {
					itemLore.add("");
				}
				itemLore.set(Integer.parseInt(args[0])-1, message);
			}
			else {
				playMenu(p);
				return true;
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			p.sendMessage(ChatColor.AQUA + "[FrostRenamer] " + ChatColor.GREEN + "Your Item description has been set to:  " + message);
			return true;
		}
		return true;
	}

}
