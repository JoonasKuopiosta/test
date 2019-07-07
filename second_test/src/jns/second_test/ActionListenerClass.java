package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class ActionListenerClass implements Listener {
	
	EventManager eventManager;
	ParticipantManager participantManager;
	
	public ActionListenerClass(EventManager eventMang, ParticipantManager partMang) {
		eventManager = eventMang;
		participantManager = partMang;
		Bukkit.getLogger().info("Acton manager aktiivinen");
	}
	
	@EventHandler
	public void onTest(EntityDamageByEntityEvent event) {
		int result = whoIsHittingWho(event.getDamager(), event.getEntity());
		switch (result) {
		case 0:
			event.setCancelled(true); // friendly fire cancelled
			break;
		case 1:
			// changing team to monster
			break;
		case 2:
			break;
		}
	}
	
	@EventHandler
	public void onBucket(final PlayerBucketFillEvent event) {
		if (event != null) {
			Player player = event.getPlayer();
			boolean result_1;
					;
			result_1 = participantManager.isPlayerExist(player);
			if (result_1) {
				Participant part = participantManager.getParticipant(player);
				if (part.getTeam().equalsIgnoreCase("monster")) {
					eventManager.teamManager.participantToSurvivor(part);
				} else {
					eventManager.teamManager.participantToMonster(part);
					Bukkit.getLogger().info("Yritys monsteriksi!");
				}
			}
			Bukkit.getLogger().info(player.getDisplayName() + " heitti! On mukana: " + result_1);
		}
	}
	
	
	// -1 = false alert
	// 0 = friendly fire
	// 1 = monster -> player (monster hits player)
	// 2 = player -> monster (player hits monster)
	private int whoIsHittingWho(Entity damager, Entity damaged) {
		if (damager instanceof Player && damaged instanceof Player) { // Are both players?
			Participant attacker = eventManager.participantManager.getParticipant(((Player) damager));
			Participant victim = eventManager.participantManager.getParticipant(((Player) damaged));
			if (attacker != null && victim != null) { // Are both participants?
				String attTeam = attacker.getTeam();
				String vicTeam = victim.getTeam();
				
				if (attTeam.equals(vicTeam)) { // Are their teams the same?
					return 0; // friendly fire
				}
				if (attTeam.equals("monster")) { // Is attacker monster?
					return 1; // monster -> player
				}
				return 2; // If it's nothing else it must be player -> monster
			}
		}
		return -1;
	}
}
