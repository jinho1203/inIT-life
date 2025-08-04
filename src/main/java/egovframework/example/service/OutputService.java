package egovframework.example.service;

import java.util.List;

import egovframework.example.vo.OutputVO;

public interface OutputService {
	
	List<OutputVO> getAllList(Long inputId);
	
	int insertOutput(OutputVO outputVO);
	
	
}
