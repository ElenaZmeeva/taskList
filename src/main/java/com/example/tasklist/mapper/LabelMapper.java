package com.example.tasklist.mapper;

import com.example.tasklist.dto.LabelDto;
import com.example.tasklist.entity.Label;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LabelMapper {

    LabelDto toDto (Label label);

    Label toEntity(LabelDto labelDto);
}
