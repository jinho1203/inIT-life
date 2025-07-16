package egovframework.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.example.mapper.ProjectMapper;
import egovframework.example.service.ProjectService;
import egovframework.example.vo.ProjectVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> getAllList() {
		return projectMapper.getAllList();
	}

	@Override
	public int insertProject(ProjectVO projectVO) {
		return projectMapper.insertProject(projectVO);
	}

	@Override
	public int deleteProject(Long projectId) {
		return projectMapper.deleteProject(projectId);
	}

}
