package cn.wifilist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wifilist.domain.floor;
import cn.wifilist.domain.type;
import cn.wifilist.exception.ListTypeException;
import cn.wifilist.service.TypeService;
import cn.wifilist.utils.JsonUtils;

/**
 * Servlet implementation class TypeServlet
 */
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TypeService service = new TypeService();
		
		List<type> listtype = new ArrayList<type>();
		floor floormess = new floor();
		
		try {
			floormess = service.getfloor();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			listtype = service.ListAllType();
		} catch (ListTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String json = JsonUtils.toJSONString(listtype);
		
		PrintWriter out = response.getWriter();
		
		if(json == null){
			out.write("{\"errCode\":1,\"data\":获取业态失败}");
		}else{
			out.write("{\"errCode\":0,\"data\":"+json+",\"floor\":{\"min\":"+floormess.getMinfloor()+",\"max\":"+floormess.getMaxfloor()+"}}");
		}
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
