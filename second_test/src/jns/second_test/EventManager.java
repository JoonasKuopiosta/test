package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EventManager{

	private Main main;
	private World world;
	private ParticipantManager participantManager;
	private TeamManager teamManager;
	private int totalLoopsDone;
	
	public EventManager(Main _main, World _world) {
		main = _main;
		world = _world;
	}
	
	public void startUp() {
		participantManager = new ParticipantManager();
	}
	
	public void startGame() {
		teamManager = new TeamManager(participantManager);
		//TODO: TÄHÄN!
	}
	
	public void commandToPlayerManager(String[] argus, Player caster) {participantManager.commandParser(argus, caster);}
	
	public void test() {
		Bukkit.getLogger().info("Timeri toimii!");
	}
	
	public void runnable() {
		int ticksBetweenCycles = 20;
		float desiredMaxMinutes = 15.0f;
		int maxCycles = (int) ((desiredMaxMinutes*60)/(ticksBetweenCycles/20));
		totalLoopsDone = 0;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (main.getGameRunning() && (totalLoopsDone < (maxCycles))) {
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
