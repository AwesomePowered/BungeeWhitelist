package net.lazlecraft.bungeewhitelist;

import net.craftminecraft.bungee.bungeeyaml.pluginapi.ConfigurablePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeWhitelist extends ConfigurablePlugin implements Listener {

    public String kickmsg;
    public static final String PREFIX = ChatColor.GREEN+"["+ChatColor.AQUA+"BHITE"+ChatColor.GREEN+"] "+ChatColor.AQUA;

    public void onEnable() {
        registerFeature();
        kickmsg = this.getConfig().getString("KickMessage");
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BhiteList(this));
    }

    public void registerFeature() {
        ProxyServer.getInstance().getPluginManager().registerListener(this, this);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    @EventHandler
    public void onLogin(LoginEvent ev) {
        if(getConfig().getBoolean("Enabled")) {
            String p = ev.getConnection().getName();
            if (!BhiteList.containsIgnoreCase(getConfig().getStringList("Whitelist"), p)) {
                ev.setCancelled(true);
                ev.setCancelReason(ChatColor.translateAlternateColorCodes('&', kickmsg));
            }
        }
    }
}
