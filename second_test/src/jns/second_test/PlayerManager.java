package jns.second_test;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PlayerManager{
	
	float maxDistForNear;
	String prsdArgs;
	
	ArrayList<Participant> participantList = new ArrayList<Participant>();
	// A list that contains all current participants
	
	public PlayerManager() {
		maxDistForNear = 10.0f;
	}
	
	public void commandParser(String[] argus, Player caster) {
		boolean isPlayer = caster instanceof Player;
		String infoText = "Sallitut player-komennot: add, remove, addnear, printall, clear";
		boolean result = false;
		
		if (argus[1] != null){
			String param = argus[2];
			
			switch(argus[1]) {
			case "add":
				if (param == null) {
					infoText = "Käyttötapa: add [pelaajan nimi]";
				} else {
					result = addParticipant(getPlayerByName(param));
					if (result) {
						infoText = "Lisäsit onnistuneesti pelaajan " + param;
					} else {
						infoText = "Nimellä " + param+ " ei löytynyt pelaajaa!";
					}
				}
				break;
			case "remove":
				if (param == null) {
					infoText = "Käyttötapa: remove [pelaajan nimi]";
				} else {
					result = removeParticipant(getPlayerByName(param));
					if (result) {
						infoText = "Poistit onnistuneesti pelaajan " + param;
					} else {
						infoText = "Nimellä " + param + " ei löytynyt eventin osanottajaa!";
					}
				}
				break;
			case "addnear":
				if (!isPlayer) {
					infoText = "addnear ei ole sallittu konsoli-komento!";
				} else {
					addNearbyPlayersToList(caster, maxDistForNear);
					int num = addNearbyPlayersToList(caster, maxDistForNear);
					if (num < 1) {
						infoText = "Lähelläsi ei yhtään pelaajaa!";
					} else {
						infoText = "Lisäsit " + num + " pelaajaa!";
					}
				}
				break;
			case "clear":
				int num = clearAllParticipants();
				if (num < 1) {
					infoText = "Ei yhtään pelaajaa poistettavaksi!";
				} else {
					infoText = "Poistit juuri " + num + " pelaajaa";
				}
				break;
			case "printall":
				printAllParticipants(caster);
				infoText = null;
				break;
			}
		}
		
		if (infoText != null) {
			if (isPlayer) {
				if (result) {
					caster.sendMessage(ChatColor.GREEN + infoText);
				} else {
					caster.sendMessage(ChatColor.RED + infoText);
				}
			} else {
				Bukkit.getLogger().info(infoText);
			}
		}
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
		for (Participant part : participantList) {
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
		int amountRemoved = participantList.size();
		participantList.clear();
		return amountRemoved;
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
			for (Participant part : participantList) {
				if (part.getPlayer() == player) {
					participantList.remove(part);
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
			participantList.add(participant);
		}
	}
	
	private boolean checkIfNotListed(Player player) {
		for (Participant part : participantList) {
			if (part.getPlayer() == player) {
				return false;
			}
		}
		return true;
	}
	
	private void printAllParticipants(Player caster) {
		int size = participantList.size();
		
		if (participantList.size() < 1) {
			if (caster instanceof Player) {
				caster.sendMessage(ChatColor.RED + "Ei pelaajia :(");
			} else {
				Bukkit.getLogger().info("Ei pelaajia :(");
			}
		}
		
		for (Participant part : participantList) {
			if (caster instanceof Player) {
				caster.sendMessage(ChatColor.GREEN + part.toString() + " " + size);
			} else {
				Bukkit.getLogger().info(part.toString() + " " + size);
			}
		}
	}
}
