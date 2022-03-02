package com.rookieand.runeprofession;

import com.rookieand.runeprofession.command.InfomationCommand;
import com.rookieand.runeprofession.command.InformationTabComp;
import com.rookieand.runeprofession.gui.GeneratedInventory;
import com.rookieand.runeprofession.listener.BlockListener;
import com.rookieand.runeprofession.listener.FishingListener;
import com.rookieand.runeprofession.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class RuneProfession extends JavaPlugin {

    ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static RuneProfession plugin;
    public ConfigManager configManager;
    //public GeneratedInventory infoInv;

    public RuneProfession() { plugin = this;}

    @Override
    public void onEnable() {
        console.sendMessage(ChatColor.GREEN + "[RuneProfession] " + ChatColor.WHITE + "플러그인이 성공적으로 "+ ChatColor.YELLOW + "활성화" + ChatColor.WHITE + " 되었습니다!");
        console.sendMessage(ChatColor.GREEN + "[RuneProfession] " + ChatColor.WHITE + "Created by "+ ChatColor.YELLOW + "RookieAND_" + ChatColor.AQUA + " (Discord : RookieAND_#1234)");

        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new FishingListener(), this);

        getCommand("전문기술").setExecutor(new InfomationCommand());
        getCommand("전문기술").setTabCompleter(new InformationTabComp());

        this.configManager = new ConfigManager(this);
    }

    @Override
    public void onDisable() {
        console.sendMessage(ChatColor.GREEN + "[RuneProfession] " + ChatColor.WHITE + "플러그인이 성공적으로 "+ ChatColor.RED + "비활성화" + ChatColor.WHITE + " 되었습니다!");
        console.sendMessage(ChatColor.GREEN + "[RuneProfession] " + ChatColor.WHITE + "Created by "+ ChatColor.YELLOW + "RookieAND_" + ChatColor.AQUA + " (Discord : RookieAND_#1234)");
    }

    public void onReload() {
        this.configManager = new ConfigManager(this);
        //this.infoInv = new GeneratedInventory(this);
        console.sendMessage(ChatColor.GREEN + "[RuneProfession] " + ChatColor.WHITE + "플러그인이 성공적으로 "+ ChatColor.YELLOW + "리로드" + ChatColor.WHITE + " 되었습니다!");
    }

    public static String getPrefix() { return "§a§lRune | "; }

}
