package Servlet;

import Bean.Attend;
import Bean.Staff;
import Bean.Vwage;
import DAO.AttendDao;
import DAO.StaffDao;
import DAO.VwageDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getSalary", value = "/getSalary")
public class getSalaryServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String Sno = null;
		List<Vwage> list = null;
		if(request.getParameter("Sno") == null) {
			Sno = (String) request.getSession().getAttribute("Sno");
			String date = request.getParameter("date");
			list = new VwageDao().select(Sno, date);
		} else {
			//管理员页面
			Sno = request.getParameter("Sno");
			String date = request.getParameter("date2");
			list = new VwageDao().select(Sno, date);
		}

		JSONArray total = new JSONArray();
		for (Vwage vwage : list) {
			JSONObject json = new JSONObject();
			json.put("Sno", vwage.getSno());
			json.put("Rdate", vwage.getRdate().toString());
			json.put("Bsalary", vwage.getBsalary());
			json.put("Wsub", vwage.getWsub());
			json.put("Msub", vwage.getMsub());
			json.put("Tsub", vwage.getTsub());
			json.put("Hsub", vwage.getHsub());
			json.put("Late", vwage.getLate());
			json.put("Leave", vwage.getLeave());
			json.put("Evec", vwage.getEvec());
			json.put("Fattend", vwage.getFattend());
			json.put("Extra", vwage.getExtra());
			json.put("Stax", vwage.getStax());
			json.put("Sins", vwage.getSins());
			json.put("Sala", vwage.getSala());
			json.put("Npay", vwage.getNpay());
			total.add(json);
		}
		String str = total.toString();
		out.write(str);
	}
}
