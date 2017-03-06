package cn.wifilist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wifilist.domain.user;
import cn.wifilist.service.UserService;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		user realuser = new user();
		
		String username = request.getParameter("user");
		String password = request.getParameter("pwd");
		
		realuser.setUsername(username);
		realuser.setPassword(password);
		
		UserService service = new UserService();
		
		user matchuser = new user();
		try {
			 matchuser = service.login(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(matchuser != null){
			if(matchuser.getRool().equals("admin"))
			{
				request.getSession().setAttribute("user", matchuser);
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
			}else{
				out.println("{\"errCode\":0,\"errMsg\":\"OK\"}");
			}
		}else{
			out.println("{\"errCode\":1,\"errMsg\":\"用户名或密码错误\"}");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
