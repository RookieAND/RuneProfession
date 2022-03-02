package com.rookieand.runeprofession.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformationTabComp implements TabCompleter {

    private static final String[] COMMANDS = {"reload", "리로드", "help", "도움말"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        }
        return completions;
    }
}
