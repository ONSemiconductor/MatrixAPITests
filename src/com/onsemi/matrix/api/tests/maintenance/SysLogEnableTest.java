/*
** Copyright 2015 ON Semiconductor
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**  http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/

package com.onsemi.matrix.api.tests.maintenance;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

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
public class SysLogEnableTest extends TestCase {

	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("log_enable", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("log_enable", "0");
	}

	@HttpTest(method = Method.GET, path = "vb.htm?paratest=log_enable", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void syslogenable_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "log_enable=0", "Default log_enable value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "vb.htm?log_enable=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void syslogenable_SetParameterValueTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "log_enable", "Response doesn't contain log_enable");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=log_enable"), 
				"log_enable=0", "Log_enable value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "vb.htm?log_enable=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void syslogenable_SetParameterValueTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "log_enable", "Response doesn't contain log_enable");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=log_enable"),
				"log_enable=1", "Log_enable value isn't equal 1");
	}

	@HttpTest(method = Method.GET, path = "vb.htm?log_enable=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void syslogenable_SetParameterValueToNaN_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String syslogenableSetResponse = response.getBody();
		assertFalse("Response contains OK", syslogenableSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", syslogenableSetResponse.contains("NG"));
		assertTrue("Response doesn't contain log_enable", syslogenableSetResponse.contains("log_enable"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=log_enable"), "NaN",
				"Log_enable equals NaN");
		String syslogenableGetResponse = Utils.sendRequest("/vb.htm?paratest=log_enable").getBody();
		assertTrue("Log_enable hasn't default value", syslogenableGetResponse.contains("log_enable=0"));
	}

	@HttpTest(method = Method.GET, path = "vb.htm?log_enable=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void syslogenable_SetParameterValueTo3_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String syslogenableSetResponse = response.getBody();
		assertFalse("Response contains OK", syslogenableSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", syslogenableSetResponse.contains("NG"));
		assertTrue("Response doesn't contain log_enable", syslogenableSetResponse.contains("log_enable"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=log_enable"), "3",
				"Log_enable equals 3");
		String syslogenableGetResponse = Utils.sendRequest("/vb.htm?paratest=log_enable").getBody();
		assertTrue("Log_enable hasn't default value", syslogenableGetResponse.contains("log_enable=0"));
	}

	@HttpTest(method = Method.GET, path = "vb.htm?log_enable=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void syslogenable_SetParameterValueToNegative_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String syslogenableSetResponse = response.getBody();
		assertFalse("Response contains OK", syslogenableSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", syslogenableSetResponse.contains("NG"));
		assertTrue("Response doesn't contain log_enable", syslogenableSetResponse.contains("log_enable"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=log_enable"), "-1",
				"Log_enable equals -1");
		String syslogenableGetResponse = Utils.sendRequest("/vb.htm?paratest=log_enable").getBody();
		assertTrue("Log_enable hasn't default value", syslogenableGetResponse.contains("log_enable=0"));
	}

	@HttpTest(method = Method.GET, path = "vb.htm?log_enable=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void syslogenable_SetParameterValueToEmpty_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String syslogenableSetResponse = response.getBody();
		assertFalse("Response contains OK", syslogenableSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", syslogenableSetResponse.contains("NG"));
		assertTrue("Response doesn't contain log_enable", syslogenableSetResponse.contains("log_enable"));
		String syslogenableGetResponse = Utils.sendRequest("/vb.htm?paratest=log_enable").getBody();
		assertTrue("Log_enable hasn't default value", syslogenableGetResponse.contains("log_enable=0"));
	}

}
