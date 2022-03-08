package DAO;

import Bean.RP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RPDao {
    public static void main(String[] args) {
        RPDao rpDao = new RPDao();
        rpDao.TestDelete();
        rpDao.TestDelete();
        rpDao.TestSelectAll();
    }

    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要添加的奖罚类型:");
        String RPtype = scanner.nextLine();
        System.out.print("请输入要金额:");
        int RPmoney = scanner.nextInt();
        RP rp = new RP(RPtype, RPmoney);
        add(rp);
    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的奖罚类型:");
        String RPtype = scanner.nextLine();
        delete(RPtype);
    }
    public void TestSelectAll(){
        List<RP> list = selectAll();
        for(RP rp:list){
            System.out.println(rp);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入奖罚类型:");
        String RPtype = scanner.nextLine();
        System.out.print("请输入列名:");
        String col = scanner.nextLine();
        System.out.print("请输入要修改的值:");
        String value = scanner.nextLine();
        update(RPtype,col,value);
    }
    public int add(RP rp){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into RP values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,rp.getRPtype());
            preparedStatement.setInt(2,rp.getRPmoney());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;

    }
    public int delete(String RPtype){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from RP where RPtype = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,RPtype);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;

    }
    public List<RP>selectAll(){
        List<RP> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from RP";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                RP rp = new RP(resultSet.getString(1),
                        resultSet.getInt(2));
                list.add(rp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;

    }
    public  int update(String RPtype,String col,String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = null;
            if(col == "RPmoney")
                sql = "update RP set "+col+" = "+value+" where RPtype = '"+RPtype+"'";
            else
                sql = "update RP set "+col+" = '"+value+"' where RPtype = '"+RPtype+"'";
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
