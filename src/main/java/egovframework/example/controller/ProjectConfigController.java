package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.service.SourceService;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projectConfig")
public class ProjectConfigController {
	
	private final SourceService sourceService;
	
	@GetMapping
	public String ShowSourceList(@RequestParam Long projectId, Model model) {
		
		List<SourceVO> sourceList = sourceService.getAllList(projectId);
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("paramProjectId", projectId);
		
		return "main/projectConfig";
	}
	
	@PostMapping
	@ResponseBody
	public String insertSource(@RequestBody SourceVO sourceVO) {
		
		System.out.println("받은 소스명: " + sourceVO.getSourceName());
		System.out.println("받은 URL: " + sourceVO.getBaseUrl());
		System.out.println("받은 프로젝트 ID: " + sourceVO.getProjectId());
		if(sourceVO.getSourceName() == null || sourceVO.getSourceName().trim().isEmpty() 
				|| sourceVO.getBaseUrl() == null || sourceVO.getBaseUrl().trim().isEmpty()
				|| sourceVO.getProjectId() == 0) {
			return "fail";
		}
		
		sourceService.insertSource(sourceVO);
			
		return "success";
	}
	
}
