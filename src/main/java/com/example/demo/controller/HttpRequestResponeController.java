package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.form.PersonForm;
import com.example.demo.model.Person;



@Controller
public class HttpRequestResponeController {

	/**
	 * body ban chat la binary
	 * Connect = keep-alive
	 * content-lenght: 20
	 * Phan content-type: application/octect-stream   => dùng html form ko dc. Phai dung ARC plugin của google hoặc Từ javaSource moi set dc content-type 
	 * neu html form type= text  => thì inputstream se tra ve la empty
	 * html form type sẽ phải quy đổi ra các content-type chuẩn của http protocol
	 */
	@RequestMapping(value = "/report/{objectId}", method = RequestMethod.GET)
	@ResponseBody 
	public void generateReport(
			@PathVariable("objectId") Long objectId, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {

		String uri = ((HttpServletRequest)request).getRequestURI().toString();
		System.out.println("***** CustomFilter.doFilter: " + uri);

		//
		if(objectId == 1){
			//forwardTest.html => can khai bao trong Controller
			((HttpServletResponse)response).sendRedirect("/");
		}else if(objectId == 2){
			((HttpServletRequest)request).getRequestDispatcher("/form").forward(request, response);
		}else{
			String st = "@controller: HttpServletRequest and HttpServletResponse";
			byte[] byteArr = st.getBytes();
			response.setContentType("application/octect-stream");
			response.getOutputStream().write(byteArr);
		}

	}
}