package org.mmga.makelogingreatagain.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;
import org.mmga.makelogingreatagain.utils.PluginUtils;

import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.mmga.makelogingreatagain.constants.StringConstants.*;
import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;
import static org.mmga.makelogingreatagain.utils.InventoryUtils.*;

/**
 * @author wzp
 * @version 1.0.0
 * @data 2022/4/24
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
            UUID uniqueId = player.getUniqueId();
            String uuid = uniqueId.toString();
            ItemStack itemStack = event.getCurrentItem();
            //登陆GUI
            if (inventoryLoginTitle.equals(title)) {
                boolean isInput = tryHandleInput(openInventory, player, itemStack, plugin);
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
                        // 免责声明提示
                        player.performCommand("tellraw @s {\"text\": \"继续游玩则表明您同意我们的《免责声明》，如不同意，请退出服务器（点击此消息即可查看）\",\"color\": \"green\",\"clickEvent\": {\"action\": \"open_url\", \"value\": \"https://docs.qq.com/doc/DWEVMQmxRTHh2akRU\"}}");
                        if (BungeeCordMessageListener.NEED_LOGIN.containsKey(uniqueId)){
                            BungeeCordMessageListener.NEED_LOGIN.put(uniqueId,false);
                        }
                        player.closeInventory();
                    }else{
                        player.kickPlayer(wrongPassword);
                    }
                }
                event.setCancelled(true);
            } else if (inventoryRegisterTitle.equals(title)) {
                boolean isInput = tryHandleInput(openInventory, player, itemStack, plugin);
                if (!isInput) {
                    playerInputIndexAt.put(player,0);
                    isPlayerUpper.put(player,false);
                    player.openInventory(getReRegisterInventory(plugin));
                }
                event.setCancelled(true);
            } else if (inventoryReRegisterTitle.equals(title)) {
                boolean isInput = tryHandleInputReRegister(openInventory, player,itemStack, plugin);
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
                        if (BungeeCordMessageListener.NEED_LOGIN.containsKey(uniqueId)){
                            BungeeCordMessageListener.NEED_LOGIN.put(uniqueId,false);
                        }
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
