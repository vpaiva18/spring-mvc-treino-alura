package br.com.alura.mvc.mudi.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("nome", "Mundo");
		
		
		return "hello";
	}
	
}
