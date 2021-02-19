package ru.itis.services;

import ru.itis.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsers(int page, int size);
    void addUser(UserDto userDto);
    UserDto getUser(Long userId);
}
