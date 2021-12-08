package com.main.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by vitaminjr on 31.01.17.
 */
public class MysqlConnector  {

    /**
     * The Connection.
     */
    Connection connection;
    /**
     * The Host name.
     */
    String hostName = "localhost";
    /**
     * The Port.
     */
    String port = "3306";
    /**
     * The Name db.
     */
    String nameDB = "";
    /**
     * The Login.
     */
    String login = "root";
    /**
     * The Password.
     */
    String password = "master";

    /**
     * Instantiates a new Mysql connector.
     *
     * @param nameDB the name db
     */
    public MysqlConnector(String nameDB) {
        this.nameDB = nameDB;
    }

    /**
     * Connect boolean.
     *
     * @return the boolean
     */
    public boolean connect(){
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String url = "jdbc:mysql://" + hostName + ":" + port + "/" + nameDB + "?useUnicode=yes&characterEncoding=UTF-8";
                    DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
                    DriverManager.setLoginTimeout(10);
                    connection = DriverManager.getConnection(url, login, password);
                    return true;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                    return false;
                }
    }

    /**
     * Create db boolean.
     *
     * @return the boolean
     */
    public boolean createDb(){
        try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
                //Class.forName("com.mysql.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://" + hostName + ":" + port + "/";
                Connection con = DriverManager.getConnection(url, login, password);
                Statement stmt = con.createStatement();
                String query = "CREATE database " + nameDB;
                stmt.execute(query);

        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Execute update sql query int.
     *
     * @param query the query
     * @return the int
     */
    public int executeUpdateSQLQuery(String query) {
        try {
            if(connect()) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                return 0;
            }else
                return -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    /**
     * Execute insert sql query int.
     *
     * @param query the query
     * @return the int
     */
    public int executeInsertSQLQuery(String query) {
        try {

            java.sql.PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return -1;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (int) generatedKeys.getLong(1);
                }
                else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Execute sql query result set.
     *
     * @param query the query
     * @return the result set
     */
    public ResultSet executeSQLQuery(String query) {
        if(connect() == true) {
            try {
                connection.createStatement();

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.first();
                int row = resultSet.getRow();
                if (resultSet != null && row != 0)
                    return resultSet;
                else
                    return null;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

}
