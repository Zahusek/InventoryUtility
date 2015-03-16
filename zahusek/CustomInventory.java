package thezaha;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
 
public class CustomInventory implements Listener {
	
	public static ArrayList<CreateInventory> list = new ArrayList<CreateInventory>(); 
	
    public CustomInventory(Plugin p) {
        Bukkit.getPluginManager().registerEvents(this, p);
    }
    
    public static class CreateInventory {
        private Click click;
        private Inventory inv;
        
    	public CreateInventory(String name, int size, Click click){
    		this.click = click;
    		this.inv = Bukkit.getServer().createInventory(null, (size*9), name);
    		list.add(this);
    	}
    	public String getName(){
    		return inv.getName();
    	}
    	public int getSize(){
    		return inv.getSize();
    	}
    	public Click getClick(){
    		return click;
    	}
    	public Inventory getInventory() {
    		return inv;
    	}
    	public CreateInventory getInventory(String name){
    		for(CreateInventory ci : CustomInventory.list)
    		if(ci.getName().equals(name)) return ci;
			return null;
    	}
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent e) {
    	for(CreateInventory ci : CustomInventory.list){
    		if(ci.getName().equals(e.getInventory().getName())){
    			e.setCancelled(true);
    			Player p = (Player) e.getWhoClicked();
				  p.openInventory(ci.getInventory());//debug - nie usuwac, inaczej itemy beda sie klonować ... \\ no delete, otherwise items will be cloned
    			if(!ci.getClick().click(p, ci, e))
    				p.closeInventory();
    		}
    	}
    }
	public interface Click {
		public abstract boolean click(Player p, CreateInventory ci, InventoryClickEvent e);
	}
}
