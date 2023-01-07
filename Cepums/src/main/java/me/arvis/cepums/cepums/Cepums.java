package me.arvis.cepums.cepums;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Cepums extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    getCommand("Ctests").setExecutor(new commands());
    getCommand("Cversija").setExecutor(new commands());
    getCommand("cepums").setExecutor(new commands());
    getCommand("piens").setExecutor(new commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
