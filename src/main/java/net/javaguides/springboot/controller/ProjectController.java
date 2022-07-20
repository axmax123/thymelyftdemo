package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    //Display project
    @GetMapping("/")
    public String viewHomePage(Model model){

        return findPaginated(1, "firstName", "asc", model);
    }


    @GetMapping("/showNewProjectForm")
    public String showNewProjectForm(Model model) {
        // create model attribute to bind form data
        Project project = new Project();
        model.addAttribute("project", project);
        return "new_project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute("project") Project project) {
        // save Project to database
        projectService.saveProject(project);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get Project from the service
        Project project = projectService.getProjectById(id);

        // set Project as a model attribute to pre-populate the form
        model.addAttribute("project", project);
        return "update_project";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProjectById(@PathVariable (value = "id") long id) {

        // call delete Project method
        this.projectService.deleteProjectById(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Project> page = projectService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Project> listProject = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProject", listProject);
        return "index";
    }



}
