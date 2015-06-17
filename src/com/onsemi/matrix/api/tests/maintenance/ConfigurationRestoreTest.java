package com.onsemi.matrix.api.tests.maintenance;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

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

// test can change IP on default (192.168.1.168) -> camera is unavailable
@RunWith( HttpJUnitRunner.class )
public class ConfigurationRestoreTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@HttpTest(method = Method.GET, path = "/vb.htm?restoreconfig", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void configurationrestore_RestoreConfiguration_ShouldReturnOK() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK restoreconfig", "response contains 'OK restoreconfig'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?restoreconfig=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void configurationrestore_TryToSetTo0_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG restoreconfig", "response contains 'NG restoreconfig'");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?restoreconfig=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void configurationrestore_TryToSetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG restoreconfig", "response contains 'NG restoreconfig'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?restoreconfig=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void configurationrestore_TryToSetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG restoreconfig", "response contains 'NG restoreconfig'");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?restoreconfig=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void configurationrestore_TryToSetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG restoreconfig", "response contains 'NG restoreconfig'");
	}
}