package jns.second_test;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import net.minecraft.server.v1_13_R2.PlayerConnection;

public class ChatCommands {
	
	public ChatCommands(EventManager eventManager) {
		// TODO Auto-generated constructor stub
	}
	
	// 0 = not active, 1 = player setup, 2 = match setup, 3 = match running
	public void requestReceiver(int gameStage, Player caster) {
		
		
		statusMessage(gameStage, caster);
	}
	
	
	private void statusMessage(int gameStage, Player caster) {
		String text;
		
		switch (gameStage) {
		case 0:
			text = "1/4 Eventti ei ole aktivoitu.";
			caster.sendMessage(ChatColor.GREEN + text);
			//notActiveCommands(caster);
			break;
		case 1:
			text = "2/4 Eventti p‰‰ll‰, lis‰‰ pelaajat!";
			break;
		case 2:
			text = "3/4 Lis‰‰ viel‰ pelin ehdot.";
			break;
		case 3:
			text = "4/4 Peli on k‰ynniss‰!";
			break;
		}
	}
	
	private void notActiveCommands(Player caster) {
		String serializedText = createSerializedText("[add]", "Lis‰‰ pelaajan", "Lis‰‰ pelaajan eventtiin", "/event add");
		messageSender(serializedText, caster);
	}
	
	private void messageSender(String serializedText, Player caster) {
		PlayerConnection connection = ((CraftPlayer) caster).getHandle().playerConnection;
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(serializedText));
		connection.sendPacket(packet);
	}
	
	private String createSerializedText(String clickText, String hoverText, String description, String command) {
		String text = "[";
		text += createClicklableText(clickText, hoverText, command);
		text += ",";
		text += createDescriptionText(description);
		text += "]";
		
		return text;
	}
	
	private String createClicklableText(String clickText, String hoverText, String command) {
		String text = "{\"text\":\"" + clickText + "\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + hoverText + "\"}]}}}";
		
		return text;
	}
	
	private String createDescriptionText(String description) {
		String text = "{\"text\":\"" + description + "\",\"color\":\"none\"}";
		return text;
	}
}
