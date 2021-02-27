package com.cafe24.sharekim93.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@WebServlet("/KakaoLogin")
public class KakaoLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "https://kauth.kakao.com/oauth/token?"
				+ "grant_type=authorization_code"
				+ "&client_id=1def83c8f6ac181e4df9db51d15d7f0e"
				+ "&redirect_uri=http://sharekim93.cafe24.com/HealthShare/KakaoLogin"
				+ "&code="+request.getParameter("code");
		String userapi = "https://kapi.kakao.com/v2/user/me";
		
		URL kakourl =null; HttpURLConnection conn = null; JsonParser parser = new JsonParser();
		BufferedReader br = null; StringBuffer buffer = new StringBuffer();  
		
		try {
			kakourl = new URL(url);
			conn = (HttpURLConnection) kakourl.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);	conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		} 
		catch (MalformedURLException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		
		int responseCode = conn.getResponseCode(); 
		if(responseCode ==200) {br=new BufferedReader(new InputStreamReader(conn.getInputStream()));}
		else {br= new BufferedReader(new InputStreamReader(conn.getErrorStream()));}
		
		String readLine = "";
		while((readLine=br.readLine())!=null) {buffer.append(readLine);}
		br.close();
		
		String result = buffer.toString();
		JsonObject object = (JsonObject) parser.parse(result);
		String access_token = object.get("access_token").getAsString();
		
		URL userurl = new URL(userapi);
		HttpURLConnection userconn = (HttpURLConnection) userurl.openConnection();
		userconn.setRequestMethod("GET");
		userconn.setRequestProperty("Authorization", "Bearer "+access_token);
		
		responseCode = userconn.getResponseCode();
		if(responseCode == 200) { br = new BufferedReader(new InputStreamReader(userconn.getInputStream())); }
		else {br = new BufferedReader(new InputStreamReader(userconn.getErrorStream()));}
		
		String userLine = "";
		StringBuffer userBuffer = new StringBuffer();
		while((userLine = br.readLine())!=null) {userBuffer.append(userLine);}
		
		String email=""; String id="";
		//String birthyear="";
		if(responseCode ==200) {
		JsonObject userobject = (JsonObject) parser.parse(userBuffer.toString());
		JsonObject properties = (JsonObject) userobject.get("properties");
		//email = properties.get("email").getAsString();
		id = userobject.get("id").getAsString();
		//birthyear = properties.get("birthyear").getAsString();
		}
		//request.setAttribute("email", email);
		//request.setAttribute("birthyear", birthyear);
		request.setAttribute("kakaoid", id);
		request.setAttribute("responseCode", responseCode);
		request.getRequestDispatcher(request.getContextPath()+"/login.do").forward(request, response);
	}
}
