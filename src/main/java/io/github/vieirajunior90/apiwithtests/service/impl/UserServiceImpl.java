package io.github.vieirajunior90.apiwithtests.service.impl;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;
import io.github.vieirajunior90.apiwithtests.repository.UserRepository;
import io.github.vieirajunior90.apiwithtests.service.UserService;
import io.github.vieirajunior90.apiwithtests.service.exception.DataIntegrityViolationException;
import io.github.vieirajunior90.apiwithtests.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto dto) {
        findByEmail(dto);
        return userRepository.save(mapper.map(dto, User.class));
    }

    private void findByEmail(UserDto dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());

        if (user.isPresent()) {
            throw new DataIntegrityViolationException("Email already exists");
        }
    }
}
