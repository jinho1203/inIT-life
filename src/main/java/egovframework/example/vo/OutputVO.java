package egovframework.example.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OutputVO {
	
	private Long outputId;
	private String outputKey;
	private String outputValue;
	private LocalDate createdAt;		// 생성일자
	private boolean isDeleted;			// 삭제
	private String outputExplain;
	
	private Long sourceId;
	private String reqParam;
}
