package DAO;

import Bean.Bwage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BwagDao {
    public static void main(String[] args) {
        BwagDao bwagDao = new BwagDao();
        bwagDao.TestDelete();
        bwagDao.TestDelete();
        bwagDao.TestDelete();
        bwagDao.TestSelectAll();
    }

    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入增加的职位名称:");
        String Spos = scanner.nextLine();
        System.out.print("请输入基本工资:");
        int Bsalary = scanner.nextInt();
        Bwage bwage = new Bwage(Spos, Bsalary);
        add(bwage);
    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的职位名称:");
        String Spos = scanner.nextLine();
        delete(Spos);
    }
    public void TestSelectAll(){
        List<Bwage> list = selectAll();
        for(Bwage bwage : list){
            System.out.println(bwage);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入职位名称:");
        String Spos = scanner.nextLine();
        System.out.print("请输入要修改的基本工资:");
        int Bsalary = scanner.nextInt();
        update(Spos,Bsalary);
    }

    public int add(Bwage bwage){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "insert into Bwage values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bwage.getSpos());
            preparedStatement.setInt(2,bwage.getBsalary());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public int delete(String Spos){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "delete from Bwage where Spos = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Spos);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public List<Bwage> selectAll(){
        List<Bwage>list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "select * from Bwage";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String Spos = resultSet.getString(1);
                int Bsalary = resultSet.getInt(2);
                Bwage bwage = new Bwage(Spos, Bsalary);
                list.add(bwage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public int update(String Spos, int Bsalary){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "update Bwage set Bsalary = ? where Spos = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2,Spos);
            preparedStatement.setInt(1,Bsalary);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }


}
