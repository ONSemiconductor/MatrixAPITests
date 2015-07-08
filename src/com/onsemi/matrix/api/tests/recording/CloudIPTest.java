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
public class CloudIPTest {
	@Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;
    
    @After
    public void resetSettingsAfterTest() throws InterruptedException{
        Thread.sleep(Settings.getAfterTestDelay());
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=cloud_ip",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void cloudip_GetCloudIP_ShouldReturnOK() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_ip", "Response doesn't contain 'OK cloud_ip'");
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_ip=52.8.158.176",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void cloudip_SetIPTo52_8_158_176_ShouldBeEqual52_8_158_176() throws InterruptedException {
    	Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_ip", "Response doesn't contain 'OK cloud_ip'");
        String expected = "OK cloud_ip=52.8.158.176";
        String actual = Utils.sendRequest("/vb.htm?paratest=cloud_ip").getBody().replace("\n", "");
        assertTrue(String.format("Expected: '%s' Actual '%s'", expected, actual), actual.compareTo(expected) == 0);
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_ip=168.1.168.NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void cloudip_SetCloudIPTo168_1_168_NaN_ShouldReturnNG() {
    	Utils.printResponse(response);
    	assertOk(response);
    	Utils.verifyResponse(response, "NG cloud_ip", "Response doesn't contain 'NG cloud_ip'");
    	String cloudip = Utils.sendRequest("/vb.htm?paratest=cloud_ip").getBody().replace("\n", "");
        assertTrue("cloud_ip equals 168.1.168.NaN", !cloudip.contains("168.1.168.NaN"));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_ip=168.1.168.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void cloudip_SetCloudIPTo168_1_168_ShouldReturnNG() {
    	Utils.printResponse(response);
    	assertOk(response);
    	Utils.verifyResponse(response, "NG cloud_ip", "Response doesn't contain 'NG cloud_ip'");
    	String cloudip = Utils.sendRequest("/vb.htm?paratest=cloud_ip").getBody().replace("\n", "");
        assertTrue("cloud_ip equals 168.1.168.", !cloudip.contains("168.1.168."));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_ip=168.1.168.-1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 4
    )
    public void cloudip_SetCloudIPTo168_1_168_NegativeNumber_ShouldReturnNG() {
    	Utils.printResponse(response);
    	assertOk(response);
    	Utils.verifyResponse(response, "NG cloud_ip", "Response doesn't contain 'NG cloud_ip'");
    	String cloudip = Utils.sendRequest("/vb.htm?paratest=cloud_ip").getBody().replace("\n", "");
        assertTrue("cloud_ip equals 168.1.168.-1", !cloudip.contains("168.1.168.-1"));
    }
}
