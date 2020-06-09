package com.capgemini.controller;

import com.capgemini.exception.NullException;
import com.capgemini.exception.WrongValueException;
import com.capgemini.model.Test;
import com.capgemini.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DiagnosticCenter")
@CrossOrigin("http://localhost:4200")
public class TestController {
	
    @Autowired
    TestService testService;
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/getAllLabTests")
    public ResponseEntity<List<Test>> getAll() {
        try {
            List<Test> list = testService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            throw new NullException(e.getMessage());
        }
    }

    @PostMapping("/addLabTest")
    public ResponseEntity<Test> addLabTest(@RequestBody Test test) {
        try {
            testService.addTest(test);
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            throw new WrongValueException(e.getMessage());
        }
    }
    @DeleteMapping("/deleteLabTest/{testId}")
    public ResponseEntity<?> deleteLabTest(@PathVariable("testId") int testId) {
        try {
            testService.deleteTest(testId);
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            throw new NullException(e.getMessage());
        }
    }
    @PutMapping("/updateLabTest")
    public ResponseEntity<?> updateLabTest(@RequestBody Test test) {
        try {
            testService.updateTest(test);
            return new ResponseEntity<String>("Updated", HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            throw new WrongValueException(e.getMessage());
        }
    }
}
