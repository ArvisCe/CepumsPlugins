package me.arvis.cepums.cepums;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
public class commands implements CommandExecutor{
    List CookieCommandP = new ArrayList<String>();
    List CookieCommandT = new ArrayList<Long>();
    List MilkCommandP = new ArrayList<String>();
    List MilkCommandT = new ArrayList<Long>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("Ctests"))
        {
            player.sendMessage(ChatColor.GREEN + "komanda strādā!");
        }
        if(command.getName().equalsIgnoreCase("Cversija"))
        {
            player.sendMessage(ChatColor.GREEN + "Cepuma plugin versija: " + ChatColor.BLUE + "v1.1");
        }
        //CEPUMS
        if(command.getName().equalsIgnoreCase("cepums"))
        {
            long CoolDown =  30000;
            if(CookieCommandP.contains(player))
            {
                int index = CookieCommandP.indexOf(player);
                Date date = new Date();
                long currentTime = date.getTime();
                long time = (long) CookieCommandT.get(index);
                if(  ((time-currentTime)/1000)+CoolDown/1000 <= 0   )
                {
                    player.sendMessage(ChatColor.GREEN + "Ņem savu cepumu, "+player.getName()+"!");
                    CookieCommandT.add(currentTime);
                    CookieCommandP.add(player);
                    CookieCommandT.remove(index);
                    CookieCommandP.remove(index);
                    ItemStack cookies = new ItemStack(Material.COOKIE);
                    cookies.setAmount(1);
                    player.getInventory().addItem(cookies);
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Dabūsī vēlvienu cepumu pēc "+(((time-currentTime)/1000)+CoolDown/1000)+" sekundēm!");
                }
            }
            else
            {
                player.sendMessage(ChatColor.GREEN + "Ņem savu cepumu, "+player.getName()+"!");
                ItemStack cookies = new ItemStack(Material.COOKIE);
                cookies.setAmount(1);
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
            long CoolDown =  30000;
            if(MilkCommandP.contains(player))
            {
                int index = MilkCommandP.indexOf(player);
                Date date = new Date();
                long currentTime = date.getTime();
                long time = (long) MilkCommandT.get(index);
                if(  ((time-currentTime)/1000)+CoolDown/1000 <= 0   )
                {
                    player.sendMessage(ChatColor.GREEN + "Ņem savu pienu, "+player.getName()+"!");
                    MilkCommandT.add(currentTime);
                    MilkCommandP.add(player);
                    MilkCommandT.remove(index);
                    MilkCommandP.remove(index);
                    ItemStack cookies = new ItemStack(Material.MILK_BUCKET);
                    cookies.setAmount(1);
                    player.getInventory().addItem(cookies);
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Dabūsī vēl pienu pēc "+(((time-currentTime)/1000)+CoolDown/1000)+" sekundēm!");
                }
            }
            else
            {
                player.sendMessage(ChatColor.GREEN + "Ņem savu pienu, "+player.getName()+"!");
                ItemStack cookies = new ItemStack(Material.MILK_BUCKET);
                cookies.setAmount(1);
                player.getInventory().addItem(cookies);
                MilkCommandP.add(player);

                Date date = new Date();
                long timeMilli = date.getTime();
                MilkCommandT.add(timeMilli);
            }

        }
        return false;
    }
}
