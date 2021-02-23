package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsers(int page, int size);
    void addUser(UserDto userDto);
    void addUser(UserForm userForm, String sessionId);
    UserDto getUser(Long userId);
    void confirmUserWithCode(String code);
}
