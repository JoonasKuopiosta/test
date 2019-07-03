package jns.second_test;

import java.util.ArrayList;

abstract class TeamManager {
	
	ArrayList<Participant> participantList;
	
	public TeamManager() {
		participantList = new ArrayList<Participant>();
	}
}
