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
	public void resetSettingAfterTest() throws InterruptedException {
		Utils.setValue("snapshot", "0");
		Thread.sleep(Settings.getAfterTestDelay());
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=snapshot", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void snapshot_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "snapshot=0", "Default snapshot value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void snapshot_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "snapshot", "Response doesn't contain snapshot");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=snapshot"), 
				"snapshot=0", "Snapshot value isn't equal 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void snapshot_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "snapshot", "Response doesn't contain snapshot");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=snapshot"),
				"snapshot=1", "Snapshot value isn't equal 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void snapshot_SetToNaN_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response contains OK", snapshotSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", snapshotSetResponse.contains("NG"));
		assertTrue("Response doesn't contain snapshot", snapshotSetResponse.contains("snapshot"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=snapshot"), "NaN",
				"Snapshot equals NaN");
		String snapshotGetResponse = Utils.sendRequest("/vb.htm?paratest=snapshot").getBody();
		assertTrue("Snapshot hasn't default value", snapshotGetResponse.contains("snapshot=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void snapshot_SetTo3_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response contains OK", snapshotSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", snapshotSetResponse.contains("NG"));
		assertTrue("Response doesn't contain snapshot", snapshotSetResponse.contains("snapshot"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=snapshot"), "3",
				"Snapshot equals 3");
		String snapshotGetResponse = Utils.sendRequest("/vb.htm?paratest=snapshot").getBody();
		assertTrue("Snapshot hasn't default value", snapshotGetResponse.contains("snapshot=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void snapshot_SetToNegativeNumber_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response contains OK", snapshotSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", snapshotSetResponse.contains("NG"));
		assertTrue("Response doesn't contain snapshot", snapshotSetResponse.contains("snapshot"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=snapshot"), "-1",
				"Snapshot equals -1");
		String snapshotGetResponse = Utils.sendRequest("/vb.htm?paratest=snapshot").getBody();
		assertTrue("Snapshot hasn't default value", snapshotGetResponse.contains("snapshot=0"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void snapshot_SetToEmpty_ResponseShouldContainNG() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response contains OK", snapshotSetResponse.contains("OK"));
		assertTrue("Response doesn't contain NG", snapshotSetResponse.contains("NG"));
		assertTrue("Response doesn't contain snapshot", snapshotSetResponse.contains("snapshot"));
		String snapshotGetResponse = Utils.sendRequest("/vb.htm?paratest=snapshot").getBody();
		assertTrue("Snapshot hasn't default value", snapshotGetResponse.contains("snapshot=0"));
	}
}
