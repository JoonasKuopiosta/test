package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Loadout {
	
	private Material[] teamClothing = {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET};
	
	public Loadout() {
		
	}
	
	
	
	public boolean updateToGenericPlayer(PlayerInventory playerInv) {
		if (playerInv != null) {
			playerInv.clear();
			genericSurvivorLeatherArmor(playerInv);
			bucketHand(playerInv);
			return true;
		}
		return false;
	}
	
	public boolean updateToGenericMonster(PlayerInventory playerInv) {
		if (playerInv != null) {
			playerInv.clear();
			genericMonsterLeatherArmor(playerInv);
			bucketHand(playerInv);
			return true;
		}
		return false;
	}
	
	
	
	// ========= ARMOR SECTION =========
	
	private void genericSurvivorLeatherArmor(PlayerInventory playerInv) {
		ItemStack[] armor = new ItemStack[4];
		
		for (int i = 0; i < armor.length; i++) {
			armor[i] = colorLeather(new ItemStack(teamClothing[i]), Color.GREEN);
		}
		playerInv.setArmorContents(armor);
	}
	
	private void genericMonsterLeatherArmor(PlayerInventory playerInv) {
		ItemStack[] armor = new ItemStack[4];
		
		for (int i = 0; i < armor.length; i++) {
			armor[i] = colorLeather(new ItemStack(teamClothing[i]), Color.RED);
		}
		playerInv.setArmorContents(armor);
	}
	
	
	
	// ========= TOOL BAR =========
	
	private void bucketHand(PlayerInventory playerInv) {
		ItemStack bucket = new ItemStack(Material.BUCKET);
		playerInv.addItem(bucket);
	}
	
	
	
	// ========= PRIVATE OTHER METHODS =========
	
	private ItemStack colorLeather(ItemStack armor, Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
		meta.setColor(color);
		armor.setItemMeta(meta);
		return armor;
	}
}
