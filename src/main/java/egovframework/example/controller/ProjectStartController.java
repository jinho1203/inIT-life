package egovframework.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.service.InputService;
import egovframework.example.service.ProjectService;
import egovframework.example.service.SourceService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projectStart")
public class ProjectStartController {
	
	@GetMapping
	public String ShowSourceList() {
		
		return "main/projectStart";
	}
}
