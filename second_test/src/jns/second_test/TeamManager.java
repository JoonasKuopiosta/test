package jns.second_test;

public class TeamManager {
	
	private ParticipantManager participantManager;
	private Loadout loadout;
	
	public TeamManager(ParticipantManager partManager) {
		participantManager = partManager;
		loadout = new Loadout();
	}
	
	public void participantToMonster(Participant part) {
		
		// team change
		part.setTeam("monster");
		
		// change armor
		loadout.updateToGenericPlayer(part.getPlayer().getInventory());
	}
}