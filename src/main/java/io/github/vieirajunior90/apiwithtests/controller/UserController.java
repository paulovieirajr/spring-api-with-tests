package io.github.vieirajunior90.apiwithtests.controller;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;
import io.github.vieirajunior90.apiwithtests.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserServiceImpl userService;
    final ModelMapper mapper;

    public UserController(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDto.class));
    }
}
