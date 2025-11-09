package com.example.project.Service.DataService;

import com.example.project.Model.User;
import org.springframework.transaction.annotation.Transactional;

public interface DataRepository {

    @Transactional
    User findUserById(int id) throws Exception;

    @Transactional
    User createUser(String name) throws Exception;

    int healthCheck() throws Exception;

    User updateUser(String newName, int id) throws Exception;
}
