/** Copyright 2015 ON Semiconductor
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

package com.onsemi.matrix.api.tests.recording;

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
public class CloudEnableTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());

	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;

	@BeforeClass
	public static void resetSettingsBeforeTests() {
		Utils.setValue("cloud_enable", "0");
	}

	@After
	public void resetSettingsAfterTest() throws InterruptedException {
		Utils.setValue("cloud_enable", "0");
		Thread.sleep(Settings.getAfterTestDelay());
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=cloud_enable", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void cloudenable_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "cloud_enable=0", "Default value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?cloud_enable=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void cloudenable_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK cloud_enable", "Response doesn't contain 'OK cloud_enable'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_enable"), 
				"cloud_enable=0", "cloud_enable value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?cloud_enable=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void cloudenable_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK cloud_enable", "Response doesn't contain 'OK cloud_enable'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_enable"), 
				"cloud_enable=1", "cloud_enable value isn't equal 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?cloud_enable=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void cloudenable_SetToNaN_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String cloudenable = response.getBody();
		assertTrue("Response doesn't contain 'NG cloud_enable'", cloudenable.contains("NG cloud_enable"));
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_enable"),
				"cloud_enable=0", "cloud_enable doesn't have default value");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?cloud_enable=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void cloudenable_SetTo3_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String cloudenable = response.getBody();
		assertTrue("Response doesn't contain 'NG cloud_enable'", cloudenable.contains("NG cloud_enable"));
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_enable"),
				"cloud_enable=0", "cloud_enable doesn't have default value");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?cloud_enable=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void cloudenable_SetToNegativeNumber_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String cloudenable = response.getBody();
		assertTrue("Response doesn't contain 'NG cloud_enable'", cloudenable.contains("NG cloud_enable"));
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_enable"),
				"cloud_enable=0", "cloud_enable doesn't have default value");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?cloud_enable=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void cloudenable_SetToEmpty_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String cloudenable = response.getBody();
		assertTrue("Response doesn't contain 'NG cloud_enable'", cloudenable.contains("NG cloud_enable"));
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_enable"),
				"cloud_enable=0", "cloud_enable doesn't have default value");
	}
}
