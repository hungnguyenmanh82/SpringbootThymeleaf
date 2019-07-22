package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

/**
@controller: bản chất là đăng ký nhận Event từ client (vd: http event, STOMP event với websocket)
@RequestMapping(): là đăng ký nhận Event từ Http request
 */
@Controller
public class HttpResponseHeaderController {


	@RequestMapping(value = "/responseHeader", method = RequestMethod.GET)
	// @ResponseBody void: ko trả ve giá trị => dùng HttpServletResponse để trả về giá trị
	public @ResponseBody String generateReport(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {

		String uri = ((HttpServletRequest)request).getRequestURI().toString();
		System.out.println("***** uri: " + uri);
		
		//======================================================================
		//  add Cookies to response Header
		//======================================================================
		Cookie myCookie = new Cookie("testCookies", "ABCDEFFFFFF");
		myCookie.setHttpOnly(true);   //javaScript can not get this cookies from source code
		myCookie.setSecure(false);    //true: browser must send with https instead of http
		myCookie.setMaxAge(24*60*60); //second: 24h* 60m *60s
		myCookie.setPath("/");        //uri: all subdirectory of this path is availabe to send this cookies
//		myCookie.setDomain(".dichly.com");  // sub domain can not use this cookies
		response.addCookie(myCookie);
		
		//======================================================================
		//  add fields to header
		//======================================================================
		// Set refresh, autoload time as 5 seconds    
//		response.setIntHeader("Refresh",5);// “Refresh” attribute trong http response header.	
		
		response.setDateHeader("Expires", System.currentTimeMillis() + 604800000L); // 1 week in future
		response.setHeader("Cache-Control","max-age=3600");
		
		response.setStatus(200);// status Code in Header response
		
		return "please, check response Header and Chrome Dev mode";
	}
}