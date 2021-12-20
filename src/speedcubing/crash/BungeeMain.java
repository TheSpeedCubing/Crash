package speedcubing.crash;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {
    public void onEnable() {
        getProxy().registerChannel("cubingCrash");
        getProxy().getPluginManager().registerCommand(this, new crash());
    }
}
