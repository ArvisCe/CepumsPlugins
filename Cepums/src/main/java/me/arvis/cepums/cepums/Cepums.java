package me.arvis.cepums.cepums;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Cepums extends JavaPlugin {
    FileConfiguration config;
    public String GetCookieMessage = "";
    @Override
    public void onEnable() {
        // Plugin startup logic
    saveDefaultConfig();
    config = getConfig();
    getLogger().info("Plugin ieslēdzas");
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
