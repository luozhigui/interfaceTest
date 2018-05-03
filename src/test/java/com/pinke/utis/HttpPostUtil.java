package com.pinke.utis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpPostUtil {
	private CloseableHttpClient httpClient;
	private CookieStore cookieStore;
	public HttpPostUtil() {
		cookieStore = new BasicCookieStore();
		httpClient = HttpClients.custom() // 自定义httpclient对象
				.setDefaultCookieStore(cookieStore) // 设置默认cookie存储区
				.build(); 
	}
	public String doPost(String url, List<NameValuePair> params, List<Header> headers) {
		HttpPost post = new HttpPost(url);
		String responseStr = null;
		if(params!=null) {
			post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));//中文转义
					//post.setEntity(new UrlEncodedFormEntity(params));	
		}
		if(headers!=null) {
			Header[] h = new Header[headers.size()];
			post.setHeaders(headers.toArray(h));
		}
		CloseableHttpResponse response2 = null;
		try {
			response2 = httpClient.execute(post); // 使用httpclient发送post请求
			HttpEntity entity = response2.getEntity();  // 获取响应实例
			responseStr = EntityUtils.toString(entity); // 将实例转换为字符串
			//!!切记 上代码 多给了个String 导致 结果失败 1 不是0
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
		return responseStr;
	}
}