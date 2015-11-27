package com.test.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.app.entities.User;
import com.test.app.services.UserService;
import com.test.app.services.impl.Images;
import com.test.app.services.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.validation.Valid;

@Controller
//@RequestMapping(value={"/pages"}) //match url with /pages
public class MainController implements ServletContextAware {
	
	
	
	private UserServiceImpl userService;	
	private ServletContext servletContext;
	//it will look for @bean for the interface return after configuration scan libraries
	
	@Autowired //@Autowired is always specify
//	@Qualifier("ssi") //specify Qulifier for multiple return StudentService, if inject via constructor and setter
	UserService stuservice;
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;		 
	}
	
	@RequestMapping(value={"/","/list"})
	public ModelAndView listuser(){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/listUsr");
		mav.addObject("users", stuservice.listUser());	
		
		return mav;
	}
	
	@RequestMapping(value="/add")
	public String insertData(){
		
		return "/add";
	}
	
	
//	
//	@RequestMapping(value="/add", method = RequestMethod.POST)
//	public ModelAndView insertData(@ModelAttribute("stu11") User usr){				
//		
//		stuservice.insertUser(usr);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("redirect:/list"); //redirect to /list url
//		mav.addObject("users", stuservice.listUser());	
//		
//		return mav;
//	}
	
	@RequestMapping(value="/delete-{id}") //declare variable id that contain id of student	
	public ModelAndView deleteData(@PathVariable("id") int id){ //to get variable id from url above
//		@RequestParam // is use with control name in a form
				
		if(!stuservice.deleteUser(id))			
			return new ModelAndView("redirect:/list","message","Unsuccessful delete");
		
		return new ModelAndView("redirect:/list","message","success");		
	}	
	
	@RequestMapping(value="update-{id}")
	public ModelAndView updateData(@PathVariable("id") int uID){		
		
		ModelAndView mav = new ModelAndView("/update","usr",new UserServiceImpl().getUser(uID));
		return mav;
	}
	
	@RequestMapping(value="update-{id}", method = RequestMethod.POST)
	public String updateData(@PathVariable("id") int uID, @ModelAttribute("usr") User usr){		
				
		usr.setId(uID);
		System.out.println(usr.getId());		
		new UserServiceImpl().updateUser(usr);
		return "redirect:/";
	}	
	
	@RequestMapping(value="view-{id}")
	public ModelAndView viewData(@PathVariable("id") int uID){
				
		return new ModelAndView("viewUsr","user",new UserServiceImpl().getUser(uID));
	}
	
	
	
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPersonFromForm(@Valid User user,
	BindingResult bindingResult,
	@RequestParam(value = "img", required = false) MultipartFile image) {
	 
	String oldName = image.getOriginalFilename();
	String extension;
	String titleWithExtension;
	boolean saveImage = false;
	
	extension = oldName.substring(oldName.lastIndexOf("."));
	System.out.println(extension);
	
	titleWithExtension = user.getUserName() + extension;	
	user.setImg(titleWithExtension);	
	saveImage = new Images().addImage(titleWithExtension, bindingResult, image, "WEB-INF/pages/images/",
			servletContext);
		
	if (!saveImage) {
	
		return "redirect:/add";			
	}
	
	new UserServiceImpl().insertUser(user);
	return "redirect:/";
	}
}
