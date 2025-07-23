package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.service.ProjectService;
import egovframework.example.service.SourceService;
import egovframework.example.vo.ProjectVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
	
	private final ProjectService projectService;
	private final SourceService sourceService;
	
    @GetMapping
    public String showMain(Model model) {
    	
    	/*
    	 * url : main 에서 프로젝트 List 출력
    	 * Project / Source
    	 */
    	List<ProjectVO> projectList = projectService.getAllList();
    	List<SourceVO> sourceList = sourceService.getAllList();
    	
    	model.addAttribute("projectList", projectList);
    	model.addAttribute("sourceList", sourceList);
    	
        return "main";  
    }
    /*
     * Project 분류 및 등록
     */
    @PostMapping("/project")
    @ResponseBody
    public String insertProject(@RequestBody ProjectVO projectVO) {
        
        if (projectVO.getProjectName() == null || projectVO.getProjectName().trim().isEmpty()) {
             return "fail";
         }
        
        int result = projectService.insertProject(projectVO);
        
        return result > 0 ? "success" : "fail";
     }
    
    
    @PostMapping("/project/delete")
    @ResponseBody
    public String deleteProject(@RequestBody List<Long> projectId) {
    	for(Long id : projectId) {
    		projectService.deleteProject(id);
    	}
    	
    	return "success";
    }
    
    
}
