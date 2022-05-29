package fr.chifouu.pchifouu;

import fr.chifouu.pchifouu.arenas.Arena;
import fr.chifouu.pchifouu.arenas.ArenaManager;
import fr.chifouu.pchifouu.commands.DuelCommand;
import fr.chifouu.pchifouu.event.EventManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class PChifouu extends JavaPlugin {

    private EventManager eventManager;
    private ArenaManager arenaManager = new ArenaManager();
    public static File arenaFile;
    public static YamlConfiguration arenaConfiguration;
    private Arena arena;

    @Override
    public void onEnable() {
       getCommand("duel").setExecutor(new DuelCommand(this));
       eventManager = new EventManager(this);
       //fichier
        loadArenaConfig();


        ConfigurationSection arenaSection = arenaConfiguration.getConfigurationSection("arenas");
        for(String string : arenaSection.getKeys(false))
        {
            String loc1 = arenaSection.getString(string+ ".loc1");
            String loc2 = arenaSection.getString(string+ ".loc2");
            Arena arena = new Arena(parsetToString(loc1),parsetToString(loc2));
            arenaManager.addArena(arena);
        }

    }

    private String parsetToString(String parset) {
        return parset;
    }


    private void loadArenaConfig() {

        if(!getDataFolder().exists())
        {
            getDataFolder().mkdir();
        }

        arenaFile = new File(getDataFolder() + File.separator + "arenas.yml");

        if(!arenaFile.exists()){
            try {
                arenaFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        arenaConfiguration = YamlConfiguration.loadConfiguration(arenaFile);


    }

    @Override
    public void onDisable() {
    }
}
