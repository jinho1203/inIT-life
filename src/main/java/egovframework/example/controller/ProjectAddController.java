package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.service.ProjectService;
import egovframework.example.vo.ProjectVO;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/projectAdd")
public class ProjectAddController {
	
	private final ProjectService projectService;
	
	@GetMapping
	public String showProjectList(Model model) {
		
		List<ProjectVO> projectList = projectService.getAllList();
		model.addAttribute("projectList", projectList);
		
		return "main/projectAdd";
	}
	
	
}
