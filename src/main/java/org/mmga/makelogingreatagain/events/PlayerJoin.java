package org.mmga.makelogingreatagain.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

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
            Player player = event.getPlayer();
            String name = player.getUniqueId().toString();
            InventoryClick.isPlayerLogin.put(player,false);
            boolean playerExist = !isPlayerExist(name);
            InventoryClick.isPlayerRegister.put(player,playerExist);
            InventoryClick.playerInputIndexAt.put(player,0);
            InventoryClick.playerInputPasswordRe.put(player,"");
            InventoryClick.playerInputPassword.put(player,"");
        }catch (SQLException e){
            errorOnSqlException(e);
        }
    }
}
