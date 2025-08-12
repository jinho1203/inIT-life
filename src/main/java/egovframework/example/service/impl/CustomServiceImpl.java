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

	    CustomVO sourceBaseUrl = customMapper.getBaseUrl(sourceId);
	    String baseUrl = sourceBaseUrl.getBaseUrl();

	    customVO.setBaseUrl(baseUrl);

	    Map<String, String> selectedData = customVO.getSelectedData();

	    StringBuilder fullUrlBuilder = new StringBuilder(baseUrl);
	    if (selectedData != null && !selectedData.isEmpty()) {
	        fullUrlBuilder.append("?");
	        List<String> paramString = new ArrayList<>();

	        for (Map.Entry<String, String> entry : selectedData.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            paramString.add(key + "=" + value);
	        }

	        fullUrlBuilder.append(String.join("&", paramString));
	    }

	    String customFullUrl = fullUrlBuilder.toString();

	    customVO.setCustomFullUrl(customFullUrl);
	    customMapper.createCustomFullUrl(customVO);

	    return customFullUrl;
	}

	@Override
	public CustomVO getFullUrl(Long sourceId) {
		return customMapper.getFullUrl(sourceId);
	}
	
	@Override
	public void saveSelectedData(CustomVO customVO) {
	    Map<String, String> selectedData = customVO.getSelectedData();
	    Long sourceId = customVO.getSourceId();

	    // 선택된 데이터가 없으면 저장 안 함 or 기본 처리
	    if (selectedData == null || selectedData.isEmpty()) {
	        return;
	    }

	    // 예시: 선택된 항목을 DB에 저장하는 Mapper 호출 (Mapper 메서드 구현 필요)
	    // 예) customMapper.saveSelectedParams(sourceId, selectedData);

	    // 만약 저장 로직이 없으면 아래와 같이 로그 확인용 출력도 가능
	    System.out.println("선택된 데이터 DB 저장: sourceId=" + sourceId + ", params=" + selectedData);
	}
}
