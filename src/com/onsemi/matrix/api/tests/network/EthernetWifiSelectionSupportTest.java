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

package com.onsemi.matrix.api.tests.network;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class EthernetWifiSelectionSupportTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultInternetWifi(){
        Utils.sendRequest("/vb.htm?internet_wifi=0");
    }

    @After
    public void setInternetWifiTo0() throws InterruptedException{
        Utils.sendRequest("/vb.htm?internet_wifi=0");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=internet_wifi",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void internet_wifi_GetDefaultValue_ShouldBe0(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("response doesn't contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "internet_wifi=0", "Default internet_wifi value isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void internet_wifi_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "internet_wifi", "response doesn't contain internet_wifi");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"), "internet_wifi=1", "internet_wifi value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=2",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void internet_wifi_SetTo2_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internetwifiBody = response.getBody();
        assertTrue("Response should contain 'NG internet_wifi'", internetwifiBody.contains("NG internet_wifi"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=0", "internet_wifi should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void internet_wifi_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internetwifiBody = response.getBody();
        assertTrue("Response doesn't contain 'NG internet_wifi'", internetwifiBody.contains("NG internet_wifi"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=0", "internet_wifi isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void internet_wifi_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internetwifiBody = response.getBody();
        assertTrue("Response doesn't contain 'NG internet_wifi'", internetwifiBody.contains("NG internet_wifi"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=0", "internet_wifi isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void internet_wifi_SetToEmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internetwifiBody = response.getBody();
        assertTrue("Response doesn't contain 'NG internet_wifi'", internetwifiBody.contains("NG internet_wifi"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=0", "internet_wifi isn't equal 0");
    }
}
