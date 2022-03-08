package Servlet;


import DAO.StaffDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "setPersonalInfo", value = "/setPersonalInfo")
public class setPersonalInfo extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Sno = (String) request.getSession().getAttribute("Sno");
		String Stel = request.getParameter("Stel");
		String Saddr = request.getParameter("Saddr");

		System.out.println(Stel);
		System.out.println(Saddr);
		new StaffDao().update(Sno, "Stel", Stel);
		new StaffDao().update(Sno, "Saddr", Saddr);

	}
}
