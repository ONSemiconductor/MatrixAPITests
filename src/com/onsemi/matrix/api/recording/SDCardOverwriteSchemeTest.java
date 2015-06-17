package com.onsemi.matrix.api.recording;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertFalse;
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
public class SDCardOverwriteSchemeTest {

	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("sdoverwrite_enable", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("sdoverwrite_enable", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=sdoverwrite_enable", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void sdcardoverwritescheme_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "sdoverwrite_enable=0", "default value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sdoverwrite_enable=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void sdcardoverwritescheme_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "sdoverwrite_enable", "response contains sdoverwrite_enable");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sdoverwrite_enable"), 
				"sdoverwrite_enable=0", "sdoverwrite_enable value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sdoverwrite_enable=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void sdcardoverwritescheme_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "sdoverwrite_enable", "response contains sdoverwrite_enable");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sdoverwrite_enable"),
				"sdoverwrite_enable=1", "sdoverwrite_enable value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sdoverwrite_enable=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void sdcardoverwritescheme_SetToNaN_ShouldThrowException() {
		Utils.printResponse(response);
		String sdOverwriteEnableSetResponse = response.getBody();
		assertFalse("Response should not contain OK", sdOverwriteEnableSetResponse.contains("OK"));
		String sdOverwriteEnableGetResponse = Utils.sendRequest("/vb.htm?paratest=sdoverwrite_enable").getBody();
		assertTrue("sdoverwrite_enable has default value", sdOverwriteEnableGetResponse.contains("sdoverwrite_enable=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sdoverwrite_enable=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void sdcardoverwritescheme_SetTo3_ShouldThrowException() {
		Utils.printResponse(response);
		String sdOverwriteEnableSetResponse = response.getBody();
		assertFalse("Response should not contain OK", sdOverwriteEnableSetResponse.contains("OK"));
		String sdOverwriteEnableGetResponse = Utils.sendRequest("/vb.htm?paratest=sdoverwrite_enable").getBody();
		assertTrue("sdoverwrite_enable has default value", sdOverwriteEnableGetResponse.contains("sdoverwrite_enable=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sdoverwrite_enable=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void sdcardoverwritescheme_SetToNegativeNumber_ShouldThrowException() {
		Utils.printResponse(response);
		String sdOverwriteEnableSetResponse = response.getBody();
		assertFalse("Response should not contain OK", sdOverwriteEnableSetResponse.contains("OK"));
		String sdOverwriteEnableGetResponse = Utils.sendRequest("/vb.htm?paratest=sdoverwrite_enable").getBody();
		assertTrue("sdoverwrite_enable has default value", sdOverwriteEnableGetResponse.contains("sdoverwrite_enable=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sdoverwrite_enable=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void sdcardoverwritescheme_SetToEmpty_ShouldThrowException() {
		Utils.printResponse(response);
		String sdOverwriteEnableSetResponse = response.getBody();
		assertFalse("Response should not contain OK", sdOverwriteEnableSetResponse.contains("OK"));
		String sdOverwriteEnableGetResponse = Utils.sendRequest("/vb.htm?paratest=sdoverwrite_enable").getBody();
		assertTrue("sdoverwrite_enable has default value", sdOverwriteEnableGetResponse.contains("sdoverwrite_enable=0"));
	}
}
