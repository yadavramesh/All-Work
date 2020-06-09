package com.capgemini.testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.DiagnosticCenterDao;
import com.capgemini.model.DiagnosticCenter;
import com.capgemini.service.DiagnosticCenterServiceImp;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DiagnosticCenterTest {

@Mock
DiagnosticCenterDao diagnosticCenterDao;

@InjectMocks
DiagnosticCenterServiceImp service;
	
 @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
    @Test
    public void findByIdTest()
    {
    	long centerId=1000;
    	DiagnosticCenter diagnosticCenter=new DiagnosticCenter(1000,"AIIMS", "8956235623", "Gomti Nagar", "aiims@gmail.com");
    	Optional<DiagnosticCenter> dCenter=Optional.of(diagnosticCenter);
    	when(service.findById(centerId)).thenReturn(dCenter);
    	assertEquals(dCenter, diagnosticCenterDao.findById(centerId));
    	
    }


	@Test
	 public void getAllTest() {
      List<DiagnosticCenter> dlist = new ArrayList<DiagnosticCenter>();
      DiagnosticCenter dOne  = new DiagnosticCenter(1000,"AIIMS", "8956235623", "Gomti Nagar", "aiims@gmail.com");
      DiagnosticCenter dTwo = new DiagnosticCenter(1001,"Alex","8954124512","Vikaskhand","alex@gmail.com");
      DiagnosticCenter dThree = new DiagnosticCenter(1002,"Zynack","8974124512","MohanlalaGanj","zynack@gmail.com");
       
      dlist.add(dOne);
      dlist.add(dTwo);
      dlist.add(dThree);
       
      when(service.getAll()).thenReturn(dlist);
       
      //test
      List<DiagnosticCenter> dlists = diagnosticCenterDao.findAll();
       
      assertEquals(3, dlists.size());
      verify(diagnosticCenterDao, times(1)).findAll();
		
	}
	
	
}
