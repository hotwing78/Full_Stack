package com.fullstack.ppmtool.service;

import com.fullstack.ppmtool.domain.Project;
import com.fullstack.ppmtool.exceptions.ProjectIdException;
import com.fullstack.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){
        //Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException("Project ID: " + project.getProjectIdentifier().toUpperCase()+ "already exists.");
        }
    }

    public Project findByProjectIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID: "+ projectId + " does not exists.");
        }
        return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    }
}
