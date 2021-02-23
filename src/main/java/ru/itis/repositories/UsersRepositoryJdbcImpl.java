package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UsersRepositoryJdbcImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from account where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from account order by id limit :limit offset :offset ;";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into account(email, name, password, sessionId, confirmCode) values (?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from account";

    //language=SQL
    private static final String SQL_CONFIRM = "update account set state 'CONFIRMED' where confirmCode = ?";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .email(row.getString("email"))
            .name(row.getString("name"))
            .password(row.getString("password"))
            .sessionId(row.getString("sessionId"))
            .confirmCode(row.getString("confirmCode"))
            .build();

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Deprecated
    @Override
    public void saveSession(String sessionId) {
    }

    @Override
    public void confirmUserWithCode(String code) {
        jdbcTemplate.update(SQL_CONFIRM, code);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        //email, name, password, session, code
        jdbcTemplate.update(SQL_INSERT_USER,
                entity.getEmail(),
                entity.getName(),
                entity.getPassword(),
                entity.getSessionId(),
                entity.getConfirmCode());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findFirstByEmail(String email) {
        return Optional.empty();
    }
}
