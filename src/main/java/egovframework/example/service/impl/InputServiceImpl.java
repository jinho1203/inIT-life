package egovframework.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.InputMapper;
import egovframework.example.service.InputService;
import egovframework.example.vo.InputVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
	
	private final InputMapper inputMapper;
	
	@Override
	public List<InputVO> getAllList(Long sourceId) {
		return inputMapper.getAllList(sourceId);
	}
	
	@Override
	public int insertInput(InputVO inputVO) {
		return inputMapper.insertInput(inputVO);
	}

	@Override
	public int deleteInput(Long inputId) {
		return inputMapper.deleteInput(inputId);
	}

	@Override
	public List<InputVO> findBySourceId(Long sourceId) {
		return inputMapper.findBySourceId(sourceId);
	}
    @Override
    public int updateInputValue(InputVO inputVO) {
        return inputMapper.updateInputValue(inputVO); // 
    }
}
