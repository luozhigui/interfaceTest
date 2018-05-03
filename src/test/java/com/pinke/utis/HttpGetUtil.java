//package com.pinke.utis;
//
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//public class HttpGetUtil {
//	private BasicCookieStore cookes;
//	private CloseableHttpClient httpClients;
//
//	public HttpGetUtil() {//哥来也
//		
//  		 cookes = new BasicCookieStore();
//   				httpClients = HttpClients.custom() // 自定义httpclient对象
//   				.setDefaultCookieStore(cookes) // 设置默认cookie存储区
//   				.build(); // 构建该httpclient对象
//		
//	}
//	public  String doGet(String url) {
//		
//		HttpGet Get = new HttpGet(url); // 创建post请求对象
//		
//		
//		
//		
//		
//		return null;
//		
//	}
//
//}
