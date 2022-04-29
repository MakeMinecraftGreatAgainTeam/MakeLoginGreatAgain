package org.mmga.makelogingreatagain.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;
import org.mmga.makelogingreatagain.utils.PluginUtils;

import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

import static org.mmga.makelogingreatagain.constants.StringConstants.*;
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
    public static ConcurrentHashMap<Player,String> playerInputPasswordRe = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Player,Boolean> isPlayerLogin = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Player,Boolean> isPlayerRegister = new ConcurrentHashMap<>();
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event){
        try {
            //获取打开的物品栏
            InventoryView openInventory = event.getWhoClicked().getOpenInventory();
            //获取物品栏窗口标题
            String title = openInventory.getTitle();
            //获取插件
            MakeLoginGreatAgainMain plugin = PluginUtils.getPlugin();
            //获取点击此物品栏的玩家
            Player player = (Player) event.getWhoClicked();
            String name = player.getName();
            String uuid = player.getUniqueId().toString();
            //登陆GUI
            if (inventoryLoginTitle.equals(title)) {
                boolean isInput = tryHandleInput(openInventory, player, event.getCurrentItem(), plugin);
                if (!isInput) {
                    String password = playerInputPassword.get(player);
                    boolean usernamePasswordRight = isUsernamePasswordRight(name,uuid,password);
                    if (usernamePasswordRight){
                        isPlayerLogin.put(player,true);
                        playerInputPassword.put(player,"");
                        playerInputPasswordRe.put(player,"");
                        playerInputIndexAt.put(player,0);
                        isPlayerRegister.put(player,true);
                        isPlayerUpper.put(player,false);
                        updateUserData(name,uuid,player.getAddress().getHostName());
                        player.sendMessage(rightPassword);
                        player.closeInventory();
                    }else{
                        player.kickPlayer(wrongPassword);
                    }
                }
                event.setCancelled(true);
            } else if (inventoryRegisterTitle.equals(title)) {
                boolean isInput = tryHandleInput(openInventory, player, event.getCurrentItem(), plugin);
                if (!isInput) {
                    playerInputIndexAt.put(player,0);
                    isPlayerUpper.put(player,false);
                    player.openInventory(getReRegisterInventory(plugin));
                }
                event.setCancelled(true);
            } else if (inventoryReRegisterTitle.equals(title)) {
                boolean isInput = tryHandleInputReRegister(openInventory, player, event.getCurrentItem(), plugin);
                if (!isInput) {
                    String first = playerInputPassword.get(player);
                    String second = playerInputPasswordRe.get(player);
                    if(first.equals(second)){
                        InetSocketAddress address = player.getAddress();
                        String formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
                        addPlayer(name,uuid,first,address.getHostName(),formatTime);
                        playerInputPassword.put(player,"");
                        playerInputPasswordRe.put(player,"");
                        playerInputIndexAt.put(player,0);
                        isPlayerRegister.put(player,true);
                        isPlayerLogin.put(player,true);
                        isPlayerUpper.put(player,false);
                        player.closeInventory();
                        player.sendMessage(successfulRegister);
                    }else{
                        playerInputPassword.put(player,"");
                        playerInputPasswordRe.put(player,"");
                        playerInputIndexAt.put(player,0);
                        player.sendMessage(twoPasswordNotEquals);
                        player.openInventory(getRegisterInventory(plugin));
                    }
                }
                event.setCancelled(true);
            }
        }catch (SQLException e){
            errorOnSqlException(e);
        }
    }
}
