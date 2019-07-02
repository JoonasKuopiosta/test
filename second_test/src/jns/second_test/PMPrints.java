package jns.second_test;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PMPrints {

	public static void printMessages(Player caster, String[] argus, boolean result) {
		switch (argus[1]) {
		case "add":
			if (result) {
				caster.sendMessage(ChatColor.GREEN + "Pelaaja " + argus[2] + " lisätty!");
			} else {
				caster.sendMessage(ChatColor.RED + "Pelaajaa nimellä " + argus[2] + " ei löydy!");
			}
			break;
		}
	}
}
