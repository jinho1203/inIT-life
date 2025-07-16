package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.mapper.ProjectMapper;
import egovframework.example.service.ProjectService;
import egovframework.example.vo.ProjectVO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/project")
@RequiredArgsConstructor
//@Controller		//HTML에 사용시 
@RestController		//POSTMAN 사용시
public class ProjectController {
	
	private final ProjectService projectService;
	
	@GetMapping
	public List<ProjectVO> getAllList(){
		
		return projectService.getAllList();
	}
	
	@PostMapping
	public void insertProject(@RequestBody ProjectVO projectVO) {
		
		int result = projectService.insertProject(projectVO);
		
		if(result == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
	}
	
	@DeleteMapping("/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {
		
		int result = projectService.deleteProject(projectId);
		
		if(result == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}
}
