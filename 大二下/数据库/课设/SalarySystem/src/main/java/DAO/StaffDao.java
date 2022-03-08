package DAO;
import Bean.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*

        Connection : 数据库连接对象
        StateMent : 执行sql对象
        ResultSet : 结果集对象
        PrepareedStatement ： 执行sql对象
 */
public class StaffDao {
    public static void main(String[] args) {
        StaffDao staffDao = new StaffDao();
//        staffDao.TestDelete();
//        staffDao.TestSelectAll();
//          staffDao.TestAdd();
        Staff staff = new StaffDao().selectByParams("Sno", "20190000").get(0);

    }
    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入员工的工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入员工的姓名:");
        String Sname = scanner.nextLine();
        System.out.print("请输入员工的性别:");
        String Ssex = scanner.nextLine();
        System.out.print("请输入员工的年龄:");
        Short Sage = scanner.nextShort();
        scanner.nextLine();
        System.out.print("请输入员工的部门名称:");
        String Dname = scanner.nextLine();
        System.out.print("请输入员工的职位:");
        String Spos = scanner.nextLine();
        System.out.print("请输入员工的入职日期:");
        String date = scanner.nextLine();
        System.out.print("请输入员工的电话号码:");
        String Stel = scanner.nextLine();
        System.out.print("请输入员工的地址:");
        String Saddr = scanner.nextLine();
        System.out.print("请输入员工的银行卡号:");
        String Card = scanner.nextLine();
        java.sql.Date Sdate = null;
        Sdate = java.sql.Date.valueOf(date);
        Staff staff = new Staff(Sno, Sname, Ssex, Sage, Dname, Spos, Sdate, Stel, Saddr, Card);
        add(staff);


    }
    public void TestDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除员工的工号:");
        String Sno = scanner.nextLine();
        delete(Sno);

    }
    public void TestSelectAll(){
        List<Staff> list = selectAll();
        for(Staff staff : list){
            System.out.println(staff);
        }
    }
    public void TestSelectByParams(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入查询的列名:");
        String col = scanner.nextLine();
        System.out.print("请输入查询的值:");
        String value = scanner.nextLine();
        List<Staff> list = selectByParams(col, value);
        for(Staff staff : list){
            System.out.println(staff);
        }

    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入员工的工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入修改的列名:");
        String col = scanner.nextLine();
        System.out.print("请输入修改的值:");
        String value = scanner.nextLine();
        update(Sno,col,value);
    }


    public int add(Staff staff){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into Staff values( ?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,staff.getSno());
            preparedStatement.setString(2,staff.getSname());
            preparedStatement.setString(3,staff.getSsex());
            preparedStatement.setShort(4,staff.getSage());
            preparedStatement.setString(5,staff.getDname());
            preparedStatement.setString(6,staff.getSpos());
            preparedStatement.setDate(7, staff.getSdate());
            preparedStatement.setString(8,staff.getStel());
            preparedStatement.setString(9,staff.getSaddr());
            preparedStatement.setString(10,staff.getCard());
            return preparedStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public int delete(String Sno){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from staff where Sno = ?";
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
    public List<Staff> selectAll(){
        List<Staff> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =  JDBCUtils.getConnection();
            String sql = "select * from staff";
            preparedStatement =  connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Staff staff = new Staff(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getShort(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public List<Staff> selectByParams(String col,String value){
        List<Staff> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Staff where "+col+" = '"+value+"'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Staff staff = new Staff(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getShort(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
                list.add(staff);
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
            String sql = "update Staff set "+col+" = '"+value+ "' where Sno = '"+Sno +"' ";
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
