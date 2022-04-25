package org.mmga.makelogingreatagain.constants;

import org.bukkit.ChatColor;

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
}
