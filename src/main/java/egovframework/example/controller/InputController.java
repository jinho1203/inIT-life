package egovframework.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.service.InputService;
import egovframework.example.vo.InputVO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/input")
@RequiredArgsConstructor
//@Controller		//HTML에 사용시 
@RestController		//POSTMAN 사용시
public class InputController {
	
	private final InputService inputService;
	
//	@GetMapping
//	public List<InputVO> getAllList(){
//		
//		return inputService.getAllList();
//	}
	
	@PostMapping
	public void insertInput(@RequestBody InputVO inputVO) {
		
		int result = inputService.insertInput(inputVO);
		
		if(result == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
	}
	
	@DeleteMapping("/{inputId}")
	public void deleteInput(@PathVariable Long inputId) {
		
		int result = inputService.deleteInput(inputId);
		
		if(result == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}
	
	@GetMapping("/{sourceId}")
	public List<InputVO> findBySourceId(@PathVariable Long sourceId) {
		
		return inputService.findBySourceId(sourceId);
	}
}
