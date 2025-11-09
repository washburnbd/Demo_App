package com.example.project.Controller;

import com.example.project.Exception.BasicException;
import com.example.project.Model.CloudTestRequest;
import com.example.project.Model.UpdateRequest;
import com.example.project.Model.User;
import com.example.project.Service.DataService.DataService;
import com.example.project.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class MainController {

    private final MainService mainService;
    private final DataService dataService;

    @Autowired
    public MainController(MainService mainService, DataService dataService) {
        this.mainService = mainService;
        this.dataService = dataService;
    }

    @PostMapping("/")
    public ResponseEntity<String> getNamePost (@RequestBody CloudTestRequest cloudTestRequest) {
        String nameResponse = mainService.getMessage(cloudTestRequest.getName());
        String databaseConnection = dataService.healthCheck();
        String message = nameResponse + " " + databaseConnection + "    ";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getNameViaId (@PathVariable int id) {
        try {
            return new ResponseEntity<>(dataService.getUserById(id), HttpStatus.OK);
        } catch (Exception exception) {
            BasicException basicException = new BasicException(exception.getMessage(),exception.getCause().toString(), Arrays.toString(exception.getStackTrace()));
            return new ResponseEntity<>(basicException.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/user/")
    public ResponseEntity<Object> createUser (@RequestBody CloudTestRequest cloudTestRequest) {
        try {
            User user = dataService.createUser(cloudTestRequest.getName());
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Not Created", HttpStatusCode.valueOf(500));
            }
        } catch (Exception exception) {
            BasicException basicException = new BasicException(exception.getMessage(),exception.getCause().toString(), Arrays.toString(exception.getStackTrace()));
            return new ResponseEntity<>(basicException.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @PutMapping("/user/")
    public ResponseEntity<Object> updateUser (@RequestBody UpdateRequest updateRequest) {
        try {
            User user = dataService.updateUser(updateRequest.getName(), updateRequest.getUserId());
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Not Updated", HttpStatusCode.valueOf(500));
            }
        } catch (Exception exception) {
            BasicException basicException = new BasicException(exception.getMessage(),exception.getCause().toString(), Arrays.toString(exception.getStackTrace()));
            return new ResponseEntity<>(basicException.toString(), HttpStatusCode.valueOf(500));
        }
    }

}
