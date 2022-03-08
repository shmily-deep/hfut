package Servlet;

import Bean.Bwage;
import Bean.Dept;
import DAO.BwagDao;
import DAO.DeptDao;
import DAO.VwageDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getJobInfoServlet", value = "/getJobInfoServlet")
public class getJobInfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		List<Bwage> list = new BwagDao().selectAll();
		JSONArray total = new JSONArray();
		for (Bwage bwage: list) {
			JSONObject json = new JSONObject();
			json.put("Spos", bwage.getSpos());
			json.put("Bsalary", bwage.getBsalary());
			total.add(json);
		}
		String str = total.toString();
		out.write(str);
	}
}