package com.test.app.restController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.app.entities.User;
import com.test.app.services.UserService;

@RestController // @Controller + @ResponseBody
public class RestMain {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/rest/users/", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAllUser(){
		List<User> users = userService.listUser();
		Map<String, Object> map = new HashMap<String,Object>();
		if(users.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "User NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "STUDENT HAS BEEN FOUNDS");
//		map.put("RESPONSE_DATA", userService.listUser());
		map.put("RESPONSE_DATA", users);
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/rest/users/", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> 
				addNewUser(@RequestBody User user){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(userService.insertUser(user)){
			map.put("MESSAGE","User HAS BEEN INSERTED.");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.CREATED);
		}else{
			map.put("MESSAGE","Use HAS NOT BEEN INSERTED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/rest/users/{id}", method= RequestMethod.DELETE )
	public ResponseEntity<Map<String,Object>> 
				deleteUser(@PathVariable("id") int id){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		if(userService.deleteUser(id)){
			map.put("MESSAGE","User HAS BEEN DELETED.");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		}else{
			map.put("MESSAGE","User HAS NOT BEEN DELETED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/rest/users/{id}", method= RequestMethod.PUT )
	public ResponseEntity<Map<String,Object>> 
				updateUser(@RequestBody User user, @PathVariable("id") int id){
		user.setId(id);
		Map<String, Object> map  = new HashMap<String, Object>();
		if(userService.updateUser(user)){
			map.put("MESSAGE","User HAS BEEN UPDATED.");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		}else{
			map.put("MESSAGE","User HAS NOT BEEN UPDATED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}

}
