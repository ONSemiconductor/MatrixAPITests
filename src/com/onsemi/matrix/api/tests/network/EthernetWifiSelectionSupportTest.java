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
import static org.junit.Assert.assertFalse;
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
    public void setInternetWifiTo0(){
        Utils.sendRequest("/vb.htm?internet_wifi=0");
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
        assertTrue("Response should contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "internet_wifi", "response contains rtsp_enable");
        Utils.verifyResponse(response, "internet_wifi=0", "default internet_wifi value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void internet_wifi_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "internet_wifi", "response contains internet_wifi");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"), "internet_wifi=1", "internet_wifi value is 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=2",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void internet_wifi_SetTo2_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internet_wifiBody = response.getBody();
        String internet_wifi = Utils.sendRequest("/vb.htm?paratest=internet_wifi").getBody();
        assertFalse("Response should not contain OK", internet_wifiBody.contains("OK"));
        assertTrue("Response should contain NG", internet_wifiBody.contains("NG"));
        assertTrue("Response should contain internet_wifi", internet_wifi.contains("internet_wifi"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "2", "internet_wifi not equal 2");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=2", "internet_wifi should be 2");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void internet_wifi_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internet_wifiBody = response.getBody();
        String internet_wifi = Utils.sendRequest("/vb.htm?paratest=internet_wifi").getBody();
        assertFalse("Response should not contain OK", internet_wifiBody.contains("OK"));
        assertTrue("Response should contain NG", internet_wifiBody.contains("NG"));
        assertTrue("Response should contain internet_wifi", internet_wifi.contains("internet_wifi"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "-1", "internet_wifi not equal -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=1", "internet_wifi should be 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void internet_wifi_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internet_wifiBody = response.getBody();
        String internet_wifi = Utils.sendRequest("/vb.htm?paratest=internet_wifi").getBody();
        assertFalse("Response should not contain OK", internet_wifiBody.contains("OK"));
        assertTrue("Response should contain NG", internet_wifiBody.contains("NG"));
        assertTrue("Response should contain internet_wifi", internet_wifi.contains("internet_wifi"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "NaN", "internet_wifi not equal NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=1", "internet_wifi should be 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?internet_wifi=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void internet_wifi_SetToEmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String internet_wifiBody = response.getBody();
        String internet_wifi = Utils.sendRequest("/vb.htm?paratest=internet_wifi").getBody();
        assertFalse("Response should not contain OK", internet_wifiBody.contains("OK"));
        assertTrue("Response should contain NG", internet_wifiBody.contains("NG"));
        assertTrue("Response should contain internet_wifi", internet_wifi.contains("internet_wifi"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=internet_wifi"),
                "internet_wifi=1", "internet_wifi should be 1");
    }
}
