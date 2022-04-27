package org.mmga.makelogingreatagain.events;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;
import org.mmga.makelogingreatagain.utils.InventoryUtils;
import org.mmga.makelogingreatagain.utils.PluginUtils;

import static org.mmga.makelogingreatagain.constants.StringConstants.*;
import static org.mmga.makelogingreatagain.events.InventoryClick.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/27
 */
public class TickEvent extends BukkitRunnable {
    @Override
    public void run() {
        MakeLoginGreatAgainMain plugin = PluginUtils.getPlugin();
        Server server = plugin.getServer();
        for (Player onlinePlayer : server.getOnlinePlayers()) {
            Boolean isLogin = InventoryClick.isPlayerLogin.get(onlinePlayer);
            Boolean isRegister = InventoryClick.isPlayerRegister.get(onlinePlayer);
            if(!isLogin){
                InventoryView openInventory = onlinePlayer.getOpenInventory();
                String title = openInventory.getTitle();
                if (!title.equals(inventoryLoginTitle) && !title.equals(inventoryRegisterTitle) && !title.equals(inventoryReRegisterTitle)) {
                    if(!isRegister){
                        onlinePlayer.openInventory(InventoryUtils.getRegisterInventory(plugin));
                    }else{
                        onlinePlayer.openInventory(InventoryUtils.getLoginInventory(plugin));
                    }
                    playerInputPasswordRe.put(onlinePlayer,"");
                    playerInputPassword.put(onlinePlayer,"");
                    playerInputIndexAt.put(onlinePlayer,0);
                    onlinePlayer.sendMessage(tryExitInventory);
                }
            }
        }

    }
}
