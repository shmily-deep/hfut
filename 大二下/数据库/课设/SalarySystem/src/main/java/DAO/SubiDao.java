package DAO;

import Bean.Subi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubiDao {
    public static void main(String[] args) {
        SubiDao subiDao = new SubiDao();
        subiDao.TestDelete();
        subiDao.TestDelete();
        subiDao.TestSelectAll();
    }
    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入津贴类型:");
        String Type = scanner.nextLine();
        System.out.print("请输入津贴:");
        int Sub = scanner.nextInt();
        Subi subi = new Subi(Type, Sub);
        add(subi);

    }

    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的津贴类型:");
        String Type = scanner.nextLine();
        delete(Type);
    }
    public void TestSelectAll(){
        List<Subi> list = selectAll();
        for(Subi subi:list){
            System.out.println(subi);
        }
    }
    private void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入津贴类型:");
        String Type = scanner.nextLine();
        System.out.print("请输入列名:");
        String col = scanner.nextLine();
        System.out.print("请输入要修改的值:");
        String value = scanner.nextLine();
        update(Type,col,value);

    }
    public int add(Subi subi){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into Subi values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,subi.getType());
            preparedStatement.setInt(2,subi.getSub());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public int delete(String Type){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from Subi where Stype = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Type);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }

        return 0;
    }
    public List<Subi>selectAll(){
        List<Subi> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Subi";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Subi subi = new Subi(resultSet.getString(1),
                        resultSet.getInt(2));
                list.add(subi);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;

    }
    public int update(String Type,String col,String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = null;
            if(col == "Sub"){
                sql = "update Subi set "+col+" ="+value+" where Stype = '"+Type+"' ";
            }else{
                sql = "update Subi set "+col+" ='"+value+"' where Stype = '"+Type+"' ";
            }
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return 0;
    }


}
