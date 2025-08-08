package egovframework.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.CustomVO;

@Mapper
public interface CustomMapper {
	
	CustomVO getBaseUrl(Long sourceId);
	
	int createCustomFullUrl(CustomVO customVO);
	
	CustomVO getFullUrl(Long sourceId);
}
