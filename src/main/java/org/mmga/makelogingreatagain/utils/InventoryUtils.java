package org.mmga.makelogingreatagain.utils;

import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import static org.mmga.makelogingreatagain.constants.ItemStackConstants.*;
import static org.mmga.makelogingreatagain.constants.StringConstants.*;

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
                zero, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,
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
}
