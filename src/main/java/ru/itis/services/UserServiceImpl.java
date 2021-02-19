package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.List;

import static ru.itis.dto.UserDto.from;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        return from(usersRepository.findAll(page, size));
    }

    @Override
    public void addUser(UserDto userDto) {
        usersRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .build());
    }

    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(usersRepository.findById(userId).orElse(null));
    }
}
