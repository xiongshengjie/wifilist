package cn.wifilist.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.wifilist.domain.user;
import cn.wifilist.exception.AddProductException;
import cn.wifilist.service.UserService;

/**
 * 后台系统
 * 用于编辑商品信息的servlet
 */
public class EditProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		// 创建javaBean,将上传数据封装.
		user realuser = new user();
		
		String id=request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username==null||password==null||username.trim().length()==0||password.trim().length()==0){
			out.print(false);
			return;
		}
		
		realuser.setId(id);
		realuser.setUsername(username);
		realuser.setPassword(password);
		
		UserService service = new UserService();
		
		boolean flag = false;

		// 调用service完成修改商品操作
		service.editUser(realuser);
		response.sendRedirect(request.getContextPath() + "/listUser");
		return;

	}

}
