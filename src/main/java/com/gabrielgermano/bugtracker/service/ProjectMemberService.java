package com.gabrielgermano.bugtracker.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gabrielgermano.bugtracker.exception.project.ProjectNotFoundException;
import com.gabrielgermano.bugtracker.exception.user.UserNotFoundException;
import com.gabrielgermano.bugtracker.model.Project;
import com.gabrielgermano.bugtracker.model.User;
import com.gabrielgermano.bugtracker.payload.response.UserResponse;
import com.gabrielgermano.bugtracker.repository.ProjectRepository;
import com.gabrielgermano.bugtracker.repository.UserRepository;

import io.jsonwebtoken.lang.Arrays;

@Service
public class ProjectMemberService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectMemberService(UserRepository userRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserResponse> addUserToProject(Long projectId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        project.addUser(user);

        projectRepository.save(project);

        return Arrays.asList(modelMapper.map(project.getUsers(), UserResponse[].class));

        
    }

    public List<UserResponse> getAllUsersFromProject(Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return Arrays.asList(modelMapper.map(project.getUsers(), UserResponse[].class));
    }
}
