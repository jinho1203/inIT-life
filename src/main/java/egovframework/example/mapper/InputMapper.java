package egovframework.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.InputVO;

@Mapper
public interface InputMapper {
	
	List<InputVO> getAllList(Long sourceId);
	
	int insertInput(InputVO inputVO);

	int deleteInput(Long inputId);
	
	List<InputVO> findBySourceId(Long sourceId);			// Input 테이블 sourceId를 통해 파라미터를 가져온다.
	
	int updateInputValue(InputVO inputVO); 
}
