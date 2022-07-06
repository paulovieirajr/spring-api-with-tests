package io.github.vieirajunior90.apiwithtests.controller;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;
import io.github.vieirajunior90.apiwithtests.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
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

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        var users = userService.findAll();
        var usersDto = users.stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();

        return ResponseEntity.ok().body(usersDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        var user = userService.create(userDto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
