package egovframework.example.service;

import java.util.List;

import egovframework.example.vo.InputVO;

public interface InputService {
	
	List<InputVO> getAllList(Long sourceId);				// 프로젝트 전체 조회
	
	int insertInput(InputVO inputVO);		// 입력항목 등록
	
	int deleteInput(Long inputId);			// 입력항목 삭제
	
	List<InputVO> findBySourceId(Long sourceId);			// Input 테이블 sourceId를 통해 파라미터를 가져온다.
	
	int updateInputValue(InputVO inputVO);  // 입력값 수정
}
