package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    UserService userService;

    //    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public String getUsersPage(@RequestParam(value = "page", required = false) Integer page,
//                                     @RequestParam(value = "size", required = false) Integer size,
//                                     Model model) {
//        if (page != null && size != null)
//            model.addAttribute("users", userService.getAllUsers(page, size));
//        else model.addAttribute("users", userService.getAllUsers());
//        return "users_view";
//    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> read() {
        List<UserDto> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserDto user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> read(@PathVariable(name = "id") Long id) {
        final UserDto user = userService.getUser(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UserDto user) {
        return userService.updateUser(id, user)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

