package com.cafe24.sharekim93.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BMICal")
public class BMICalculator extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String ReceivedHeight =request.getParameter("height");
		String ReceivedWeight =request.getParameter("weight");
		
		double height =0f;
		double weight =0f; 
		
		String result ="";
		
		try {
			if(ReceivedHeight!=null&&ReceivedHeight!="") {height=Float.parseFloat(ReceivedHeight);}
			if(ReceivedWeight!=null&&ReceivedWeight!="") {weight=Float.parseFloat(ReceivedWeight);}
				
		double bmi = (weight/Math.pow(height/100, 2));
		
		if(bmi>=35) {result="3단계 비만(고도비만)";}
		else if(bmi>30) {result="2단계 비만";}
		else if(bmi>25) {result="1단계 비만";}
		else if(bmi>23) {result="과체중(비만 전 단계)";}
		else {result="정상";}
		
		result+=" 입니다";
		}
		catch (NumberFormatException e) { result = "숫자를 입력해주세요";}
		finally{out.println(result);}
	}
}
