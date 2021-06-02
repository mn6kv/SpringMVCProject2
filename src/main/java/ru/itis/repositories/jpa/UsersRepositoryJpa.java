package ru.itis.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepositoryJpa extends JpaRepository<User, Long> {
    @Query("select user from User user order by user.id") //временно, ибо я не понял как лимиты выставлять
    List<User> findAllWithLimitOffset(int page, int size);

    @Modifying
    @Query("update User user set user.state = 'CONFIRMED' where user.confirmCode like concat('%', :code, '%')")
    void confirmUserWithCode(@Param("code") String code);

    Optional<User> findByEmail(String email);
}