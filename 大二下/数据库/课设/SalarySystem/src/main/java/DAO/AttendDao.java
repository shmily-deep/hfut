package DAO;

import Bean.Attend;
import DAO.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttendDao {
    public static void main(String[] args) {
        AttendDao attendDao = new AttendDao();
//        attendDao.TestDalete();
//        attendDao.TestSelectAll();
//        attendDao.TestAdd();
        List<Attend> list = new AttendDao().getAttend("20190001", "2019");
        System.out.println(list.get(0).getNevec());
        System.out.println(list.size());
    }
    public void TestAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入日期:");
        String str = scanner.nextLine();
        System.out.print("请输入迟到次数:");
        byte Nlate = scanner.nextByte();
        System.out.print("请输入早退次数:");
        byte Nleave = scanner.nextByte();
        System.out.print("请输入出差次数:");
        byte Nevec = scanner.nextByte();
        System.out.print("请输入加班次数:");
        byte Nextra = scanner.nextByte();
        System.out.print("请输入出勤天数:");
        byte Nattend = scanner.nextByte();
        str += "-28";
        java.sql.Date Adate = java.sql.Date.valueOf(str);
        Attend attend = new Attend(Sno, Adate, Nlate,
                Nleave, Nevec, Nextra,
                Nattend);
        add(attend);

    }
    public void TestDalete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入日期:");
        String str = scanner.nextLine();
        delete(Sno,str);
    }
    public void TestSelectAll(){
        List<Attend> list = selectAll();
        for(Attend attend:list){
            System.out.println(attend);
        }
    }
    public void TestUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入工号:");
        String Sno = scanner.nextLine();
        System.out.print("请输入日期:");
        String str = scanner.nextLine();
        System.out.print("请输入列名:");
        String col = scanner.nextLine();
        System.out.print("请输入要修改的值:");
        String value = scanner.nextLine();
        update(Sno,str,col,value);

    }

    public List<Attend> getAttend(String Sno, String data){
        List<Attend> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Attend where Sno = ? and Year(Adate) = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Sno);
            preparedStatement.setString(2, data);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Attend attend = new Attend(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getByte(3),
                        resultSet.getByte(4),
                        resultSet.getByte(5),
                        resultSet.getByte(6),
                        resultSet.getByte(7)
                );
                list.add(attend);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return null;
    }

    public int add(Attend attend){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into Attend values(?,?,?," +
                    "?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,attend.getSno());
            preparedStatement.setDate(2,attend.getAdate());
            preparedStatement.setByte(3,attend.getNlate());
            preparedStatement.setByte(4,attend.getNleave());
            preparedStatement.setByte(5,attend.getNevec());
            preparedStatement.setByte(6,attend.getNextra());
            preparedStatement.setByte(7,attend.getNattend());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public int delete(String Sno,String Adate){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from Attend where Sno = ? and Adate = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Sno);
            Adate+="-28";
            preparedStatement.setDate(2,java.sql.Date.valueOf(Adate));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }
    public List<Attend> selectAll(){
        List<Attend> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Attend";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Attend attend = new Attend(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getByte(3),
                        resultSet.getByte(4),
                        resultSet.getByte(5),
                        resultSet.getByte(6),
                        resultSet.getByte(7)
                );
                list.add(attend);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    public int update(String Sno,String Adate,String col,String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update Attend set "+col+" = ? where Sno = ? and Adate = ?";
            preparedStatement = connection.prepareStatement(sql);
            if(col.equals("Sno")){
                preparedStatement.setString(1,value);
            }else if(col.equals("Adate")){
                value += "-28";
                preparedStatement.setDate(1,java.sql.Date.valueOf(value));
            }else{
                preparedStatement.setByte(1,Byte.parseByte(value));
            }
            preparedStatement.setString(2,Sno);
            Adate += "-28";
            preparedStatement.setDate(3,java.sql.Date.valueOf(Adate));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
        return 0;
    }

}
