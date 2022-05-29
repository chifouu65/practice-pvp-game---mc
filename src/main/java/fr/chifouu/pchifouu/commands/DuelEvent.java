package fr.chifouu.pchifouu.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DuelEvent implements Listener {

    static DuelCommand duelCommand;

    @EventHandler
    public void onClickDuelMenu(InventoryClickEvent event) {

        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if(item == null) return;

        if (item.getType() == Material.LAVA_BUCKET) {

        }

        if (item.getType() == Material.DIAMOND_SWORD) {

        }
        event.setCancelled(true);
    }

}
