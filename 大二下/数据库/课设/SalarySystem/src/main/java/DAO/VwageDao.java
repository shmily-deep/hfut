package DAO;

import Bean.Staff;
import Bean.Vwage;
import DAO.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VwageDao {
	public static void main(String[] args) {
		VwageDao vwageDao = new VwageDao();
		vwageDao.TestSelect();
	}
	public void TestSelect(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入员工的工号:");
		String Sno = scanner.nextLine();
		System.out.print("请输入年份:");
		String year = scanner.nextLine();
		List<Vwage> list = select(Sno,year);
		for(Vwage vwage : list){
			System.out.println(vwage);
		}

	}
	public List<Vwage> select(String Sno,String year){
		List<Vwage> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = JDBCUtils.getConnection();
			String sql = "select * from V_All_Salary where Sno = ? and YEAR(Rdate) = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,Sno);
			preparedStatement.setString(2,year);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Vwage vwage = new Vwage(resultSet.getString(1),
						resultSet.getDate(2),
						resultSet.getInt(3),
						resultSet.getInt(4),
						resultSet.getInt(5),
						resultSet.getInt(6),
						resultSet.getInt(7),
						resultSet.getInt(8),
						resultSet.getInt(9),
						resultSet.getInt(10),
						resultSet.getInt(11),
						resultSet.getInt(12),
						resultSet.getInt(13),
						resultSet.getInt(14),
						resultSet.getInt(15),
						resultSet.getInt(16)
				);
				list.add(vwage);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(resultSet,preparedStatement,connection);
		}
		return list;
	}
}
