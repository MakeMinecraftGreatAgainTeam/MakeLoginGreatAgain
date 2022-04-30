package org.mmga.makelogingreatagain;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mmga.makelogingreatagain.commands.MakeLoginGreatAgain;
import org.mmga.makelogingreatagain.events.InventoryClick;
import org.mmga.makelogingreatagain.events.PlayerJoin;
import org.mmga.makelogingreatagain.events.TickEvent;
import org.mmga.makelogingreatagain.utils.PluginUtils;

import java.util.logging.Logger;

import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;
import static org.mmga.makelogingreatagain.constants.StringConstants.*;

/**
 * @author wzp
 * @date 2022/4/18
 * @version 1.0.0
 */
public final class MakeLoginGreatAgainMain extends JavaPlugin {

    public static Logger logger;

    @Override
    public void onEnable() {
        //获取日志系统
        logger = this.getLogger();
        //获取默认配置文件
        this.saveDefaultConfig();
        //重载配置文件
        this.reloadConfig();
        //数据库操作
        getDefaultTable();
        //注册指令
        this.getCommand(commandMakeLoginGreatAgain).setExecutor(new MakeLoginGreatAgain());
        this.getCommand(commandMLGA).setExecutor(new MakeLoginGreatAgain());
        //注册事件
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoin(),this);
        pluginManager.registerEvents(new InventoryClick(),this);
        TickEvent tickEvent = new TickEvent();
        tickEvent.runTaskTimer(this,0L,1L);
        boolean b = PluginUtils.reloadLanguageConfig();
        if(!b){
            logger.info(ChatColor.RED + "Didn't find full language configure,using the default config");
        }
        logger.info(ChatColor.GREEN + "MLGA插件已加载");
    }

    @Override
    public void onDisable() {
        //获取日志系统
        Logger logger = this.getLogger();
        logger.info(ChatColor.RED + "MLGA插件已卸载");
    }
}
