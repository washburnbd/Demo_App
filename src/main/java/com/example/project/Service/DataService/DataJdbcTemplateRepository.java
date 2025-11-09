package com.example.project.Service.DataService;

import com.example.project.Model.User;
import com.example.project.Service.DataService.Mappers.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DataJdbcTemplateRepository implements DataRepository{

    private final JdbcTemplate jdbcTemplate;

    public DataJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserById(int id) throws Exception {
        //Generate Query
        String query = "SELECT user_id, name FROM dataStore.user WHERE user_id = " + id;
        //Return result
        return jdbcTemplate.queryForObject(query, new UserMapper());
    }

    @Override
    public User createUser(String name) throws Exception {
        //Generate Query
        String query = "INSERT into dataStore.user (name) values (\"" + name + "\")";

        //Send Query
        int rowsAffected = jdbcTemplate.update(query);

        //See if create worked
        if (rowsAffected > 0) {
            //If yes, return with newly generated ID
            String queryCheck = "SELECT user_id, name FROM dataStore.user WHERE name = \"" + name + "\"";
            return jdbcTemplate.queryForObject(queryCheck, new UserMapper());
        } else {
            //If not, return a null
            return null;
        }
    }

    @Override
    public int healthCheck() throws NullPointerException {
        return jdbcTemplate.queryForObject("SELECT 1", Integer.class);
    }

    @Override
    public User updateUser(String newName, int id) throws Exception {
        //Generate Query
        String query = "UPDATE dataStore.user set name=\"" + newName + "\" WHERE user_id=" + id;

        //Send Query
        int rowsAffected = jdbcTemplate.update(query);

        //See if Update Worked
        if (rowsAffected > 0) {
            //If yes, return with newly generated ID
            String queryCheck = "SELECT user_id, name FROM dataStore.user WHERE user_id=" + id;
            return jdbcTemplate.queryForObject(queryCheck, new UserMapper());
        } else {
            //If not, return a null
            return null;
        }
    }
}
