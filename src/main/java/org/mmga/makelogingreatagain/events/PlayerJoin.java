package org.mmga.makelogingreatagain.events;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;
import org.mmga.makelogingreatagain.utils.PluginUtils;

import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class PlayerJoin implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        try {
            MakeLoginGreatAgainMain plugin = PluginUtils.getPlugin();
            Logger logger = plugin.getLogger();
            Player player = event.getPlayer();
            String name = player.getName();
            if (!isPlayerExist(name)) {
                logger.info("not");
            }else{
                logger.info("yes");
            }
        }catch (SQLException e){
            errorOnSqlException(e);
        }
    }
}
