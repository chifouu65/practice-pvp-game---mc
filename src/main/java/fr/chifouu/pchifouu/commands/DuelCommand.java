package fr.chifouu.pchifouu.commands;

import fr.chifouu.pchifouu.PChifouu;
import fr.chifouu.pchifouu.arenas.Arena;
import fr.chifouu.pchifouu.arenas.ArenaManager;
import fr.chifouu.pchifouu.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static fr.chifouu.pchifouu.PChifouu.arenaConfiguration;
import static fr.chifouu.pchifouu.PChifouu.arenaFile;

public class DuelCommand implements CommandExecutor {

    static PChifouu MainPlugin;
    private Arena arena;
    private static Map<Player, Player> players = new HashMap<>();

    public DuelCommand(PChifouu instance) {
        MainPlugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("duel") && sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + " /duel info ");
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("info")) {

                    player.sendMessage(ChatColor.GREEN + "[DUEL] : " + " --Duel command-- ");
                    player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "/duel ''NamePlayer''");
                    player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "/duel <Accept/Deny>");

                    return true;
                }
            }

            if (args.length >= 1) {
                String targetName = args[0];

                if (args[0].equalsIgnoreCase("accept")) {
                    if (players.containsKey(player)) {
                        Player firstTarget = players.get(player);

                        player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "duel is accept . . . starting");
                        firstTarget.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "duel is accepted");

                       player.teleport(Arena.getLoc2());
                       firstTarget.teleport(Arena.getLoc1());

                        players.remove(player);
                    }
                } else if (args[0].equalsIgnoreCase("deny")) {

                    if (players.containsKey(player)) {

                        Player firstTarget = players.get(player);
                        firstTarget.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "duel is deny. . .");

                        players.remove(player);
                    }
                }
                else if(args[0].equalsIgnoreCase("create")) {

                    if(args.length < 3){
                        player.sendMessage("You need loc1,loc2");
                        player.sendMessage(ChatColor.GREEN + "/duel createarena <loc1> <loc2>");
                        return true;
                    }
                    String arenaName = "arena-" + new Random().nextInt(999);
                    Location loc1 = parseStringToLoc(args[1]);
                    Location loc2 = parseStringToLoc(args[2]);
                    //save in config
                    arenaConfiguration.set("arenas." + arenaName + ".loc1", args[1]);
                    arenaConfiguration.set("arenas." + arenaName + ".loc1", args[2]);

                    saveArenaConfig();

                    ArenaManager arenaManager = new ArenaManager();
                    arenaManager.addArena(arena);
                    player.sendMessage("you have created a " + arenaName + " arenas !");

                }
                else if (Bukkit.getPlayer(targetName) != null) {
                    Player target = Bukkit.getPlayer(targetName);

                    if (players.containsKey(target)) {
                        player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "warning a players already as match");
                        return true;
                    }

                    players.put(target, player);
                    player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW +
                            "You have duel : " + ChatColor.GREEN + targetName);
                    target.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW +
                            player.getName() + " have send a match request " + ChatColor.GREEN + "[Accept]");

                    Inventory pInv = Bukkit.createInventory(null, 9 * 1, "Menu entre Joueurs");
                    //make item with itembuilder
                    pInv.setItem(3, new ItemBuilder(Material.DIAMOND_SWORD).setName(org.bukkit.ChatColor.YELLOW + "§lduel on pot !").toItemStack());
                    pInv.setItem(7, new ItemBuilder(Material.LAVA_BUCKET).setName(org.bukkit.ChatColor.YELLOW + "§lduel on build uhc !").toItemStack());

                    player.openInventory(pInv);

                } else {
                    player.sendMessage(ChatColor.GREEN + "[DUEL] : " + ChatColor.YELLOW + "player is invalid");
                }
            }

        }

        return true;
    }

    private void saveArenaConfig() {
        try {
            arenaConfiguration.save(arenaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location parseStringToLoc(String string) {
        String[] parsedLoc = string.split(",");

        double x = Double.valueOf(parsedLoc[0]);
        double y = Double.valueOf(parsedLoc[1]);
        double z = Double.valueOf(parsedLoc[2]);
        float yaw = Float.valueOf(parsedLoc[3]);
        float pitch = Float.valueOf(parsedLoc[4]);

        Location duel = new Location(Bukkit.getWorld("world"), x,y,z,yaw,pitch);

        return duel;
    }

    public String unpartToString(Location loc) {
        return loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + ","
                + loc.getYaw() + "," + loc.getPitch();
    }
}
