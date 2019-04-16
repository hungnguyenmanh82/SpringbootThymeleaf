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
import com.example.demo.model.ERole;
import com.example.demo.model.Person;
import com.example.demo.model.UserRole;



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
	
	@RequestMapping(value = { "/url"}, method = RequestMethod.GET)
	public String url(Model model) {
		System.out.println("/url");
		
		//vd1:
		String url = "https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#link-urls";
		model.addAttribute("urlTest",url); 
		
		//vd2:
		String urlRelative = "usingthymeleaf.html#link-urls";
		model.addAttribute("urlRelative",urlRelative); 
		
		//vd3:
		String urlPart = "tutorials";
		model.addAttribute("tutorials",urlPart);
		
		//vd4: parameter URL
		Person p = new Person("Hung","Nguyen Manh");
		model.addAttribute("person", p); // person là variable trên Model Thymleaf
		
		
		return "guide/url";  
	}
	
	@RequestMapping(value = { "/switch"}, method = RequestMethod.GET)
	public String switchTest(Model model) {
		System.out.println("/switch");
		
		//vd1:
		UserRole user = new UserRole("Hung", ERole.ADMIN, true);
		model.addAttribute("user",user); 
		
		
		return "guide/switch";  
	}
	
	@RequestMapping(value = { "/ifThen"}, method = RequestMethod.GET)
	public String ifThen(Model model) {
		System.out.println("/ifThen");
		
		//vd1:
		UserRole user = new UserRole("Hung", ERole.ADMIN, true);
		model.addAttribute("user",user); 
		
		
		return "guide/ifThen";  
	}
	


	@RequestMapping(value = { "/forEach"}, method = RequestMethod.GET)
	public String showListObjects(Model model) {
		System.out.println("/forEach");
		
		 List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobs"));
		
		model.addAttribute("persons", persons);
		
		return "guide/forEach";  
	}
	
	@RequestMapping(value = { "/cssClass"}, method = RequestMethod.GET)
	public String cssClass(Model model) {
		System.out.println("/cssClass");
		
		UserRole user = new UserRole("Hung", ERole.ADMIN, true);
		model.addAttribute("user",user); 
		return "guide/cssClass";  
	}
}