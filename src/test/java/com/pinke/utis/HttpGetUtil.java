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
//	public HttpGetUtil() {//����Ҳ
//		
//  		 cookes = new BasicCookieStore();
//   				httpClients = HttpClients.custom() // �Զ���httpclient����
//   				.setDefaultCookieStore(cookes) // ����Ĭ��cookie�洢��
//   				.build(); // ������httpclient����
//		
//	}
//	public  String doGet(String url) {
//		
//		HttpGet Get = new HttpGet(url); // ����post�������
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
