package com.example.egemendurmus.oneclean;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class PostClass {
	static InputStream veri;
	static String veri_string;
	
	
	public PostClass() {
		// TODO Auto-generated constructor stub
	}
	
	public String httpPost(String url, String method, StringEntity params, int time) {
		 
        //url: post yap�lacak adres
		//method: post mu get mi
		//params:post edilecek veriler de�i�kenler
		//time: sunucudan cevap gelmezse ka� sn sonra uygulama donmadan postun iptal edilece�i
        try {
            
            if (method == "POST") {
                
            	HttpParams httpParameters = new BasicHttpParams();
            	int timeout1 = time;
            	int timeout2 = time;
            	HttpConnectionParams.setConnectionTimeout(httpParameters, timeout1);
            	HttpConnectionParams.setSoTimeout(httpParameters, timeout2);
                DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(params);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                veri =  httpEntity.getContent();
 
            } else if (method == "GET") {
                
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format((List<? extends NameValuePair>) params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                veri =  httpEntity.getContent();            
            }
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    veri, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            veri.close();
            veri_string = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Hata " + e.toString());
                 }
       
        return veri_string; // Ald���m�z cevab�n string halini geri d�n�yoruz
 
    }

}
