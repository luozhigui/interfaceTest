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
		httpClient = HttpClients.custom() // �Զ���httpclient����
				.setDefaultCookieStore(cookieStore) // ����Ĭ��cookie�洢��
				.build(); 
	}
	public String doPost(String url, List<NameValuePair> params, List<Header> headers) {
		HttpPost post = new HttpPost(url);
		String responseStr = null;
		if(params!=null) {
			post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));//����ת��
					//post.setEntity(new UrlEncodedFormEntity(params));	
		}
		if(headers!=null) {
			Header[] h = new Header[headers.size()];
			post.setHeaders(headers.toArray(h));
		}
		CloseableHttpResponse response2 = null;
		try {
			response2 = httpClient.execute(post); // ʹ��httpclient����post����
			HttpEntity entity = response2.getEntity();  // ��ȡ��Ӧʵ��
			responseStr = EntityUtils.toString(entity); // ��ʵ��ת��Ϊ�ַ���
			//!!�м� �ϴ��� ����˸�String ���� ���ʧ�� 1 ����0
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
		return responseStr;
	}
}