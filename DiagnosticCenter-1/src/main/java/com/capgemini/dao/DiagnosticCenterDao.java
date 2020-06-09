package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.DiagnosticCenter;

@Repository
public interface DiagnosticCenterDao extends JpaRepository<DiagnosticCenter, Long> {

}
