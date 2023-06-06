package com.example.tasklist.mapper;

import com.example.tasklist.dto.UserDto;
import com.example.tasklist.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
