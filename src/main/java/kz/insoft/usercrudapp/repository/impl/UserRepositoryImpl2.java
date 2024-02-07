package kz.insoft.usercrudapp.repository.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.UserRepository;
import kz.insoft.usercrudapp.repository.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl2 implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CREATE = "INSERT user(first_name, last_name, email, address, birth_date) " +
            "VALUES (:fName, :lName, :email, :address, :bDate)";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=:id";
    private static final String DELETE = "DELETE FROM user WHERE id=:id";

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public UserRepositoryImpl2(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                               SimpleJdbcInsert simpleJdbcInsert) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, new UserRowMapper());
    }

    @Override
    public User findById(Long id) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", id);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);


        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, params, new UserRowMapper());
    }

    @Override
    public boolean create(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("FIRST_NAME", user.getFirstName());
        params.addValue("LAST_NAME", user.getLastName());
        params.addValue("EMAIL", user.getEmail());
        params.addValue("ADDRESS", user.getAddress());
        params.addValue("BIRTH_DATE", Date.valueOf(user.getBirthDate()));

//        int rows = namedParameterJdbcTemplate.update(CREATE, params);

        int rows = simpleJdbcInsert.execute(params);

        return rows > 0;
    }

    @Override
    public boolean delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        int rows = namedParameterJdbcTemplate.update(DELETE, params);
        return rows > 0;
    }

    @Override
    public boolean update(User user, Long id) {
        return false;
    }
}
