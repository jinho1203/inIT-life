package com.example.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mapper.TestMapper;
import com.example.vo.TestVO;

@org.springframework.stereotype.Controller
public class TestController {
	private final TestMapper testMapper;
	
	public TestController (TestMapper testMapper) {
		this.testMapper = testMapper;
	}
	
	@GetMapping("/test")
	public String getData(Model model) {
	    List<TestVO> list = testMapper.findAll();
	    model.addAttribute("testList", list);
	    return "test";  // 이 값이 test.jsp와 매칭되어야 합니다.
	}
}
