package com.example.project.Service.DataService;

import com.example.project.Model.User;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public User getUserById (int id) throws Exception {
        return dataRepository.findUserById(id);
    }

    public User createUser (String name) throws Exception {
        return dataRepository.createUser(name);
    }

    public User updateUser (String newName, int id) throws Exception {
        return dataRepository.updateUser(newName, id);
    }

    public String healthCheck() {
        try {
            int result = dataRepository.healthCheck();
            if (result == 1) {
                return "Database Connection Successful";
            } else {
                return "Database Connection Unsuccessful";
            }
        } catch (Exception e) {
            return "Database Connection Unsuccessful";
        }
    }
}
