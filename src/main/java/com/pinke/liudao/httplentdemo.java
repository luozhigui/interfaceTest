package com.pinke.liudao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pinke.entities.Authorization;
import com.pinke.utis.HttpPostUtil;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.pinke.entities.Authorization;
//
public class httplentdemo {
//
	public static void main(String[] args) {
//
		Gson gson = new GsonBuilder().create();
		
		CookieStore cookieStore = new BasicCookieStore();
//		//CloseableHttpClient httpClient = HttpClients.createDefault(); // ����httpclient����
		CloseableHttpClient httpClient = HttpClients.custom() // �Զ���httpclient����
				.setDefaultCookieStore(cookieStore) // ����Ĭ��cookie�洢��
				.build(); // ������httpclient����
		HttpGet Get = new HttpGet("https://api.github.com/users/luozhigui?user=liudao"); // ����post�������
		List<NameValuePair> params = new ArrayList<>(); // ����׼�����͵�post�����body��������
		params.add(new BasicNameValuePair("user", "liudao"));
		//params.add(new BasicNameValuePair("pwd", "123456"));��������ָpost����ʽ ����
//		try {
//			//post.setEntity(new UrlEncodedFormEntity(params));// ��׼���õĲ������Ϸ���post������
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		CloseableHttpResponse response = null;
		Authorization auth = null;
		try {
			response = httpClient.execute(Get); // ʹ��httpclient����post2����
			HttpEntity entity = response.getEntity();  // ��ȡ��Ӧʵ��
			String responseStr = EntityUtils.toString(entity); // ��ʵ��ת��Ϊ�ַ���
			System.out.println(responseStr);
			auth = gson.fromJson(responseStr, Authorization.class);//ת���ɶ��� ��������Ϳ�������
			
//			List<Cookie> cookies = cookieStore.getCookies(); // ��ȡcookie�洢���ڵ�����cookie
//			if(cookies!=null) { // �����Ϊ�գ��������ӡcookie
//				for(int i = 0; i < cookies.size(); i++) {
//					System.out.println(cookies.get(i));
//				}
//
//			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				response.close(); // �ر���Ӧ
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		HttpGet Get2 = new HttpGet("https://api.github.com/users/luozhigui"); // ����post�������
		List<NameValuePair> params2 = new ArrayList<>(); // ����׼�����͵�post�����body��������
		params2.add(new BasicNameValuePair("user", "liudao"));
//		try {
//			//Get2.setEntity(new UrlEncodedFormEntity(params2)); // ��׼���õĲ������Ϸ���post������
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken()); // ׼���������token��֤��ͷ��Ϣ
		Get2.addHeader(header);
		//�������login��luozhigu �����entities�� ��prvtae�ͼ��뵽ͷ�ļ�
		CloseableHttpResponse response2 = null;
		try {
			response2 = httpClient.execute(Get2); // ʹ��httpclient����post2����
			HttpEntity entity = response2.getEntity();  // ��ȡ��Ӧʵ��
			String responseStr = EntityUtils.toString(entity); // ��ʵ��ת��Ϊ�ַ���
			System.out.println(responseStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				response2.close(); // �ر���Ӧ
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	

