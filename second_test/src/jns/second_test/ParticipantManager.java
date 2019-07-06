package jns.second_test;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ParticipantManager{

	private float maxDistForNear;
	//private String prsdArgs;
	
	ArrayList<Participant> allParticipantList = new ArrayList<Participant>();
	// A list that contains all current participants
	
	public ParticipantManager() {
		maxDistForNear = 10.0f;
	}
	
	
	// =========== PUBLIC METHODS START HERE ===========
	
	public int addNearbyPlayersToList(Player caster, double maxDist) {
		// Adds all player who are around the caster below or equal to max distance
		// Ignores the caster
		int playersAdded = 0;
		
		for (Player other : Bukkit.getOnlinePlayers()) {
			if(other != caster) {
				if(other.getLocation().distance(caster.getLocation()) <= maxDist) {
					addPlayerToParticipantList(other);
					playersAdded++;
				}
			}
		}
		return playersAdded;
	}
	
	public Participant getParticipant(Player player) {
		// Returns participant to given player, returns NULL if no match can be found from
		// the participant list
		for (Participant part : allParticipantList) {
			if (part.getPlayer() == player) {
				return part;
			}
		}
		return null;
	}
	
	public boolean addParticipant(Player player) {
		// Calls method that adds player into participant list
		if (player != null) {
			addPlayerToParticipantList(player);
			return true;
		}
		return false;
	}
	
	public boolean removeParticipant(Player player) {
		// Calls method that tries to remove player from participant list
		// if false is returned player can't be found from the participant list
		return removePlayerFromParticipantList(player);
	}
	
	public int clearAllParticipants() {
		// Clears the list of players
		int amountRemoved = allParticipantList.size();
		allParticipantList.clear();
		return amountRemoved;
	}
	
	public int teleportAllParticipantsTo(Location cords) {
		int movedParticipants = 0;
		
		for (Participant part : allParticipantList) {
			part.getPlayer().teleport(cords);
			movedParticipants++;
		}
		
		
		return movedParticipants;
	}
	
	// =========== PRIVATE METHODS START HERE ===========
	
	private Player getPlayerByName(String playerName) {
		// Finds player from the SERVER by the given player name
		for (Player other : Bukkit.getOnlinePlayers()) {
			if (other.getName().equalsIgnoreCase(playerName)) {
				return other;
			}
		}
		return null;
	}
	
	private boolean removePlayerFromParticipantList(Player player) {
		// Goes through the list of participants, and if NO participant matching the player
		// can be found returns false
		if (player != null) {
			for (Participant part : allParticipantList) {
				if (part.getPlayer() == player) {
					allParticipantList.remove(part);
					return true;
				}
			}
		}
		return false;
	}
	
	private void addPlayerToParticipantList(Player player) {
		// Creates new Participant and give player as parameter, which is then added to the participant list
		if (checkIfNotListed(player)) {
			Participant participant = new Participant(player);
			allParticipantList.add(participant);
		}
	}
	
	private boolean checkIfNotListed(Player player) {
		for (Participant part : allParticipantList) {
			if (part.getPlayer() == player) {
				return false;
			}
		}
		return true;
	}
	
	private void printAllParticipants(Player caster) {
		int size = allParticipantList.size();
		
		if (allParticipantList.size() < 1) {
			caster.sendMessage(ChatColor.RED + "Ei pelaajia :(");
		}
		
		for (Participant part : allParticipantList) {
			caster.sendMessage(ChatColor.GREEN + part.toString() + " " + size);
		}
	}
	
	
}
