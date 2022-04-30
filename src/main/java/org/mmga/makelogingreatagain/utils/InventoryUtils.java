package org.mmga.makelogingreatagain.utils;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

import static org.mmga.makelogingreatagain.constants.ChatColorConstants.letterColor;
import static org.mmga.makelogingreatagain.constants.ChatColorConstants.numberColor;
import static org.mmga.makelogingreatagain.constants.IntegerConstants.maxPassword;
import static org.mmga.makelogingreatagain.constants.ItemStackConstants.*;
import static org.mmga.makelogingreatagain.constants.StringConstants.*;
import static org.mmga.makelogingreatagain.events.InventoryClick.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/24
 */
public class InventoryUtils {
    public static Inventory createInventory(JavaPlugin plugin,String title){
        Server server = plugin.getServer();
        return server.createInventory(null, 54, title);
    }
    public static void addItemLoginRegisterLower(Inventory inventory){
        addItem(inventory, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z);
    }
    public static void addItemLoginRegisterUpper(Inventory inventory){
        addItem(inventory, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z);
    }
    private static void addItem(Inventory inventory, ItemStack a, ItemStack b, ItemStack c, ItemStack d, ItemStack e, ItemStack f, ItemStack g, ItemStack h, ItemStack i, ItemStack j, ItemStack k, ItemStack l, ItemStack m, ItemStack n, ItemStack o, ItemStack p, ItemStack q, ItemStack r, ItemStack s, ItemStack t, ItemStack u, ItemStack v, ItemStack w, ItemStack x, ItemStack y, ItemStack z) {
        inventory.addItem(one,two,three,four,five,six,seven,eight,nine,
                zero,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m,
                capsLock,frame,frame,frame,exit,frame,frame,done,clear);
    }

    public static Inventory getLoginInventory(JavaPlugin plugin){
        Inventory inventory = createInventory(plugin, inventoryLoginTitle);
        addItemLoginRegisterLower(inventory);
        return inventory;
    }
    public static Inventory getRegisterInventory(JavaPlugin plugin){
        Inventory inventory = createInventory(plugin, inventoryRegisterTitle);
        addItemLoginRegisterLower(inventory);
        return inventory;
    }
    public static Inventory getReRegisterInventory(JavaPlugin plugin){
        Inventory inventory = createInventory(plugin, inventoryReRegisterTitle);
        addItemLoginRegisterLower(inventory);
        return inventory;
    }
    public static boolean tryHandleInput(InventoryView openInventory, Player player, ItemStack currentItem, JavaPlugin plugin){
        return handleInput(openInventory, player, currentItem, plugin, playerInputPassword);
    }
    public static boolean tryHandleInputReRegister(InventoryView openInventory, Player player, ItemStack currentItem, JavaPlugin plugin){
        return handleInput(openInventory, player, currentItem, plugin, playerInputPasswordRe);
    }

    private static boolean handleInput(InventoryView openInventory, Player player, ItemStack currentItem, JavaPlugin plugin, ConcurrentHashMap<Player, String> playerInputPasswordRe) {
        if(currentItem != null){
            String name = currentItem.getItemMeta().getDisplayName();
            if (FrameName.equals(name)) {
                //边框提示
                player.sendMessage(inventoryFrameClick);
            } else if (CapsLockName.equals(name)) {
                //大写锁定
                Boolean isUpper = isPlayerUpper.getOrDefault(player, false);
                //获取新的物品栏
                Inventory inventory = createInventory(plugin, openInventory.getTitle());
                if (isUpper) {
                    //小写
                    addItemLoginRegisterLower(inventory);
                    isPlayerUpper.put(player, false);
                } else {
                    //大写
                    addItemLoginRegisterUpper(inventory);
                    isPlayerUpper.put(player, true);
                }
                //将密码重新输入
                for (int x = 0; x < maxPassword; x++) {
                    ItemStack item = openInventory.getItem(x + 45);
                    if (item != null) {
                        inventory.setItem(x + 45, item);
                    }
                }
                //使玩家重新打开物品栏
                player.openInventory(inventory);
            } else if (quitServerName.equals(name)) {
                playerInputPasswordRe.put(player,"");
                playerInputIndexAt.put(player,0);
                //退出服务器
                player.kickPlayer(exitServer);
            } else if (clearInputName.equals(name)) {
                //清空密码
                for (int x = 0; x < maxPassword; x++) {
                    openInventory.setItem(x + 45, air);
                }
                playerInputIndexAt.put(player, 0);
                playerInputPasswordRe.put(player, "");
            } else if (doneName.equals(name)) {
                return false;
            } else {
                Integer index = playerInputIndexAt.getOrDefault(player, 0);
                if (index < maxPassword) {
                    openInventory.setItem(index + 45, currentItem);
                    index += 1;
                    playerInputIndexAt.put(player, index);
                    name = name.replace(letterColor.toString(), "").replace(numberColor.toString(), "");
                    String nowPassword = playerInputPasswordRe.getOrDefault(player, "");
                    nowPassword += name;
                    playerInputPasswordRe.put(player, nowPassword);
                } else {
                    player.sendMessage(tooLongPassword);
                }
            }
        }
        return true;
    }
}
