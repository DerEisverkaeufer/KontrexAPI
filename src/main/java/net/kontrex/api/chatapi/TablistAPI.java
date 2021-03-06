package net.kontrex.api.chatapi;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by DerEisverkaeufer on 05.09.2017.
 */
public class TablistAPI {

    /**
     * Sets the tablist header and footer of a specified player.
     * @param p The player which header and footer will be set.
     * @param header The header which will be set for the player.
     * @param footer The footer which will be set for the player.
     */
    public static void set(Player p, String header, String footer) {
        PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;

        IChatBaseComponent headerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}");
        IChatBaseComponent footerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter tablistPacket = new PacketPlayOutPlayerListHeaderFooter(headerComponent);

        try {
            Field field = tablistPacket.getClass().getDeclaredField("f");
            field.setAccessible(true);
            field.set(tablistPacket, footerComponent);
        } catch (Exception ex) {

        } finally {
            con.sendPacket(tablistPacket);
        }

    }

}
