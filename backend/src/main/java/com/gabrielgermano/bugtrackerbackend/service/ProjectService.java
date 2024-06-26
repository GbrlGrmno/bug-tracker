package com.gabrielgermano.bugtrackerbackend.service;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.model.ProjectMember;
import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.repository.ProjectRepository;
import com.gabrielgermano.bugtrackerbackend.request.AddMemberRequest;
import com.gabrielgermano.bugtrackerbackend.request.ProjectRequest;
import com.gabrielgermano.bugtrackerbackend.response.AddMemberResponse;
import com.gabrielgermano.bugtrackerbackend.response.ProjectResponse;
import com.gabrielgermano.bugtrackerbackend.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public ProjectResponse getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(); // TODO: create a ProjectNotFoundException;
        return modelMapper.map(project, ProjectResponse.class);
    }

    public List<ProjectResponse> getAllProjects() {
        return Arrays.asList(modelMapper.map(projectRepository.findAllByOrderByCreatedAtDesc(), ProjectResponse[].class));
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public AddMemberResponse addProjectMember(Long projectId, AddMemberRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        User user = userService.findUserById(request.getUserId());

        ProjectMember projectMember = ProjectMember.builder()
                .project(project)
                .user(user)
                .build();

        project.getProjectMembers().add(projectMember);

        projectRepository.save(project);

        return AddMemberResponse.builder()
                .message("Member added sucessfully")
                .userId(request.getUserId())
                .projectId(projectId)
                .build();
    }

    public List<UserResponse> getAllMembers(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();

        return project.getProjectMembers()
                .stream()
                .map(u -> modelMapper.map(u.getUser(), UserResponse.class))
                .collect(Collectors.toList());
    }

    public void removeUserFromProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId).orElseThrow();

        User user = userService.findUserById(userId);

        ProjectMember projectMember = project
                .getProjectMembers()
                .stream()
                .filter(pm -> Objects.equals(pm.getUser().getId(), userId))
                .findFirst()
                .orElseThrow();

        project.getProjectMembers().remove(projectMember);

        projectRepository.save(project);

    }
}
