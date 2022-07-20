package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService ProjectService;
	
	// display list of Projects
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "project_name", "asc", model);
	}
	
	@GetMapping("/showNewProjectForm")
	public String showNewProjectForm(Model model) {
		// create model attribute to bind form data
		Project Project = new Project();
		model.addAttribute("Project", Project);
		return "new_Project";
	}
	
	@PostMapping("/saveProject")
	public String saveProject(@ModelAttribute("Project") Project Project) {
		// save Project to database
		ProjectService.saveProject(Project);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Project from the service
		Project Project = ProjectService.getProjectById(id);
		
		// set Project as a model attribute to pre-populate the form
		model.addAttribute("Project", Project);
		return "update_Project";
	}
	
	@GetMapping("/deleteProject/{id}")
	public String deleteProject(@PathVariable (value = "id") long id) {
		
		// call delete Project method
		this.ProjectService.deleteProjectById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Project> page = ProjectService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Project> listProjects = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProjects", listProjects);
		return "index";
	}
}
