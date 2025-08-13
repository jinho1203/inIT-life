package egovframework.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.CustomMapper;
import egovframework.example.service.CustomService;
import egovframework.example.vo.CustomVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomServiceImpl implements CustomService {
	
	private final CustomMapper customMapper;

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

	    Map<String, List<String>> selectedData = customVO.getSelectedData();

	    StringBuilder fullUrlBuilder = new StringBuilder(baseUrl);
	    if (selectedData != null && !selectedData.isEmpty()) {
	        fullUrlBuilder.append("?");
	        List<String> paramString = new ArrayList<>();

	        for (Map.Entry<String, List<String>> entry : selectedData.entrySet()) {
	            String key = entry.getKey();
	            String combinedValue = String.join("+", entry.getValue());
	            
	            // API가 마지막 +를 요구하는 key라면 수동 추가
	            if(key.equals("outputFields") || key.equals("itmId")) {
	                combinedValue += "+";
	            }
	            
	            paramString.add(key + "=" + combinedValue);
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
		Map<String, List<String>> selectedData = customVO.getSelectedData();
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
