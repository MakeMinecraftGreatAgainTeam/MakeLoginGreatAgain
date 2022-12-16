package org.mmga.makelogingreatagain.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.UUID;

import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;

/**
 * @author wzp
 * @version 1.0.0
 */
public class PlayerJoin implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uniqueId = player.getUniqueId();
        System.out.println(BungeeCordMessageListener.NEED_LOGIN);
        Boolean orDefault = BungeeCordMessageListener.NEED_LOGIN.getOrDefault(uniqueId, true);
        if (orDefault){
            try {
                String name = player.getName();
                String uuid = uniqueId.toString();
                String ip = player.getAddress().getHostName();
                InventoryClick.isPlayerLogin.put(player,false);
                boolean playerExist = !isPlayerExist(uuid);
                if(playerExist){
                    updateUserData(name,uuid,ip);
                }
                InventoryClick.isPlayerRegister.put(player,playerExist);
                InventoryClick.playerInputIndexAt.put(player,0);
                InventoryClick.playerInputPasswordRe.put(player,"");
                InventoryClick.playerInputPassword.put(player,"");
            }catch (SQLException e){
                errorOnSqlException(e);
            }
        }else{
            InventoryClick.playerInputPassword.put(player,"");
            InventoryClick.playerInputPasswordRe.put(player,"");
            InventoryClick.playerInputIndexAt.put(player,0);
            InventoryClick.isPlayerRegister.put(player,true);
            InventoryClick.isPlayerLogin.put(player,true);
            InventoryClick.isPlayerUpper.put(player,false);
        }
    }
}
