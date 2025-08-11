package egovframework.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller 
public class ApistartController {
@GetMapping("apistart")
public String apiStart() {
	return "/main/apistart";
	}
}

