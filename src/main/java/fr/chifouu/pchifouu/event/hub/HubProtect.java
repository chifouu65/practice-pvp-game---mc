package fr.chifouu.pchifouu.event.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HubProtect implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        player.setGameMode(GameMode.CREATIVE);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setHealth(20);
        player.setLevel(0);
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        player.getInventory().clear();
        event.setJoinMessage(ChatColor.GREEN + "" +player.getName() + " [+1]");
        for(World world : Bukkit.getWorlds()){
            world.setTime(0);
        }
    }

    @EventHandler
    public void foodLevelChangeModeration(FoodLevelChangeEvent e) {

        Player p = (Player) e.getEntity();
        e.setFoodLevel(20);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onBreakSpawn(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent event) {
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpawnMobSpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        if(player.getHealth() >= 0) {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());

            player.sendMessage(ChatColor.YELLOW +"-----------------------");
            killer.sendMessage(ChatColor.YELLOW +"-----------------------");

            killer.sendMessage(ChatColor.YELLOW + killer.getName() +ChatColor.GREEN+  " have kill " + ChatColor.YELLOW + player.getName());
            player.sendMessage(ChatColor.YELLOW+ player.getName() +ChatColor.GREEN+ " was killed by " + ChatColor.YELLOW + killer.getName());

            event.setDeathMessage(ChatColor.YELLOW +"-----------------------");
        }
    }
}
