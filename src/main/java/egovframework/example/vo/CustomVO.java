package egovframework.example.vo;

import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class CustomVO {
	
	private Long customNo;
	private Long sourceId;
	private String customFullUrl;
	private LocalDate createdAt;
	/* 추가 */ 
	private Map<String, String> selectedData;

	private String baseUrl;
}
