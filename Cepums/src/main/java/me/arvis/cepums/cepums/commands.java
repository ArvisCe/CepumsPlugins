package me.arvis.cepums.cepums;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import org.bukkit.configuration.file.FileConfiguration;

import static org.bukkit.Bukkit.getLogger;

public class commands implements CommandExecutor{
    List CookieCommandP = new ArrayList<String>();
    List CookieCommandT = new ArrayList<Long>();
    List MilkCommandP = new ArrayList<String>();
    List MilkCommandT = new ArrayList<Long>();

    private static final Cepums plugin = (Cepums) Bukkit.getPluginManager().getPlugin("Cepums");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("Ctests"))
        {
            player.sendMessage(ChatColor.GREEN + "plugins strādā!");
        }
        if(command.getName().equalsIgnoreCase("Cversija"))
        {
            player.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.BLUE + "v2.5");
        }


        //CEPUMS


        if(command.getName().equalsIgnoreCase("cepums"))
        {
            long CoolDown =  plugin.getConfig().getLong("CepumaLaiks");
            if(CookieCommandP.contains(player))
            {
                int index = CookieCommandP.indexOf(player);
                Date date = new Date();
                long currentTime = date.getTime();
                long time = (long) CookieCommandT.get(index);
                if(  ((time-currentTime)/1000)+CoolDown/1000 <= 0   )
                {
                    player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("SanemCepumuZina").replace("%speletajs%", player.getName()));
                    CookieCommandT.add(currentTime);
                    CookieCommandP.add(player);
                    CookieCommandT.remove(index);
                    CookieCommandP.remove(index);
                    ItemStack cookies = new ItemStack(Material.COOKIE);
                    cookies.setAmount(plugin.getConfig().getInt("CepumuDaudzums"));
                    player.getInventory().addItem(cookies);
                }
                else
                {
                    String timeStr = "" + (((time-currentTime)/1000)+CoolDown/1000);
                    player.sendMessage(ChatColor.RED + plugin.getConfig().getString("CepumaLaikaZina")
                            .replace("%laiks%", timeStr)
                            .replace("%speletajs%", player.getName()));
                }
            }
            else
            {
                player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("SanemCepumuZina").replace("%speletajs%", player.getName()));
                ItemStack cookies = new ItemStack(Material.COOKIE);
                cookies.setAmount(plugin.getConfig().getInt("CepumuDaudzums"));
                player.getInventory().addItem(cookies);
                CookieCommandP.add(player);

                Date date = new Date();
                long timeMilli = date.getTime();
                CookieCommandT.add(timeMilli);
            }

        }


        //PIENS


        if(command.getName().equalsIgnoreCase("piens"))
        {
            long CoolDown =  plugin.getConfig().getLong("PienaLaiks");
            if(MilkCommandP.contains(player))
            {
                int index = MilkCommandP.indexOf(player);
                Date date = new Date();
                long currentTime = date.getTime();
                long time = (long) MilkCommandT.get(index);
                if(  ((time-currentTime)/1000)+CoolDown/1000 <= 0   )
                {
                    player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("SanemPienuZina").replace("%speletajs%", player.getName()));
                    MilkCommandT.add(currentTime);
                    MilkCommandP.add(player);
                    MilkCommandT.remove(index);
                    MilkCommandP.remove(index);
                    ItemStack milk = new ItemStack(Material.MILK_BUCKET);
                    milk.setAmount(plugin.getConfig().getInt("PienaDaudzums"));
                    player.getInventory().addItem(milk);
                }
                else
                {
                    String timeStr = "" + (((time-currentTime)/1000)+CoolDown/1000);
                    player.sendMessage(ChatColor.RED + plugin.getConfig().getString("PienaLaikaZina")
                            .replace("%laiks%", timeStr)
                            .replace("%speletajs%", player.getName()));
                }
            }
            else
            {
                player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("SanemPienuZina").replace("%speletajs%", player.getName()));
                ItemStack milk = new ItemStack(Material.MILK_BUCKET);
                milk.setAmount(plugin.getConfig().getInt("PienaDaudzums"));
                player.getInventory().addItem(milk);
                MilkCommandP.add(player);

                Date date = new Date();
                long timeMilli = date.getTime();
                MilkCommandT.add(timeMilli);
            }

        }
        return false;
    }
}
