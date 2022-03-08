package Servlet;

import Bean.Attend;
import Bean.RP;
import Bean.Seni;
import Bean.Subi;
import DAO.AttendDao;
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

@WebServlet(name = "attendEditServlet", value = "/attendEditServlet")
public class attendEditServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String Flag = request.getParameter("Flag");

		if(Flag.equals("1")) {
			//增加
			String Sno = request.getParameter("Sno_add");
			String totalDate = request.getParameter("Adate_add") + "-28";
			java.sql.Date Adate = Attend.strToDate(totalDate);
			byte Nlate = Byte.parseByte(request.getParameter("Nlate_add"));
			byte Nleave = Byte.parseByte(request.getParameter("Nleave_add"));
			byte Nevec = Byte.parseByte(request.getParameter("Nevec_add"));
			byte Nextra = Byte.parseByte(request.getParameter("Nextra_add"));
			byte Nattend = Byte.parseByte(request.getParameter("Nattend_add"));
			Attend attend = new Attend(Sno, Adate, Nlate, Nleave, Nevec, Nextra, Nattend);
			new AttendDao().add(attend);
		} else if(Flag.equals("2")){
			//修改
			String Sno = request.getParameter("Sno_update");
			String totalDate = request.getParameter("Adate_update") + "-28";
			java.sql.Date Adate = Attend.strToDate(totalDate);
			byte Nlate = Byte.parseByte(request.getParameter("Nlate_update"));
			byte Nleave = Byte.parseByte(request.getParameter("Nleave_update"));
			byte Nevec = Byte.parseByte(request.getParameter("Nevec_update"));
			byte Nextra = Byte.parseByte(request.getParameter("Nextra_update"));
			byte Nattend = Byte.parseByte(request.getParameter("Nattend_update"));
			Attend attend = new Attend(Sno, Adate, Nlate, Nleave, Nevec, Nextra, Nattend);
			new AttendDao().delete(Sno, request.getParameter("Adate_update"));
			new AttendDao().add(attend);
		} else if(Flag.equals("3")) {
			//删除
			String Sno = request.getParameter("Sno_delete");
			String totalDate = request.getParameter("Adate_delete") + "-28";
			java.sql.Date Adate = Attend.strToDate(totalDate);
			new AttendDao().delete(Sno, request.getParameter("Adate_delete"));
		}
	}
}