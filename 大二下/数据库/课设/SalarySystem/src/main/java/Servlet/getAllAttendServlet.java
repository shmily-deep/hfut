package Servlet;

import Bean.Attend;
import Bean.Staff;
import DAO.AttendDao;
import DAO.StaffDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getAllAttendServlet", value = "/getAllAttendServlet")
public class getAllAttendServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Sno = request.getParameter("Sno");
		String date = request.getParameter("date");
		List<Attend> list = new AttendDao().getAttend(Sno, date);
		JSONArray total = new JSONArray();
		for (Attend attend : list) {
			JSONObject json = new JSONObject();
			json.put("Sno", attend.getSno());
			json.put("Adate", attend.getMonth());
			json.put("Nlate", attend.getNlate());
			json.put("Nleave", attend.getNleave());
			json.put("Nevec", attend.getNevec());
			json.put("Nextra", attend.getNextra());
			json.put("Nattend", attend.getNattend());
			total.add(json);
		}
		String str = total.toString();
		out.write(str);
	}
}
