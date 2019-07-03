package jns.second_test;

import org.bukkit.entity.Player;

public class Participant {
	
	Player player;
	String team;
	int score;
	
	Participant(Player play) {
		player = play;
		team = "survivor";
		score = 0;
	}
	
	// =========== GETTERS START HERE ===========
	
	public Player getPlayer() {return player;}
	public String getTeam() {return team;}
	public int getScore() {return score;}
	public String getName() {return player.getName();}
	
	// =========== SETTERS START HERE ===========
	
	public boolean setTeam(String newTeam) {
		// Requires string that needs to be either "survivor" or "monster" and assigns that to the
		// participant
		switch(newTeam) {
		case "survivor":
			team = "survivor";
			return true;
		case "monster":
			team = "monster";
			return true;
		default:
			return false;
		}
	}
	
	public boolean setScore(int newScore) {
		score = newScore;
		return true;
	}
	
	// =========== REST OF METHODS START HERE ===========
	
	public String changeTeam() {
		// Changes the team of the participant to the opposite of what it currently is
		// Returns the new team assigned to the participant
		if (team == "survivor") {
			team = "monster";
		} else {
			team = "survior";
		}
		return team;
	}
	
	public boolean addScore(int scoreToAdd) {
		score += scoreToAdd;
		return true;
	}
	
	public String toString() {
		String txt = getName() + ",  pisteet: " + getScore() + ",  puoli: " + getTeam();
		return txt;
	}
}