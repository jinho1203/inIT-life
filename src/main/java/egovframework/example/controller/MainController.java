package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.service.ProjectService;
import egovframework.example.vo.ProjectVO;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
	
	private final ProjectService projectService;
	
    @GetMapping
    public String showMain(Model model) {
    	
    	/*
    	 * url : main 에서 프로젝트 List 출력
    	 */
    	List<ProjectVO> projectList = projectService.getAllList();
    	model.addAttribute("projectList", projectList);
    	
        return "main";  
    }
    
    @PostMapping("/project")
    public String insertProject(Model model, @RequestBody ProjectVO projectVO) {
    	
    	int result = projectService.insertProject(projectVO);
    	model.addAttribute("result", result);
    	
    	return "main";
    }
    
}