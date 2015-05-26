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
public class RateControlTest {
	
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.DefaultTimeout);

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("video_ratecontrol_pri_1", "0");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("video_ratecontrol_pri_1", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=video_ratecontrol_pri_1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void videoratecontrolpri1_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_ratecontrol_pri_1=0", "default video_ratecontrol_pri_1 value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_ratecontrol_pri_1=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void videoratecontrolpri1_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_ratecontrol_pri_1", "response contains video_ratecontrol_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_ratecontrol_pri_1"), 
				"video_ratecontrol_pri_1=0", "video_ratecontrol_pri_1 value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_ratecontrol_pri_1=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void videoratecontrolpri1_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "video_ratecontrol_pri_1", "response contains video_ratecontrol_pri_1");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=video_ratecontrol_pri_1"),
				"video_ratecontrol_pri_1=1", "video_ratecontrol_pri_1 value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_ratecontrol_pri_1=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void videoratecontrolpri1_SetToNaN_ShouldThrowException() {
		Utils.printResponse(response);
		String videoratecontrolpri1SetResponse = response.getBody();
		assertFalse("Response should not contain OK", videoratecontrolpri1SetResponse.contains("OK"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=video_ratecontrol_pri_1"), "NaN",
				"video_ratecontrol_pri_1 not equal NaN");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_ratecontrol_pri_1=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void videoratecontrolpri1_SetTo3_ShouldThrowException() {
		Utils.printResponse(response);
		String videoratecontrolpri1SetResponse = response.getBody();
		assertFalse("Response should not contain OK", videoratecontrolpri1SetResponse.contains("OK"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=video_ratecontrol_pri_1"), "3",
				"video_ratecontrol_pri_1 not equal 3");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_ratecontrol_pri_1=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void videoratecontrolpri1_SetToNegativeNumber_ShouldThrowException() {
		Utils.printResponse(response);
		String videoratecontrolpri1SetResponse = response.getBody();
		assertFalse("Response should not contain OK", videoratecontrolpri1SetResponse.contains("OK"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=video_ratecontrol_pri_1"), "-1",
				"video_ratecontrol_pri_1 not equal -1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?video_ratecontrol_pri_1=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void videoratecontrolpri1_SetToEmpty_ShouldThrowException() {
		Utils.printResponse(response);
		String videoratecontrolpri1SetResponse = response.getBody();
		assertFalse("Response should not contain OK", videoratecontrolpri1SetResponse.contains("OK"));
		String videoratecontrolpri1GetResponse = Utils.sendRequest("/vb.htm?paratest=video_ratecontrol_pri_1").getBody();
		assertTrue("video_ratecontrol_pri_1 has default value", videoratecontrolpri1GetResponse.contains("video_ratecontrol_pri_1=0"));
	}
}
