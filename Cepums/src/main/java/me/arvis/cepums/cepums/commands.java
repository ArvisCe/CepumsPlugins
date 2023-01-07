package me.arvis.cepums.cepums;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.FileWriter;

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
            player.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.BLUE + "v3.0");
        }


        //CEPUMS


        if(command.getName().equalsIgnoreCase("cepums"))
        {
            FileAction(player,"none");
            Long CoolDown =  plugin.getConfig().getLong("CepumaLaiks");
            Date date = new Date();
            Long currentTime = date.getTime();
            String cookieTimeSTR = GetFileLine(1,player);
            long cookieTime = Long.parseLong(cookieTimeSTR);
            if( ((cookieTime - currentTime)/1000) + CoolDown/1000 <= 0 )
            {
                ItemStack milk = new ItemStack(Material.COOKIE);
                milk.setAmount(plugin.getConfig().getInt("CepumuDaudzums"));
                player.getInventory().addItem(milk);
                player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("SanemCepumuZina").replace("%speletajs%", player.getName()));
                FileAction(player,"cepums");
            }
            else
            {
                String timeStr = "" + (((cookieTime-currentTime)/1000)+CoolDown/1000);
                player.sendMessage(ChatColor.RED + plugin.getConfig().getString("CepumaLaikaZina")
                        .replace("%laiks%", timeStr)
                        .replace("%speletajs%", player.getName()));
            }
        }


        //PIENS


        if(command.getName().equalsIgnoreCase("piens"))
        {
            FileAction(player,"none");
            Long CoolDown =  plugin.getConfig().getLong("PienaLaiks");
            Date date = new Date();
            Long currentTime = date.getTime();
            String milkTimeSTR = GetFileLine(2,player);
            long milkTime = Long.parseLong(milkTimeSTR);
            if( ((milkTime - currentTime)/1000) + CoolDown/1000 <= 0 )
            {
                ItemStack milk = new ItemStack(Material.MILK_BUCKET);
                milk.setAmount(plugin.getConfig().getInt("PienaDaudzums"));
                player.getInventory().addItem(milk);
                player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("SanemPienuZina").replace("%speletajs%", player.getName()));
                FileAction(player,"piens");
            }
            else
            {
                String timeStr = "" + (((milkTime-currentTime)/1000)+CoolDown/1000);
                player.sendMessage(ChatColor.RED + plugin.getConfig().getString("PienaLaikaZina")
                        .replace("%laiks%", timeStr)
                        .replace("%speletajs%", player.getName()));
            }
        }
        return false;
    }
    public void FileAction(Player player, String action)
    {
        try
        {
            Date date = new Date();
            long timeMilli = date.getTime();
            String path = "plugins/Cepums/playerData/"+player.getName()+".txt";
            File file = new File(path);
            if (file.createNewFile())
            {
                // created file if it didn't exist
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(player.getName());
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write("0");
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write("0");
                myWriter.close();
            }

            if(action == "cepums")
            {
                String old = GetFileLine(2,player);
                PrintWriter pw = new PrintWriter("filepath.txt");
                pw.close();


                FileWriter myWriter = new FileWriter(path);
                myWriter.write(player.getName());
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write(Long.toString(timeMilli));
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write(old);
                myWriter.close();
            }
            if(action == "piens")
            {
                String old = GetFileLine(1,player);
                PrintWriter pw = new PrintWriter("filepath.txt");
                pw.close();


                FileWriter myWriter = new FileWriter(path);
                myWriter.write(player.getName());
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write(old);
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write(Long.toString(timeMilli));
                myWriter.close();
            }
        }
        catch(IOException e)
        {
        }
    }
    public String GetFileLine(int line, Player player)
    {
        try
        {
            String returned = Files.readAllLines(Paths.get("plugins/Cepums/playerData/" + player.getName() + ".txt")).get(line);
            return returned;
        }
        catch (IOException e)
        {
            // error
        }
        return "0";
    }
}
