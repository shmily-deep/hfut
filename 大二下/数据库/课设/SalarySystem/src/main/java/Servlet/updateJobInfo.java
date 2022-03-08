package Servlet;


import Bean.Staff;
import DAO.BwagDao;
import DAO.StaffDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateJobInfo", value = "/updateJobInfo")
public class updateJobInfo extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Flag = request.getParameter("Flag");
		String Spos1 = request.getParameter("Spos1");
		String Bsalary = request.getParameter("Bsalary");
		String Sno = request.getParameter("Sno");
		String Spos2 = request.getParameter("Spos2");

		if(Flag.equals("1")) {
			new BwagDao().update(Spos1, Integer.parseInt(Bsalary));
		} else if (Flag.equals("2")) {
			new StaffDao().update(Sno, "Spos", Spos2);
		}

	}
}
