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
public class CloudPortTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @After
    public void resetSettingAfterTest() throws InterruptedException{
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=cloud_port",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void cloudport_GetCloudPort_ShouldReturnOK(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_port", "Response doesn't contain 'OK cloud_port'");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void cloudport_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_port", "Response doesn't contain 'OK cloud_port'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_port"), "cloud_port=0", "cloud_port value isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=8442",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void cloudport_SetTo8442_ValueShouldBe8442(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_port", "Response doesn't contain 'OK cloud_port'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_port"), "cloud_port=8442", "cloud_port value isn't equal 8442");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=64000",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3
    )
    public void cloudport_SetTo64000_ValueShouldBe64000(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK cloud_port", "Response doesn't contain 'OK cloud_port'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=cloud_port"), "cloud_port=64000", "cloud_port value isn't equal 64000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void cloudport_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String cloudportBody = response.getBody();
        assertTrue("Response doesn't contain 'NG cloud_port'", cloudportBody.contains("NG cloud_port"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_port"), "-1", "cloud_port equals -1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void cloudport_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String cloudportBody = response.getBody();
        assertTrue("Response doesn't contain 'NG cloud_port'", cloudportBody.contains("NG cloud_port"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_port"), "NaN", "cloud_port equals NaN");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void cloudport_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String cloudportBody = response.getBody();
        assertTrue("Response doesn't contain 'NG cloud_port'", cloudportBody.contains("NG cloud_port"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?cloud_port=64001",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 7)
    public void cloudport_SetTo64001_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String cloudportBody = response.getBody();
        assertTrue("Response doesn't contain 'NG cloud_port'", cloudportBody.contains("NG cloud_port"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=cloud_port"), "64001", "cloud_port equals 64001");
    }
}
