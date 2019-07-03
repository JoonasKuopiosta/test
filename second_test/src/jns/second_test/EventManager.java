package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EventManager{

	Main main;
	PlayerManager playerManager;
	int totalLoopsDone;
	// kommentti
	
	public EventManager(Main reference) {
		main = reference;
	}
	
	public void startUp() {
		playerManager = new PlayerManager();
	}
	
	public void commandToPlayerManager(String[] argus, Player caster) {playerManager.commandParser(argus, caster);}
	
	public void test() {
		Bukkit.getLogger().info("Jeee jee");
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
