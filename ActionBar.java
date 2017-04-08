package clear.chat.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PlayerConnection;

public class ActionBar {
	public static void sendBar(Player player, String bar) {
		if (bar == null) {
			bar = "";
		}
		bar = ChatColor.translateAlternateColorCodes('&', bar);

		bar = bar.replaceAll("%player%", player.getDisplayName());

		PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + bar + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
		con.sendPacket(packet);
	}

	public static void sendBroadBar(String bar) {
		if (bar == null) {
			bar = "";
		}
		bar = ChatColor.translateAlternateColorCodes('&', bar);
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
			IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + bar + "\"}");
			PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
			con.sendPacket(packet);
		}
	}
}
