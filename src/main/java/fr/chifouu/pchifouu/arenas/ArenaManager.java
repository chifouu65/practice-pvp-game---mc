package fr.chifouu.pchifouu.arenas;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    
    private List<Arena> arenas = new ArrayList<>();

    public void addArena(Arena arena) {
        this.arenas.add(arena);
    }
    
    public void joinArena(Player playerArena1 , Player playerArena2) {
        
        Arena nextArena = getNextArena();

        if(nextArena != null)
        {
            nextArena.getPlayers().add(playerArena1);
            nextArena.getPlayers().add(playerArena2);
        } else {
            playerArena1.sendMessage("No Arena");
            playerArena2.sendMessage("No Arena");
        }

    }

    private Arena getNextArena() {
        for(Arena arena : arenas)
        {
            if(!arena.isStart())
            {
                return arena;
            }
        }
        return null;
    }

    public List<Arena> getArenas() {
        return arenas;
    }


}
