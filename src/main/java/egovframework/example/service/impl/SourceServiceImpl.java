package egovframework.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.InputMapper;
import egovframework.example.mapper.SourceMapper;
import egovframework.example.service.SourceService;
import egovframework.example.vo.InputVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SourceServiceImpl implements SourceService {
	
	private final SourceMapper sourceMapper;
	private final InputMapper inputMapper;
	
	@Override
	public List<SourceVO> getAllList() {
		return sourceMapper.getAllList();
	}

	@Override
	public int insertSource(SourceVO sourceVO) {
		return sourceMapper.insertSource(sourceVO);
	}

	@Override
	public int deleteSource(Long sourceId) {
		return sourceMapper.deleteSource(sourceId);
	}

	@Override
	public SourceVO findBasicUrl(Long sourceId) {
		return sourceMapper.findBasicUrl(sourceId);
	}
	
	/*
	 * input 테이블에서 sourceId를 이용하여 파라미터 값을 가져온다
	 */
	@Override
	public void updateFullUrl(SourceVO sourceVO) {
		
		Long sourceId = sourceVO.getSourceId();
		
		SourceVO baseUrl = sourceMapper.findBasicUrl(sourceId);
		
		List<InputVO> inputList = inputMapper.findBySourceId(sourceId);
		
		StringBuilder queryParam = new StringBuilder();
		for(InputVO inputVO : inputList) {
			if (queryParam.length() > 0)
				queryParam.append("&");
			
			queryParam.append(inputVO.getInputKey())
					.append("=")
					.append(inputVO.getInputValue());
		}
		
		// 메인URL + 파라미터 합친 최종 URL
		String fullUrl = baseUrl + "?" + queryParam;
				
		sourceVO.setFullUrl(fullUrl);
		sourceMapper.updateFullUrl(sourceVO);
	}

}
