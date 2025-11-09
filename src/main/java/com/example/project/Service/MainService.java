package com.example.project.Service;

import org.springframework.stereotype.Service;

@Service
public class MainService {

  public String getMessage(String name) {
    return "Hello " + name + "!";
  }

}
