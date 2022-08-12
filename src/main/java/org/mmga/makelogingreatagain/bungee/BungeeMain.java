package org.mmga.makelogingreatagain.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.mmga.makelogingreatagain.bungee.events.PlayerJoin;


/**
 * Created On 2022/8/12 13:53
 *
 * @author wzp
 * @version 1.0.0
 */
public class BungeeMain extends Plugin {
    @Override
    public void onEnable() {
        ProxyServer proxy = this.getProxy();
        proxy.registerChannel("mlga:login");
        PluginManager pluginManager = proxy.getPluginManager();
        pluginManager.registerListener(this, new PlayerJoin());
    }
}
