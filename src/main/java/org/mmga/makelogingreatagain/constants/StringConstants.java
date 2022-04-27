package org.mmga.makelogingreatagain.constants;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class StringConstants {
    public static String commandMakeLoginGreatAgain = "MakeLoginGreatAgain";
    public static String commandMLGA = "MLGA";
    public static String commandReload = "reload";
    public static String stringMysql = "mysql";
    public static String stringSqlite = "sqlite";
    public static String permissionsReload = "mlga.reload";
    public static String datasourceSQLiteErrorMessage = "[SQLITE_ERROR] SQL error or missing database (no such table: %s)";
    public static String datasourceTableName = "mlga_user";
    public static String datasourceMySQLCreateTable = "CREATE TABLE `mlga_user`  (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
            "  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
            "  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
            "  `lastLoginedIP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
            "  `lastLoginedTime` datetime NOT NULL,\n" +
            "  PRIMARY KEY (`id`) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;";
    public static String datasourceSQLiteCreateTable = "CREATE TABLE \"mlga_user\" (\n" +
            "  \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"name\" TEXT NOT NULL,\n" +
            "  \"uuid\" text NOT NULL,\n" +
            "  \"password\" TEXT NOT NULL,\n" +
            "  \"lastLoginedIP\" TEXT NOT NULL,\n" +
            "  \"lastLoginedTime\" DATE NOT NULL\n" +
            ");";
    public static String inventoryLoginTitle = ChatColor.DARK_GREEN + "请登陆！";
    public static String inventoryRegisterTitle = ChatColor.DARK_RED + "请注册！";
    public static String inventoryReRegisterTitle = ChatColor.DARK_RED + "请重新输入密码！";
    public static String wrongPassword = ChatColor.RED + "[MakeLoginGreatAgain] 密码错误！";
    public static String rightPassword = ChatColor.GREEN + "密码正确！登陆成功！";
    public static String tooLongPassword = ChatColor.RED + "[MakeLoginGreatAgain] 密码过长！";
    public static String inventoryFrameClick = ChatColor.RED + "请您不要点边框，谢谢！";
    public static String tryExitInventory = ChatColor.RED + "请不要尝试关闭登陆窗口！";
    public static String exitServer = ChatColor.GREEN + "[MakeLoginGreatAgain] 成功退出服务器";
    public static String twoPasswordNotEquals = ChatColor.RED + "两次输入的密码不一致，请重试！";
    public static String successfulRegister = ChatColor.GREEN + "成功注册！";
    public static String datasourceWrong = ChatColor.RED + "你的数据库配置有误，报错已发向后台！";
    public static String reloadDone = ChatColor.GREEN + "插件重载成功！";
    public static String permissionsMissing = ChatColor.RED + "你没有权限使用此指令";
    public static String wrongArguments = ChatColor.RED + "错误的参数！";
    public static String FrameName = ChatColor.YELLOW + "边框";
    public static String CapsLockName = ChatColor.BLUE + "大小写切换";
    public static String quitServerName = ChatColor.RED + "退出服务器";
    public static String doneName = ChatColor.GREEN + "确认";
    public static String clearInputName = ChatColor.GRAY + "清空输入框";
}
