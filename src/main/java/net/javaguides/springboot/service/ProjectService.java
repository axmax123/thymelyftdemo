package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    void saveProject(Project project);
    Project getProjectById(long id);
    void deleteProjectById(long id);
    Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
