package io.github.vieirajunior90.apiwithtests.controller;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;
import io.github.vieirajunior90.apiwithtests.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


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
    private UserController controller;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void whenFindAllReturnAListOfUserDto() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<List<UserDto>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().get(INDEX_ZERO).getClass());

        assertEquals(ID, response.getBody().get(INDEX_ZERO).getId());
        assertEquals(NAME, response.getBody().get(INDEX_ZERO).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX_ZERO).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX_ZERO).getPassword());
    }

    @Test
    void whenCreateThenReturnCreated() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDto> response = controller.create(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getHeaders().getLocation()).getPath(), "/1");
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(userDto)).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = controller.update(ID, userDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).deleteById(anyInt());

        ResponseEntity<UserDto> response = controller.delete(anyInt());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).deleteById(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}