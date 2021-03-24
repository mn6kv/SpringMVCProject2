package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.util.MailSender;
import ru.itis.util.MailsGenerator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.dto.UserDto.from;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.theme}")
    private String mailTheme;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    MailsGenerator mailsGenerator;

    @Autowired
    MailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        return from(usersRepository.findAll(/*page, size*/));
    }

    @Override
    public void addUser(UserDto userDto) {
        usersRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .hashPassword(passwordEncoder.encode(userDto.getHashPassword()))
                .build());
    }
    @Override
    public void addUser(UserForm userForm, String sessionId) {

        User user = User.builder()
                .email(userForm.getEmail())
                .name(userForm.getName())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .sessionId(sessionId)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(user);

        String confirmMail = mailsGenerator.getEmailToConfirm(serverUrl, user.getConfirmCode());
        mailSender.sendMail(user.getEmail(), mailTheme, from, confirmMail);
    }

    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(usersRepository.findById(userId).orElse(null));
    }

    @Override
    public void confirmUserWithCode(String code) {
        usersRepository.confirmUserWithCode(code);
        usersRepository.setRoleUserWithCode(code);
    }

    @Override
    public boolean updateUser(Long id, UserDto newUser) {
        Optional<User> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            User oldUser = userOptional.get();
            oldUser.setEmail(newUser.getEmail());
            oldUser.setName(newUser.getName());
            oldUser.setHashPassword(newUser.getHashPassword());
            return true;
        } else return false;
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public User signIn(UserForm userForm) {
        User user = usersRepository.findUserByEmail(userForm.getEmail());
        if (passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())
                && user.getState() == User.State.CONFIRMED)
            return user;
        else return null;
    }
}
