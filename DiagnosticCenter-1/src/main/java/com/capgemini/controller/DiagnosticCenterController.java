package com.capgemini.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.DiagnosticCenterNotRemovedException;
import com.capgemini.exception.NullException;
import com.capgemini.exception.WrongValueException;
import com.capgemini.model.DiagnosticCenter;
import com.capgemini.model.Test;
import com.capgemini.service.DiagnosticCenterService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/************************************************************************************
 * @author Ramesh Yadav
 * 
 *         Description It is a Diagnostic center controller class that provides
 *         the functionality to interact customer to our product and return to
 *         the requests to frontend
 *         Created Date 27-APR-2020
 ************************************************************************************/

@RestController
@RequestMapping("/DiagnosticCenter")
@CrossOrigin(origins="http://localhost:4200")
public class DiagnosticCenterController {
	@Autowired
	DiagnosticCenterService service;
	Logger logger = LoggerFactory.getLogger(DiagnosticCenterController.class);

	/************************************************************************************
	 * Method: getAllCenter Description: To View the center details
	 * @param null- NA
	 * @returns ResponseEntity - 200,Ok if there is atleast one center is present
	 * @throws NullException- It is raised due to empty list of center
	 ************************************************************************************/

	@GetMapping("/getAllCenter")
	@ApiOperation(value = "Get all the Diagnostic Center", notes = "Here we can get the Diagnostic Center Details", response = DiagnosticCenter.class)
	public ResponseEntity<List<DiagnosticCenter>> getAll() {
		try {
			List<DiagnosticCenter> list = service.getAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new NullException(e.getMessage());
		}
	}

	/************************************************************************************
	 * Method: getCenter Description: To view center and tests present under it
	 * @param centerId - center's id
	 * @returns ResponseEntity - 200 OK, if centerId exists
	 * @throws NullException- It is raised due to invalid center id
	 ************************************************************************************/

	@GetMapping("/getCenter/{centerId}")
	@ApiOperation(value = "Get all the Diagnostic Center detail of specific Center Id", notes = "Here we can get the Diagnostic Center Details", response = DiagnosticCenter.class)
	public ResponseEntity<DiagnosticCenter> getCenter(
			@ApiParam(value = "CenterId of Diagnostic Center you need to retrieve", required = true) @Valid @PathVariable("centerId") long centerId) {
		Optional<DiagnosticCenter> center = service.findById(centerId);
		try {
			if (center.isPresent()) {
				return new ResponseEntity<DiagnosticCenter>(center.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new NullException(e.getMessage());
		}
		return new ResponseEntity<DiagnosticCenter>(HttpStatus.NOT_FOUND);
	}

	/************************************************************************************
	 * Method: addCenter Description: To add center
	 * @param centerId- center's id
	 * @returns String - Center added successfully
	 * @throws NullException- It is raised due to wrong format of data
	 ************************************************************************************/

	@PostMapping("/add")
	@ApiOperation(value = "Add the Diagnostic Center", notes = "Here we can add the Diagnostic Center Details", response = DiagnosticCenter.class)
	public ResponseEntity<DiagnosticCenter> addDiagnosticCenter(@Valid @RequestBody DiagnosticCenter diagnosticCenter) {
		try {
			service.saveDiagnosticCenter(diagnosticCenter);
			return new ResponseEntity<>(diagnosticCenter, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WrongValueException(e.getMessage());
		}
	}

	/************************************************************************************
	 * Method: removeCenter Description: To remove center details
	 * @param centerId - center's id
	 * @returns String - Center deleted successfully
	 * @throws NullException- It is raised due to invalid center id
	 ************************************************************************************/

	@DeleteMapping("/delete/{centerId}")
	@ApiOperation(value = "delete  the specific Diagnostic Center ", notes = "Here we can delete the Diagnostic Center Details by Id", response = DiagnosticCenter.class)
	public ResponseEntity<?> deleteDiagnosticCenter(@Valid @PathVariable("centerId") long centerId) {
		try {
			service.deleteDiagnosticCenter(centerId);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			throw new DiagnosticCenterNotRemovedException(e.getMessage());
		}
	}

	/************************************************************************************
	 * Method: updateCenter Description: To update center details
	 * @param centerId- center's id
	 * @returns String - Center updated successfully
	 * @throws NullException- It is raised due to invalid center id
	 ************************************************************************************/
	@PutMapping("/update/{centerId}")
	@ApiOperation(value = "Update the Diagnostic Center Details", notes = "Here we can update the Diagnostic Center by Id", response = DiagnosticCenter.class)
	public ResponseEntity<?> updateDiagnosticCenter(@Valid @PathVariable("centerId") long centerId, @RequestBody DiagnosticCenter diagnosticCenter) {
		try {
			service.updateDiagnosticCenter(centerId,diagnosticCenter);
			return new ResponseEntity<String>("Updated", HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			throw new WrongValueException(e.getMessage());
		}
	}
	/************************************************************************************
	 * Method: addTest
     * Description: To add Test under a particular Diagnostic Center
	 * @param centerId       - center's id
	 * @returns ResponseEntity      - true if center Id is correct and test added successfully
	 * @throws WrongValueException - It is raised due to invalid centerId and wrong format of test 
	 ************************************************************************************/
	@PostMapping("addTests/{centerId}")
	public ResponseEntity<Test> addTest(@PathVariable long centerId, @RequestBody Test test) {	
		try {
		service.addTest(centerId, test);
		return new ResponseEntity<>(test,HttpStatus.OK);
		}
		catch(Exception e) {
			throw new WrongValueException(e.getMessage());
		}
	}
	/************************************************************************************
	 * Method: getCenterTest Description: To View the center Test via CenterId
	 * @param null- CenterId
	 * @returns ResponseEntity - 200,Ok if there is center present having centerId and return all the test available
	 * @throws NullException- It is raised due to empty list of Test
	 ************************************************************************************/
	
	@GetMapping("/getCenterTest/{centerId}")
	 public ResponseEntity<?> getCenterTest( @Valid @PathVariable("centerId") long centerId) {
	 Optional<DiagnosticCenter> center = service.findById(centerId);
	 try {
	  if (center.isPresent()) {
	  return new ResponseEntity<List<Test>>(center.get().getListOfTest(), HttpStatus.OK);
	  }
	 } catch (Exception e) {
	  throw new NullException(e.getMessage());
	 }
	 return new ResponseEntity<DiagnosticCenter>(HttpStatus.NOT_FOUND);

	 }

}
