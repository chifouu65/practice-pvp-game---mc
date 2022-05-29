package fr.chifouu.pchifouu.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class ScoreboardPlayer implements Listener {

    @EventHandler
    public void onJoinSb(PlayerJoinEvent event) {
        CreateSb(event.getPlayer());
    }

    public void CreateSb(Player player) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective(ChatColor.GREEN +"§lPractice","aaaa");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore("");
        score.setScore(4);
        Score score2 = obj.getScore(ChatColor.GREEN + "§lConnected : " +
                ChatColor.WHITE + "§f" + (Bukkit.getOnlinePlayers().size()) +
                "/" + Bukkit.getMaxPlayers());
        score2.setScore(3);
        player.setScoreboard(board);



    }

}
