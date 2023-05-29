package com.example.tasklist.controller;

import com.example.tasklist.dto.UserDto;
import com.example.tasklist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todo/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.addUser(userDto);
        return ResponseEntity.ok(userDto1);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDto> getUser(@PathVariable("name") String name) {
        UserDto userDto = userService.getUser(name);
        if (userDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/id")
    public ResponseEntity<UserDto> getUserId(@RequestParam Long id){
        UserDto userDto=userService.getUser(id);
        if (userDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user,@PathVariable Long id){
        UserDto userDto= userService.updateUser(user,id);
        if(userDto==null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
