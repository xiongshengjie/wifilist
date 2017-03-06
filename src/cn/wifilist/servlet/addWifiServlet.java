package cn.wifilist.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import cn.wifilist.utils.FileUploadUtils;
import cn.wifilist.utils.JsonUtils;
import cn.wifilist.utils.IdUtils;
import cn.wifilist.domain.shop;
import cn.wifilist.domain.user;
import cn.wifilist.domain.wifilist;
import cn.wifilist.service.UserService;
import cn.wifilist.service.WifiService;

/**
 * Servlet implementation class addWifi
 */
@WebServlet("/addWifi")
public class addWifiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addWifiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		user realuser =new user();
		shop realshop = new shop();
		List<wifilist> realwifilist = new ArrayList<wifilist>();
		
		PrintWriter out = response.getWriter();
		
		Map<String, String> map = new HashMap<String,String>();
		String uid = IdUtils.getUUID();
		map.put("id", uid);
		
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 设置临时文件存储位置
		dfif.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));
		// 设置上传文件缓存大小为10m
		dfif.setSizeThreshold(1024 * 1024 * 10);
		// 创建上传组件
		ServletFileUpload upload = new ServletFileUpload(dfif);
		// 处理上传文件中文乱码
		upload.setHeaderEncoding("utf-8");
		try {
			// 解析request得到所有的FileItem
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			// 遍历所有FileItem
			for (FileItem item : items) {
				// 判断当前是否是上传组件
				if (item.isFormField()) {
					// 不是上传组件
					String fieldName = item.getFieldName(); // 获取组件名称
					String value = item.getString("utf-8"); // 解决乱码问题
					map.put(fieldName, value);
				} else {
					// 是上传组件
					// 得到上传文件真实名称
					String fileName = item.getName();
					fileName = FileUploadUtils.subFileName(fileName);

					// 得到随机名称
					String randomName = FileUploadUtils
							.generateRandonFileName(fileName);

					// 得到随机目录
					String randomDir = FileUploadUtils
							.generateRandomDir(randomName);
					// 图片存储父目录
					String imgurl_parent = "/productImg" + randomDir;

					File parentDir = new File(this.getServletContext()
							.getRealPath(imgurl_parent));
					// 验证目录是否存在，如果不存在，创建出来
					if (!parentDir.exists()) {
						parentDir.mkdirs();
					}

					String imgurl = imgurl_parent + "/" + randomName;

					map.put("imgurl", imgurl);

					IOUtils.copy(item.getInputStream(), new FileOutputStream(
							new File(parentDir, randomName)));
					item.delete();

				}

			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		String username = map.get("user");
		String pwd = map.get("pwd");
		String wifidata = map.get("data");
		
		realuser.setUsername(username);
		realuser.setPassword(pwd);
		
		UserService userService = new UserService();
		user matchuser = new user();
		try {
			 matchuser = userService.login(username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(matchuser == null){
			out.println("{\"errCode\":1,\"errMsg\":\"用户名或密码错误\"}");
		}else{
			//用json-lib将json数据转化为Map<String,String>,取出shop的属性
			 Map<String, String> shopmap = new HashMap<String, String>();
			 shopmap = JsonUtils.parseJSON2MapString(wifidata);
			 wifidata = shopmap.get("list");
			 
			realshop.setShopname(shopmap.get("name"));
			realshop.setBuildingname(shopmap.get("building"));
			realshop.setShoplatitude(new BigDecimal(shopmap.get("latitude")));
			realshop.setShoplogitude(new BigDecimal(shopmap.get("longitude")));
			realshop.setFloor(Integer.parseInt(shopmap.get("floor")));
			realshop.setImageurl(map.get("imgurl"));
			realshop.setAccuracy(shopmap.get("accuracy"));
			realshop.setUsername(username);
			
			List<Map<String,String>> wifiMap = new ArrayList<Map<String,String>>();
		
			wifiMap = JsonUtils.parseJSON2ListString(wifidata);
			for(Map<String,String> wifi:wifiMap) {
				String place = wifi.get("place");
				
				List<Map<String,String>> wifiList = new ArrayList<Map<String,String>>();
				wifiList = JsonUtils.parseJSON2ListString(wifi.get("wifi"));
				
				for(Map<String,String> ssid:wifiList) {
					String wifiname = ssid.get("ssid");
					int wifilevel = Integer.parseInt(ssid.get("level"));
					
					wifilist wi = new wifilist();
					wi.setPlace(place);
					wi.setSsid(wifiname);
					wi.setWifilevel(wifilevel);
					
					realwifilist.add(wi);
				}
			}
			
			WifiService service = new WifiService();
			try {
				 int correct = service.addwifi(realshop, realwifilist, uid);
				 if(correct > 0)
				 {
					 out.print("{\"errCode\":0,\"errMsg\":\"上传成功^_^\"}");
				 }else{
					 out.print("{\"errCode\":1,\"errMsg\":\"上传失败-_-\"}");
				 }
				 return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
