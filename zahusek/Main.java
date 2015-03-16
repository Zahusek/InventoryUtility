package thezaha;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import thezaha.CustomInventory.Click;
import thezaha.CustomInventory.CreateInventory;

public class Main extends JavaPlugin{
	
	private CreateInventory i1;
	private CreateInventory i2;
	private CreateInventory i3;
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	@Override
	public void onEnable() {
		new CustomInventory(this);
		i1 = new CreateInventory("i1", 1, new Click() {
			
			@Override
			public boolean click(Player p, CreateInventory ci, InventoryClickEvent e) {
				ItemStack i = e.getCurrentItem();
				if(i != null && i.getType().equals(Material.GRASS) && i.hasItemMeta() && i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Idz do &5inv1"))){
					p.openInventory(i2.getInventory());
				}
				else if(i != null && i.getType().equals(Material.GRASS) && i.hasItemMeta() && i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Idz do &5inv2"))){
					p.openInventory(i3.getInventory());
				}
				return true;
			}
		});
		
		i1.getInventory().setItem(3, setItemStack(Material.GRASS, new int[] {1, 0}, "&6Idz do &5inv1", Arrays.asList("&9Juz teraz!")));
		i1.getInventory().setItem(4, setItemStack(Material.WOOL, new int[] {1, 5}, " ", null));
		i1.getInventory().setItem(5, setItemStack(Material.GRASS, new int[] {2, 0}, "&6Idz do &5inv2", Arrays.asList("&9Juz teraz!", "&eMhmmm :D")));
		
		i2 = new CreateInventory("i2", 2, new Click() {

			@Override
			public boolean click(Player p, CreateInventory ci, InventoryClickEvent e) {
				int s = e.getSlot();
				if(s == 0)
					p.openInventory(i1.getInventory());
				return true;
			}
		});
		
		i2.getInventory().setItem(0, setItemStack(Material.WOOL, new int[] {1, 14}, "&cWroc do Menu Glownego !", null));
		
		
		i3 = new CreateInventory("i3", 3, new Click() {

			@Override
			public boolean click(Player p, CreateInventory ci, InventoryClickEvent e) {
				int s = e.getSlot();
				if(s == 0)
					p.openInventory(i1.getInventory());
				return true;
			}
		});
		
		i3.getInventory().setItem(0, setItemStack(Material.WOOL, new int[] {1, 14}, "&cWroc do Menu Glownego !", null));
		
		for(CreateInventory b : CustomInventory.list)
		System.out.print("Gui Inventory: " + b.getName() + " - zaladowane !");
		
		super.onEnable();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!command.getName().equalsIgnoreCase("test")) return true;
		((Player)sender).openInventory(i1.getInventory());
		return super.onCommand(sender, command, label, args);
	}
	public ItemStack setItemStack(Material m, int[] n, String name, List<String> lore){
		ItemStack is = new ItemStack(m, n[0]); 
		if(n[1] != 0)
			is.setDurability((short) n[1]);
		ItemMeta im = is.getItemMeta();
		if(name !=null)
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		if(lore != null)
			for(String list : lore)
				im.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', list)));
		is.setItemMeta(im);
		
		return is;
	}
}
