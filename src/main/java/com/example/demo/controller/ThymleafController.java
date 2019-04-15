package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.form.PersonForm;
import com.example.demo.model.Person;



@Controller
public class ThymleafController {
	
	@RequestMapping(value = { "/thymleafGuide"}, method = RequestMethod.GET)
	public String guide(Model model) {
		System.out.println("/thymleafGuide");
		
		return "guide/thymleafGuide";  // trả về guide/thymleafGuide.html
	}
	
	@RequestMapping(value = { "/variable"}, method = RequestMethod.GET)
	public String variable(Model model) {
		System.out.println("/variable");
		
		String msg = "I did really well today, hihi";
		model.addAttribute("message", msg); // message là variable trên Model Thymleaf
		//
		Person p = new Person("Hung","Nguyen Manh");
		model.addAttribute("person", p); // person là variable trên Model Thymleaf
		
		return "guide/variable";  
	}

	@RequestMapping(value = { "/basicObject"}, method = RequestMethod.GET)
	public String basicObject(Model model) {
		System.out.println("/basicObject");
		
		return "guide/basicObject";  
	}

	@RequestMapping(value = { "/utilityObject"}, method = RequestMethod.GET)
	public String utilityObject(Model model) {
		System.out.println("/utilityObject");
		
		return "guide/utilityObject";  
	}
}