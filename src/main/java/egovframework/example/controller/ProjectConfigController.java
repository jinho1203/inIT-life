package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.service.InputService;
import egovframework.example.service.SourceService;
import egovframework.example.vo.InputVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projectConfig")
public class ProjectConfigController {
	
	private final SourceService sourceService;
	private final InputService inputService;
	
	@GetMapping
	public String showConfigList(Model model, 
								@RequestParam(required = false) Long projectId,
								@RequestParam(required = false) Long sourceId) {
	    
	    if (projectId != null) {
	        List<SourceVO> sourceList = sourceService.getAllList(projectId);
	        model.addAttribute("sourceList", sourceList);
	        model.addAttribute("paramProjectId", projectId);
	    } else {
	        model.addAttribute("sourceList", List.of()); // 빈 리스트 처리
	        model.addAttribute("paramProjectId", null);  // null 처리
	    }
	    
	    if(sourceId != null) {
	    	List<InputVO> inputList = inputService.getAllList(sourceId);
	    	model.addAttribute("inputList", inputList);
	    	model.addAttribute("paramSourceId", sourceId);
	    } else {
	    	model.addAttribute("inputList", List.of()); // 빈 리스트 처리
	        model.addAttribute("paramSourceId", null);  // null 처리
	    }

	    return "main/projectConfig";
	}
	
	@PostMapping("/source")
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

	
	/*
	 * inPut 영역
	 */
	
	@PostMapping("/input")
	@ResponseBody
	public String insertInput(@RequestBody InputVO inputVO) {
		
		System.out.println("받은 항목키: " + inputVO.getInputKey());
		System.out.println("받은 입력값: " + inputVO.getInputValue());
		System.out.println("필수 조건 여부: " + inputVO.getReqParam());
		System.out.println("sourceId NUM : " + inputVO.getSourceId());
		
		if(inputVO.getInputKey() == null || inputVO.getInputKey().trim().isEmpty()
				|| inputVO.getInputValue() == null || inputVO.getInputValue().trim().isEmpty()) {
			return "fail";
		}
		
		inputService.insertInput(inputVO);
		
		return "success";
	}
	
	@DeleteMapping
	@ResponseBody
	public String deleteInput(@RequestBody List<Long> inputId) {
		
		for(Long id : inputId) {
			inputService.deleteInput(id);
		}
		return "success";
	}
	
	/*
	 * outPut 영역
	 */
	
//	@PostMapping
//	@ResponseBody
//	public String insertOutput
}
