package net.bitfred.Z750;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.PlayerConnection;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    Logger logger = getLogger();
    String[] contributors;
    String[] messages;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        contributors = new String[] {
                "BitFred",
                "kato",
                "Brianetta",
                "LaxWasHere",
                "boomboompower"
        };
        broadcast("&a--------&fZ750--------");
        broadcast("&aContributors:");
        for (String contributor : contributors) {
        	broadcast("&a" + contributor);
        }
        broadcast("&a--------&fZ750--------");
        
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
                "&9Z750 is &6&ospecial!",
                "&eZ750 is &emy amigo!",
                "&5Z750 is sometimes here when needed! (even if late!)",
                "&1Z750 is &lbold!",
                "&dZ750 likes &cCafeBabe&d!",
                "&6Z750 is bae!"
        };
    }

    /*
     * For the sendTitle method
     * 3 = fadeIn
     * 5 = stay
     * 3 = fadeOut
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(getRandomMessage());
        sendTitle(e.getPlayer(), 3, 5, 3, getRandomMessage());
        sendActionBar(e.getPlayer(), getRandomMessage());
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
    
    private void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String subtitle) {
	    String title = ChatColor.translateAlternateColorCodes('&', "&6&lZ760");
    	
	    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
	    
	    PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
	    connection.sendPacket(packetPlayOutTimes);

	    IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
	    PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);
	    connection.sendPacket(packetPlayOutSubTitle);
	       
	    IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
	    PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON);
	    connection.sendPacket(packetPlayOutTitle);
    }
    
    
    private void sendActionBar(Player p, String message) {
	
    	IChatBaseComponent chatBase = ChatSerializer.a("{\"text\": \"" + message + "\"}");
    	PacketPlayOutChat playOutChat = new PacketPlayOutChat(chatBase, (byte) 2);
    	
    	((CraftPlayer)p).getHandle().playerConnection.sendPacket(playOutChat);
    }
    
    private void broadcast(String message) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
