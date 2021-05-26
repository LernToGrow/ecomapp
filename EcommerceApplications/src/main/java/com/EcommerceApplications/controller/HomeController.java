package com.EcommerceApplications.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EcommerceApplications.dao.UserRepository;
import com.EcommerceApplications.entites.User;
import com.EcommerceApplications.helper.Message;

@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userrepository;
	

	@RequestMapping("/")
	public String home(Model m)
	{
		m.addAttribute("title","Home-E-commerce");
		return "home";
	}
	@RequestMapping("/about")
	public String about(Model m)
	{
		m.addAttribute("title","About-E-commerce");
		return "about";
	}
	@RequestMapping("/register")
	public String register(Model m)
	{
		m.addAttribute("title","Register-E-commerce");
		m.addAttribute("user",new User());
		return "register";
	}
	
	//handerler for register user
	@RequestMapping(value="do_register",method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user")User user,BindingResult result1,@RequestParam(value="agreement",defaultValue="false")boolean agreement,Model m,HttpSession session)
	{
		try {
			if(!agreement)
			{
				System.out.println("you have not agreed the terms and condition");
				throw new Exception("you have not agreed the terms and condition");
			}
			if(result1.hasErrors())
			{
				m.addAttribute("user",user);
				return "register";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("Default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("agreement"+agreement);
			System.out.println("User"+user);
			User result = this.userrepository.save(user);
			m.addAttribute("user",new User());
			session.setAttribute("message", new Message("Sucessfully Registred!!","alert-success"));
			return "register";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong!!"+e.getMessage(),"alert-danger"));
			return "register";
		}
		
	}
	
	///hander for login user
	@GetMapping("/signin")
	public String customLogin(Model m)
	{
		m.addAttribute("title","Login page"); 
		return "login";
	}
}
