package com.proyecto.proyecto;



import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.servicios.UserService;
import com.proyecto.proyecto.User;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	public static String md5(String input) {
        
        String md5 = null;
      
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	
		
	@RequestMapping("/")
	public ModelAndView irformulario(){
		ModelAndView MV = new ModelAndView("home","user",new User());
		return MV;
	}
	
	@RequestMapping(value = "/insert2", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity inserData2(@RequestParam("nombre") String nombre, @RequestParam("password") String password) {
		
		try{
		 	
			Random random = new SecureRandom();
		 	String token =md5(Integer.toString(random.nextInt()));
		
			User user = new User();
			user.setNombre(nombre);
			user.setCode(token);
			user.setStatus(1);
			user.setPassword(md5(password));
			
			userService.insertData(user);
			String respuesta ="{\"acces_code\": \""+token+"\"}";
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			String respuesta = "{\"acces_code\": \"Error\"}";
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/update3", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity update(@RequestParam("nombre") String nombre, @RequestParam("password") String password, @RequestParam("acces_token") String token ) {
		
		try{
			User user = new User();
			user = userService.getUser(nombre, md5(password), token);
			String respuesta ="{\"acces_code\": \""+user.getCode()+"\"}";
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			String respuesta = "{\"acces_code\": "+e+"}";
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
		}
		
		
	}	

	@RequestMapping("/update")
	public ModelAndView updateUser(@ModelAttribute User user) {
		userService.updateData(user);
		List<User> userList = userService.getUserList(user.getNombre());
		return new ModelAndView("exito", "userList", userList);
	}
	
	/*@RequestMapping(value = "/update2", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity update2(@RequestParam("nombre") String nombre,@RequestParam("code") String code) {
		User user = new User();
		user.setNombre(nombre);
		user.setCode(code);
		userService.insertData(user);
		
		String respuesta = "{\"acces_code\": "+user.getCode()+" }";
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
	}
	*/
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity lista(@RequestParam("nombre") String nombre, @RequestParam("password") String password, @RequestParam("acces_token") String token ) {
		
		try{
			User user = new User();
			user = userService.getUser(nombre, md5(password), token);
			String respuesta ="{\"acces_code\": \""+user.getCode()+"\"}";
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			String respuesta = "{\"acces_code\": "+e+"}";
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity(respuesta, responseHeaders, HttpStatus.CREATED);
		}
		
		
	}	
	
	
	
	
}
