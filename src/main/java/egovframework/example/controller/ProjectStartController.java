package egovframework.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.service.CustomService;
import egovframework.example.service.InputService;
import egovframework.example.service.OutputService;
import egovframework.example.service.ProjectService;
import egovframework.example.service.SourceService;
import egovframework.example.vo.CustomVO;
import egovframework.example.vo.InputVO;
import egovframework.example.vo.OutputVO;
import egovframework.example.vo.ProjectVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projectStart")
public class ProjectStartController {
	
	private final ProjectService projectService;
	private final SourceService sourceService;
	private final InputService inputService;
	private final OutputService outputService;
	private final CustomService customService;
	/*
	 * 화면만 불러오는거임
	 */
	@GetMapping
	public String ShowSourceList(Model model,
								@RequestParam(required = false) Long projectId,
								@RequestParam(required = false) String projectName,
								@RequestParam(required = false) Long sourceId) {
		
		List<ProjectVO> projectList = projectService.getAllList();
		List<SourceVO> sourceList = sourceService.getAllList(projectId);
    	
    	model.addAttribute("projectList", projectList);
    	model.addAttribute("sourceList", sourceList);

        model.addAttribute("selectedProjectId", projectId);
        model.addAttribute("selectedProjectName", projectName);
        model.addAttribute("selectedSourceId", sourceId);
		return "main/projectStart";
	}
	
	/*
	 * API 목록 projectId로 구분하여 List 출력
	 */
	@GetMapping("/getSourceList")
	@ResponseBody
	public List<SourceVO> getSourceList(@RequestParam Long projectId) {
		
		return sourceService.getAllList(projectId);
	}
	
	@GetMapping("/IOList")
	@ResponseBody
	public Map<String, Object> getInOutList(@RequestParam Long sourceId){
		
		List<InputVO> inputList = inputService.getAllList(sourceId);
		List<OutputVO> outputList = outputService.getAllList(sourceId);
		
		 Map<String, Object> result = new HashMap<>();
		 result.put("inputList", inputList);
		 result.put("outputList", outputList);
		
		return result;
	}
	
	/*
	 * fullUrl 가져와서 팝업창에 URL 띄우기
	 */
	@PostMapping("/customFullUrl")
	@ResponseBody
	public String createCustomFullUrl(@RequestBody CustomVO customVO) {
		
		String customFullUrl = customService.createCustomFullUrl(customVO);
		System.out.println("custom 최종 Url : " + customFullUrl);
		
		return customFullUrl;
	}
}
