package egovframework.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.SourceVO;

@Mapper
public interface SourceMapper {
	
	List<SourceVO> getAllList();
	
	int insertSource(SourceVO sourceVO);
	
	int deleteSource(Long sourceId);
	
	SourceVO findBasicUrl(Long sourceId);					// baseUrl 불러오기
	void updateFullUrl(SourceVO sourceVO);					// fullUrl 저장하기			
}
