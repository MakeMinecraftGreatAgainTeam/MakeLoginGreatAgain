package org.mmga.makelogingreatagain;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author wzp
 * @date 2022/4/18
 * @version 1.0.0
 */
public final class MakeLoginGreatAgainMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger logger = this.getLogger();
        logger.info(ChatColor.GREEN + "MLGA插件已加载");
    }

    @Override
    public void onDisable() {
        Logger logger = this.getLogger();
        logger.info(ChatColor.RED + "MLGA插件已卸载");
    }
}
