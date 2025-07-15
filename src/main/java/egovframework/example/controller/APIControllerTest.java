package egovframework.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIControllerTest {
	
	@GetMapping("/api/test")
	public String callApi() throws IOException{
		
		/*
		StringBuilder urlBuilder = new StringBuilder("https://kosis.kr/openapi/Param/statisticsParameterData.do");
		
		urlBuilder.append("?" + URLEncoder.encode("method","UTF-8") + "=" + URLEncoder.encode("getList", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("apiKey","UTF-8") + "=ZGFlYjQxMTg4NTM0OTAxZDFjNzJiN2IxMDI5MjQwNjE="); 
		
		
		String itmIdRaw = "T1+T2+";
        urlBuilder.append("&" + URLEncoder.encode("itmId","UTF-8") + "=" + URLEncoder.encode(itmIdRaw, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("objL1", "UTF-8") + "=" + URLEncoder.encode("ALL", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("objL2", "UTF-8") + "=" + URLEncoder.encode("ALL", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("objL3", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL4", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL5", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL6", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL7", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL8", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("jsonVD","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("prdSe","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("newEstPrdCnt","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("orgId","UTF-8") + "=" + URLEncoder.encode("101", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("tblId","UTF-8") + "=" + URLEncoder.encode("INH_1C91", "UTF-8")); 
        */
		
		StringBuilder urlBuilder = new StringBuilder("https://kosis.kr/openapi/Param/statisticsParameterData.do");
		
		urlBuilder.append("?" + URLEncoder.encode("method","UTF-8") + "=" + URLEncoder.encode("getList", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("apiKey","UTF-8") + "=ZGFlYjQxMTg4NTM0OTAxZDFjNzJiN2IxMDI5MjQwNjE="); 
		
		String itmIdRaw = "13103134767999+";
        urlBuilder.append("&" + URLEncoder.encode("itmId","UTF-8") + "=" + URLEncoder.encode(itmIdRaw, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("objL1", "UTF-8") + "=" + URLEncoder.encode("ALL", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("objL2", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL3", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL4", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL5", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL6", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL7", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("objL8", "UTF-8") + "=");
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("jsonVD","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("prdSe","UTF-8") + "=" + URLEncoder.encode("M", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("newEstPrdCnt","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("orgId","UTF-8") + "=" + URLEncoder.encode("101", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("tblId","UTF-8") + "=" + URLEncoder.encode("DT_1YL7101E", "UTF-8")); 
        
        URL url = new URL(urlBuilder.toString());
        
        System.out.println("url : " + url);
        System.out.println("itmIdRaw : " + itmIdRaw);
        
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        
        conn.setRequestMethod("GET");
        
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
        	rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null ) {
        	sb.append(line);
        }
		
        rd.close();
        conn.disconnect();
        
        System.out.println(sb.toString());
        return sb.toString();
	}
	
}
