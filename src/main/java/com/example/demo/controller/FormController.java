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

import com.example.demo.form.LoginForm;
import com.example.demo.form.PersonForm;
import com.example.demo.model.Person;


/**
 * Spingboot sẽ lấy data từ body of Form post request "/loginForm"=> mapping LoginForm.class
 *
 */
@Controller
public class FormController {
	
	
    /**
     * form nay de tao POST request voi url = "/post"
     */
    @RequestMapping(path = "/loginForm", method = RequestMethod.GET)
    public String showFormForPost(){
    	System.out.println("/loginForm");
    	
        return "loginForm";   // trả về templates/loginForm.html
    }
    
    /**
     * @modelAtribute("personForm") PersonForm personForm:  
     * Springboot sẽ lấy dữ liệu từ Post Request (html form) ghi vào PersonForm và
     *  save ở Model với attribute name = "personForm"
     */
    @RequestMapping(value = { "/logintest" }, method = RequestMethod.POST)
    public @ResponseBody String savePerson(@ModelAttribute("loginForm") LoginForm loginForm) {
    	
    	System.out.println("/logintest");
    	
        String userName = loginForm.getUserName();
        String passWord = loginForm.getPassword();

        return "userName:"+ userName +"   ,password:"+passWord;
    }
   
}