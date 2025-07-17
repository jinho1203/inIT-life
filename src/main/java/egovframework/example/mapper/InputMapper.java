package egovframework.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.InputVO;

@Mapper
public interface InputMapper {
	
	int insertInput(InputVO inputVO);
}
