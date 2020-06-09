package com.capgemini.service;

import java.util.List;
import java.util.Optional;
import com.capgemini.model.DiagnosticCenter;
import com.capgemini.model.Test;

public interface DiagnosticCenterService {

	public DiagnosticCenter saveDiagnosticCenter(DiagnosticCenter diagnosticCenter);

	public String deleteDiagnosticCenter(long centerId);

	public String updateDiagnosticCenter(long centerId,DiagnosticCenter diagnosticCenter);

	public Optional<DiagnosticCenter> findById(long centerId);

	public List<DiagnosticCenter> getAll();
	
	public Test addTest(long centerId,Test test);
	



}
