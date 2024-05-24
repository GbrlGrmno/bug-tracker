package com.gabrielgermano.bugtrackerbackend.service;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.repository.ProjectRepository;
import com.gabrielgermano.bugtrackerbackend.request.ProjectRequest;
import com.gabrielgermano.bugtrackerbackend.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProjectResponse createProject(ProjectRequest projectRequest)  {
        Project project = projectRepository.save(modelMapper.map(projectRequest, Project.class));
        return modelMapper.map(project, ProjectResponse.class);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(); // TODO: create a ProjectNotFoundException;
    }

    public List<ProjectResponse> getAllProjects() {
        return Arrays.asList(modelMapper.map(projectRepository.findAllByOrderByCreatedAtDesc(), ProjectResponse[].class));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // TODO: assign a user to a project
    public void addUserToProject(String username, Long projectId) {
        User user = userService.findUserByUsername(username);
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.getUsers().add(user);
    }


}
