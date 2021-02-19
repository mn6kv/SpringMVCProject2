package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAll();
    Optional<User> findFirstByEmail(String email);
}