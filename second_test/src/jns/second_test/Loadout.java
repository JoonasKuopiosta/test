package jns.second_test;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Loadout {
	
	PlayerInventory genericSurvivor;
	PlayerInventory genericMonster;
	Material[] teamClothing = {Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS};
	
	public Loadout() {
		createGenericSurvior();
		createGenericMonster();
	}
	
	private void createGenericSurvior() {
		ItemStack[] armor = new ItemStack[4];
		
		for (int i = 0; i < armor.length; i++) {
			armor[i] = colorLeather(new ItemStack(teamClothing[i]), Color.GREEN);
		}
		genericSurvivor.setArmorContents(armor);
	}
	
	private void createGenericMonster() {
		ItemStack[] armor = new ItemStack[4];
		
		for (int i = 0; i < armor.length; i++) {
			armor[i] = colorLeather(new ItemStack(teamClothing[i]), Color.RED);
		}
	}
	
	public boolean updateToGenericPlayer(PlayerInventory playerInv) {
		if (playerInv != null) {
			updateLoadout(playerInv, genericSurvivor);
			return true;
		}
		return false;
	}
	
	public boolean updateToGenericMonster(PlayerInventory playerInv) {
		if (playerInv != null) {
			updateLoadout(playerInv, genericMonster);
			return true;
		}
		return false;
	}
	
	private void updateLoadout(PlayerInventory playerInv, PlayerInventory templateInv) {
		// Armor
		playerInv.setArmorContents(templateInv.getArmorContents());
	}
	
	private ItemStack colorLeather(ItemStack armor, Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
		meta.setColor(color);
		armor.setItemMeta(meta);
		return armor;
	}
}
