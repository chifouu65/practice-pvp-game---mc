package fr.chifouu.pchifouu.arenas;

import fr.chifouu.pchifouu.PChifouu;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    public PChifouu main;

    private static Location loc1;
    private static Location loc2;

    private List<Player> players;
    private boolean isStart;

    public Arena(String loc1, String loc2) {
        this.loc1 = Arena.loc1;
        this.loc2 = Arena.loc2;
        this.players = new ArrayList<>();
        this.isStart = true;
    }

    public static Location getLoc1() {
        return Arena.loc1;
    }

    public static Location getLoc2() {
        return Arena.loc2;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isStart() {
        return isStart;
    }

}
