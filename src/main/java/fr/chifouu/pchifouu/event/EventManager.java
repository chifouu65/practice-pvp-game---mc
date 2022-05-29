package fr.chifouu.pchifouu.event;

import fr.chifouu.pchifouu.PChifouu;
import fr.chifouu.pchifouu.commands.DuelEvent;
import fr.chifouu.pchifouu.event.hub.HubProtect;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private final PChifouu instance;
    private final PluginManager pluginManager;

    public EventManager (PChifouu instance) {
        this.instance = instance;
        this.pluginManager = Bukkit.getPluginManager();
        registerEvents();
    }

    private void registerEvents() {
        addNewEvent(new DuelEvent());
        addNewEvent(new HubProtect());
        addNewEvent(new ScoreboardPlayer());
    }

    public void addNewEvent(Listener listener){
        pluginManager.registerEvents(listener, instance);
    }
}
