package frostRenamer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.ChatColor;


public class FrostRenamer extends JavaPlugin {

	
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[FrostRenamer] Plugin Enabled.");
		return;
			
	}

	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[FrostRenamer] Plugin Disabled.");
	}
	
	private void playMenu(Player p) {
		p.sendMessage(ChatColor.GOLD+ "                      FrostRenamer Menu");
		p.sendMessage(ChatColor.GOLD + "You may use /Rename (name), or /describe (descrption) to rename or describe an item");				
		p.sendMessage(ChatColor.BLUE + "=-=-=-=-=-=-=-=-=-=FrostCraft.com=-=-=-=-=-=-=-=-=-=-=");			
		p.sendMessage(ChatColor.BLUE + "&0 - Black");
		p.sendMessage(ChatColor.BLUE + "&1 - Dark Blue");
		p.sendMessage(ChatColor.BLUE + "&2 - Dark Green");
		p.sendMessage(ChatColor.BLUE + "&3 - Dark Aqua");
		p.sendMessage(ChatColor.BLUE + "&4 - Dark Red");
		p.sendMessage(ChatColor.BLUE + "&5 - Dark Purple");
		p.sendMessage(ChatColor.BLUE + "&6 - Gold");
		p.sendMessage(ChatColor.BLUE + "&7 - Gray");
		p.sendMessage(ChatColor.BLUE + "&8 - Dark Gray");
		p.sendMessage(ChatColor.BLUE + "&9 - Blue");
		p.sendMessage(ChatColor.BLUE + "&a - Green");
		p.sendMessage(ChatColor.BLUE + "&b - Aqua");
		p.sendMessage(ChatColor.BLUE + "&c - Red");
		p.sendMessage(ChatColor.BLUE + "&d - Light Purple");
		p.sendMessage(ChatColor.BLUE + "&e- Yellow");
		p.sendMessage(ChatColor.BLUE + "&f - White");
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
			if (args.length < 1) {
				playMenu(p);
				return true;
			}
			
			//add to lore
			if (args[0].EqualsIgnoreCase("add") {
				
			}
			//clear lore
			else if (args[0].EqualsIgnoreCase("clear") {
			}
			//set a specific lore line
			else if (StringUtils.isNumeric(args[0])){
			}
			
			ItemStack item = p.getInventory().getItemInMainHand();
			ItemMeta itemMeta = item.getItemMeta();
			String message = args[1];
			
			for (int i=2;i<args.length;i++)
			{
				message = message + " " + args[i]; 
			}
			
			message = ChatColor.translateAlternateColorCodes('&', message);
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(message);
			itemMeta.setLore(itemLore);

			
			item.setItemMeta(itemMeta);	
			p.getInventory().getItemInMainHand().setItemMeta(itemMeta);
			
			p.sendMessage(ChatColor.AQUA + "[FrostRenamer] " + ChatColor.GREEN + "Your Item description has been set to:  " + message);
			return true;
		}
			
		return true;
	}

}
