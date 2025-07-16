package egovframework.example.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProjectVO {
	
	private Long projectId;				// 프로젝트 ID -> 1. KOSIS , 2. 통계청, 3. 관세청 등
	private String projectName; 		// 프로젝트 명
	private String projectExplain;		// 프로젝트 설명
	private LocalDateTime createdAt;	// 생성일자
	private boolean isDeleted;			// 삭제
}
