package egovframework.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.OutputMapper;
import egovframework.example.service.OutputService;
import egovframework.example.vo.OutputVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class OutputServiceImpl implements OutputService{
	
	private final OutputMapper outputMapper;
	
	@Override
	public List<OutputVO> getAllList(Long inputId) {
		return outputMapper.getAllList(inputId);
	}

	@Override
	public int insertOutput(OutputVO outputVO) {
		return outputMapper.insertOutput(outputVO);
	}

}
