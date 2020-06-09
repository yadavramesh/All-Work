package com.capgemini.service;

import com.capgemini.dao.TestDao;
import com.capgemini.exception.*;
import com.capgemini.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service

public class TestServiceImp implements TestService {
    @Autowired
    TestDao testDao;

    @Override
    public Test addTest(Test test) {
        Optional<Test> findById = testDao.findById(test.getTestId());
        if (!findById.isPresent()) {
        	testDao.save(test);
            return test;
        } else {
            throw new DiagnosticCenterNotAdded("Diagnostic Center is not found!!!!!");
        }
    }

    @Override
    public String deleteTest(int testId) {
        if (testId != 0) {
            Optional<Test> findById = testDao.findById(testId);
            if (findById.isPresent()) {
                testDao.deleteById(testId);
                return "Test  Remove";
            } else
                throw new TestNotRemoveException("Test Not Deleted");
        } else {
            throw new WrongValueException("TestId Incorrect");
        }
    }

    @Override
    public String updateTest( Test test) {
        Optional<Test> findById = testDao.findById(test.getTestId());
        if (findById.isPresent()) {
            testDao.save(test);
            return "Test updated";

        } else
            throw new ResourceNotFoundException("Test not Updated");
    }

    @Override
    public List<Test> getAll() {
        List<Test> testList = testDao.findAll();
        if (testList == null) {
            throw new NullException("Test Lab List is Empty !!!!!!");
        } else {
            return testList;
        }
    }
}
