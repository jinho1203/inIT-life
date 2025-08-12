package egovframework.example.service;

import egovframework.example.vo.CustomVO;

public interface CustomService {
	
	CustomVO getBaseUrl(Long sourceId);
	
	String createCustomFullUrl(CustomVO customVO);
	
	CustomVO getFullUrl(Long sourceId);
	
	// 새로 추가하는 메서드
	void saveSelectedData(CustomVO customVO); 
}
