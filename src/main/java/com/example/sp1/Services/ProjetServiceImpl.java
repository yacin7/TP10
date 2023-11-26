package com.example.sp1.Services;

import com.example.sp1.Repositories.ProjetRepo;
import com.example.sp1.Repositories.UserRepo;
import com.example.sp1.entities.Project;
import com.example.sp1.entities.Role;
import com.example.sp1.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ProjetServiceImpl implements IProjetService {
    ProjetRepo projetRepo;
    UserRepo userRepo;

    @Override
    public Project addProject(Project project) {
        return projetRepo.save(project);
    }

    @Override
    public void assignProjectToDeveloper(int projectId, int devId) {
        Project project = projetRepo.findById(projectId).orElse(null);
        User user = userRepo.findById(devId).orElse(null);
        if (user == null) {
            throw new RuntimeException("user inexistant");
        } else if (user.getRole() != Role.Developer) {
            throw new RuntimeException("cette user n est pas un developpeur");
        } else {
            Set<Project> pjts = user.getProjects();
            pjts.add(project);
            user.setProjects(pjts);
            projetRepo.save(project);
            userRepo.save(user);
        }
    }
}
