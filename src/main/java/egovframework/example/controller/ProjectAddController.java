package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping
	@ResponseBody
	public String insertProject(@RequestBody ProjectVO projectVO) {
        
		System.out.println("받은 프로젝트명: " + projectVO.getProjectName());
        System.out.println("받은 설명: " + projectVO.getProjectExplain());
        if (projectVO.getProjectName() == null || projectVO.getProjectName().trim().isEmpty()) {
            return "fail";
        }
        
        projectService.insertProject(projectVO);
        
        return "success";
	}
	
	@DeleteMapping
	@ResponseBody
	public String deleteProject(@RequestBody List<Long> projectId) {
    	
		for(Long id : projectId) {
    		projectService.deleteProject(id);
    	}
    	
    	return "success";
	}
}
