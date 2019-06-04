package idu.cs.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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
import idu.cs.entity.UserEntity;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;
import idu.cs.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.repository.query.Param;

@Controller
public class QuestionController {
	@Autowired UserService questionService;
	
}