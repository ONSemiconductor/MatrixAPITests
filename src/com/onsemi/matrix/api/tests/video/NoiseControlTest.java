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
public class NoiseControlTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("tnfltctrl", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("tnfltctrl", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=tnfltctrl", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void noisecontrol_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "tnfltctrl=0", "default value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?tnfltctrl=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void noisecontrol_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK tnfltctrl", "response contains 'OK tnfltctrl'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=tnfltctrl"), 
				"tnfltctrl=0", "tnfltctrl value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?tnfltctrl=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void noisecontrol_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK tnfltctrl", "response contains 'OK tnfltctrl'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=tnfltctrl"),
				"tnfltctrl=1", "tnfltctrl value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?tnfltctrl=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void noisecontrol_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG tnfltctrl", "response contains 'NG tnfltctrl'");
		String noisecontrolGetResponse = Utils.sendRequest("/vb.htm?paratest=tnfltctrl").getBody();		
		assertTrue("tnfltctrl has default value", noisecontrolGetResponse.contains("tnfltctrl=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?tnfltctrl=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void noisecontrol_SetTo3_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG tnfltctrl", "response contains 'NG tnfltctrl'");
		String noisecontrolGetResponse = Utils.sendRequest("/vb.htm?paratest=tnfltctrl").getBody();		
		assertTrue("tnfltctrl has default value", noisecontrolGetResponse.contains("tnfltctrl=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?tnfltctrl=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void noisecontrol_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG tnfltctrl", "response contains 'NG tnfltctrl'");
		String noisecontrolGetResponse = Utils.sendRequest("/vb.htm?paratest=tnfltctrl").getBody();		
		assertTrue("tnfltctrl has default value", noisecontrolGetResponse.contains("tnfltctrl=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?tnfltctrl=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void noisecontrol_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "OK tnfltctrl", "response contains 'NG tnfltctrl'");
		String noisecontrolGetResponse = Utils.sendRequest("/vb.htm?paratest=tnfltctrl").getBody();		
		assertTrue("tnfltctrl has default value", noisecontrolGetResponse.contains("tnfltctrl=0"));
	}
}
