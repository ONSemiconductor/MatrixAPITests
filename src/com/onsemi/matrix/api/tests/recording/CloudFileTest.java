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
public class CloudFileTest {
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
            path = "/vb.htm?paratest=cloud_file",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
	public void cloudfile_GetCloudFileName_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_file", "Response doesn't contain 'OK cloud_file'");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_file=12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
	public void cloudfile_SetCloudFileNameTo12345_ShouldBeEqual12345() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_file", "Response doesn't contain 'OK cloud_file'");
        String expected = "OK cloud_file=12345";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_file").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_file=3test.mp4",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
	public void cloudfile_SetCloudFileNameTo3test_mp4_ShouldBeEqual3test_mp4() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_file", "Response doesn't contain 'OK cloud_file'");
        String expected = "OK cloud_file=3test.mp4";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_file").getBody();
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.contains(expected));
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_file=qwertyuiopasdfghjklzxcvbnm12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
	public void cloudfile_SetCloudFileNameToStringWithLength31_ShouldReturnOK() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_file", "Response doesn't contain 'OK cloud_file'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_file"), 
        		"qwertyuiopasdfghjklzxcvbnm12345", "cloud_file doesn't equal qwertyuiopasdfghjklzxcvbnm12345");
	}
	
	@HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_file=qwertyuiopasdfghjklzxcvbnm123456",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 4
    )
	public void cloudfile_SetCloudFileNameToStringWithLength32_ShouldReturnNG() {
		Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG cloud_file", "Response doesn't contain 'NG cloud_file'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_file"), 
        		"qwertyuiopasdfghjklzxcvbnm123456", "cloud_file equals qwertyuiopasdfghjklzxcvbnm123456");
	}
}
