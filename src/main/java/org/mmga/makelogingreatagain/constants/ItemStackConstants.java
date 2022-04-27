package org.mmga.makelogingreatagain.constants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.*;
import static org.mmga.makelogingreatagain.constants.ChatColorConstants.*;
import static org.mmga.makelogingreatagain.constants.StringConstants.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/24
 */
public class ItemStackConstants {
    public static ItemStack frame = getStackWithName(YELLOW_STAINED_GLASS_PANE,64, FrameName);
    public static ItemStack capsLock = getStackWithName(LIGHT_BLUE_STAINED_GLASS_PANE,1,CapsLockName);
    public static ItemStack exit = getStackWithName(RED_STAINED_GLASS_PANE,1,quitServerName);
    public static ItemStack done = getStackWithName(GREEN_STAINED_GLASS_PANE,1,doneName);
    public static ItemStack clear = getStackWithName(GRAY_STAINED_GLASS_PANE,1,clearInputName);
    public static ItemStack one = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "1");
    public static ItemStack two = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "2");
    public static ItemStack three = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "3");
    public static ItemStack four = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "4");
    public static ItemStack five = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "5");
    public static ItemStack six = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "6");
    public static ItemStack seven = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "7");
    public static ItemStack eight = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "8");
    public static ItemStack nine = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "9");
    public static ItemStack zero = getStackWithName(MAGENTA_STAINED_GLASS_PANE,1,numberColor + "0");
    public static ItemStack a = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "a");
    public static ItemStack b = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "b");
    public static ItemStack c = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "c");
    public static ItemStack d = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "d");
    public static ItemStack e = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "e");
    public static ItemStack f = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "f");
    public static ItemStack g = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "g");
    public static ItemStack h = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "h");
    public static ItemStack i = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "i");
    public static ItemStack j = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "j");
    public static ItemStack k = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "k");
    public static ItemStack l = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "l");
    public static ItemStack m = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "m");
    public static ItemStack n = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "n");
    public static ItemStack o = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "o");
    public static ItemStack p = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "p");
    public static ItemStack q = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "q");
    public static ItemStack r = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "r");
    public static ItemStack s = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "s");
    public static ItemStack t = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "t");
    public static ItemStack u = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "u");
    public static ItemStack v = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "v");
    public static ItemStack w = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "w");
    public static ItemStack x = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "x");
    public static ItemStack y = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "y");
    public static ItemStack z = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "z");
    public static ItemStack A = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "A");
    public static ItemStack B = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "B");
    public static ItemStack C = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "C");
    public static ItemStack D = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "D");
    public static ItemStack E = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "E");
    public static ItemStack F = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "F");
    public static ItemStack G = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "G");
    public static ItemStack H = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "H");
    public static ItemStack I = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "I");
    public static ItemStack J = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "J");
    public static ItemStack K = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "K");
    public static ItemStack L = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "L");
    public static ItemStack M = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "M");
    public static ItemStack N = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "N");
    public static ItemStack O = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "O");
    public static ItemStack P = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "P");
    public static ItemStack Q = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "Q");
    public static ItemStack R = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "R");
    public static ItemStack S = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "S");
    public static ItemStack T = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "T");
    public static ItemStack U = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "U");
    public static ItemStack V = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "V");
    public static ItemStack W = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "W");
    public static ItemStack X = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "X");
    public static ItemStack Y = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "Y");
    public static ItemStack Z = getStackWithName(BLUE_STAINED_GLASS_PANE,1,letterColor + "Z");
    public static ItemStack air = new ItemStack(AIR);
    public static ItemStack getStackWithName(Material material, int amount, String name){
        ItemStack stack = new ItemStack(material,amount);
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(name);
        stack.setItemMeta(itemMeta);
        return stack;
    }
}
