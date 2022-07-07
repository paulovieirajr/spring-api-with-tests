package io.github.vieirajunior90.apiwithtests.controller;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;
import io.github.vieirajunior90.apiwithtests.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserControllerTest {

    public static final int ID = 1;
    public static final String NAME = "John";
    public static final String EMAIL = "john@email.com";
    public static final String PASSWORD = "123";
    public static final String USER_NOT_FOUND = "User not found";
    public static final int INDEX_ZERO = 0;
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    private User user;
    private UserDto userDto;

    @InjectMocks
    final UserController controller;

    @Mock
    final UserServiceImpl service;

    @Mock
    final ModelMapper mapper;

    UserControllerTest(UserController controller, UserServiceImpl service, ModelMapper mapper) {
        this.controller = controller;
        this.service = service;
        this.mapper = mapper;
    }


    @BeforeEach
    void setUp() {
        openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}