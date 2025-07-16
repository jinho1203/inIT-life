package egovframework.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.example.service.SourceService;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SourceServiceImpl implements SourceService {
	
	private SourceService sourceService;
	
	@Override
	public List<SourceVO> getAllList() {
		return sourceService.getAllList();
	}

	@Override
	public int insertSource(SourceVO sourceVO) {
		return sourceService.insertSource(sourceVO);
	}

	@Override
	public int deleteSource(Long sourceId) {
		return sourceService.deleteSource(sourceId);
	}

}
