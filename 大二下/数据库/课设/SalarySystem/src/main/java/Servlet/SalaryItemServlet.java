package Servlet;

import Bean.RP;
import Bean.Seni;
import Bean.Subi;
import DAO.RPDao;
import DAO.SeniDao;
import DAO.SubiDao;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SalaryItemServlet", value = "/SalaryItemServlet")
public class SalaryItemServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Flag = request.getParameter("Flag");

		if(Flag.equals("1")) {
			//传回初始信息
			List<RP> listRP = new RPDao().selectAll();
			List<Subi> listSubi = new SubiDao().selectAll();
			List<Seni> listSeni = new SeniDao().selectAll();
			JSONObject json = new JSONObject();
			json.put("late",listRP.get(0).getRPmoney());
			json.put("evec",listRP.get(1).getRPmoney());
			json.put("extra",listRP.get(2).getRPmoney());
			json.put("attend",listRP.get(3).getRPmoney());
			json.put("leave",listRP.get(4).getRPmoney());
			json.put("Msub",listSubi.get(0).getSub());
			json.put("Tsub",listSubi.get(1).getSub());
			json.put("Hsub",listSubi.get(2).getSub());
			json.put("workAge1",listSeni.get(0).getWsub());
			json.put("workAge3",listSeni.get(1).getWsub());
			json.put("workAge5",listSeni.get(2).getWsub());
			json.put("workAge10",listSeni.get(3).getWsub());
			String str = json.toString();
			out.write(str);
		} else if(Flag.equals("2")){
			//修改奖罚RP信息
			String test = request.getParameter("late");
			System.out.println(test);
			int late = Integer.parseInt(request.getParameter("late"));
			int evec = Integer.parseInt(request.getParameter("evec"));
			int extra = Integer.parseInt(request.getParameter("extra"));
			int attend = Integer.parseInt(request.getParameter("attend"));
			int leave = Integer.parseInt(request.getParameter("leave"));
			new RPDao().update("迟到", "RPmoney", late + "");
			new RPDao().update("出差", "RPmoney", evec + "");
			new RPDao().update("加班", "RPmoney", extra + "");
			new RPDao().update("全勤", "RPmoney", attend + "");
			new RPDao().update("早退", "RPmoney", leave + "");
		} else if(Flag.equals("3")) {
			//修改补贴Subi信息
			int Msub = Integer.parseInt(request.getParameter("Msub"));
			int Tsub = Integer.parseInt(request.getParameter("Tsub"));
			int Hsub = Integer.parseInt(request.getParameter("Hsub"));
			new SubiDao().update("餐饮补贴", "Sub", Msub + "");
			new SubiDao().update("交通补贴", "Sub", Tsub + "");
			new SubiDao().update("住房补贴", "Sub", Hsub + "");
		} else if(Flag.equals("4")) {
			//修改工龄Seni信息
			int workAge1 = Integer.parseInt(request.getParameter("workAge1"));
			int workAge3 = Integer.parseInt(request.getParameter("workAge3"));
			int workAge5 = Integer.parseInt(request.getParameter("workAge5"));
			int workAge10 = Integer.parseInt(request.getParameter("workAge10"));
			new SeniDao().update((byte) 1, "Wsub", workAge1 + "");
			new SeniDao().update((byte)	3, "Wsub", workAge3 + "");
			new SeniDao().update((byte) 5, "Wsub", workAge5 + "");
			new SeniDao().update((byte)10, "Wsub", workAge10 + "");
		}

	}
}