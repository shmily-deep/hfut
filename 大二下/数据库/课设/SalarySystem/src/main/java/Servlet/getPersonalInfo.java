package Servlet;



import Bean.Staff;
import DAO.StaffDao;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getPersonalInfo", value = "/getPersonalInfo")
public class getPersonalInfo extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Sno = (String) request.getSession().getAttribute("Sno");
		Staff staff = new StaffDao().selectByParams("Sno", Sno).get(0);

		JSONObject json = new JSONObject();
		json.put("Sno", staff.getSno());
		json.put("Sname", staff.getSname());
		json.put("Ssex", staff.getSsex());
		json.put("Sage", staff.getSage());
		json.put("Dname", staff.getDname());
		json.put("Spos", staff.getSpos());
		json.put("Sdate", staff.getSdate().toString());
		json.put("Stel", staff.getStel());
		json.put("Saddr", staff.getSaddr());
		json.put("Card", staff.getCard());
		String str = json.toString();
		out.write(str);
	}
}
