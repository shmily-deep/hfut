package DAO;

import Bean.Pwage;
import DAO.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//个人工资表
public class PwageDao {
    public static void main(String[] args) {
        PwageDao pwageDao = new PwageDao();
        pwageDao.TestSelectAll();
        pwageDao.TestAdd();
        pwageDao.TestSelectAll();
    }
    public void TestAdd(){  //  结算
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入日期:");
        String Rdate = scanner.nextLine();
        add(Rdate);


    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入日期:");
        String Rdate = scanner.nextLine();
        delete(Sno,Rdate);
    }
    public void TestSelectAll(){
        List<Pwage>list = selectAll();
        for(Pwage pwage : list){
            System.out.println(pwage);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入日期:");
        String Rdate = scanner.nextLine();
        System.out.print("请输入列名:");
        String col = scanner.nextLine();
        System.out.print("请输入要修改的值:");
        String value = scanner.nextLine();
        update(Sno,Rdate,col,value);
    }
    public int add(String Rdate){
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "{call dbo.p_wage(?)}";
            callableStatement = connection.prepareCall(sql);
            Rdate += "-28";
            callableStatement.setDate(1,java.sql.Date.valueOf(Rdate));
            return callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(callableStatement,connection);
        }

        return 0;
    }
    public int delete(String Sno,String Rdate){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from Pwage where Sno  = ? and Rdate = ?";
            preparedStatement = connection.prepareStatement(sql);
            Rdate += "-28";
            preparedStatement.setString(1,Sno);
            preparedStatement.setDate(2,java.sql.Date.valueOf(Rdate));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public List<Pwage> selectAll(){
        List<Pwage> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql=  "select * from Pwage";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Pwage pwage = new Pwage(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10));
                list.add(pwage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public int update(String Sno,String Rdate,String col,String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "update Pwage set "+col+" = ? where Sno = ? and Rdate = ?";
            preparedStatement = connection.prepareStatement(sql);
            if(col.equals("Sno")){
                preparedStatement.setString(1,value);
            }else if(col.equals("Rdate")){
                value += "-28";
                preparedStatement.setDate(1,java.sql.Date.valueOf(value));
            }else{
                preparedStatement.setInt(1,Integer.parseInt(value));
            }
            preparedStatement.setString(2,Sno);
            Rdate += "-28";
            preparedStatement.setDate(3,java.sql.Date.valueOf(Rdate));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
}
