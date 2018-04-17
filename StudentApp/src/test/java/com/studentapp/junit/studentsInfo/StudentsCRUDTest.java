package com.studentapp.junit.studentsInfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {

static String firstName = TestUtils.getRandomValue()+"SMOKEUSERFIRST";
static String lastName = TestUtils.getRandomValue()+"SMOKEUSERLAST";
static String email = TestUtils.getRandomValue()+"xyz@gmail.com";
static String programme = "ComputerScience";
static int studentId;

@Steps
StudentSerenitySteps steps;

@Title("This test will create a new student")
@Test
public void test001CreateStudent(){
	
	ArrayList<String> courses = new ArrayList<String>();
	courses.add("Java");
	courses.add("C++");
	
	steps.createStudent(firstName, lastName, email, programme, courses)
	.statusCode(201)
	.spec(ReusableSpecifications.getGenericResponseSpec());
	
}

@Title("Verify that the student is added to the application")
@Test
public void test002GetStudent() {
		
	HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
	assertThat(value,hasValue(firstName));
	
	studentId = (int) value.get("id");
	
	}

@Title("Update user information and verify updated information")
@Test
public void test003UpdateStudent() {

	ArrayList<String> courses = new ArrayList<String>();
	courses.add("Java");
	courses.add("C++");
	
	firstName = firstName+"_Updated";
	
	steps.updateStudent(studentId, firstName, lastName, email, programme, courses);	
	HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
	assertThat(value,hasValue(firstName));
	
}

@Title("Delete student and verify that the student is deleted")
@Test
public void test004DeleteStudent() {

	steps.DeleteStudent(studentId);
	steps.getStudentId(studentId).statusCode(404)
	.spec(ReusableSpecifications.getGenericResponseSpec());
}

}

