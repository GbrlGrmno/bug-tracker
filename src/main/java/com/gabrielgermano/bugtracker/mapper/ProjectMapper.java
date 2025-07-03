package com.gabrielgermano.bugtracker.mapper;

import com.gabrielgermano.bugtracker.model.Project;
import com.gabrielgermano.bugtracker.payload.request.ProjectRequest;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
    Project mapToProject(ProjectRequest projectRequest);
    ProjectResponse mapToProjectResponse(Project project);
    List<ProjectResponse> mapToProjectResponseList(List<Project> projects);


}
