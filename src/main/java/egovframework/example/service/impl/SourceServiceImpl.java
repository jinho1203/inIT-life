package egovframework.example.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.InputMapper;
import egovframework.example.mapper.OutputMapper;
import egovframework.example.mapper.SourceMapper;
import egovframework.example.service.SourceService;
import egovframework.example.vo.InputVO;
import egovframework.example.vo.OutputVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SourceServiceImpl implements SourceService {
	
	private final SourceMapper sourceMapper;
	private final InputMapper inputMapper;
	private final OutputMapper outputMapper;
	
	@Override
	public List<SourceVO> getAllList(Long projectId) {
		return sourceMapper.getAllList(projectId);
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
	 * baseUrl + input/output Param = fullUrl
	 */
	@Override
	public String createFullUrl(SourceVO sourceVO) {
		Long sourceId = sourceVO.getSourceId();
		String baseUrl = sourceVO.getBaseUrl();
		
		// input , output 가져오기
		List<InputVO> inputList = inputMapper.findBySourceId(sourceId);
		List<OutputVO> outputList = outputMapper.findBySourceId(sourceId);
		
		// key 기준으로 value들을 합치기 위한 Map
		Map<String , List<String>> paramMap = new LinkedHashMap<>();
		
		// input param 처리
		for(InputVO input : inputList) {
			//if(!input.getReqParam().equals("Y")) continue;  --> 필수값일 때만 (보류)

			String key = input.getInputKey();
			String value = input.getInputValue();
			
			paramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
		}
				
		// output param 처리
		for(OutputVO output : outputList) {
//			if(!output.getReqParam().equals("Y")) continue;
			
			String key = output.getOutputKey();
			String value = output.getOutputValue();
			
			paramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
		}
		
		// Map -> URL 파라미터 문자열 변환
		StringBuilder fullUrlBuilder = new StringBuilder(baseUrl);
		if(!paramMap.isEmpty()) {
			fullUrlBuilder.append("?");
			List<String> paramString = new ArrayList<>();
			
			for(Map.Entry<String, List<String>> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				String value = String.join("+", entry.getValue());
				paramString.add(key + "=" + value);
			}
			
			fullUrlBuilder.append(String.join("&", paramString));
		}
		
		String fullUrl = fullUrlBuilder.toString();

		// fullUrl  DB에 저장
		sourceVO.setFullUrl(fullUrl);
		sourceMapper.createFullUrl(sourceVO);
		
		return fullUrl;
	}

}
