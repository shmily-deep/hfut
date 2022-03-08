package DAO;

import Bean.Seni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeniDao {
    public static void main(String[] args) {
        SeniDao seniDao = new SeniDao();
        seniDao.TestDelete();
        seniDao.TestDelete();
        seniDao.TestSelectAll();
    }
    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入添加的工龄:");
        byte Wage = scanner.nextByte();
        System.out.print("请输入添加的补贴:");
        int Wsub = scanner.nextInt();
        Seni seni = new Seni(Wage, Wsub);
        add(seni);
    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的工龄:");
        byte Wage = scanner.nextByte();
        delete(Wage);
    }
    public void TestSelectAll(){
        List<Seni> list = selectAll();
        for(Seni seni : list){
            System.out.println(seni);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工龄:");
        byte Wage = scanner.nextByte();
        scanner.nextLine();
        System.out.print("请输入要修改的列名:");
        String col = scanner.nextLine();
        System.out.print("请输入要修改的值:");
        String value = scanner.nextLine();
        update(Wage,col,value);

    }
    public int add(Seni seni){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into Seni values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setByte(1,seni.getWage());
            preparedStatement.setInt(2,seni.getWsub());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public int delete(Byte Wage){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from Seni where Wage = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setByte(1,Wage);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public List<Seni> selectAll(){
        List<Seni> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Seni";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Seni seni = new Seni(resultSet.getByte(1), resultSet.getInt(2));
                list.add(seni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public int update(byte Wage,String col,String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update Seni set "+col+" = "+value+" where Wage = "+Wage+" ";
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
