package com.rookieand.runeprofession.command;

import com.rookieand.runeprofession.RuneProfession;
import com.rookieand.runeprofession.gui.GeneratedInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfomationCommand implements CommandExecutor {

    private static final String[] COMMANDS = {"reload", "help", "도움말", "리로드"};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("전문기술")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(RuneProfession.getPrefix() + ChatColor.RED + "해당 명령어는 오직 유저만 입력할 수 있습니다.");
            }
            if (args.length == 0) {
                Player player = (Player) sender;
                GeneratedInventory genInv = new GeneratedInventory(player);
                genInv.openInfoInventory(player);
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("리로드") || args[0].equalsIgnoreCase("reload")) {
                    sender.sendMessage(RuneProfession.getPrefix() + ChatColor.WHITE + "RuneProfession 플러그인을 " + ChatColor.RED + "리로드" + ChatColor.WHITE + " 하는 중입니다.");
                    RuneProfession.plugin.onReload();
                }
                if (args[0].equalsIgnoreCase("도움말") || args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(RuneProfession.getPrefix() + ChatColor.WHITE + "/전문기술 : 현재 자신의 전문 기술 숙련도와 등급을 봅니다.");
                    sender.sendMessage(RuneProfession.getPrefix() + ChatColor.WHITE + "/전문기술 리로드 : 플러그인의 컨피그를 모두 리로드 합니다.");
                }
            }
        }
        return true;
    }
}
