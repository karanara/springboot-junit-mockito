package com.springboot.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.springboot.mockito.MvcTestingExampleApplication;
import com.springboot.mockito.dao.ApplicationDao;
import com.springboot.mockito.models.CollegeStudent;
import com.springboot.mockito.models.StudentGrades;
import com.springboot.mockito.service.ApplicationService;

@SpringBootTest(classes=MvcTestingExampleApplication.class)
public class MockitoTestClass {

	//@Mock
	@MockBean
	//MockBean used for that corner case and any dependencies can be injected
	private ApplicationDao applicationDao;
	
	//Inject Mocks only being used for dependencies having @Mock and @Spy
	//@InjectMocks
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	CollegeStudent collegeStudent;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	StudentGrades studentGrade;
	
	@BeforeEach
	public void BeforeTestMethod() {
		collegeStudent.setFirstname("Ramya");
		collegeStudent.setLastname("karanam");
		collegeStudent.setEmailAddress("kramya1209@gmail.com");
		collegeStudent.setStudentGrades(studentGrade);
	}
	
	@DisplayName("When and Verify")
	@Test
	public void assertEqualsandVerify() {
		//System.out.println(collegeStudent.getStudentGrades().getMathGradeResults());
		when(applicationDao.addGradeResultsForSingleClass(studentGrade.getMathGradeResults())).thenReturn(100.00);
		assertEquals(100.00,applicationService.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults())
				,"Value should be equal");
	}
	
	@DisplayName("Find GPA mock")
	@Test
	public void assertMockingGPA() {
		when(applicationDao.findGradePointAverage(studentGrade.getMathGradeResults())).thenReturn(90.0);
		assertEquals(90.0,applicationService.findGradePointAverage(collegeStudent.getStudentGrades().getMathGradeResults()),"gpa should be equal");
	}
	
	@DisplayName("Find Object is null")
	@Test
	public void testAssertNotNull() {
		when(applicationDao.checkNull(studentGrade.getMathGradeResults())).thenReturn(true);
		assertNotNull(applicationService.checkNull(collegeStudent.getStudentGrades().getMathGradeResults()));
	}
	@DisplayName("Throwing Run time")
	@Test
	public void testRunTimeThrowException() {
		CollegeStudent student1 = (CollegeStudent)context.getBean("collegeStudent");
		doThrow((RuntimeException.class)).when(applicationDao).checkNull(student1);
		assertThrows(RuntimeException.class,()->{applicationService.checkNull(student1);});
		verify(applicationDao).checkNull(student1);
	}
	@DisplayName("Multiple Assertion times")
	@Test
	public void testMultipleStubbingTimes() {
		CollegeStudent student2 = (CollegeStudent)context.getBean("collegeStudent");
        when(applicationDao.checkNull(student2)).thenThrow(RuntimeException.class).thenReturn("Dont throw exception from second time");
        assertThrows(RuntimeException.class,()->{applicationService.checkNull(student2);});
        assertEquals("Dont throw exception from second time",applicationService.checkNull(student2));
        verify(applicationDao,times(2)).checkNull(student2);
	}
	
}
