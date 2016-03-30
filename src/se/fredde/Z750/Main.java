package se.fredde.Z750;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends JavaPlugin implements Listener {

    List<String> messages = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        messages.add("&0Z750 is amazing!");
        messages.add("&1Z750 is our god!");
        messages.add("&2Z750 is perfect!");
        messages.add("&3Z750 is handsome!");
        messages.add("&4Z750 is great!");
        messages.add("&5Z750 is intelligent!");
        messages.add("&6Z750 is cool!");
        messages.add("&7Z750 is everything!");
        messages.add("&8Z750 is sexy!");
        messages.add("&9Z750 is dope!");
        messages.add("&aZ750 is our guru!");
        messages.add("&bZ750 is our man!");
        messages.add("&cZ750 is strong!");
        messages.add("&dZ750 is life!");
        messages.add("&eZ750 is funny!");
        messages.add("&fZ750 is pro!");
        messages.add("&fZ750 has a huge... manhood!");
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        e.setMotd(cc(messages.get(ThreadLocalRandom.current().nextInt(messages.size()))));
    }

    public String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
