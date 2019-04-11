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
public class BinaryBodyController {

	/**
	 * body ban chat la binary
	 * Connect = keep-alive
	 * content-lenght: 20
	 * Phan content-type: application/octect-stream   => dùng html form ko dc. Phai dung ARC plugin của google hoặc Từ javaSource moi set dc content-type 
	 * neu html form type= text  => thì inputstream se tra ve la empty
	 * html form type sẽ phải quy đổi ra các content-type chuẩn của http protocol
	 */
	@RequestMapping(path = "/postBinary", method = RequestMethod.POST)
	//@ResponseBody String: de tra ve body of response kieu String
	//@ResponseBody byte[]: de tra ve body of response kieu byte[]
	public @ResponseBody String acceptData(InputStream dataStream) throws IOException{

		//read data from inputStream
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];

		// -1: inputStream da doc het inputstream (= ket thuc của post request)
		while ((nRead = dataStream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		//        buffer.flush();
		byte[] byteArray = buffer.toByteArray();

		return new String(byteArray);
	}
}