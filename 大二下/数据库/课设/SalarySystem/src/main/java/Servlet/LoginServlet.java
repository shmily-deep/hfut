package Servlet;

import Bean.Usr;
import DAO.UsrDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String Sno= request.getParameter("number");
		String pwd = request.getParameter("password");
		System.out.println(Sno);
		System.out.println(pwd);
		Usr usr = new UsrDao().get(Sno);
		if(usr == null) {
			out.write("userNoExist");
		} else {

			if(pwd.equals(usr.getPwd())) {
				request.getSession().setAttribute("Sno", Sno);
				if(usr.getIden() == 0) {
					//身份为员工
					System.out.println("是员工");
					out.write("staff");
				} else if(usr.getIden() == 1) {
					//身份为管理员
					out.write("manager");
				}
			} else {
				out.write("passwordError");
			}
		}
	}

}
