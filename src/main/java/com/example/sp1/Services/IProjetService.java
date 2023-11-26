package com.example.sp1.Services;

import com.example.sp1.entities.Project;

public interface IProjetService {
    Project addProject(Project project);

    void assignProjectToDeveloper(int projectId, int devId);
}
