package net.lazlecraft.bungeewhitelist;

import net.craftminecraft.bungee.bungeeyaml.pluginapi.ConfigurablePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeWhitelist extends ConfigurablePlugin implements Listener {
	
	public String kickmsg;
	
	public void onEnable() {
		registerFeature();
		kickmsg = this.getConfig().getString("KickMessage");
	}
	
	public void registerFeature() {
		if (ProxyServer.getInstance().getConfigurationAdapter().getBoolean("online_mode",true)) {
			ProxyServer.getInstance().getPluginManager().registerListener(this, this);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		else {
			System.out.println(ChatColor.GREEN + "A message from LaxWasHere");
			System.out.println("56 47 68 70 63 79 42 77 62 48 56 6e 61 57 34 67 5a 47 39 6c 63 79 42 75 62 33 51 67 63 33 56 77 63 47 39 79 64 43 42 6a 63 6d 46 6a 61 32 56 6b 49 48 4e 6c 63 6e 5a 6c 63 6e 4d 75 49 45 6c 6d 49 48 6c 76 64 53 42 33 61 58 4e 6f 49 48 52 76 49 48 56 7a 5a 53 42 30 61 47 6c 7a 49 48 42 73 64 57 64 70 62 69 77 67 59 6e 56 35 49 48 52 6f 5a 53 42 6d 64 57 4e 72 61 57 35 6e 49 47 64 68 62 57 55 67 62 33 49 67 59 32 39 6b 5a 53 42 76 62 6d 55 67 65 57 39 31 63 6e 4e 6c 62 47 59 75 49 46 6c 76 64 53 42 68 63 6d 55 67 59 57 34 67 59 57 4a 76 62 57 6c 75 59 58 52 70 62 32 34 67 64 47 38 67 64 47 68 6c 49 47 31 70 62 6d 56 6a 63 6d 46 6d 64 43 42 6a 62 32 31 74 64 57 35 70 64 48 6b 75");
			ProxyServer.getInstance().stop();
		}
	}
	
	@EventHandler
	public void onLogin(LoginEvent ev) {
		String p = ev.getConnection().getName();
		if (!getConfig().getStringList("Whitelist").contains(p)) {
			ev.setCancelled(true);
			ev.setCancelReason(ChatColor.translateAlternateColorCodes('&', kickmsg));
		}
	}
}
