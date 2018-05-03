

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
   		//CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpclient对象
    				httpClient = HttpClients.custom() // 自定义httpclient对象
    				.setDefaultCookieStore(cookieStore) // 设置默认cookie存储区
    				.build(); // 构建该httpclient对象
    		HttpGet Get = new HttpGet("https://api.github.com/users/luozhigui?user=liudao"); // 创建post请求对象
    		List<NameValuePair> params = new ArrayList<>(); // 创建准备传送的post请求的body参数集合
    		params.add(new BasicNameValuePair("user", "liudao"));
    		CloseableHttpResponse response = null;
    		
    		try {
    			response = httpClient.execute(Get); // 使用httpclient发送post2请求
    			HttpEntity entity = response.getEntity();  // 获取响应实例
    			String responseStr = EntityUtils.toString(entity); // 将实例转换为字符串
    			System.out.println("888888"+responseStr);
    			auth = gson.fromJson(responseStr, Authorization.class);//转换成对象 下面请求就可以用了
    			
    			//List<Cookie> cookies = cookieStore.getCookies(); // 获取cookie存储区内的所有cookie
//    			if(cookies!=null) { // 如果不为空，则逐个打印cookie
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
    				response.close(); // 关闭响应
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
//		 * 2、进行查询操作，需要提供Authorization头，cookie，以及传送参数user
//		 */
//		SearchBookResult sbr = null;
//		String url = "http://fanyi.baidu.com/v2transapi/"; // 创建post请求对象
//		List<NameValuePair> params = new ArrayList<>(); // 创建准备传送的post请求的body参数集合
//		params.add(new BasicNameValuePair("query", "i love you"));
//		Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken()); // 准备添加用于token认证的头信息
//		List<Header> headers = new ArrayList<>();
//		headers.add(header);
//		String responseStr = postUtil.doPost(url, params, headers);
//		sbr = gson.fromJson(responseStr, SearchBookResult.class);
//		assertNotEquals(sbr.geterror(), 997);
//	}


    	
    	
    	
    	
    	
   ///////////////////////
    	
    HttpPost post = new HttpPost("http://fanyi.baidu.com/v2transapi/");
    SearchBookResult sbr = null;
	List<NameValuePair> params2 = new ArrayList<>(); // 创建准备传送的post请求的body参数集合
	params2.add(new BasicNameValuePair("query", "i love you"));
	try {
		post.setEntity(new UrlEncodedFormEntity(params2)); // 将准备好的参数集合放入post请求中
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken()); // 准备添加用于token认证的头信息
	post.addHeader(header);
	//如果你是login：luozhigu 你就在entities包 改prvtae就加入到头文件
	CloseableHttpResponse response2 = null;
	try {
		response2 = httpClient.execute(post); // 使用httpclient发送post2请求
		HttpEntity entity = response2.getEntity();  // 获取响应实例
		String responseStr = EntityUtils.toString(entity); 
		sbr = gson.fromJson(responseStr, SearchBookResult.class);
		// 将实例转换为字符串
		System.out.println(responseStr);
		
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		try {
			response2.close(); // 关闭响应
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
