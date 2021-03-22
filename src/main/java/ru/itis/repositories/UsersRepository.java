package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User user set state = 'CONFIRMED' where user.confirmCode like concat('%', :code, '%') ")
    void confirmUserWithCode(@Param("code") String code);

    @Modifying
    @Query("update User user set role = 'USER' where user.confirmCode like concat('%', :code, '%') ")
    void setRoleUserWithCode(@Param("code") String code);

    User findUserByEmail(String email);

    Optional<User> findByEmail(String email);
}