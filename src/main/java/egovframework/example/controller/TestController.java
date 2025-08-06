package egovframework.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
	
@Controller
public class TestController {
	@GetMapping("error")
	public String hello() {
		return "error";
	}
	
	
}
