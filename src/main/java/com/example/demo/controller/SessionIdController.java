package com.example.demo.controller;


import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * Springboot security sử dụng HttpSession của Servlet => trên các @controller thì SessionId lấy từ cookies đều giống nhau (đã test)
 *
 */
@Controller
public class SessionIdController {
  
    //================================ test: GET and POST ======================================   
    @RequestMapping(path = "/getSessionId", method = RequestMethod.GET)
    @ResponseBody    //de tra ve kieu String
    public String testGetRequest(HttpSession session){
    	
    	String sessionId = "SessionId = " + session.getId();

        return sessionId;
    }
}
