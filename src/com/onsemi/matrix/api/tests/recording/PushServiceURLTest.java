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
public class PushServiceURLTest {
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
            path = "/vb.htm?paratest=push_service_url",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
	public void pushserviceurle_GetPushServiceUrl_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK push_service_url", "Response doesn't contain 'OK push_service_url'");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?push_service_url=https://matrix-cam-89717.appspot.com/_ah/api/messageEndpoint/v1/sendMessage/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
	public void pushserviceurl_SetPushServiceUrlOnValidUrl_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK push_service_url", "Response doesn't contain 'OK push_service_url'");
        String expected = "OK push_service_url=https://matrix-cam-89717.appspot.com/_ah/api/messageEndpoint/v1/sendMessage/";
        String actual = Utils.sendRequest("/vb.htm?paratest=push_service_url").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	@HttpTest(method = Method.GET,
            path = "/vb.htm?push_service_url=NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
	public void pushserviceurl_SetPushServiceUrlOnNaN_ShouldReturnNG() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG push_service_url", "Response doesn't contain 'NG push_service_url'");
	}
}
