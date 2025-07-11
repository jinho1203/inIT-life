package egovframework.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.TestVO;

@Mapper
public interface TestMapper {
	List<TestVO> findAll();
}
