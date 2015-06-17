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
public class SaturationTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("saturation", "128");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("saturation", "128");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=saturation", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void saturation_GetDefaultValue_ShouldBe128() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "saturation=128", "default value is 128");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void saturation_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK saturation", "response contains 'OK saturation'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=saturation"), 
				"saturation=0", "saturation value is 0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=128", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void saturation_SetTo128_ValueShouldBe128() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK saturation", "response contains 'OK saturation'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=saturation"),
				"saturation=128", "saturation value is 128");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=255", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void saturation_SetTo255_ValueShouldBe255() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK saturation", "response contains 'OK saturation'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=saturation"),
				"saturation=255", "saturation value is 255");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void saturation_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG saturation", "response contains 'NG saturation'");
		String saturationGetResponse = Utils.sendRequest("/vb.htm?paratest=saturation").getBody();
		assertTrue("saturation has default value", saturationGetResponse.contains("saturation=128"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=512", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void saturation_SetTo512_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG saturation", "response contains 'NG saturation'");
		String saturationGetResponse = Utils.sendRequest("/vb.htm?paratest=saturation").getBody();
		assertTrue("saturation has default value", saturationGetResponse.contains("saturation=128"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void saturation_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG saturation", "response contains 'NG saturation'");
		String saturationGetResponse = Utils.sendRequest("/vb.htm?paratest=saturation").getBody();
		assertTrue("saturation has default value", saturationGetResponse.contains("saturation=128"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?saturation=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 7)
	public void saturation_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG saturation", "response contains 'NG saturation'");
		String saturationGetResponse = Utils.sendRequest("/vb.htm?paratest=saturation").getBody();		
		assertTrue("saturation has default value", saturationGetResponse.contains("saturation=128"));
	}
}