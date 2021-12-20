package speedcubing.crash;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.md_5.bungee.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.List;

public class crash extends Command implements TabExecutor {
    public crash() {
        super("crash");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer ? commandSender.hasPermission("crash.use") : commandSender instanceof ConsoleCommandSender) {
            if (strings.length < 1) {
                commandSender.sendMessage(new TextComponent("/crash <player>"));
            } else {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                if (target != null) {
                    commandSender.sendMessage(new TextComponent("[Crash] crashing player \""+strings[0]+"\" ..."));
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF(strings[0]);
                    target.getServer().getInfo().sendData("cubingCrash", out.toByteArray());
                } else {
                    commandSender.sendMessage(new TextComponent("Offline or not exist."));
                }
            }
        } else commandSender.sendMessage(new TextComponent("You don't have permission to perform this command !"));
    }


    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        List<String> names = new ArrayList<>();
        if (strings.length == 1) {
            String str = strings[0].toLowerCase();
            for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
                String name = p.getName();
                if (name.toLowerCase().startsWith(str))
                    names.add(name);
            }
        }
        return names;
    }
}
