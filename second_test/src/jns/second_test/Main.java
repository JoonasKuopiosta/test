package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public Permission playerPermission = new Permission("TrueEventAdmin.allowed");
	
	EventManager eventManager;
	ChatCommands chatCommands;
	ParticipantManager participantManager;
	
	// 0 = not active, 1 = player setup, 2 = match setup, 3 = match running
	private int gameStage;
	private World world;
	
	//TODO: Systeemi joka automaattisesti karsii poissaolevat pelaajat
	
	@Override
	public void onEnable() {
		// Informs the server console that the plugin is active
		// and sets gameRunning to false
		getLogger().info("Joonas plugin is enabled v2.0.0");
		
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(playerPermission);
		
		chatCommands = new ChatCommands();
		participantManager = new ParticipantManager();
		eventManager = new EventManager(this, world, participantManager);
		Bukkit.getPluginManager().registerEvents(new ActionListenerClass(eventManager, participantManager), this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public int getGameStage() {
		return gameStage;
	}
	
	public void setEventStage(int newStage) {
		if (newStage >= 0 && newStage <= 3) {
			gameStage = newStage;
		}
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("event") && sender instanceof Player) {
			
			if (!sender.hasPermission("TrueEventAdmin.allowed")) {
				sender.sendMessage(ChatColor.RED +  "EI OLE PERMEJÄ!");
			}
			
			String[] argus = toLenghtOf5(args);
			Player caster = (Player) sender;
			String identifier = argus[0];
			String command = argus[1];
			
			String[] returnMsg = new String[2];
			
			getLogger().info(sender.getName() + " label: " + label + " gameState: " + getGameStage());
			
			if (gameStage == 0) {
				if (identifier.equalsIgnoreCase("activate")) {
					if (command.equalsIgnoreCase("true")) {
						setEventStage(1);
						chatCommands.requestReceiver(gameStage, caster);
					}
					return false;
				}
				chatCommands.requestReceiver(gameStage, caster);
			} else {
				if (identifier.equalsIgnoreCase("player")) {
					returnMsg = participantManager.commandListener(argus, gameStage, caster);
				}
				sendMessage(returnMsg, caster);
			}
		}
		return false;
	}
	
	// =========== PRIVATE METHODS START HERE ===========
	
	private void sendMessage(String[] message, Player player) {
		String text = "";
		if (message != null) {
			if (message[0] != null && message[1] != null) {
				switch (message[0]) {
				case "T":
					text += ChatColor.GREEN;
					break;
				case "F":
					text += ChatColor.RED;
				case "W":
					text += ChatColor.YELLOW;
				}
				
				text += message[1];
				player.sendMessage(text);
			}
		}
	}
	
	private String[] toLenghtOf5(String[] args) {
		String[] argus = {" ", " ", " ", " ", " "};
		
		for (int i = 0; i < args.length; i++) {
			argus[i] = args[i];
		}
		return argus;
	}
}