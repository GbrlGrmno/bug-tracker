package com.gabrielgermano.bugtracker.service;

import java.util.List;

import com.gabrielgermano.bugtracker.mapper.ProjectMapper;
import com.gabrielgermano.bugtracker.mapper.UserMapper;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
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
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;

    public ProjectMemberService(UserRepository userRepository, ProjectRepository projectRepository, UserMapper userMapper, ProjectMapper projectMapper) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.userMapper = userMapper;
        this.projectMapper = projectMapper;
    }

    public List<UserResponse> addUserToProject(Long projectId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        project.addUser(user);

        projectRepository.save(project);

        return userMapper.mapToUserResponseList(project.getUsers());

        
    }

    public List<UserResponse> getAllUsersFromProject(Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return userMapper.mapToUserResponseList(project.getUsers());
    }

    public List<ProjectResponse> getAllProjectFromUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return projectMapper.mapToProjectResponseList(user.getProjects());
    }

    public void deleteUserFromProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        project.removeUser(user);

        projectRepository.save(project);
    }
}
