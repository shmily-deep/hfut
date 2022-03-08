package Servlet;

import Bean.Usr;
import DAO.UsrDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "changePasswordServlet", value = "/changePasswordServlet")
public class changePasswordServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String Sno = request.getParameter("number");
		String pwd = request.getParameter("password");
		String new_pwd = request.getParameter("new_password");

		UsrDao dao = new UsrDao();
		Usr usr = dao.get(Sno);
		if(usr == null) {
			out.write("userNoExist");
		} else {
			if(pwd.equals(usr.getPwd())) {
				//密码正确，修改密码
				dao.update(Sno, "pwd", new_pwd);
				out.write("success");
			} else {
				out.write("passwordError");
			}
		}
	}
}
