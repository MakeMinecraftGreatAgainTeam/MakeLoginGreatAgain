package org.mmga.makelogingreatagain.utils;

import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mmga.makelogingreatagain.MakeLoginGreatAgainMain.logger;
import static org.mmga.makelogingreatagain.utils.DataBaseUtils.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/18
 */
public class DataBaseResult {
    private final ResultSet resultSet;
    private final Statement statement;
    private final Connection connection;
    public DataBaseResult(ResultSet resultSet,Statement statement,Connection connection){
        this.resultSet = resultSet;
        this.statement = statement;
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
    public ResultSet getResultSet() {
        return resultSet;
    }
    public Statement getStatement() {
        return statement;
    }
    public void close(){
        try {
            this.resultSet.close();
            this.statement.close();
            this.connection.close();
        } catch (SQLException e) {
            logger.info(ChatColor.RED + "断开连接出现错误");
            errorOnSqlException(e);
        }
    }
}
