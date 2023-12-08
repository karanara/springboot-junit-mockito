package com.springboot.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import com.springboot.mockito.MvcTestingExampleApplication;
import com.springboot.mockito.models.CollegeStudent;
import com.springboot.mockito.models.StudentGrades;

@SpringBootTest(classes=MvcTestingExampleApplication.class)
public class ReflectionUtilsTest {
 
	@Autowired
	ApplicationContext context;
	
	@Autowired
	CollegeStudent collegeStudent;
	
	@Autowired
	StudentGrades studentGrade;
	
	@BeforeEach
	public void testBeforeEach() {
		collegeStudent.setFirstname("Ramya");
		collegeStudent.setLastname("Karanam");
		collegeStudent.setEmailAddress("kramya1209@gmail.com");
		collegeStudent.setStudentGrades(studentGrade);
		ReflectionTestUtils.setField(collegeStudent,"Id",1);
		ReflectionTestUtils.setField(collegeStudent,"studentGrades", new StudentGrades(new ArrayList<> (Arrays.asList(100.00,50.00,70.00))));
	}
	@DisplayName("Comparing ID")
	@Test
	public void testAssertId() {
		assertEquals(1,ReflectionTestUtils.getField(collegeStudent, "Id"),"id should be equal");
	}
	@DisplayName("calling private method")
	@Test
	public void testPrivateMethod() {
		assertEquals("Ramya  1",ReflectionTestUtils.invokeMethod(collegeStudent, "getFullNameAndId"), "Failed in calling private method");
	}
	


}
