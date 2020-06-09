package com.capgemini.service;

import com.capgemini.model.Test;

import java.util.List;

public interface TestService {
    public Test addTest(Test test) ;
    public String deleteTest(int testId);
    public String updateTest(Test test);
    public List<Test> getAll();
}
