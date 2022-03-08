package DAO;

import Bean.Dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeptDao {
    public static void main(String[] args) {
        DeptDao deptDao = new DeptDao();
        for(int i=0;i<3;i++){
            deptDao.TestAdd();
        }
        deptDao.TestSelect();
        deptDao.TestUpdate();
        deptDao.TestUpdate();
        deptDao.TestSelect();
        for(int i=0;i<3;i++){
            deptDao.TestDelete();
        }
        deptDao.TestSelect();


    }
    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入部门名称:");
        String Dame = scanner.nextLine();
        System.out.print("请输入部门经理姓名:");
        String Manger = scanner.nextLine();
        Dept dept = new Dept(Dame, Manger, 0);
        add(dept);

    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的部门名称:");
        String Dame = scanner.nextLine();
        delete(Dame);

    }
    public void TestSelect(){
        List<Dept> list = select();
        for(Dept dept:list){
            System.out.println(dept);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入部门名称:");
        String Dame = scanner.nextLine();
        System.out.print("请输入修改后部门经理姓名:");
        String Manger = scanner.nextLine();
        update(Dame,Manger);
    }


    public int add(Dept dept){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "insert into Dept values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,dept.getDname());
            preparedStatement.setString(2,dept.getManger());
            preparedStatement.setInt(3,0);
            return  preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(preparedStatement,connection);
        }


        return 0;
    }
    public int delete(String Dname){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "delete from Dept where Dname = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Dname);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public List<Dept> select(){
        List<Dept> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "select * from Dept";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String Dname = resultSet.getString(1);
                String Manger = resultSet.getString(2);
                int Tnum = resultSet.getInt(3);
                Dept dept = new Dept(Dname, Manger, Tnum);
                list.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public int update(String Dname,String Manger){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DAO.JDBCUtils.getConnection();
            String sql = "update Dept set Manger = ? where Dname = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Manger);
            preparedStatement.setString(2,Dname);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.JDBCUtils.close(preparedStatement,connection);
        }
        return 0;

    }



}
