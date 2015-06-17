package com.onsemi.matrix.api.tests.video;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;

@RunWith( HttpJUnitRunner.class )
public class DefaultTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		setDefaultSettings();
	}
	
	@After
	public void resetSettingAfterTest() {
		setDefaultSettings();
	}
	
	private static void setDefaultSettings() {
		Utils.setValue("brightness", "128");
		Utils.setValue("contrast", "128");
		Utils.setValue("saturation", "128");
		Utils.setValue("sharpness", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?brightness=120&contrast=120&saturation=120&sharpness=0&shutterspeed=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void default_SetValues_ShouldSetValues() {
		Utils.printResponse(response);
		assertOk(response);
		checkParameter("brightness", "OK brightness=120");
		checkParameter("contrast", "OK contrast=120");
		checkParameter("saturation", "OK saturation=120");
		checkParameter("sharpness", "OK sharpness=0");
		checkParameter("shutterspeed", "OK shutterspeed=0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?brightness=512&contrast=120&saturation=120&sharpness=0&shutterspeed=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void default_SetValuesWithIncorrectBrightnessValue_ShouldSetValues() {
		Utils.printResponse(response);
		assertOk(response);
		checkParameter("brightness", "OK brightness=128"); // default value
		checkParameter("contrast", "OK contrast=120");
		checkParameter("saturation", "OK saturation=120");
		checkParameter("sharpness", "OK sharpness=0");
		checkParameter("shutterspeed", "OK shutterspeed=0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?brightness=120&contrast=512&saturation=512&sharpness=0&shutterspeed=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void default_SetValuesWithIncorrectContrastAndSaturationValues_ShouldSetValues() {
		Utils.printResponse(response);
		assertOk(response);
		checkParameter("brightness", "OK brightness=120");
		checkParameter("contrast", "OK contrast=128"); // default value
		checkParameter("saturation", "OK saturation=128"); // default value
		checkParameter("sharpness", "OK sharpness=0");
		checkParameter("shutterspeed", "OK shutterspeed=0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?brightness=120&contrast=120&saturation=120&sharpness=512&shutterspeed=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void default_SetValuesWithIncorrectSharpnessValue_ShouldSetValues() {
		Utils.printResponse(response);
		assertOk(response);
		checkParameter("brightness", "OK brightness=120");
		checkParameter("contrast", "OK contrast=120");
		checkParameter("saturation", "OK saturation=120"); 
		checkParameter("sharpness", "OK sharpness=0"); // default value
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?brightness=125&contrast=125", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void default_SetNotAllValues_ShouldSetValues() {
		Utils.printResponse(response);
		assertOk(response);
		checkParameter("brightness", "OK brightness=125");
		checkParameter("contrast", "OK contrast=125");
		checkParameter("saturation", "OK saturation=128"); // default value
		checkParameter("sharpness", "OK sharpness=0"); // default value
	}
	
	private static void checkParameter(String parameterName, String expectedValue) {
		String actualValue = Utils.sendRequest(String.format("/vb.htm?paratest=%s", parameterName)).getBody();
		assertTrue(String.format("Expected: '%s' Actual: '%s'", expectedValue, actualValue.replace("\n", "")), 
				actualValue.contains(expectedValue));
	}
}
