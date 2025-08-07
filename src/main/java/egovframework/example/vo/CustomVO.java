package egovframework.example.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomVO {
	
	private Long customNo;
	private Long sourceId;
	private String customFullUrl;
	private LocalDate createdAt;
	
	private String baseUrl;
}
