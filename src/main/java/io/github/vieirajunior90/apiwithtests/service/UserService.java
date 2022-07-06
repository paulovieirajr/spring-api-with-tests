package io.github.vieirajunior90.apiwithtests.service;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDto dto);
    User update(UserDto dto);
    void deleteById(Integer id);

}
