package com.studentapp.junit.studentsInfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase{

	@Steps
	StudentSerenitySteps steps;	
	
	@WithTags(
			{
			@WithTag("studentFeature:SMOKE"),
			@WithTag("studentFeature:NEGATIVE")
			}
			)
	@Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
	@Test
	public void inValidMethod() {
		SerenityRest
		.rest()
		.given()
		.when()
		.post("/list")
		.then()
		.statusCode(405)
		.log()
		.all();
	}
	
	
	@WithTags(
			{
			@WithTag("studentFeature:SMOKE"),
			@WithTag("studentFeature:POSITIVE")
			}
			)
	@Title("This test will verify that a status code of 200 is returned for our GET request")
	@Test
	public void verifyStatusIs200() {
	
		steps.getAllStudents().statusCode(200);
	}
	
	@WithTags(
			{
			@WithTag("studentFeature:SMOKE"),
			@WithTag("studentFeature:NEGATIVE")
			}
			)
	@Title("Returns a status code of 400 if the use tries to access an invalid resource")
	@Test
	public void incorrectResource() {
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/listfake")
		.then()
		.statusCode(400);
	}
	
}
