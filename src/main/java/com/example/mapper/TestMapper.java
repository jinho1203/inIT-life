package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.TestVO;

@Mapper
public interface TestMapper {
	List<TestVO> findAll();
}
