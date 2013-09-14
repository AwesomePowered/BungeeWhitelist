package net.lazlecraft.bungeewhitelist;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class BhiteList extends Command {

    private final BungeeWhitelist whitelist;

    public BhiteList(BungeeWhitelist whitelist) {
        super("bhitelist", "bhitelist.edit");
        this.whitelist = whitelist;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        // enable/disable
        if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("enable")) {
                whitelist.getConfig().set("Enabled", true);
                whitelist.saveConfig();
                commandSender.sendMessage(BungeeWhitelist.PREFIX+"Enabled");
            } else if(strings[0].equalsIgnoreCase("disable")) {
                whitelist.getConfig().set("Enabled", false);
                whitelist.saveConfig();
                commandSender.sendMessage(BungeeWhitelist.PREFIX+"Disabled");
            } else {
                error(commandSender);
            }
        } else if(strings.length == 2) {
            // add/remove
            if(strings[0].equalsIgnoreCase("add")) {
                String toAdd = strings[1];
                List<String> players = whitelist.getConfig().getStringList("Whitelist");
                if(containsIgnoreCase(players, toAdd)) {
                    commandSender.sendMessage(BungeeWhitelist.PREFIX+toAdd+" is already bhitelisted");
                } else {
                    players.add(toAdd);
                    whitelist.getConfig().set("Whitelist", players);
                    whitelist.saveConfig();
                    commandSender.sendMessage(BungeeWhitelist.PREFIX+toAdd+" is now bhitelisted");
                }
            } else if(strings[0].equalsIgnoreCase("remove")) {
                String toRemove = strings[1];
                List<String> players = whitelist.getConfig().getStringList("Whitelist");
                if(containsIgnoreCase(players, toRemove)) {
                    removeIgnoreCase(players, toRemove);
                    whitelist.getConfig().set("Whitelist", players);
                    whitelist.saveConfig();
                    commandSender.sendMessage(BungeeWhitelist.PREFIX+toRemove+" is no longer bhitelisted");
                } else {
                    commandSender.sendMessage(BungeeWhitelist.PREFIX+toRemove+" is not bhitelisted");
                }
            } else {
                error(commandSender);
            }
        } else {
            error(commandSender);
        }
    }

    public static boolean containsIgnoreCase(List<String> list, String value) {
        for(String s : list) {
            if(s.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public void removeIgnoreCase(List<String> list, String value) {
        int key = -1;
        for(int i=0; i<list.size(); i++) {
            String s = list.get(i);
            if(s.equalsIgnoreCase(value)) {
                key = i;
            }
        }
        if(key != -1) {
            list.remove(key);
        }
    }

    public void error(CommandSender commandSender) {
        commandSender.sendMessage(BungeeWhitelist.PREFIX+"Usage: /bhitelist enable|disable|add|remove [player]");
    }
}
