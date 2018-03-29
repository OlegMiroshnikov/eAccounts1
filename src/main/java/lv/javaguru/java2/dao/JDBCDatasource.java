package lv.javaguru.java2.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCDatasource {
    private static final String DB_CONFIG_FILE = "database.properties";

    protected String userName = null;
    protected String password = null;
    protected String serverName = null;
    protected Integer portNumber = null;
    protected String databaseName = null;


    private MysqlDataSource dataSource;

    public JDBCDatasource() {
        initDatabaseConnectionProperties();
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

    private void initDatabaseConnectionProperties() {
        Properties properties = new Properties();
        try {
            this.dataSource = new MysqlDataSource();
            properties.load(JDBCDatabase.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
            serverName = properties.getProperty("database.server.name");
            portNumber = Integer.parseInt(properties.getProperty("database.port.number"));
            databaseName = properties.getProperty("database.name");
            userName = properties.getProperty("database.user.name");
            password = properties.getProperty("database.user.password");
            dataSource.setServerName(serverName);
            dataSource.setPortNumber(portNumber);
            dataSource.setDatabaseName(databaseName);
            dataSource.setUser(userName);
            dataSource.setPassword(password);
        } catch (IOException e){
            System.out.println("Exception while reading JDBC datasource configuration from file = " + DB_CONFIG_FILE);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() {
        try{
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
