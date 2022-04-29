package org.mmga.makelogingreatagain.utils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.mmga.makelogingreatagain.MakeLoginGreatAgainMain;

import java.sql.*;
import java.text.SimpleDateFormat;

import static org.mmga.makelogingreatagain.MakeLoginGreatAgainMain.logger;
import static org.mmga.makelogingreatagain.utils.PluginUtils.*;
import static org.mmga.makelogingreatagain.constants.StringConstants.*;
import static org.mmga.makelogingreatagain.constants.IntegerConstants.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class DataBaseUtils {
    /**
     * 更新最后登陆IP和最后登陆时间以及uuid和用户名
     * @param name 玩家用户名
     * @param uuid 玩家uuid
     * @param ip 玩家ip
     */
    public static void updateUserData(String name,String uuid,String ip){
        Thread thread = new Thread(() -> {
            runSqlUpdate("update mlga_user set `name` = ?, `lastLoginedIP` = ?,`lastLoginedTime` = ? where `uuid` = ?", name, ip, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()), uuid);
        });
        thread.start();

    }
    /**
     * 判断一个玩家输入的密码是否正常
     * @param name 用户名
     * @param uuid 玩家uuid
     * @param password 密码
     * @return 是否能成本登陆
     */
    public static boolean isUsernamePasswordRight(String name,String uuid, String password) throws SQLException {
        String passWord = Sha256.get256(password);
        DataBaseResult dataBaseResult = runSqlQuery("select * from mlga_user where `uuid` = ? and `password` = ?", uuid,passWord);
        boolean next = dataBaseResult.getResultSet().next();
        dataBaseResult.close();
        return next;
    }

    /**
     * 将玩家添加至数据库
     * @param name 用户名
     * @param uuid 玩家uuid
     * @param password 玩家密码
     * @param lastLoginedIp 玩家最后登陆的IP
     * @param lastLoginedTime 玩家最后登陆的时间
     */
    public static void addPlayer(String name, String uuid, String password, String lastLoginedIp, String lastLoginedTime){
        String passWord = Sha256.get256(password);
        runSqlUpdate("insert into mlga_user (`name`,`uuid`,`password`,`lastLoginedIP`,`lastLoginedTime`) values (?,?,?,?,?)",name,uuid,passWord,lastLoginedIp,lastLoginedTime);
    }
    /**
     * 检测玩家是否存在于数据库中
     * @param uuid 玩家uuid
     * @return 玩家是否存在于数据库中
     * @throws SQLException 当SQL关闭连接或获取连接错误时抛出
     */
    public static boolean isPlayerExist(String uuid) throws SQLException {
        DataBaseResult dataBaseResult = runSqlQuery("select * from mlga_user where `uuid` = ?", uuid);
        boolean next = dataBaseResult.getResultSet().next();
        dataBaseResult.close();
        return !next;
    }
    /**
     * 若表不存在则创建表
     */
    public static void getDefaultTable(){
        if(!isTableExist(datasourceTableName)) {
            MakeLoginGreatAgainMain plugin = getPlugin();
            FileConfiguration config = plugin.getConfig();
            String type = config.getString("datasource.type");
            if (stringMysql.equals(type)) {
                runSqlUpdate(datasourceMySQLCreateTable);
            }else if(stringSqlite.equals(type)){
                runSqlUpdate(datasourceSQLiteCreateTable);
            }else{
                logger.info(ChatColor.RED + "无法创建默认表，因为数据源配置有误");
            }

        }
    }
    public static boolean isTableExist(String tableName){
        Connection connection = getConnection();
        try {
            connection.createStatement().executeQuery("select * from `" + tableName + "`;");
        } catch (SQLException e) {
            MakeLoginGreatAgainMain plugin = getPlugin();
            FileConfiguration config = plugin.getConfig();
            String type = config.getString("datasource.type");
            if(stringMysql.equals(type)) {
                if (e.getErrorCode() != datasourceErrorCodeTableNotExist) {
                    logger.info(ChatColor.RED + "执行语句 select * from `" + tableName + "`; 时出错");
                    errorOnSqlException(e);
                }
                return false;
            }else if(stringSqlite.equals(type)){
                if (!String.format(datasourceSQLiteErrorMessage, tableName).equals(e.getLocalizedMessage())) {
                    logger.info(ChatColor.RED + "执行语句 select * from `" + tableName + "`; 时出错");
                    errorOnSqlException(e);
                }
                return false;
            }else{
                logger.info(ChatColor.RED + "执行语句 select * from `" + tableName + "`; 时出错");
                errorOnSqlException(e);
                return false;
            }
        }
        return true;
    }
    /**
     * 测试数据库连接
     * @return 是否成功连接到数据库
     */
    public static boolean testDatasourceConfig(){
        return getConnection() != null;
    }
    /**
     * 通过配置文件获取数据库连接
     * @return 数据库连接
     */
    public static Connection getConnection(){
        MakeLoginGreatAgainMain plugin = getPlugin();
        FileConfiguration config = plugin.getConfig();
        String type = config.getString("datasource.type");
        Connection c = null;
        if(stringMysql.equals(type)){
            String datasourceUrl = config.getString("datasource.url");
            String datasourceUser = config.getString("datasource.user");
            String datasourcePassword = config.getString("datasource.password");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                logger.info(ChatColor.RED + "获取MySQL JDBC失败");
            }
            try {
                c = DriverManager.getConnection(datasourceUrl,datasourceUser,datasourcePassword);
            } catch (SQLException e) {
                logger.info(ChatColor.RED + "连接MySQL数据库失败！");
                errorOnSqlException(e);
            }
        }else if(stringSqlite.equals(type)){
            try {
                Class.forName("org.sqlite.JDBC");
                try{
                    c = DriverManager.getConnection("jdbc:sqlite:plugins/MakeLoginGreatAgain/datasource.db");
                }catch (SQLException e){
                    logger.info("访问Sqlite数据库失败");
                    errorOnSqlException(e);
                }
            } catch (ClassNotFoundException e) {
                logger.info(ChatColor.RED + "获取sqliteJDBC失败");
            }
        }else{
            logger.info(ChatColor.RED + "你的数据库配置有误：错误的数据库类型");
        }
        if(c != null){
            try {
                c.setAutoCommit(true);
            } catch (SQLException e) {
                logger.info(ChatColor.RED + "设置数据库自动提交错误");
                errorOnSqlException(e);
            }
        }
        return c;
    }
    /**
     * 运行MySql查询语句（select）
     * @param sql sql语句
     * @param args sql语句的参数
     * @return 查询的结果集
     */
    public static DataBaseResult runSqlQuery(String sql, Object... args){
        Connection c = getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        if (c != null) {
            try {
                preparedStatement = getStatement(c,sql,args);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                logger.info(ChatColor.RED + "执行查询指令" + preparedStatement + "时出现错误！");
                errorOnSqlException(e);
            }
        }
        return new DataBaseResult(resultSet,preparedStatement,c);
    }

    /**
     * 运行MySql修改语句（update、insert、delete）
     * @param sql sql语句
     * @param args sql语句的参数
     */
    public static void runSqlUpdate(String sql,Object... args){
        Connection c = getConnection();
        if (c != null) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = getStatement(c,sql,args);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.info(ChatColor.RED + "执行语句" + preparedStatement + "时出现错误！");
                errorOnSqlException(e);
            }
            try{
                c.close();
            } catch (SQLException e) {
                logger.info(ChatColor.RED + "断开连接失败！");
                errorOnSqlException(e);
            }
        }
    }
    /**
     * 语句处理（可防止sql注入）
     * @param c MySql连接（可使用TccCore.getConnection()来获取）
     * @param sql sql语句
     * @param args sql语句的参数
     * @return 编译好的sql语句
     * @throws SQLException 当编译语句出现问题时抛出
     */
    public static PreparedStatement getStatement(Connection c, String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if(arg instanceof String){
                preparedStatement.setString(i + 1, (String) arg);
            }
            else if(arg instanceof Integer){
                preparedStatement.setInt(i + 1,(Integer) arg);
            }else{
                preparedStatement.setObject(i + 1,arg);
            }
        }
        return preparedStatement;
    }

    /**
     * 显示SQL错误专用方法
     * @param e 错误
     */
    public static void errorOnSqlException(SQLException e){
        logger.info(ChatColor.RED + "SqlState = " + e.getSQLState());
        logger.info(ChatColor.RED + "ErrorMessage = " + e.getLocalizedMessage());
        logger.info(ChatColor.RED + "ErrorCode = " + e.getErrorCode());
    }
}
