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
public class CloudStreamTest {
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
            path = "/vb.htm?paratest=cloud_stream",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
	public void cloudstream_GetCloudStreamName_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_stream", "Response doesn't contain 'OK cloud_stream'");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_stream=12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
	public void cloudstream_SetCloudStreamNameTo12345_ShouldBeEqua12345() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_stream", "Response doesn't contain 'OK cloud_stream'");
        String expected = "OK cloud_stream=12345";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_stream").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_stream=3test",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
	public void cloudstream_SetCloudStreamNameTo3test_ShouldBeEqual3test() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_stream", "Response doesn't contain 'OK cloud_stream'");
        String expected = "OK cloud_stream=3test";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_stream").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_stream=qwertyuiopasdfghjklzxcvbnm12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
	public void cloudstream_SetCloudStreamNameToStringWithLength31_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_stream", "Response doesn't contain 'OK cloud_stream'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_stream"), 
        		"qwertyuiopasdfghjklzxcvbnm12345", "cloud_stream doesn't equal qwertyuiopasdfghjklzxcvbnm12345");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_stream=qwertyuiopasdfghjklzxcvbnm123456",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 4
    )
	public void cloudstream_SetCloudStreamNameToStringWithLength32_ShouldReturnNG() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG cloud_stream", "Response doesn't contain 'NG cloud_stream'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_stream"), 
        		"qwertyuiopasdfghjklzxcvbnm123456", "cloud_stream equals qwertyuiopasdfghjklzxcvbnm123456");
	}
}
