package com.example.sp1.RestController;

import com.example.sp1.Services.IProjetService;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Project;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("projet")
public class ProjectRestController {
    IProjetService iProjetService;
    @PostMapping("/ajouterprojet")
    public Project addProject(@RequestBody Project project){
        return iProjetService.addProject(project);
    }

    @PutMapping("/affecteradev")
    public void assignProjectToDeveloper(int projectId, int devId) {
        iProjetService.assignProjectToDeveloper(projectId,devId);
    }
}
