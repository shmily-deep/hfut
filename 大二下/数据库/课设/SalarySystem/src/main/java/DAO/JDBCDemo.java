package DAO;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://localhost:1433;databaseName=EDBC";
        String userName = "sa";
        String userPwd = "123456";

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(dbURL, userName, userPwd);
            statement = connection.createStatement();
            String sql = "select * from student";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String Sno,Sname,Ssex,ClsNO,Saddr,Sdept;
            double Sage,Hegit;
            while(resultSet.next()){
                Sno = resultSet.getString(1);
                Sname = resultSet.getString(2);
                Ssex = resultSet.getString(3);
                ClsNO = resultSet.getString(4);
                Saddr = resultSet.getString(5);
                Sage = resultSet.getDouble(6);
                Hegit = resultSet.getDouble(7);
                Sdept = resultSet.getString(8);
                System.out.println(Sno+"\t"+Sname+"\t"+Ssex+"\t"
                +ClsNO+"\t"+Saddr+"\t"+Sage+"\t"+Hegit+"\t"+Sdept);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }


        }
    }
}
