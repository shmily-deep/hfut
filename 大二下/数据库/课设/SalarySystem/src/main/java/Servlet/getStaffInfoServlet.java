package Servlet;

import Bean.Staff;
import DAO.StaffDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getStaffInfoServlet", value = "/getStaffInfoServlet")
public class getStaffInfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		List<Staff> list = null;
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String Dname = request.getParameter("Dname");
		String Flag = request.getParameter("Flag");
		if(Flag.equals("1")) {
			list = new StaffDao().selectByParams("Sno", Sno);
		} else if(Flag.equals("2")) {
			list = new StaffDao().selectByParams("Sname", Sname);
		} else if(Flag.equals("3")) {
			list = new StaffDao().selectByParams("Dname", Dname);
		}
		JSONArray total = new JSONArray();
		for (Staff staff : list) {
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
			total.add(json);
		}
		String str = total.toString();
		out.write(str);
	}
}
