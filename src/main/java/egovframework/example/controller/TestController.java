package egovframework.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import egovframework.example.mapper.TestMapper;
import egovframework.example.vo.TestVO;

@Controller
public class TestController {
	private final TestMapper testMapper;
	
	public TestController (TestMapper testMapper) {
		this.testMapper = testMapper;
	}
	
//	@GetMapping("/test")
//	public List<TestVO> getData() {
//	    return testMapper.findAll();
//	}
	
	@GetMapping("/test")
	public String hello() {
		return "test";
	}
	
	
}
