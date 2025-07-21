package egovframework.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.service.SourceService;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/source")
@RequiredArgsConstructor
//@Controller		//HTML에 사용시 
@RestController		//POSTMAN 사용시
public class SourceController {
	
	private final SourceService sourceService;
	
	@GetMapping
	public List<SourceVO> getAllList(){
		
		return sourceService.getAllList();
	}
	
	@PostMapping
	public void insertSource(@RequestBody SourceVO sourceVO) {
		
		int result = sourceService.insertSource(sourceVO);
		
		if(result == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}		
	}
	
	@DeleteMapping
	public void deleteSource(@PathVariable Long sourceId) {
		
		int result = sourceService.deleteSource(sourceId);
		
		if(result == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}			
	}
	
	@GetMapping("/{sourceId}")
	public SourceVO findBasicUrl(@PathVariable Long sourceId) {
			
		return sourceService.findBasicUrl(sourceId);
	}
	/*
	@GetMapping("")
	public void updateFullUrl(SourceVO sourceVO) {
		
		sourceService.updateFullUrl(sourceVO);
	}
	*/
}
