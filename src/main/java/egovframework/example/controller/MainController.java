package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String insertProject(@RequestBody ProjectVO projectVO) {
    	
    	int result = projectService.insertProject(projectVO);
    	
    	return result > 0 ? "success" : "fail";
    }
    
    @DeleteMapping("/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
    	
    	int result = projectService.deleteProject(projectId);
    	
    	return result > 0 ? "success" : "fail";
    }
    
}