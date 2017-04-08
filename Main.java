package clear.chat.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;;

public class Main extends JavaPlugin implements Listener {

	public static Plugin plugin;
	static Main instance;

	String cslprefix = "[LaggChat]";

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
		plugin.getConfig().addDefault(prefix, "&7&l[&a&lLAGG CHAT&7&l] &r> ");

		String help = "help.1";
		plugin.getConfig().addDefault(help, "&e/laggchat tatca &fxóa tất cả chat toàn bộ máy chủ");
		String help2 = "help.2";
		plugin.getConfig().addDefault(help2, "&e/laggchat banthan &fxóa chat của bản thân");
		String help3 = "help.3";
		plugin.getConfig().addDefault(help3, "&e/laggchat reload &fkhởi động lại plugin");
		String help4 = "help.4";
		plugin.getConfig().addDefault(help4, "&eBạn có thể dùng lệnh rút gọn &a/lc");

		String clearall = "message.clear-all";
		plugin.getConfig().addDefault(clearall, "&cCHAT ĐÃ ĐƯỢC XÓA BỞI &7%p");
		String clear = "message.clear";
		plugin.getConfig().addDefault(clear, "&cCHAT ĐÃ ĐƯỢC DỌN SẠCH!");

		String broadcast = "message.broadcast-actionbar";
		plugin.getConfig().addDefault(broadcast, "&cCHAT ĐÃ ĐƯỢC XÓA BỞI &7%p");

		String title = "title.title";
		plugin.getConfig().addDefault(title, "&a&lHOÀN TẤT!");
		String subtitle = "title.subtitle";
		plugin.getConfig().addDefault(subtitle, "&fBạn đã dọn sạch khung chat");
		String reloadtitle = "title.reload-title";
		plugin.getConfig().addDefault(reloadtitle, "&a&lRELOAD CONFIGURATION!");
		String reloadsubtitle = "title.reload-subtitle";
		plugin.getConfig().addDefault(reloadsubtitle, "&fConfig, plugin đã được reload");

		String noperm = "message.no-perm";
		plugin.getConfig().addDefault(noperm, "&cBạn không có quyền để làm việc này!");
		String reload = "message.reload";
		plugin.getConfig().addDefault(reload, "&aPlugin đã được reload");

		String music1 = "music.tatca";
		plugin.getConfig().addDefault(music1, "ENTITY_PLAYER_LEVELUP");
		String music2 = "music.banthan";
		plugin.getConfig().addDefault(music2, "BLOCK_NOTE_PLING");
		String music3 = "music.trogiup";
		plugin.getConfig().addDefault(music3, "ITEM_SHIELD_BLOCK");
		String music4 = "music.reload";
		plugin.getConfig().addDefault(music4, "BLOCK_LEVER_CLICK");
		String music5 = "music.no-perm";
		plugin.getConfig().addDefault(music5, "ITEM_SHIELD_BREAK");

		getConfig().options().copyDefaults(true);
		saveConfig();

	}

	PluginDescriptionFile pdf = getDescription();

	@Override
	public void onEnable() {
		instance = this;
		plugin = this;
		loadingConfiguration();

		ConsoleCommandSender console = Bukkit.getConsoleSender();
		getServer().getPluginManager().registerEvents(this, this);

		console.sendMessage(cslprefix + ChatColor.GREEN + " Plugin da duoc bat!");
		console.sendMessage(cslprefix + ChatColor.DARK_PURPLE + " Code: " + ChatColor.AQUA + "Explorer");
		console.sendMessage(cslprefix + ChatColor.DARK_PURPLE + " Y tuong: " + ChatColor.AQUA + "Explorer");
		console.sendMessage(cslprefix + ChatColor.DARK_PURPLE + " Version: " + ChatColor.RESET + pdf.getVersion());

	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(cslprefix + ChatColor.RED + " Lenh chi co the su dung trong tro choi!");
		} else {
			Player p = (Player) sender;
			Location loc = p.getLocation();

			if (cmd.getName().equalsIgnoreCase("lc") || cmd.getName().equalsIgnoreCase("laggchat")) {
				if (args.length < 1) {
					if (p.hasPermission("lc.help") || p.isOp()) {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.trogiup")), 4F, 1F);
						p.sendMessage(ChatColor.WHITE + "Plugin made by " + ChatColor.AQUA + "Explorer "
								+ ChatColor.WHITE + "!");
						p.sendMessage(ChatColor.WHITE + "Version " + pdf.getVersion());
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.1"))));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.2"))));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.3"))));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.4"))));
						return true;
					} else {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.no-perm")), 4F, 1F);
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
						return true;
					}
				} else if (args.length == 1 && args[0].equalsIgnoreCase("trogiup")) {
					if (p.hasPermission("lc.help") || p.isOp()) {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.trogiup")), 4F, 1F);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.1"))));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.2"))));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.3"))));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.4"))));
						return true;
					} else {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.no-perm")), 4F, 1F);
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
						return true;
					}
				} else if (args.length > 1) {
					loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.no-perm")), 4F, 1F);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&cKhông rõ yêu cầu, /lc trogiup để xem lại lệnh!"));
					return true;
				} else if (args.length == 1 && args[0].equalsIgnoreCase("tatca")) {
					if (p.hasPermission("lc.clearall") || p.isOp()) {
						for (Player pl : getServer().getOnlinePlayers()) {
							pl.getLocation().getWorld().playSound(pl.getLocation(),
									Sound.valueOf(getConfig().getString("music.tatca")), 4F, 1F);
							for (int i = 0; i < 101; i++) {
								pl.sendMessage("");
							}
						}
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
								getConfig().getString("message.clear-all").replaceAll("%p", p.getDisplayName())));
						Titles.sendTitle(p,
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.title")),
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.subtitle")),
								20, 40, 20);
						ActionBar.sendBroadBar(ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("message.broadcast-actionbar").replaceAll("%p", p.getDisplayName())));
						return true;
					} else {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.no-perm")), 4F, 1F);
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
						return true;
					}

				} else if (args.length == 1 && args[0].equalsIgnoreCase("banthan")) {
					if (p.hasPermission("lc.clear") || p.isOp()) {
						for (int i = 0; i < 101; i++) {
							p.sendMessage("");
						}
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.banthan")), 4F, 1F);
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.clear")));
						Titles.sendTitle(p,
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.title")),
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("title.subtitle")),
								20, 40, 20);

						return true;
					} else {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.no-perm")), 4F, 1F);
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
						return true;
					}

				} else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
					if (p.hasPermission("lc.reload") || p.isOp()) {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.reload")), 4F, 1F);
						reloadConfig();
						saveConfig();

						ConsoleCommandSender console = Bukkit.getConsoleSender();

						Titles.sendTitle(p,
								ChatColor.translateAlternateColorCodes('&',
										getConfig().getString("title.reload-title")),
								ChatColor.translateAlternateColorCodes('&',
										getConfig().getString("title.reload-subtitle")),
								20, 40, 20);

						console.sendMessage(cslprefix + ChatColor.GREEN + " Plugin duoc reload boi " + ChatColor.GRAY
								+ sender.getName());
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"))
								+ ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.reload")));
					} else {
						loc.getWorld().playSound(loc, Sound.valueOf(getConfig().getString("music.no-perm")), 4F, 1F);
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.no-perm")));
					}
				}
			}
		}

		return true;
	}
}
