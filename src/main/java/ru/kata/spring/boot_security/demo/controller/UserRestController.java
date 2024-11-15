package ru.kata.spring.boot_security.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    
    private final UserServiceImpl userService;
    
    private final ModelMapper modelMapper;
    
    @Autowired
    public UserRestController(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        UserDTO user = modelMapper.map(userService.getUserByUsername(principal.getName()), UserDTO.class);
        return ResponseEntity.ok(user);
    }
}


