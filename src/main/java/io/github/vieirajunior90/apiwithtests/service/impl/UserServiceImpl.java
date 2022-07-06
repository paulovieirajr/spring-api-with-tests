package io.github.vieirajunior90.apiwithtests.service.impl;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.repository.UserRepository;
import io.github.vieirajunior90.apiwithtests.service.UserService;
import io.github.vieirajunior90.apiwithtests.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
}
