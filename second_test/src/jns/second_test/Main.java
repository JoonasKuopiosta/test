package jns.second_test;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	EventManager eventManager;
	ChatCommands chatCommands;
	
	// 0 = not active, 1 = player setup, 2 = match setup, 3 = match running
	private int eventStage;
	private World world;
	
	//TODO: Systeemi joka automaattisesti karsii poissaolevat pelaajat
	
	@Override
	public void onEnable() {
		// Informs the server console that the plugin is active
		// and sets gameRunning to false
		getLogger().info("Joonas plugin is enabled v2.0.0");
		eventManager = new EventManager(this, world);
		chatCommands = new ChatCommands(eventManager);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public int getEventStage() {
		return eventStage;
	}
	
	public void setEventStage(int newStage) {
		if (newStage >= 0 && newStage <= 3) {
			eventStage = newStage;
		}
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("event") && sender instanceof Player) {
			Player caster = (Player) sender;
			if (eventStage == 0) {
				chatCommands.requestReceiver(eventStage, caster);
			}
		}
		return false;
	}
	
	// =========== PRIVATE METHODS START HERE ===========
	
	private String[] toLenghtOf5(String[] args) {
		String[] argus = new String[5];
		
		for (int i = 0; i < args.length; i++) {
			argus[i] = args[i];
		}
		return argus;
	}
}