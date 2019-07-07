package jns.second_test;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class ChatCommands {
	
	public ChatCommands() {
		// TODO Auto-generated constructor stub
	}
	
	// 0 = not active, 1 = player setup, 2 = match setup, 3 = match running
	public void requestReceiver(int gameStage, Player caster) {
		statusMessage(gameStage, caster);
	}
	
	
	private void statusMessage(int gameStage, Player caster) {
		caster.sendMessage(ChatColor.GREEN + "===== TRUE EVENT =====");
		
		String text;
		
		switch (gameStage) {
		case 0:
			text = "1/4 Eventti ei ole aktivoitu.";
			caster.sendMessage(ChatColor.GREEN + text);
			notActiveCommands(caster);
			break;
		case 1:
			text = "2/4 Eventti p‰‰ll‰, lis‰‰ pelaajat!";
			caster.sendMessage(ChatColor.GREEN + text);
			playerSetup(caster);
			break;
		case 2:
			text = "3/4 Lis‰‰ viel‰ pelin ehdot.";
			caster.sendMessage(ChatColor.GREEN + text);
			break;
		case 3:
			text = "4/4 Peli on k‰ynniss‰!";
			caster.sendMessage(ChatColor.GREEN + text);
			break;
		}
	}
	
	private void notActiveCommands(Player caster) {
		String serializedText = createAcceptDeclineAndDesc("Aktivoi event?", "/event activate");
		messageSender(serializedText, caster);
	}
	
	private void playerSetup(Player caster) {
		String serializedText = createClickableAndDesc("[addNear]", "/event player addNear", "Lis‰‰ pelaajat l‰helt‰si", "/event player addNear", "dark_aqua", "aqua");
		messageSender(serializedText, caster);
		serializedText = createClickableAndDesc("[list]", "/event player list", "Listaa kaikki eventin pelaajat", "/event player list", "dark_aqua", "aqua");
		messageSender(serializedText, caster);
		serializedText = createClickableAndDesc("[tpAll]", "/event player tpAll", "Teleporttaa eventin pelaajat luoksesi", "/event player tpAll", "dark_aqua", "aqua");
		messageSender(serializedText, caster);
		serializedText = createClickableAndDesc("[clear]", "/event player clear", "Tyhjent‰‰ kaikki pelaajat pelist‰", "/event player clear", "dark_aqua", "aqua");
		messageSender(serializedText, caster);
		
		caster.sendMessage(ChatColor.DARK_AQUA + "/event player add [nimi]" + ChatColor.AQUA +  " Lis‰‰ pelaajan");
		caster.sendMessage(ChatColor.DARK_AQUA + "/event player remove [nimi]" + ChatColor.AQUA + " Poistaa pelaajan");
		
		serializedText = createClickableAndDesc("[next]", "/event next", "Jatka er‰n valmisteluun", "/event next", "dark_green", "blue");
		messageSender(serializedText, caster);
		serializedText = createClickableAndDesc("[stop]", "/event stop", "Keskeyt‰ event", "/event stop", "red", "blue");
		messageSender(serializedText, caster);
	}
	
	
	
	
	// ====== MESSAGE METHODS =======
	
	private void messageSender(String serializedText, Player caster) {
		PlayerConnection connection = ((CraftPlayer) caster).getHandle().playerConnection;
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(serializedText));
		connection.sendPacket(packet);
	}
	
	private String createClickableAndDesc(String clickText, String hoverText, String description, String command, String clickableColor, String descriptionColor) {
		String text = "[\"\",";
		text += createClicklableText(clickText, hoverText, command, clickableColor);
		text += ",";
		text += createDescriptionText(description, descriptionColor);
		text += "]";
		
		return text;
	}
	
	private String createAcceptDeclineAndDesc(String description, String command) {
		String text = "[\"\",";
		text += createClicklableText("[Yes]", "/event activate true", (command + " true"), "dark_green");
		text += ",";
		text += createDescriptionText("/ ", "green");
		text += ",";
		text += createClicklableText("[No]", "/event activate false", (command + " false"), "red");
		text += ",";
		text += createDescriptionText(description, "green");
		text += "]";
		
		return text;
	}
	
	private String createClicklableText(String clickText, String hoverText, String command, String color) {
		String text = "{\"text\":\"" + clickText +  "\",\"color\":\"" + color + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command +  "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + hoverText + "\"}}";
		return text;
	}
	
	private String createDescriptionText(String description, String color) {
		String text = "{\"text\":\" " + description + "\",\"color\":\"" + color + "\"}";
		return text;
	}
}
