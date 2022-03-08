package DAO;

import java.sql.*;

public class JDBCUtils {
    private static String dbURL;
    private static String userName;
    private static String userPwd;
    private static String driverName;
    static {
        //静态代码块，只会执行一次，读取文件，获取值

        try{
            dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=SalaryDatabase";
            userName = "sa";
            userPwd = "123456";
            driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driverName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection(dbURL,userName,userPwd);


    }
    public static void close(Statement stat,Connection conn){
        if(stat != null){
            try{
                stat.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
    public static void close(ResultSet resultSet,Statement stat,Connection conn){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(stat != null){
            try{
                stat.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }


}
