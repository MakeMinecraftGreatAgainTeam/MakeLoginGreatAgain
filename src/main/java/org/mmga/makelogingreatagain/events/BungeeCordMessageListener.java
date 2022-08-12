package org.mmga.makelogingreatagain.events;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;

/**
 * Created On 2022/8/12 14:25
 *
 * @author wzp
 * @version 1.0.0
 */
public class BungeeCordMessageListener implements PluginMessageListener {
    public final static Map<UUID, Boolean> NEED_LOGIN = new HashMap<>();
    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        boolean b = byteBuf.readBoolean();
        UUID uniqueId = player.getUniqueId();
        if (b){
            boolean playerExist = false;
            try {
                playerExist = !isPlayerExist(uniqueId.toString());
            } catch (SQLException e) {
                errorOnSqlException(e);
            }
            InventoryClick.isPlayerRegister.put(player,playerExist);
            InventoryClick.playerInputIndexAt.put(player,0);
            InventoryClick.playerInputPasswordRe.put(player,"");
            InventoryClick.playerInputPassword.put(player,"");
        }
        NEED_LOGIN.put(uniqueId,b);
    }
}
