package egovframework.example.service;

import java.util.List;

import egovframework.example.vo.SourceVO;

public interface SourceService {
	
	List<SourceVO> getAllList();							// 전체 소스 불러오기
	
	int insertSource(SourceVO sourceVO);					// 소스 등록
	
	int deleteSource(Long sourceId);

	SourceVO findBasicUrl(Long sourceId);					// baseUrl 불러오기
	void updateFullUrl(SourceVO sourceVO);					// fullUrl 저장하기			
}
