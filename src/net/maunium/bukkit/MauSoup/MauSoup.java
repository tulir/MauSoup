package net.maunium.bukkit.MauSoup;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MauSoup extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteract(PlayerInteractEvent evt) {
		Soup: if (evt.getItem() != null && (evt.getAction().equals(Action.RIGHT_CLICK_AIR) || evt.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& evt.getItem().getType() == Material.MUSHROOM_SOUP) {
			evt.setCancelled(true);
			
			Player p = evt.getPlayer();
			if (p.getHealth() < p.getMaxHealth() - 7) p.setHealth(p.getHealth() + 7);
			else if (p.getHealth() < p.getMaxHealth()) p.setHealth(p.getMaxHealth());
			else if (p.getFoodLevel() < 13) {
				p.setFoodLevel(p.getFoodLevel() + 7);
				if (p.getSaturation() < 13) p.setSaturation(p.getSaturation() + 7);
				else p.setSaturation(20);
			} else if (p.getFoodLevel() < 20) {
				p.setFoodLevel(20);
				if (p.getSaturation() < 13) p.setSaturation(p.getSaturation() + 7);
				else p.setSaturation(20);
			} else break Soup;
			
			evt.getItem().setItemMeta(null);
			evt.getItem().setType(Material.BOWL);
		}
	}
}