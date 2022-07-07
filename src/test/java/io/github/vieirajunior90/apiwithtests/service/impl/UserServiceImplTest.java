package io.github.vieirajunior90.apiwithtests.service.impl;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.domain.dto.UserDto;
import io.github.vieirajunior90.apiwithtests.repository.UserRepository;
import io.github.vieirajunior90.apiwithtests.service.exception.DataIntegrityViolationException;
import io.github.vieirajunior90.apiwithtests.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "John";
    public static final String EMAIL = "john@email.com";
    public static final String PASSWORD = "123";
    public static final String USER_NOT_FOUND = "User not found";
    public static final int INDEX_ZERO = 0;
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        var response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFound() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));

        try {
            service.findById(ID);
        }
        catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USER_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        var response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX_ZERO).getClass());
        assertEquals(user, response.get(INDEX_ZERO));
        assertEquals(ID, response.get(INDEX_ZERO).getId());
        assertEquals(NAME, response.get(INDEX_ZERO).getName());
        assertEquals(EMAIL, response.get(INDEX_ZERO).getEmail());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        var response = service.create(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_EXISTS, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        var response = service.update(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void whenUpdateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.update(userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_EXISTS, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());

        service.deleteById(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));

        try {
            service.deleteById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USER_NOT_FOUND, ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}