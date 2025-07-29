package egovframework.example.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SourceVO {
	
	private Long sourceId;				// 소스 ID -> 1. KOSIS 의 OO , 2. 통계청의 OO
	private String sourceName;			// 소스 명
	private String baseUrl;				// 메인 URL -> https://kosis.kr/openapi/Param/statisticsParameterData.do?
	private String fullUrl;				// 최종 URL -> 메인 URL + 추가 파라미터 
	private LocalDate createdAt;		// 생성일자
	private boolean isDeleted;			// 삭제
	
	private Long projectId;
}
