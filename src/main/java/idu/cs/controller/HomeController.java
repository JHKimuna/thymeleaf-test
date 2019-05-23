package idu.cs.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
		return "index";
	}
	
	@GetMapping("/")
	public String loadWelcome(Model model) {
		return "welcome";
	}
	
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}
	
	@GetMapping("/users/{id}")
	public String getUserssById(@PathVariable(value="id") Long userId, 
			Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found" + userId));
		model.addAttribute("user", user);
		/*
		model.addAttribute("id", ""+userId);
		model.addAttribute("name", ""+user.getName());
		model.addAttribute("company", ""+user.getCompany());
		*/
		return "user";
	}
	
	@GetMapping("/regform")
	public String loadRegForm(Model model) {
		return "regform";
	}
	
	@PostMapping("/users")
	public String createUser(@Valid /*@RequestBody*/ User user, Model model) {
		// @RequestBody는 main.js를 사용하는 방법.
		userRepo.save(user);
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
	
	@PutMapping("/users/{id}")
	// @RequestMapping(value=""/users/{id}" method=RequestMethod.DELETE 와 같다.
	public String updateUserById(@PathVariable(value="id") Long userId, 
			@Valid /*@RequestBody*/ User userDetails, Model model) throws ResourceNotFoundException {
		// userDetails 폼을 통해 전송된 객체, user는 id로 jpa를 통해서 가져온 객체
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found" + userId) );
		user.setName(userDetails.getName());
		user.setCompany(userDetails.getCompany());
		User userUpdate = userRepo.save(user); // 객체 삭제 -> jpa : record 삭제로 적용
		// model.addAttribute("user", userUpdate);
		return "redirect:/users"; // 업데이트가 성공하면 users 자원을 get방식으로 접근하되 
		// model에 user어트리뷰트를 전달한다.
	}
	
	
	@DeleteMapping("/users/{id}")
	// @RequestMapping(value=""/users/{id}" method=RequestMethod.DELETE 와 같다.
	public String deleteUsersById(@PathVariable(value="id") Long userId, 
			Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found" + userId) );
		userRepo.delete(user); // 객체 삭제 -> jpa : record 삭제로 적용
		model.addAttribute("name", user.getName());
		return "disjoin";
	}
	/* Ajax 사용시 사용이라고 추측.
	@GetMapping("/disjoin")
	public String disjoinForm(Model model) {
		return "disjoin";
	}
	*/
	
}
