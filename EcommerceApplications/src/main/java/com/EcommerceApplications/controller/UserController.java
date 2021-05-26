package com.EcommerceApplications.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.EcommerceApplications.dao.CategoryRepository;
import com.EcommerceApplications.dao.UserRepository;
import com.EcommerceApplications.dao.productRepository;
import com.EcommerceApplications.entites.Category;
import com.EcommerceApplications.entites.Product;
import com.EcommerceApplications.entites.User;
import com.EcommerceApplications.helper.Message;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private productRepository productrepository;
	
	private Product newproduct =new Product() ;
	
	@ModelAttribute
	public void addCommonData(Model model,Principal principal)
	{
		String username = principal.getName();
		System.out.println("User Name "+username);
		
		User user = userRepository.getUserByUserName(username);
		System.out.println("UserName"+user);
		model.addAttribute("user",user);
	}
	//index page 
	@RequestMapping("/index")
	public String Dashbord(Model model,Principal principal)
	{	
		model.addAttribute("title","User Dashbord");
		return "normal/user_dashbord";
	}
	
	
	//open add from handler
	@GetMapping("/add-product")
	public String openAddProductForm(Model model)
	{
		model.addAttribute("title","Add Product");
		model.addAttribute("product",new Product()); 
		List<Category> clist=categoryRepository.findAll();
		model.addAttribute("category",clist);
		return "normal/add_product_from";
	}
	
	//open add from handler
	@GetMapping("/add-Category")
	public String openAddCategoryForm(Model model)
	{
		model.addAttribute("title","Add Category");
		model.addAttribute("category",new Category()); 
		return "normal/add_Category_from";
	}
	
	// to store product
	@PostMapping("/process-product")
	public String processProduct(Model m,
			@ModelAttribute Product product,
		/*	@RequestParam("pphoto")MultipartFile file,*/
			Principal principal,
			HttpSession session)
	{
		
			try {
				String name=principal.getName();
				List<Category> clist=categoryRepository.findAll();
				m.addAttribute("category",clist);
				/*User user = this.userRepository.getUserByUserName(name);
				this.userRepository.save(user);*/
//				if(file.isEmpty())
//				{
//					System.out.println("file is empty");
//		
//				}
//				else
//				{
//					product.setPphoto(file.getOriginalFilename());
//					File savefile = new ClassPathResource("static/img").getFile();
//					Path path = Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
//					Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
//					System.out.println("Image is uploaded");
//				}
				
				session.setAttribute("message",new Message("Product added Sucessfully add new one","success"));
				this.productrepository.save(product);
				System.out.println("data"+product);		
				
			} catch (Exception e) {
				session.setAttribute("message",new Message("Something went wrong try again","danger	"));
				System.out.println("error"+e.getMessage());
				e.printStackTrace();
			}
			return "normal/add_product_from";
	}
	
	// to store category
	@PostMapping("/process-category")
	public String processCategory(@ModelAttribute Category category,Principal principal,HttpSession session)
	{
		
/*		String name=principal.getName();
		User user = this.userRepository.getUserByUserName(name);*/
		try {
			this.categoryRepository.save(category);
			System.out.println("data"+category);	
			session.setAttribute("message",new Message("Category added Sucessfully add new one","success"));
		} catch (Exception e) {
			session.setAttribute("message",new Message("Something went wrong try again","danger	"));
			e.printStackTrace();
		}	
		return "normal/add_category_from";
	}
	
	//to display product
	@GetMapping("show-product")
	public String showproduct(Model m,@ModelAttribute Product p) {
		m.addAttribute("title","Show user Contact");
		List<Product> products=this.productrepository.findAll();
		//Category c=new Category();
		//PageRequest of = PageRequest.of(page, 5);
//		List<Product> products1=this.productrepository.findProductsByCategory();
		m.addAttribute("AllProducts",products);
		return "normal/show_products";
	}
	
	
	//delete product by id
	@GetMapping("/delete/{pid}")
	public String deleteProduct(@PathVariable("pid") Integer pid,Model m,HttpSession session)
	{
		
		//newproduct.setCategory(null);
		this.productrepository.deleteById(pid);
		System.out.println("deleted ");
		session.setAttribute("message",new Message("product deleted Successfully","success"));
		return "redirect://normal/show_products/0";
	}
	
}
