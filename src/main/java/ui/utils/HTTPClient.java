package ui.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class HTTPClient {
    private static final Logger logger = Logger.getLogger(HTTPClient.class);
    private String responseText;

  private HttpClient getClient(){
      return HttpClientBuilder.create().build();
  }
  private HttpGet getRequest(String url){
      return  new HttpGet(url);
  }
    private HttpPost postRequest(String url,String body,String type) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);
        switch (type) {
            case "json":
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                break;
            case "form":
                httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        }
        return httpPost;
    }
    public String get(String url){
        try {
            HttpResponse response = getClient().execute(getRequest(url));
            responseText = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return responseText;
    }
    public String post(String url,String body,String type){
        try {
            HttpResponse response = getClient().execute(postRequest(url,body,type));
            responseText = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText;
    }

}
