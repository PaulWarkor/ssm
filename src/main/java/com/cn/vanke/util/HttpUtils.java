/**
 *
 */
package com.cn.vanke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.constants.MediaTypes;


/**
 * 
 * 功能说明：：Http请求工具类
 * 
 * HttpUtils.java
 */
public class HttpUtils {
	
	 private final static Logger log = LoggerFactory.getLogger(HttpUtils.class); 
	
	 /**
	  * Http GET 方式访问
	  * @param url
	  * @return
	  * @throws ClientProtocolException
	  * @throws IOException
	  */
	 public static String get(String url) throws ClientProtocolException, IOException {  
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpGet httpGet = new HttpGet(url);
		  CloseableHttpResponse response = httpClient.execute(httpGet);
		  try{
			  HttpEntity entity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (entity != null) {
				  //EntityUtils类：目标服务器响应可信任，并且响应包体长度不大
				  return EntityUtils.toString(entity,Consts.UTF_8);
			  }
			  return null;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	    }  

	/**
	 * Http POST 方式访问
	 * @param url  请求URL
	 * @param data 请求参数
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException 
	 *
	 */
	public static String post(String url,
			List<? extends org.apache.http.NameValuePair> data) throws ClientProtocolException, IOException {
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost(url);
		  if(data != null){
			  httpPost.setEntity(new UrlEncodedFormEntity(data,Consts.UTF_8));
		  }
		  CloseableHttpResponse response = httpClient.execute(httpPost);
		  try{
			  HttpEntity entity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (entity != null) {
				  return EntityUtils.toString(entity,Consts.UTF_8);
			  }
			  return null;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	}
	
	/**
	 * http的PUT方式访问
	 * @param url 请求URL url +"/"+put更新参数
	 * @param data 请求参数
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String put(String url,
			List<? extends org.apache.http.NameValuePair> data) throws ClientProtocolException, IOException {
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPut httpPut = new HttpPut(url);
		  if(data != null){
			  httpPut.setEntity(new UrlEncodedFormEntity(data,Consts.UTF_8));
		  }
		  CloseableHttpResponse response = httpClient.execute(httpPut);
		  try{
			  HttpEntity entity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (entity != null) {
				  return EntityUtils.toString(entity,Consts.UTF_8);
			  }
			  return null;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	}
	
	/**
	 * http delete 方式访问
	 * @param url url地址， 格式：url +"/"+删除参数
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String delete(String url) throws ClientProtocolException, IOException {
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpDelete httpDelete = new HttpDelete(url);
		  CloseableHttpResponse response = httpClient.execute(httpDelete);
		  try{
			  HttpEntity entity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (entity != null) {
				  return EntityUtils.toString(entity,Consts.UTF_8);
			  }
			  return null;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	}
	
	/**
	 * Http POST方式请求JSON包体
	 * @param url 请求URL
	 * @param data json包体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postJson(String url,String data) throws ClientProtocolException, IOException {
		return HttpUtils.post(url, data, MediaTypes.JSON);
	}
	
	/**
	 * Http POSt方式请求 xml包体
	 * @param url 请求URL
	 * @param data xml包体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postXml(String url,String data) throws ClientProtocolException, IOException {
		return HttpUtils.post(url, data, MediaTypes.APPLICATION_XML);
	}
	
	
	
	/**
	 * Http POST 方式访问
	 * @param url 请求URL
	 * @param data 请求参数(可以是json串 也可以是xml格式包体)
	 * @param contentType 对应请求内容格式
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url,String data,String contentType) throws ClientProtocolException, IOException {
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost(url);
		  if(StringUtils.isNotEmpty(data)){
			  StringEntity entity = new StringEntity(data, Consts.UTF_8);
			  entity.setContentType(contentType);
			  httpPost.setEntity(entity);
		  }
		  CloseableHttpResponse response = httpClient.execute(httpPost);
		  try{
			  HttpEntity entity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (entity != null) {
				  return EntityUtils.toString(entity,Consts.UTF_8);
			  }
			  return null;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	}
	
	/**
	 * Http PUT 方式访问
	 * @param url url地址， 格式：url +"/"+put参数
	 * @param data  请求参数(可以是json串 也可以是xml格式包体)
	 * @param contentType 对应请求内容格式
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String put(String url,String data,String contentType) throws ClientProtocolException, IOException {
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPut httpPut = new HttpPut(url);
		  if(StringUtils.isNotEmpty(data)){
			  StringEntity entity = new StringEntity(data, Consts.UTF_8);
			  entity.setContentType(contentType);
			  httpPut.setEntity(entity);
		  }
		  CloseableHttpResponse response = httpClient.execute(httpPut);
		  try{
			  HttpEntity entity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (entity != null) {
				  return EntityUtils.toString(entity,Consts.UTF_8);
			  }
			  return null;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	}
	
	/**
	 * Http PUT方式请求JSON包体
	 * @param url url地址， 格式：url +"/"+put参数
	 * @param data json包体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String putJson(String url,String data) throws ClientProtocolException, IOException {
		return HttpUtils.put(url, data, MediaTypes.JSON);
	}
	
	/**
	 * Http PUT方式请求 xml包体
	 * @param url url地址， 格式：url +"/"+put参数
	 * @param data xml包体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String putXml(String url,String data) throws ClientProtocolException, IOException {
		return HttpUtils.put(url, data, MediaTypes.APPLICATION_XML);
	}
	
	
	
	
	/**
	 * Http Post 表单提交 附加一个文件
	 * @param url 请求url
	 * @param file 文件
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postFile(String url,File file) throws ClientProtocolException, IOException{
		
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost(url);
		  HttpEntity requestEntity = MultipartEntityBuilder.create()
				  .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				  .addBinaryBody("file",FileUtils.toByteArray(file))
				  .setCharset(Consts.UTF_8).build();
		  httpPost.setEntity(requestEntity);
		  CloseableHttpResponse response = httpClient.execute(httpPost);
		  HttpEntity responseEntity = null;
		  try{
			  String responseStr = null;
			  //获取响应对象
			  responseEntity = response.getEntity();
			  log.debug("Response Status: " + response.getStatusLine());
			  if (responseEntity != null) {
				  responseStr =  EntityUtils.toString(responseEntity,Consts.UTF_8);
			  }
			  //销毁
			  EntityUtils.consumeQuietly(responseEntity);
			  return responseStr;
		  }finally{
			  response.close();
			  httpClient.close();
		  }
	}
	
	/**
	 * 模拟文件http上传(基于jdk原始方式)
	 * @param url
	 * @param file
	 * @return
	 */
	public static String postFileOriginal(String url,File file){
		try {
			URL postUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			byte[] f = FileUtils.toByteArray(file);
			//StringBuilder sb = new StringBuilder();
			conn.setRequestProperty("Content-Type", "multipart/form-data");
			conn.setRequestProperty("Content-Length", String.valueOf(f.length));
			OutputStream out = conn.getOutputStream();
			out.write(f);
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String responseStr = "";
			String line = null;
			while((line = in.readLine()) != null){
				responseStr += line;
			}
			in.close();
			out.close();
			return responseStr;
		} catch (MalformedURLException e) {
			log.error("MalformedURLException："+url);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("IOException：IO异常。");
			e.printStackTrace();
			
		}
		return null;
		
	}
	

}
