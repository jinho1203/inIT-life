package egovframework.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import egovframework.example.vo.ProjectVO;

@Mapper
public interface ProjectMapper {
	
	List<ProjectVO> getAllList();						// 프로젝트 전체 조회
	
	int insertProject(ProjectVO projectVO); 			// 프로젝트 등록
	
	int deleteProject(Long projectId);				// 프로젝트 삭제
}
