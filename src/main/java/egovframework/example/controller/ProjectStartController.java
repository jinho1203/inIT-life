package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import egovframework.example.service.ProjectService;
import egovframework.example.service.SourceService;
import egovframework.example.vo.ProjectVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projectStart")
public class ProjectStartController {
	
	private final ProjectService projectService;
	private final SourceService sourceService;
	
	@GetMapping
	public String ShowSourceList(Model model) {
		
		List<ProjectVO> projectList = projectService.getAllList();
//    	List<SourceVO> sourceList = sourceService.getAllList();
    	
    	model.addAttribute("projectList", projectList);
//    	model.addAttribute("sourceList", sourceList);
		
		return "main/projectStart";
	}
}
