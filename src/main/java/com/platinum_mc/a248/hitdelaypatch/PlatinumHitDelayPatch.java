package com.platinum_mc.a248.hitdelaypatch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlatinumHitDelayPatch extends JavaPlugin {

	private int noDamageTicks = 20;
	
	@Override
	public void onEnable() {
		getServer().getScheduler().runTaskTimer(this, this::resetHitDelays, 11L, 20L);
	}
	
	private void resetHitDelays() {
		for (Player player : getServer().getOnlinePlayers()) {
			player.setMaximumNoDamageTicks(noDamageTicks);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("pmchitdelay.use")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, you cannot use this."));
			return true;
		}
		if (args.length != 1) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cWrong usage"));
			return true;
		}
		try {
			noDamageTicks = Integer.parseInt(args[0]);
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aChanged to " + args[0]));

		} catch (NumberFormatException ex) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNot a number"));
		}
		return true;
	}
	
}
