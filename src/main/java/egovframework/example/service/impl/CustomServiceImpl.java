package egovframework.example.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.CustomMapper;
import egovframework.example.mapper.InputMapper;
import egovframework.example.mapper.OutputMapper;
import egovframework.example.service.CustomService;
import egovframework.example.vo.CustomVO;
import egovframework.example.vo.InputVO;
import egovframework.example.vo.OutputVO;
import egovframework.example.vo.SourceVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomServiceImpl implements CustomService {
	
	private final CustomMapper customMapper;
	private final InputMapper inputMapper;
	private final OutputMapper outputMapper;

	@Override
	public CustomVO getBaseUrl(Long sourceId) {
		return customMapper.getBaseUrl(sourceId);
	}
	
	@Override
	public String createCustomFullUrl(CustomVO customVO) {
		Long sourceId = customVO.getSourceId();
		
		//DB에서 baseUrl 가져오기
		CustomVO sourceBaseUrl = customMapper.getBaseUrl(sourceId);
		String baseUrl = sourceBaseUrl.getBaseUrl();
		
		customVO.setBaseUrl(baseUrl);
		
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
		
		String customFullUrl = fullUrlBuilder.toString();
		
		// customFullUrl DB에 저장
		customVO.setCustomFullUrl(customFullUrl);
		customMapper.createCustomFullUrl(customVO);
		
		return customFullUrl;
	}

	@Override
	public CustomVO getFullUrl(Long sourceId) {
		return customMapper.getFullUrl(sourceId);
	}
}
