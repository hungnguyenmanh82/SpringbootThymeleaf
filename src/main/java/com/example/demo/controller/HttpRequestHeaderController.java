package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class HttpRequestHeaderController {

	/**
	 * 
	 */
	@RequestMapping(value = "/requestHeader", method = RequestMethod.GET)
	// @ResponseBody void: ko trả ve giá trị => dùng HttpServletResponse để trả về giá trị
	public @ResponseBody Map<String, String> generateReport(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		String uri = ((HttpServletRequest)request).getRequestURI().toString();
		String remoteAddr = request.getRemoteAddr();
		System.out.println("***** uri: " + uri);
		System.out.println("***** remoteAddress: " + remoteAddr);
		
		//=============================================================
		Map<String, String> map = new HashMap<String, String>();
		
		// là interface cho kiểu link list 1 chieu
		Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;

	}
}