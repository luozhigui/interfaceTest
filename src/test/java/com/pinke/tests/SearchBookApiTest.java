

package com.pinke.tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pinke.entities.Authorization;
import com.pinke.entities.SearchBookResult;
import com.pinke.utis.HttpPostUtil;

public class SearchBookApiTest {
	private Authorization auth ;
	private CloseableHttpClient httpClient;
	private CookieStore cookieStore ;
	 @BeforeClass
  public void dologin() throws UnsupportedEncodingException {
		  
		  
   	Gson gson = new GsonBuilder().create();
   		 cookieStore = new BasicCookieStore();
   		//CloseableHttpClient httpClient = HttpClients.createDefault(); // ����httpclient����
    				httpClient = HttpClients.custom() // �Զ���httpclient����
    				.setDefaultCookieStore(cookieStore) // ����Ĭ��cookie�洢��
    				.build(); // ������httpclient����
    		HttpGet Get = new HttpGet("https://api.github.com/users/luozhigui?user=liudao"); // ����post�������
    		List<NameValuePair> params = new ArrayList<>(); // ����׼�����͵�post�����body��������
    		params.add(new BasicNameValuePair("user", "liudao"));
    		CloseableHttpResponse response = null;
    		
    		try {
    			response = httpClient.execute(Get); // ʹ��httpclient����post2����
    			HttpEntity entity = response.getEntity();  // ��ȡ��Ӧʵ��
    			String responseStr = EntityUtils.toString(entity); // ��ʵ��ת��Ϊ�ַ���
    			System.out.println("888888"+responseStr);
    			auth = gson.fromJson(responseStr, Authorization.class);//ת���ɶ��� ��������Ϳ�������
    			
    			//List<Cookie> cookies = cookieStore.getCookies(); // ��ȡcookie�洢���ڵ�����cookie
//    			if(cookies!=null) { // �����Ϊ�գ��������ӡcookie
//    				for(int i = 0; i < cookies.size(); i++) {
//    					System.out.println(cookies.get(i));
//    					
//    				}
//   			}
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
    }

    @Test
    public void testSearcheBook() {
//    	HttpPostUtil postUtil = new HttpPostUtil();
    	Gson gson = new GsonBuilder().create();
//		/*
//		 * 2�����в�ѯ��������Ҫ�ṩAuthorizationͷ��cookie���Լ����Ͳ���user
//		 */
//		SearchBookResult sbr = null;
//		String url = "http://fanyi.baidu.com/v2transapi/"; // ����post�������
//		List<NameValuePair> params = new ArrayList<>(); // ����׼�����͵�post�����body��������
//		params.add(new BasicNameValuePair("query", "i love you"));
//		Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken()); // ׼���������token��֤��ͷ��Ϣ
//		List<Header> headers = new ArrayList<>();
//		headers.add(header);
//		String responseStr = postUtil.doPost(url, params, headers);
//		sbr = gson.fromJson(responseStr, SearchBookResult.class);
//		assertNotEquals(sbr.geterror(), 997);
//	}


    	
    	
    	
    	
    	
   ///////////////////////
    	
    HttpPost post = new HttpPost("http://fanyi.baidu.com/v2transapi/");
    SearchBookResult sbr = null;
	List<NameValuePair> params2 = new ArrayList<>(); // ����׼�����͵�post�����body��������
	params2.add(new BasicNameValuePair("query", "i love you"));
	try {
		post.setEntity(new UrlEncodedFormEntity(params2)); // ��׼���õĲ������Ϸ���post������
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken()); // ׼���������token��֤��ͷ��Ϣ
	post.addHeader(header);
	//�������login��luozhigu �����entities�� ��prvtae�ͼ��뵽ͷ�ļ�
	CloseableHttpResponse response2 = null;
	try {
		response2 = httpClient.execute(post); // ʹ��httpclient����post2����
		HttpEntity entity = response2.getEntity();  // ��ȡ��Ӧʵ��
		String responseStr = EntityUtils.toString(entity); 
		sbr = gson.fromJson(responseStr, SearchBookResult.class);
		// ��ʵ��ת��Ϊ�ַ���
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
    	
	assertEquals(sbr.geterror(), 997);
	
    
    
    
    
    
    

    
    }
    
    
    
    
    
    
    
    
    

    @AfterClass
    public void afterClass() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
