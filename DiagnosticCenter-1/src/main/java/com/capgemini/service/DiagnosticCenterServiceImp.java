package com.capgemini.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.dao.DiagnosticCenterDao;
import com.capgemini.dao.TestDao;
import com.capgemini.exception.DiagnosticCenterNotAdded;
import com.capgemini.exception.DiagnosticCenterNotRemovedException;
import com.capgemini.exception.InvalidException;
import com.capgemini.exception.NullException;
import com.capgemini.exception.WrongValueException;
import com.capgemini.model.DiagnosticCenter;
import com.capgemini.model.Test;

@Service
public class DiagnosticCenterServiceImp implements DiagnosticCenterService {
	@Autowired
	DiagnosticCenterDao diagnosticCenterDoa;
	@Autowired
	TestDao testDao;

	@Override
	public DiagnosticCenter saveDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		Optional<DiagnosticCenter> findById = diagnosticCenterDoa.findById(diagnosticCenter.getCenterId());
		if (!findById.isPresent()) {
			diagnosticCenterDoa.save(diagnosticCenter);
			return diagnosticCenter;
		} else {
			throw new DiagnosticCenterNotAdded("Diagnostic Center Already Added");
		}
	}

	@Override
	public String deleteDiagnosticCenter(long centerId) {
		if (centerId != 0) {
			Optional<DiagnosticCenter> findById = diagnosticCenterDoa.findById(centerId);
			if (findById.isPresent()) {
				diagnosticCenterDoa.deleteById(centerId);
				return "DiagnosticCenter  deleted";
			} else {
				throw new DiagnosticCenterNotRemovedException("Oop!! Diagnostic Center is not deleted ");
			}
		}

		else {
			throw new WrongValueException("Oop!!!CenterId Incorrect!!!!!!");
		}
	}

	@Override
	public String updateDiagnosticCenter(long centerId,DiagnosticCenter diagnosticCenter) {
		if (diagnosticCenter != null) {
			Optional<DiagnosticCenter> findById = diagnosticCenterDoa.findById(centerId);
			if (findById.isPresent()) {
				findById.get().setCenterName(diagnosticCenter.getCenterName());
				findById.get().setAddress(diagnosticCenter.getAddress());
				findById.get().setEmail(diagnosticCenter.getEmail());
				findById.get().setContactNo(diagnosticCenter.getContactNo());
				diagnosticCenterDoa.save(findById.get());
				return "DiagnosticCenter updated";
			} else {
				throw new NullException("Diagnostic Center is not Updated!!!!!!!");
			}
		} else {
			throw new WrongValueException("Details are Incorrect!!!!!!!!!!");
		}
	}

	@Override
	public Optional<DiagnosticCenter> findById(long centerId) {
		if (centerId != 0 && !(centerId < 0)) {
			Optional<DiagnosticCenter> findById = diagnosticCenterDoa.findById(centerId);
			return findById;
		} else {
			throw new InvalidException("CenterId is invalid");
		}
	}

	@Override
	public List<DiagnosticCenter> getAll() {
		// TODO Auto-generated method stub
		List<DiagnosticCenter> dList = diagnosticCenterDoa.findAll();
		if (dList == null) {
			throw new NullException("Diagnostic Center List is Empty !!!!!!");
		} else {
			return dList;
		}
	}
	 @Override
	    public Test addTest(long centerId,Test test) {
	        Optional<DiagnosticCenter> findById = diagnosticCenterDoa.findById(centerId);
	        if (findById.isPresent()) 
	        {
	        	//test.setTestId(test.getTestId());
	        	test.setTestName(test.getTestName());
	        	test.setCost(test.getCost());
	        	findById.get().getListOfTest().add(test);
	            diagnosticCenterDoa.save(findById.get());
	            return test;
	        } else {
	            throw new DiagnosticCenterNotAdded("Diagnostic Center is not found!!!!!");
	        }
	    }
}
