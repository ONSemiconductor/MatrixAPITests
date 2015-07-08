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
public class CloudPasswordTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());

	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;

	@After
	public void resetSettingsAfterTest() throws InterruptedException {
		Thread.sleep(Settings.getAfterTestDelay());
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=cloud_pwd",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
	public void cloudpwd_GetCloudPassword_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_pwd", "Response doesn't contain 'OK cloud_pwd'");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_pwd=12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
	public void cloudpwd_SetCloudPasswordTo12345_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_pwd", "Response doesn't contain 'OK cloud_pwd'");
        String expected = "OK cloud_pwd=12345";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_pwd").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_pwd=5matrix94",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
	public void cloudpwd_SetCloudPasswordTo5matrix94_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_pwd", "Response doesn't contain 'OK cloud_pwd'");
        String expected = "OK cloud_pwd=5matrix94";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_pwd").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_pwd=qwertyuiopasdfg",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
	public void cloudpwd_SetCloudPasswordToStringWithLength15_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_pwd", "Response doesn't contain 'OK cloud_pwd'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_pwd"), 
        		"qwertyuiopasdfg", "cloud_pwd equals qwertyuiopasdfg");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_pwd=qwertyuiopasdfgh",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 4
    )
	public void cloudpwd_SetCloudPasswordToStringWithLength16_ShouldReturnNG() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG cloud_pwd", "Response doesn't contain 'NG cloud_pwd'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_pwd"), "qwertyuiopasdfgh", "cloud_pwd equals qwertyuiopasdfgh");
	}
}
