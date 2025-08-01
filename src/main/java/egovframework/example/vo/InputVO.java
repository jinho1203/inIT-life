package egovframework.example.vo;

import java.time.LocalDate;

import lombok.Data;
@Data
public class InputVO {
	
	private Long inputId;				// 입력 ID -> 1. KOSIS OO 의 추가 파라미터 
	private String inputExplain;		// 설명 -> 서울 인구수 조사
	private String inputKey;			// 입력 항목 키 -> objL1 (지역별) , objL2(경제별)
	private String inputValue;			// 입력 값 -> 서울
	private LocalDate createdAt;		// 생성일자
	private boolean isDeleted;			// 삭제
	
	private Long projectId;
	private Long sourceId;
	private String reqParam;
}
