package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository ProjectRepository;

	@Override
	public List<Project> getAllProjects() {
		return ProjectRepository.findAll();
	}

	@Override
	public void saveProject(Project Project) {
		this.ProjectRepository.save(Project);
	}

	@Override
	public Project getProjectById(long id) {
		Optional<Project> optional = ProjectRepository.findById(id);
		Project Project = null;
		if (optional.isPresent()) {
			Project = optional.get();
		} else {
			throw new RuntimeException(" Project not found for id :: " + id);
		}
		return Project;
	}

	@Override
	public void deleteProjectById(long id) {
		this.ProjectRepository.deleteById(id);
	}

	@Override
	public Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ProjectRepository.findAll(pageable);
	}
}
