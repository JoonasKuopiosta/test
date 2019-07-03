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
	}
	
	public PlayerInventory getGenericSurvivor() {return genericSurvivor;}
	public PlayerInventory getGenericMonster() {return genericMonster;}
	
	public void createGenericSurvior() {
		ItemStack[] armor = new ItemStack[4];
		
		for (int i = 0; i < armor.length; i++) {
			armor[i] = colorLeather(new ItemStack(teamClothing[i]), Color.GREEN);
		}
		genericSurvivor.setArmorContents(armor);
	}
	
	public void createGenericMonster() {
		ItemStack[] armor = new ItemStack[4];
		
		for (int i = 0; i < armor.length; i++) {
			armor[i] = colorLeather(new ItemStack(teamClothing[i]), Color.RED);
		}
	}
	
	private ItemStack colorLeather(ItemStack armor, Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
		meta.setColor(color);
		armor.setItemMeta(meta);
		return armor;
	}
}
