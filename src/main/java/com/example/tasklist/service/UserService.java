package com.example.tasklist.service;

import com.example.tasklist.dto.UserDto;
import com.example.tasklist.entity.User;
import com.example.tasklist.mapper.UserMapper;
import com.example.tasklist.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final static Logger logger= LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;
    private final UserRepository userRepository;



    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto userDto){
        User user1=userMapper.toEntity(userDto);
        userRepository.save(user1);
        logger.info("User saved");
       return userMapper.toDto(user1);
    }

    public UserDto getUser(String fullName) {
        User user1=userRepository.findUserByFullName(fullName);
        return userMapper.toDto(user1) ;
    }

    public UserDto getUser(Long id) {
        User user1=userRepository.findById(id).orElseThrow();
        return userMapper.toDto(user1);
    }

    public UserDto updateUser(UserDto userDto,Long id){
        User user1=userRepository.findById(id).orElseThrow();
        user1.setFullName(userDto.getFullName());
        user1.setEmail(userDto.getEmail());
        userRepository.save(user1);
        logger.info("User updated");
        return userMapper.toDto(user1);
    }
    public void deleteUser(Long id){
        User user1=userRepository.findById(id).orElseThrow();
        userRepository.delete(user1);
        logger.info("User deleted");
}
}
