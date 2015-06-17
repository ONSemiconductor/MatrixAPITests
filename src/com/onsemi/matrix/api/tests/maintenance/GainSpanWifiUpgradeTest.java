package com.onsemi.matrix.api.tests.maintenance;

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
public class GainSpanWifiUpgradeTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("firmwareupgrade_gs", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("firmwareupgrade_gs", "0");
	}

	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=firmwareupgrade_gs", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void gainspanwifiupgrade_GetValue_ShouldReturnOK() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK firmwareupgrade_gs", "response contains 'OK firmwareupgrade_gs'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?firmwareupgrade_gs=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void gainspanwifiupgrade_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK firmwareupgrade_gs", "response contains 'OK firmwareupgrade_gs'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=firmwareupgrade_gs"), 
				"firmwareupgrade_gs=0", "firmwareupgrade_gs value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?firmwareupgrade_gs=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void gainspanwifiupgrade_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK firmwareupgrade_gs", "response contains 'OK firmwareupgrade_gs'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=firmwareupgrade_gs"),
				"firmwareupgrade_gs=1", "firmwareupgrade_gs value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?firmwareupgrade_gs=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void gainspanwifiupgrade_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG firmwareupgrade_gs", "response contains 'NG firmwareupgrade_gs'");
		String firmwareupgradegsGetResponse = Utils.sendRequest("/vb.htm?paratest=firmwareupgrade_gs").getBody();
		assertTrue("firmwareupgrade_gs has default value", firmwareupgradegsGetResponse.contains("firmwareupgrade_gs=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?firmwareupgrade_gs=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void gainspanwifiupgrade_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG firmwareupgrade_gs", "response contains 'NG firmwareupgrade_gs'");
		String firmwareupgradegsGetResponse = Utils.sendRequest("/vb.htm?paratest=firmwareupgrade_gs").getBody();
		assertTrue("firmwareupgrade_gs has default value", firmwareupgradegsGetResponse.contains("firmwareupgrade_gs=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?firmwareupgrade_gs=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void gainspanwifiupgrade_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG firmwareupgrade_gs", "response contains 'NG firmwareupgrade_gs'");
		String firmwareupgradegsGetResponse = Utils.sendRequest("/vb.htm?paratest=firmwareupgrade_gs").getBody();
		assertTrue("firmwareupgrade_gs has default value", firmwareupgradegsGetResponse.contains("firmwareupgrade_gs=0"));
	}
}