package com.gabrielgermano.bugtracker.service;

import com.gabrielgermano.bugtracker.exception.project.ProjectNotFoundException;
import com.gabrielgermano.bugtracker.mapper.ProjectMapper;
import com.gabrielgermano.bugtracker.model.Project;
import com.gabrielgermano.bugtracker.payload.request.ProjectRequest;
import com.gabrielgermano.bugtracker.payload.response.MessageResponse;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import com.gabrielgermano.bugtracker.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);


    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        logger.info("Entering createProject method inside the service layer");
        Project savedProject = projectRepository.save(projectMapper.mapToProject(projectRequest));
        logger.info("Project created successfully");
        return projectMapper.mapToProjectResponse(savedProject);
    }

    public List<ProjectResponse> getAllProjects() {
        logger.info("Entering getAllProjects method inside the service layer");
        return projectMapper.mapToProjectResponseList(projectRepository.findAll());
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        logger.info("Project successfully found");
        return projectMapper.mapToProjectResponse(project);
    }

    public ProjectResponse updateProjectById(Long id, ProjectRequest projectRequest) {
        logger.info("Entering updateProjectById method inside the service layer");
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        project.setDescription(projectRequest.description());
        project.setName(projectRequest.name());
        logger.info("Project successfully updated");
        return projectMapper.mapToProjectResponse(projectRepository.save(project));
    }

    public MessageResponse deleteProjectById(Long id) {
        logger.info("Entering deleteProjectById method inside the service layer");
        if (!projectRepository.existsById(id)) {
            logger.error("Project with id {} not found", id);
            throw new ProjectNotFoundException(id);
        } else {
            projectRepository.deleteById(id);
            return new MessageResponse("Project with id " + id + " was successfully deleted.");
        }
    }
}
