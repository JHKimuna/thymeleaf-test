package idu.cs.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired UserRepository userRepo;
	// DI(Dependency Injection)
	// IoC(Inversion of Control) 
	// github 사용하기 : backlog.com/git-tutorial/kr
	// team 메뉴 사용.
	// share - github 사이트에서 repository 추가 - add to index - commit -
	@GetMapping("/test")
	public String home(Model model) {
		model.addAttribute("test", "인덕컴소");
		model.addAttribute("kjh", "김준하");
		System.out.println("ouwouw");
		return "index";
	}
	
	@GetMapping("/")
	public String loadWelcome(Model model) {
		System.out.println("gas");
		return "welcome";
	}
	
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		System.out.println("nnn");
		return "userlist";
	}
	
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value="id") Long userId, 
			Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).get();
		model.addAttribute("id", ""+userId);
		model.addAttribute("name", ""+user.getName());
		model.addAttribute("company", ""+user.getCompany());
		System.out.println("ddd");
		//Optional<User> user = userRepo.findById(userId);
		return "user";
	}
	
	@GetMapping("/regform")
	public String loadRegForm(Model model) {
		model.addAttribute("users", userRepo.findAll());
		System.out.println("ggg");
		return "regform";
	}
	
	@PostMapping("/create")
	public String CreateUser(@Valid @RequestBody User user, Model model) {
		userRepo.save(user);
		
		System.out.println("aaaaaa");
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
	
}
