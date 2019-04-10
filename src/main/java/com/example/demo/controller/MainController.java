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
 
        model.addAttribute("message", message);
 
        return "index";  // trả về templates/index.html
    }
 
    // use @ResponseBody return String instead of template file from Thymeleaf
    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {
 
        model.addAttribute("persons", persons);
 
        return "personList"; // trả về templates/personList.html
        // use @ResponseBody return String instead of template file from Thymeleaf
    }
 
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
 
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
 
        return "addPerson"; // trả về templates/addPerson.html
    }
 
    
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
            @ModelAttribute("personForm") PersonForm personForm) {
 
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
    @ResponseBody    //de tra ve kieu String
    public String testGetRequest(){
        return "test: Get request tra ve String";
    }
 
    /**
     * form nay de tao POST request voi url = "/post"
     */
    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public String showFormForPost(){
        return "form";
    }
    
    /**
     * body request ko thể chứa ký tự đặc biệt của http
     */
    @RequestMapping(path = "/post", method = RequestMethod.POST)
    @ResponseBody    //de tra ve kieu String
    public String testPostRequest(@RequestBody String request){

        return "body of Post request:\n"+ request;
    }
    
    
    /**
     * form nay de tao POST request voi url = "/post"
     */
    @RequestMapping(path = "/formBinary", method = RequestMethod.GET)
    public String showFormForPostBinary(){
        return "formBinary";
    }
    
}