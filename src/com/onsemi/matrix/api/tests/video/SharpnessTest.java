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
public class SharpnessTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("sharpness", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("sharpness", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=sharpness", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void sharpness_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "sharpness=0", "default value isn't 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void sharpness_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK sharpness", "response doesn't contain 'OK sharpness'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sharpness"), 
				"sharpness=0", "sharpness value isn't 0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=5", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void sharpness_SetTo5_ValueShouldBe5() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK sharpness", "response doesn't contain 'OK sharpness'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sharpness"),
				"sharpness=5", "sharpness value isn't 5");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=25", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void sharpness_SetTo25_ValueShouldBe25() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK sharpness", "response doesn't contain 'OK sharpness'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sharpness"),
				"sharpness=25", "sharpness value isn't 25");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void sharpness_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG sharpness", "response doesn't contain 'NG sharpness'");
		String sharpnessGetResponse = Utils.sendRequest("/vb.htm?paratest=sharpness").getBody();
		assertTrue("sharpness doesn't have default value", sharpnessGetResponse.contains("sharpness=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=512", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void sharpness_SetTo512_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG sharpness", "response doesn't contain 'NG sharpness'");
		String sharpnessGetResponse = Utils.sendRequest("/vb.htm?paratest=sharpness").getBody();
		assertTrue("sharpness doesn't have default value", sharpnessGetResponse.contains("sharpness=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void sharpness_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG sharpness", "response doesn't contain 'NG sharpness'");
		String sharpnessGetResponse = Utils.sendRequest("/vb.htm?paratest=sharpness").getBody();
		assertTrue("sharpness doesn't have default value", sharpnessGetResponse.contains("sharpness=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?sharpness=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 7)
	public void sharpness_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG sharpness", "response doesn't contain 'NG sharpness'");
		String sharpnessGetResponse = Utils.sendRequest("/vb.htm?paratest=sharpness").getBody();		
		assertTrue("sharpness doesn't have default value", sharpnessGetResponse.contains("sharpness=0"));
	}

}
