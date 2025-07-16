package egovframework.example.service;

import java.util.List;

import egovframework.example.vo.ProjectVO;

public interface ProjectService {
	
	List<ProjectVO> getAllList();				// 프로젝트 전체 조회
	
	int insertProject(ProjectVO projectVO);		// 프로젝트 등록
	
	int deleteProject(Long projectId);			// 프로젝트 삭제
}
