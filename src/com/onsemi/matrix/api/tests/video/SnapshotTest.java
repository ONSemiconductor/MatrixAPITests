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
	public void resetSettingAfterTest() {
		Utils.setValue("snapshot", "0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=snapshot", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void snapshot_GetDefaultValue_ShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "snapshot=0", "default snapshot value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void snapshot_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "snapshot", "response contains snapshot");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=snapshot"), 
				"snapshot=0", "snapshot value is 0");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void snapshot_SetTo1_ValueShouldBe1() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "snapshot", "response contains snapshot");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=snapshot"),
				"snapshot=1", "snapshot value is 1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void snapshot_SetToNaN_ShouldThrowException() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response should not contain OK", snapshotSetResponse.contains("OK"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=snapshot"), "NaN",
				"snapshot not equal NaN");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=3", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void snapshot_SetTo3_ShouldThrowException() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response should not contain OK", snapshotSetResponse.contains("OK"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=snapshot"), "3",
				"snapshot not equal 3");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void snapshot_SetToNegativeNumber_ShouldThrowException() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response should not contain OK", snapshotSetResponse.contains("OK"));
		Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=snapshot"), "-1",
				"snapshot not equal -1");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?snapshot=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void snapshot_SetToEmpty_ShouldThrowException() {
		Utils.printResponse(response);
		String snapshotSetResponse = response.getBody();
		assertFalse("Response should not contain OK", snapshotSetResponse.contains("OK"));
		String snapshotGetResponse = Utils.sendRequest("/vb.htm?paratest=snapshot").getBody();
		assertTrue("snapshot has default value", snapshotGetResponse.contains("snapshot=0"));
	}
}
