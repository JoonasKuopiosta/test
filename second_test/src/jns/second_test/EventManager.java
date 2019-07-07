package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EventManager{

	private Main main;
	private World world;
	ActionListenerClass actionManager;
	TeamManager teamManager;
	ParticipantManager participantManager;
	private int totalLoopsDone;
	
	public EventManager(Main _main, World _world, ParticipantManager _part) {
		main = _main;
		world = _world;
		participantManager = _part;
		teamManager = new TeamManager(participantManager);
	}
	
	public void startUp() {
	}
	
	public void startGame() {
		//TODO: TÄHÄN!
	}
	
	public void test() {
		Bukkit.getLogger().info("Timeri toimii!");
	}
	
	public void getGameSate() {
		main.getGameStage();
	}
	
	public void runnable() {
		int ticksBetweenCycles = 20;
		float desiredMaxMinutes = 15.0f;
		int maxCycles = (int) ((desiredMaxMinutes*60)/(ticksBetweenCycles/20));
		totalLoopsDone = 0;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if ((main.getGameStage() == 3) && (totalLoopsDone < (maxCycles))) {
					// The total loops done max equals to 15 minutes
					
					// Here goes the code that gets run every loop
					// TODO: asd asd asd
					test();
					
					totalLoopsDone++;
				} else {
					this.cancel();
				}
			}
		}.runTaskTimerAsynchronously(main, 0, ticksBetweenCycles);
	}
}
