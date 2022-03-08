package DAO;
import Bean.Remit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemitDao {
    public static void main(String[] args) {
        RemitDao remitDao = new RemitDao();
        remitDao.TestSelectAll();
        remitDao.TestUpdate();
        remitDao.TestSelectAll();
    }
    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入个人所得税:");
        int Stax = scanner.nextInt();
        System.out.print("请输入五险一金:");
        int Sins = scanner.nextInt();
        Remit remit = new Remit(Sno, Stax, Sins);
        add(remit);
    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的工号:");
        String Sno = scanner.nextLine();
        delete(Sno);
    }
    public void TestSelectAll(){
        List<Remit> list = selectAll();
        for(Remit remit:list){
            System.out.println(remit);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入列名:");
        String col = scanner.nextLine();
        System.out.print("请输入要修改的值:");
        String value = scanner.nextLine();
        update(Sno,col,value);
    }
    public int add(Remit remit){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into Remit values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,remit.getSno());
            preparedStatement.setInt(2,remit.getStax());
            preparedStatement.setInt(3,remit.getSins());
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
            String sql = "delete from Remit where Sno = ?";
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
    public List<Remit>selectAll(){
        List<Remit> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Remit";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Remit remit = new Remit(resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
                list.add(remit);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;

    }
    public int update(String Sno,String col,String value){
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update Remit set "+col+" = ? where Sno = ?";
            preparedStatement = connection.prepareStatement(sql);

            if(col.equals("Sno")){
                preparedStatement.setString(1,value);
            }else{
                preparedStatement.setInt(1,Integer.parseInt(value));
            }
            preparedStatement.setString(2,Sno);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
}
