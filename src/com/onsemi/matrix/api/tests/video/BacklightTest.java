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
public class BacklightTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("backlight", "1");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("backlight", "1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=backlight", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void backlight_GetDefaultValue_ShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "backlight=1", "default value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void backlight_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK backlight", "response contains 'OK backlight'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=backlight"), 
				"backlight=0", "backlight value is 0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void backlight_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK backlight", "response contains 'OK backlight'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=backlight"),
				"backlight=1", "backlight value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=2", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void backlight_SetTo2_ValueShouldBe2() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK backlight", "response contains 'OK backlight'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=backlight"),
				"backlight=2", "backlight value is 2");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void backlight_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG backlight", "response contains 'NG backlight'");
		String backlightGetResponse = Utils.sendRequest("/vb.htm?paratest=backlight").getBody();
		assertTrue("backlight has default value", backlightGetResponse.contains("backlight=1"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=50", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void backlight_SetTo50_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG backlight", "response contains 'NG backlight'");
		String backlightGetResponse = Utils.sendRequest("/vb.htm?paratest=backlight").getBody();
		assertTrue("backlight has default value", backlightGetResponse.contains("backlight=1"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void backlight_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG backlight", "response contains 'NG backlight'");
		String backlightGetResponse = Utils.sendRequest("/vb.htm?paratest=backlight").getBody();
		assertTrue("backlight has default value", backlightGetResponse.contains("backlight=1"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?backlight=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 7)
	public void backlight_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG backlight", "response contains 'NG backlight'");
		String backlightGetResponse = Utils.sendRequest("/vb.htm?paratest=backlight").getBody();		
		assertTrue("backlight has default value", backlightGetResponse.contains("backlight=1"));
	}
}
