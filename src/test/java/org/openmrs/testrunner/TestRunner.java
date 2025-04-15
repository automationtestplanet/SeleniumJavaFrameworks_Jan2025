package org.openmrs.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(
		features = "Features",
		glue = "org.openmrs.stepdefinition",
		plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		tags = "@RegisterPatient",
		snippets = SnippetType.CAMELCASE,
		dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {

}
