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
public class VideoResolutionTest {
	
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("video_resolution_pri_1", "0");
	}
	
	@After
	public void resetSettingAfterTest() throws InterruptedException {
		Utils.setValue("video_resolution_pri_1", "0");
		Thread.sleep(Settings.getAfterTestDelay());
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=video_resolution_pri_1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void video_resolution_pri_1_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_resolution_pri_1=0", "default value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void videoresolutionpri1_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK video_resolution_pri_1", "Response doesn't contain 'OK video_resolution_pri_1'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1"), 
				"video_resolution_pri_1=0", "Video_resolution_pri_1 value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void videoresolutionpri1_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK video_resolution_pri_1", "Response doesn't contain 'OK video_resolution_pri_1'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1"),
				"video_resolution_pri_1=1", "Video_resolution_pri_1 value isn't equal 1");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=2", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void videoresolutionpri1_SetTo2_ValueShouldBe2() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK video_resolution_pri_1", "Response doesn't contain 'OK video_resolution_pri_1'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1"), 
				"video_resolution_pri_1=2", "Video_resolution_pri_1 value isn't equal 2");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void videoresolutionpri1_SetTo3_ValueShouldBe3() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK video_resolution_pri_1", "Response doesn't contain 'OK video_resolution_pri_1'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1"),
				"video_resolution_pri_1=3", "Video_resolution_pri_1 value isn't equal 3");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=NaN", 
			authentications = { @Authentication(type = BASIC, user = "admin", password = "admin") }, order = 5)
	public void videoresolutionpri1_SetToNaN_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videoresolutionpri1SetResponse = response.getBody();
		assertTrue("Response doesn't contain 'NG video_resolution_pri_1'", videoresolutionpri1SetResponse.contains("NG video_resolution_pri_1"));
		String videoresolutionpri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1").getBody();
		assertTrue("video_resolution_pri_1 doesn't have default value", videoresolutionpri1GetResponse.contains("video_resolution_pri_1=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=10", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void videoresolutionpri1_SetTo10_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videoresolutionpri1SetResponse = response.getBody();
		assertTrue("Response doesn't contain 'NG video_resolution_pri_1'", videoresolutionpri1SetResponse.contains("NG video_resolution_pri_1"));
		String videoresolutionpri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1").getBody();
		assertTrue("video_resolution_pri_1 doesn't have default value", videoresolutionpri1GetResponse.contains("video_resolution_pri_1=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 7)
	public void videoresolutionpri1_SetToNegativeNumber_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videoresolutionpri1SetResponse = response.getBody();
		assertTrue("Response doesn't contain 'NG video_resolution_pri_1'", videoresolutionpri1SetResponse.contains("NG video_resolution_pri_1"));
		String videoresolutionpri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1").getBody();
		assertTrue("video_resolution_pri_1 doesn't have default value", videoresolutionpri1GetResponse.contains("video_resolution_pri_1=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_resolution_pri_1=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 8)
	public void videoresolutionpri1_SetToEmpty_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videoresolutionpri1SetResponse = response.getBody();
		assertTrue("Response doesn't contain 'NG video_resolution_pri_1'", videoresolutionpri1SetResponse.contains("NG video_resolution_pri_1"));
		String videoresolutionpri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_resolution_pri_1").getBody();
		assertTrue("video_resolution_pri_1 doesn't have default value", videoresolutionpri1GetResponse.contains("video_resolution_pri_1=0"));
	}

}
