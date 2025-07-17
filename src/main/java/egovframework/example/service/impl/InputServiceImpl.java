package egovframework.example.service.impl;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.InputMapper;
import egovframework.example.mapper.ProjectMapper;
import egovframework.example.service.InputService;
import egovframework.example.vo.InputVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
	
	private final InputMapper inputMapper;
	
	@Override
	public int insertInput(InputVO inputVO) {
		return inputMapper.insertInput(inputVO);
	}
	

}
