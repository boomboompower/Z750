package se.fredde.Z750;

import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

public class Main extends JavaPlugin implements Listener {

    String[] messages;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        messages = new String[]{
                "&0Z750 is amazing!",
                "&1Z750 is our god!",
                "&2Z750 is perfect!",
                "&3Z750 is handsome!",
                "&4Z750 is great!",
                "&5Z750 is intelligent!",
                "&6Z750 is cool!",
                "&7Z750 is everything!",
                "&8Z750 is sexy!",
                "&9Z750 is dope!",
                "&aZ750 is our guru!",
                "&bZ750 is our man!",
                "&cZ750 is strong!",
                "&dZ750 is life!",
                "&eZ750 is funny!",
                "&fZ750 is pro!",
                "&fZ750 has a huge... manhood!",
                "&0Z&17&25&30&4 i&5s&6 d&8e&9c&ae&bn&ct&d!",
                "&6Z750 is a taco!",
                "&5Z750 is sometimes here when needed! (even if late!)"
        };
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(getRandomMessage());
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        e.setMotd(getRandomMessage());
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) e.getEntity();
            target.setCustomName(getRandomMessage());
            target.setCustomNameVisible(true);
        }
    }

    public String getRandomMessage() {
        return ChatColor.translateAlternateColorCodes('&', messages[ThreadLocalRandom.current().nextInt(messages.length)]);
    }

}
