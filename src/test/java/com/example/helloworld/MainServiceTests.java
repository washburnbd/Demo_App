package com.example.helloworld;

import com.example.project.Service.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MainService.class)
public class MainServiceTests {

    @Autowired
    private MainService mainService;

    @Test
    void testName() {
        String expectedResult = "Hello Person!";
        String result = mainService.getMessage("Person");
        assert(expectedResult.equals(result));
    }
}
