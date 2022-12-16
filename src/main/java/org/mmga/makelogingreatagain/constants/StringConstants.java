package org.mmga.makelogingreatagain.constants;

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
    public static String inventoryLoginTitle = "请登陆";
    public static String inventoryRegisterTitle = "请注册";
    public static String inventoryReRegisterTitle = "请重新输入密码";
    public static String wrongPassword = "密码错误！";
    public static String rightPassword = "登陆成功！";
    public static String tooLongPassword = "密码过长！";
    public static String inventoryFrameClick = "请不要点击边框！";
    public static String tryExitInventory = "请不要尝试退出登陆/注册页面";
    public static String exitServer = "感谢游玩！";
    public static String twoPasswordNotEquals = "两次输入的密码不一致，请重试！";
    public static String successfulRegister = "注册成功！";
    public static String datasourceWrong = "您的数据库配置有误，报错已发向后台！";
    public static String reloadDone = "插件重载成功！";
    public static String permissionsMissing = "你没有权限使用此指令！";
    public static String wrongArguments = "错误的参数！";
    public static String FrameName = "边框";
    public static String CapsLockName = "大小写锁定";
    public static String quitServerName = "退出服务器";
    public static String doneName = "确认";
    public static String clearInputName = "清空输入框";
}
