package com.gabrielgermano.bugtracker.service;

import com.gabrielgermano.bugtracker.exception.project.ProjectNotFoundException;
import com.gabrielgermano.bugtracker.model.Project;
import com.gabrielgermano.bugtracker.payload.request.ProjectRequest;
import com.gabrielgermano.bugtracker.payload.response.MessageResponse;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import com.gabrielgermano.bugtracker.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);


    public ProjectService(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        logger.info("Entering createProject method inside the service layer");
        Project savedProject = projectRepository.save(modelMapper.map(projectRequest, Project.class));
        logger.info("Project created successfully");
        return modelMapper.map(savedProject, ProjectResponse.class);
    }

    public List<ProjectResponse> getAllProjects() {
        logger.info("Entering getAllProjects method inside the service layer");
        return Arrays.asList(modelMapper.map(projectRepository.findAll(), ProjectResponse[].class));
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        logger.info("Project successfully found");
        return modelMapper.map(project, ProjectResponse.class);
    }

    public ProjectResponse updateProjectById(Long id, ProjectRequest projectRequest) {
        logger.info("Entering updateProjectById method inside the service layer");
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        project.setDescription(projectRequest.getDescription());
        project.setName(projectRequest.getName());
        logger.info("Project successfully updated");
        return modelMapper.map(projectRepository.save(project), ProjectResponse.class);
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
