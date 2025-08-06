package egovframework.example.service;

import java.util.List;

import egovframework.example.vo.OutputVO;

public interface OutputService {
	
//	List<OutputVO> getAllList(Long inputId);
	List<OutputVO> getAllList(Long sourceId);
	
	int insertOutput(OutputVO outputVO);
	
	int deleteOutput(Long outputId);
	
	List<OutputVO> findBySourceId(Long sourceId);
}
