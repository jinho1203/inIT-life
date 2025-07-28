package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.service.InputService;
import egovframework.example.service.ProjectService;
import egovframework.example.service.SourceService;
import egovframework.example.vo.ProjectVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projectConfig")
public class ProjectConfigController {
	
	private final ProjectService projectService;
	private final SourceService sourceService;
	private final InputService inputService;
	
	@GetMapping
	public String ShowSourceList(Model model) {
		
		List<SourceVO> sourceList = sourceService.getAllList();
		model.addAttribute("sourceList", sourceList);
		
		return "main/projectConfig";
	}
}
