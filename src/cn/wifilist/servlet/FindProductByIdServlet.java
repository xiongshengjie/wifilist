package cn.wifilist.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.wifilist.domain.user;
import cn.wifilist.exception.FindProductByIdException;
import cn.wifilist.service.UserService;

/**
 * 根据商品id查找指定商品信息的servlet
 */
public class FindProductByIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到商品的id
		String id = request.getParameter("id");
		// 获取type参数值，此处的type用于区别普通用户和超级用户
		String type = request.getParameter("type");
		
		UserService service = new UserService();
		
		try {
			// 调用service层方法，通过id查找商品
			user p = service.findProductById(id);
			request.setAttribute("p", p);

			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
