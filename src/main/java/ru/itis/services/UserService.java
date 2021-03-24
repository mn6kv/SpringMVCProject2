package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsers(int page, int size);
    void addUser(UserDto userDto);
    void addUser(UserForm userForm, String sessionId);
    UserDto getUser(Long userId);
    void confirmUserWithCode(String code);
    boolean updateUser(Long id, UserDto user);
    void deleteUser(Long id);
    User signIn(UserForm userForm);
}
