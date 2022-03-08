package Servlet;


import Bean.Staff;
import DAO.StaffDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "add_delete_Staff", value = "/add_delete_Staff")
public class add_delete_Staff extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Flag = request.getParameter("Flag");
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String Ssex = request.getParameter("Ssex");
		String Sage = request.getParameter("Sage");
		String Dname = request.getParameter("Dname");
		String Spos = request.getParameter("Spos");
		String Sdate = request.getParameter("Sdate");
		String Stel = request.getParameter("Stel");
		String Saddr = request.getParameter("Saddr");
		String Card = request.getParameter("Card");
		String Sno_delete = request.getParameter("Sno_delete");
		if(Flag.equals("1")) {
			Staff staff = new Staff(Sno, Sname, Ssex, Short.parseShort(Sage), Dname, Spos, Staff.strToDate(Sdate), Stel, Saddr, Card);
			new StaffDao().add(staff);
		} else if(Flag.equals("2")){
			new StaffDao().delete(Sno_delete);
		}
	}
}
