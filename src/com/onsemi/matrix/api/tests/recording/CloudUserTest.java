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
public class CloudUserTest {
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
            path = "/vb.htm?paratest=cloud_user",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
	public void clouduser_GetCloudUserName_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_user", "Response doesn't contain 'OK cloud_user'");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_user=123",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
	public void clouduser_SetCloudUserNameTo123_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_user", "Response doesn't contain 'OK cloud_user'");
        String expected = "OK cloud_user=123";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_user").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_user=10est123",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
	public void clouduser_SetCloudUserNameTo10est123_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_user", "Response doesn't contain 'OK cloud_user'");
        String expected = "OK cloud_user=10est123";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_user").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_user=qwertyuiopasdfghjklzxcvbnm12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
	public void clouduser_SetCloudUserNameToStringWithLength31_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_user", "Response doesn't contain 'OK cloud_user'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_user"), 
        		"qwertyuiopasdfghjklzxcvbnm12345", "cloud_user equals qwertyuiopasdfghjklzxcvbnm123456789");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_user=qwertyuiopasdfghjklzxcvbnm123456",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 4
    )
	public void clouduser_SetCloudUserNameToStringWithLength32_ShouldReturnNG() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG cloud_user", "Response doesn't contain 'NG cloud_user'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_user"), 
        		"qwertyuiopasdfghjklzxcvbnm123456789", "cloud_user equals qwertyuiopasdfghjklzxcvbnm123456789");
	}
}
