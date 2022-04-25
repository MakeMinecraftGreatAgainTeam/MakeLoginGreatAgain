package org.mmga.makelogingreatagain.events;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;
import org.mmga.makelogingreatagain.utils.PluginUtils;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import static org.mmga.makelogingreatagain.constants.StringConstants.*;
import static org.mmga.makelogingreatagain.constants.IntegerConstants.*;
import static org.mmga.makelogingreatagain.constants.ItemStackConstants.*;
import static org.mmga.makelogingreatagain.constants.ChatColorConstants.*;
import static org.mmga.makelogingreatagain.utils.InventoryUtils.*;
import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/24
 */
public class InventoryClick implements Listener {
    public static ConcurrentHashMap<Player,Integer> playerInputIndexAt = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Player,Boolean> isPlayerUpper = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Player,String> playerInputPassword = new ConcurrentHashMap<>();
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event){
        try {
            InventoryView openInventory = event.getWhoClicked().getOpenInventory();
            String title = openInventory.getTitle();
            MakeLoginGreatAgainMain plugin = PluginUtils.getPlugin();
            Logger logger = plugin.getLogger();
            Player player = (Player) event.getWhoClicked();
            if (inventoryLoginTitle.equals(title)) {
                ItemStack currentItem = event.getCurrentItem();
                if (frame.equals(currentItem)) {
                    player.sendMessage(ChatColor.RED + "请您不要点边框，谢谢！");
                } else if (capsLock.equals(currentItem)) {
                    Boolean isUpper = isPlayerUpper.getOrDefault(player, false);
                    Inventory inventory = createInventory(plugin, inventoryLoginTitle);
                    if (isUpper) {
                        addItemLoginRegisterLower(inventory);
                        isPlayerUpper.put(player, false);
                    } else {
                        addItemLoginRegisterUpper(inventory);
                        isPlayerUpper.put(player, true);
                    }
                    for (int x = 0; x < maxPassword; x++) {
                        ItemStack item = openInventory.getItem(x + 45);
                        if (item != null) {
                            inventory.setItem(x + 45, item);
                        }
                    }
                    player.openInventory(inventory);
                } else if (exit.equals(currentItem)) {
                    player.kickPlayer("[MakeLoginGreatAgain] 成功退出服务器");
                } else if (clear.equals(currentItem)) {
                    for (int x = 0; x < maxPassword; x++) {
                        openInventory.setItem(x + 45, air);
                    }
                    playerInputIndexAt.put(player, 0);
                    playerInputPassword.put(player, "");
                } else if (done.equals(currentItem)) {
                    String password = playerInputPassword.get(player);
                    String name = player.getName();
                    isUsernamePasswordRight(name, password);
                } else {
                    Integer index = playerInputIndexAt.getOrDefault(player, 0);
                    if (index < maxPassword) {
                        openInventory.setItem(index + 45, currentItem);
                        index += 1;
                        playerInputIndexAt.put(player, index);
                        String displayName = currentItem.getItemMeta().getDisplayName();
                        displayName = displayName.replace(letterColor.toString(), "").replace(numberColor.toString(), "");
                        String nowPassword = playerInputPassword.getOrDefault(player, "");
                        nowPassword += displayName;
                        playerInputPassword.put(player, nowPassword);
                    } else {
                        player.sendMessage(ChatColor.RED + "密码长度达到上限！");
                    }
                }
                event.setCancelled(true);
            } else if (inventoryRegisterTitle.equals(title)) {
                ItemStack currentItem = event.getCurrentItem();
                String displayName = currentItem.getItemMeta().getDisplayName();
                logger.info(displayName);
                event.setCancelled(true);
            } else if (inventoryReRegisterTitle.equals(title)) {
                ItemStack currentItem = event.getCurrentItem();
                String displayName = currentItem.getItemMeta().getDisplayName();
                logger.info(displayName);
                event.setCancelled(true);
            }
        }catch (SQLException e){
            errorOnSqlException(e);
        }
    }
}
