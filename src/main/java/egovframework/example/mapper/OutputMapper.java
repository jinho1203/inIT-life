package egovframework.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.OutputVO;

@Mapper
public interface OutputMapper {
	
	List<OutputVO> getAllList(Long inputId);
	
	int insertOutput(OutputVO outputVO);
	
	
}
