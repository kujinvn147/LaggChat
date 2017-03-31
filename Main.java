package clear.chat.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.mavecrit.coreAPI.Actionbar.ActionBar;
import de.mavecrit.coreAPI.Titles.Titles;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public static Plugin plugin;
	static Main instance;

	String cslprefix = "[LaggChat]";
	String devcraft = "[DevCraftForum]";

	public static String replaceColors(String string) {
		return string.replaceAll("(?i)&([a-f0-9])", "�$1");
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public static Main getInstance() {
		return instance;
	}

	public void loadingConfiguration() {

		String prefix = "prefix";
		plugin.getConfig().addDefault(prefix, "&7&l[&a&lLAGG CHAT&7&l] &r>");

		String help = "help.1";
		plugin.getConfig().addDefault(help, "&e/lc tatca &fxóa tất cả chat toàn bộ máy chủ");
		String help2 = "help.2";
		plugin.getConfig().addDefault(help2, "&e/lc banthan &fxóa chat của bản thân");
		String help3 = "help.3";
		plugin.getConfig().addDefault(help3, "&e/lc reload &fkhởi động lại plugin");

		String clearall = "message.clear-all";
		plugin.getConfig().addDefault(clearall, "&cCHAT ĐÃ ĐƯỢC XÓA BỞI &7 ");
		String clear = "message.clear";
		plugin.getConfig().addDefault(clear, "&cCHAT ĐÃ ĐƯỢC DỌN SẠCH!");

		String broadcast = "message.broadcast-actionbar";
		plugin.getConfig().addDefault(broadcast, "&cCHAT ĐÃ ĐƯỢC XÓA BỞI &7 ");

		String title = "title.title";
		plugin.getConfig().addDefault(title, "&a&lHOÀN TẤT!");
		String subtitle = "title.subtitle";
		plugin.getConfig().addDefault(subtitle, "&fBạn đã dọn sạch khung chat");

		String noperm = "message.no-perm";
		plugin.getConfig().addDefault(noperm, "&cBạn không có quyền để làm việc này!");
		String reload = "message.reload";
		plugin.getConfig().addDefault(reload, "&aPlugin đã được reload");
		
		getConfig().options().copyDefaults(true);
		saveConfig();

	}

	@Override
	public void onEnable() {
		instance = this;
		plugin = this;
		loadingConfiguration();

		ConsoleCommandSender console = Bukkit.getConsoleSender();
		getServer().getPluginManager().registerEvents(this, this);

		console.sendMessage(cslprefix + ChatColor.GREEN + " Plugin da duoc bat!");
		console.sendMessage(cslprefix + " Code: KujinVN");
		console.sendMessage(cslprefix + " Y tuong: KujinVN");
		console.sendMessage(devcraft + " Devcraft was here!");

	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("lc") || cmd.getName().equalsIgnoreCase("laggchat")) {
			if (args.length < 1) {
				if (p.hasPermission("lc.help") || p.isOp()) {
					p.sendMessage(ChatColor.GOLD + "Plugin made by KujiNVN!");
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
							+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.1"))));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
							+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.2"))));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
							+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.3"))));
					return true;
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
					return true;
				}
			} else if (args.length == 1 && args[0].equalsIgnoreCase("trogiup")) {
				if (p.hasPermission("lc.help") || p.isOp()) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
							+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.1"))));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
							+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.2"))));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
							+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.3"))));
					return true;
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
					return true;
				}
			} else if (args.length > 1) {
				p.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&cKhông rõ yêu cầu, /lc help để xem lại lệnh!"));
				return true;
			} else if (args.length == 1 && args[0].equalsIgnoreCase("tatca")) {
				if (p.hasPermission("lc.clearall") || p.isOp()) {
					for (int i = 0; i < 101; i++) {
						Bukkit.broadcastMessage("");
					}
					Bukkit.broadcastMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.clear-all"))
									+ p.getName());
					ActionBar.sendBroadBar(ChatColor.translateAlternateColorCodes('&',
							getConfig().getString("message.broadcast-actionbar")) + p.getName());
					Titles.sendTitle(p,
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.title")),
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.subtitle")), 20,
							40, 20);
					return true;
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
					return true;
				}

			} else if (args.length == 1 && args[0].equalsIgnoreCase("banthan")) {
				if (p.hasPermission("lc.clear") || p.isOp()) {
					for (int i = 0; i < 101; i++) {
						p.sendMessage("");
					}
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.clear")));
					Titles.sendTitle(p,
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.title")),
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.subtitle")), 20,
							40, 20);
					return true;
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
					return true;
				}

			} else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				if (p.hasPermission("lc.reload") || p.isOp()) {
					reloadConfig();
					saveConfig();

					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.reload")));
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
				}
			}
		}
		return true;
	}
}
