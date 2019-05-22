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
public class MainController {

	private static List<Person> persons = new ArrayList<Person>();

	static {
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobs"));
	}

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;          //biến này dùng trực tiếp ở *.html

	@Value("${error.message}")    // Inject via application.properties
	private String errorMessage;   //biến này dùng trực tiếp ở *.html

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		System.out.println("/");
		model.addAttribute("message", message);

		return "index";  // trả về templates/index.html
	}

	// use @ResponseBody return String instead of template file from Thymeleaf
	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {
		System.out.println("/personList");
		
		model.addAttribute("persons", persons);

		return "personList"; // trả về templates/personList.html
		// use @ResponseBody return String instead of template file from Thymeleaf
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {
		System.out.println("/addPerson");
		
		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "addPerson"; // trả về templates/addPerson.html
	}


	/**
	 * @modelAtribute("personForm") PersonForm personForm:  
	 * Springboot sẽ lấy dữ liệu từ Post Request (html form) ghi vào PersonForm và
	 *  save ở Model với attribute name = "personForm"
	 */
	@RequestMapping(value = { "/createPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {
		
		System.out.println("/createPerson");
		
		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();

		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);

			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

	//================================ test: GET and POST ======================================   
	@RequestMapping(path = "/get", method = RequestMethod.GET)
	//de tra ve kieu String
	public @ResponseBody  String testGetRequest(){
		System.out.println("/get");
		
		return "test: Get request tra ve String";
	}

	/**
	 * form nay de tao POST request voi url = "/post"
	 */
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String showFormForPost(){
		System.out.println("/form");
		
		return "form";   // trả về templates/form.html
	}

	/**
	 * body request ko thể chứa ký tự đặc biệt của http
	 */
	@RequestMapping(path = "/post", method = RequestMethod.POST)
	@ResponseBody    //de tra ve kieu String
	public String testPostRequest(@RequestBody String request){
		System.out.println("/post");
		
		return "body of Post request:\n"+ request;
	}


	/**
	 * form nay de tao POST request voi url = "/post"
	 */
	@RequestMapping(path = "/formBinary", method = RequestMethod.GET)
	public String showFormForPostBinary(){
		System.out.println("/formBinary");
		return "formBinary";
	}

}