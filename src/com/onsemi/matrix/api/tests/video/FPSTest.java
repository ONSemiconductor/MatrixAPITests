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
public class FPSTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("video_fps_pri_1", "30");
	}
	
	@After
	public void resetSettingAfterTest() throws InterruptedException {
		Utils.setValue("video_fps_pri_1", "30");
		Thread.sleep(Settings.getAfterTestDelay());
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=video_fps_pri_1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void videofpspri1_GetDefaultValue_ShouldBe30() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1=30", "Default video_fps_pri_1 value isn't equal 30");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=5", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void videofpspri1_SetTo5_ValueShouldBe5() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1", "Response doesn't contain video_fps_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"), 
				"video_fps_pri_1=5", "Video_fps_pri_1 value isn't equal 5");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=10", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void videofpspri1_SetTo10_ValueShouldBe10() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1", "Response doesn't contain video_fps_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"),
				"video_fps_pri_1=10", "Video_fps_pri_1 value isn't equal 10");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=15", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void videofpspri1_SetTo15_ValueShouldBe15() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1", "Response doesn't contain video_fps_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"), 
				"video_fps_pri_1=15", "Video_fps_pri_1 value isn't equal 15");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=20", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void videofpspri1_SetTo20_ValueShouldBe20() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1", "Response doesn't contain video_fps_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"),
				"video_fps_pri_1=20", "Video_fps_pri_1 value isn't equal 20");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=25", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void videofpspri1_SetTo25_ValueShouldBe25() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1", "Response doesn't contain video_fps_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"), 
				"video_fps_pri_1=25", "Video_fps_pri_1 value isn't equal 25");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=30", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void videofpspri1_SetTo30_ValueShouldBe30() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_fps_pri_1", "Response doesn't contain video_fps_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"),
				"video_fps_pri_1=30", "Video_fps_pri_1 value isn't equal 30");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 7)
	public void videofpspri1_SetToNaN_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videofpspri1SetResponse = response.getBody();
		assertFalse("Response contains OK", videofpspri1SetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", videofpspri1SetResponse.contains("NG"));
		assertTrue("Response doesn't contain video_fps_pri_1", videofpspri1SetResponse.contains("video_fps_pri_1"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"), "NaN",
				"Video_fps_pri_1 equals NaN");
		String videofpspri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1").getBody();
		assertTrue("Video_fps_pri_1 hasn't default value", videofpspri1GetResponse.contains("video_fps_pri_1=30"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=120", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 8)
	public void videofpspri1_SetTo120_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videofpspri1SetResponse = response.getBody();
		assertFalse("Response contains OK", videofpspri1SetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", videofpspri1SetResponse.contains("NG"));
		assertTrue("Response doesn't contain video_fps_pri_1", videofpspri1SetResponse.contains("video_fps_pri_1"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"), "120",
				"Video_fps_pri_1 equals 120");
		String videofpspri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1").getBody();
		assertTrue("Video_fps_pri_1 hasn't default value", videofpspri1GetResponse.contains("video_fps_pri_1=30"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 9)
	public void videofpspri1_SetToNegativeNumber_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videofpspri1SetResponse = response.getBody();
		assertFalse("Response contains OK", videofpspri1SetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", videofpspri1SetResponse.contains("NG"));
		assertTrue("Response doesn't contain video_fps_pri_1", videofpspri1SetResponse.contains("video_fps_pri_1"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1"), "-1",
				"Video_fps_pri_1 equals -1");
		String videofpspri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1").getBody();
		assertTrue("Video_fps_pri_1 hasn't default value", videofpspri1GetResponse.contains("video_fps_pri_1=30"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_fps_pri_1=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 10)
	public void videofpspri1_SetToEmpty_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String videofpspri1SetResponse = response.getBody();
		assertFalse("Response contains OK", videofpspri1SetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", videofpspri1SetResponse.contains("NG"));
		assertTrue("Response doesn't contain video_fps_pri_1", videofpspri1SetResponse.contains("video_fps_pri_1"));
		String videofpspri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_fps_pri_1").getBody();		
		assertTrue("Video_fps_pri_1 hasn't default value", videofpspri1GetResponse.contains("video_fps_pri_1=30"));
	}
}
