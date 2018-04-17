package com.studentapp.junit.studentsInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8080/student";
	}
	
	
	@Test
	public void getAllStudents(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	@Test
	public void thisIsAFailingTest() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
	}

	@Pending
	@Test
	public void thisIsPendingTest() {
		
	}
	
	@Ignore
	@Test
	public void thisIsSkippedTest() {
		
	}
	
	@Test
	public void thisIsErrorTest() {
		System.out.println("This is an error"+(5/0));
	}
	
	@Test
	public void fileDoesNotExist() throws FileNotFoundException {
		File file = new File("C://file.text");
		FileReader fr = new FileReader(file);
	}
	
	@Manual
	@Test
	public void thisIsAManualTest() {
	}
	
	@Title("This will get information for all of the students from Student App")
	@Test
	public void test01(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
}
