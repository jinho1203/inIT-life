package egovframework.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.SourceVO;

@Mapper
public interface SourceMapper {
	
	List<SourceVO> getAllList();
	
	int insertSource(SourceVO sourceVO);
	
	int deleteSource(Long sourceId);
}
