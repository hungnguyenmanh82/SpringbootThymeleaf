package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Truy cập vào private folder.
 *
 */
@Controller
public class DownloadPrivateController {

	/**
	 * ko chưa content-type  trong Response header
	 */
	@RequestMapping(path = "/image/smile", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage() throws IOException {
		System.out.println("/image/smile");
		
		InputStream in = getClass().getResourceAsStream("/file/smile.jpg"); //  WEB-INF/classes/file/smile  
		return IOUtils.toByteArray(in);
	}

	/**
	 * content-type = MediaType.IMAGE_JPEG_VALUE: ở header của Response 
	 */
	@RequestMapping(path = "/image/smile.jpg", 
						method = RequestMethod.GET,
						produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType() throws IOException {
		
		System.out.println("/image/smile.jpg");
		InputStream in = getClass().getResourceAsStream("/file/smile.jpg");
		return IOUtils.toByteArray(in);
	}

	
	/**
	 * content-type: application/octect-stream  
	 */
	@RequestMapping(path = "/file/file.txt", 
						method = RequestMethod.GET,
						produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getFile() throws IOException {
		System.out.println("/file/file.txt");
		
		InputStream in = getClass().getResourceAsStream("/file/testReadfile.txt");
		return IOUtils.toByteArray(in);
	}

}