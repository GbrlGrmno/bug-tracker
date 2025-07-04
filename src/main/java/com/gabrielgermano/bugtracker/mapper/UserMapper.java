package com.gabrielgermano.bugtracker.mapper;

import com.gabrielgermano.bugtracker.model.User;
import com.gabrielgermano.bugtracker.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponse mapToUserResponse(User user);
    List<UserResponse> mapToUserResponseList(Collection<User> users);
}
