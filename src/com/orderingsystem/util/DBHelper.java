package com.orderingsystem.util;


import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/db_food?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String ACCOUNT = "root";
    private static final String PASSWORD = "123456";
    static{
        try {
            Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getCon() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(URL,ACCOUNT,PASSWORD);
        return con;
    }
    public static void closeObj(Connection con, PreparedStatement ps, ResultSet rs){
        try {
            if(null != con && !con.isClosed()) con.close();
            if(null != ps) ps.close();
            if(null != rs) rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
