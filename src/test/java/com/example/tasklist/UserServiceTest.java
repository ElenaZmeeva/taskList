package com.example.tasklist;

import com.example.tasklist.dto.UserDto;
import com.example.tasklist.entity.User;
import com.example.tasklist.mapper.UserMapper;
import com.example.tasklist.repository.UserRepository;
import com.example.tasklist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class UserServiceTest {

    @Mock
    UserRepository repository;
    @Mock
    UserMapper mapper;
    UserService service;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.service=new UserService(mapper, repository);
    }
    @Test
    void testAddUser(){
        UserDto dto= new UserDto(1L, "Olga","lav@gmail.com");
        User entity=mapper.toEntity(dto);
        service.addUser(dto);
        when(repository.save(entity)).thenReturn(entity);
    }
    @Test
    void testGet(){
        User user= new User();
        user.setId(3L);
        user.setFullName("Nina");
        user.setEmail("cat@mail.ru");
        when(repository.findById(3L)).thenReturn(Optional.of(user));
    }
    @Test
    void testDelete(){
        repository=mock(UserRepository.class);
        User user= new User();
        repository.delete(user);
        verify(repository).delete(user);
}
    @Test
    void testUpdate(){
        UserDto userDto=new UserDto(4L,"Karina","karina@bk.ru");
        User entity=mapper.toEntity(userDto);
        entity.setId(userDto.getId());
        entity.setFullName(userDto.getFullName());
        entity.setEmail(userDto.getEmail());
        service.updateUser(userDto,4L);
        when(repository.save(entity)).thenReturn(entity);

}
}



