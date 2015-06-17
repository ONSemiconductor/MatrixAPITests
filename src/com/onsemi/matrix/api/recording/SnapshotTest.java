package com.onsemi.matrix.api.recording;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

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
public class SnapshotTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("snapshot", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("snapshot", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void snapshot_SetTo0_ShouldReturnOK() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK snapshot", "response contains 'OK snapshot'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void snapshot_SetTo1_ShouldReturnOK() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK snapshot", "response contains 'OK snapshot'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void snapshot_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG snapshot", "response contains 'NG snapshot'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void snapshot_SetTo3_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG snapshot", "response contains 'NG snapshot'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void snapshot_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG snapshot", "response contains 'NG snapshot'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void snapshot_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG snapshot", "response contains 'NG snapshot'");
	}
}
