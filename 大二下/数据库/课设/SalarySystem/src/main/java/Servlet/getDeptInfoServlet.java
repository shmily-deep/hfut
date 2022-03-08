package Servlet;

import Bean.Dept;
import Bean.Staff;
import DAO.DeptDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getDeptInfoServlet", value = "/getDeptInfoServlet")
public class getDeptInfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		List<Dept> list = new DeptDao().select();;
		JSONArray total = new JSONArray();
		for (Dept dept: list) {
			JSONObject json = new JSONObject();
			json.put("Dname", dept.getDname());
			json.put("Manger", dept.getManger());
			json.put("Tnum", dept.getTnum());
			total.add(json);
		}
		String str = total.toString();
		out.write(str);
	}
}