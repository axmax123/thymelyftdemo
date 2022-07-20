package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ProjectService {
	List<Project> getAllProjects();
	void saveProject(Project Project);
	Project getProjectById(long id);
	void deleteProjectById(long id);
	Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
