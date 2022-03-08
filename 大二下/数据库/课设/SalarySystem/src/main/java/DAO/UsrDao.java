package DAO;

import Bean.Usr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsrDao {

    public Usr get(String Sno) {

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        Usr usr = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Usr where Sno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Sno);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString(2);       //获取密码
                int iden = rs.getInt(3);         //获取身份
                usr = new Usr(Sno, pwd, iden);
            }
            return usr;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return null;
    }

    public int add(Usr usr){
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        System.out.println("注册城");
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into Usr values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,usr.getSno());
            preparedStatement.setString(2,usr.getPwd());
            preparedStatement.setInt(3,usr.getIden());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }

    public int delete(String Sno){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from Usr where Sno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Sno);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public List<Usr> selectAll(){
        List<Usr> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Usr";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Usr usr = new Usr(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                );
                list.add(usr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public int update(String Sno,String col,String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update Usr set "+col+" = '"+value+"' where Sno = '"+Sno+"'";
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
}
