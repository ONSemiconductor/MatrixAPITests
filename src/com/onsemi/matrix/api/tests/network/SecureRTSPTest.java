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
public class SecureRTSPTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.DefaultTimeout);

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultSecureRTSP(){
        Utils.sendRequest("/vb.htm?rtsp_enable=0");
    }

    @After
    public void setSecureRTSPTo0(){
        Utils.sendRequest("/vb.htm?rtsp_enable=0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=rtsp_enable",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void rtsp_enable_GetDefaultValue_ShouldBe0(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "rtsp_enable", "response contains rtsp_enable");
        Utils.verifyResponse(response, "rtsp_enable=0", "default rtsp_enable value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtsp_enable=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void rtsp_enable_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "rtsp_enable", "response contains rtsp_enable");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "rtsp_enable=0", "rtsp_enable value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtsp_enable=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void rtsp_enable_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "rtsp_enable", "response contains rtsp_enable");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "rtsp_enable=1", "rtsp_enable value is 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtsp_enable=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtsp_enable_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsp_enable = response.getBody();
        assertFalse("Response should not contain OK", rtsp_enable.contains("OK"));
        assertTrue("Response should contain NG", rtsp_enable.contains("NG"));
        assertTrue("Response should contain rtsp_enable", rtsp_enable.contains("rtsp_enable"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "NaN", "rtsp_enable not equal NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "rtsp_enable=0", "rtsp_enable should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtsp_enable=3",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void rtsp_enable_SetTo3_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsp_enable = response.getBody();
        assertFalse("Response should not contain OK", rtsp_enable.contains("OK"));
        assertTrue("Response should contain NG", rtsp_enable.contains("NG"));
        assertTrue("Response should contain rtsp_enable", rtsp_enable.contains("rtsp_enable"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "3", "rtsp_enable not equal 3");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "rtsp_enable=0", "rtsp_enable should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtsp_enable=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void rtsp_enable_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsp_enable = response.getBody();
        assertFalse("Response should not contain OK", rtsp_enable.contains("OK"));
        assertTrue("Response should contain NG", rtsp_enable.contains("NG"));
        assertTrue("Response should contain rtsp_enable", rtsp_enable.contains("rtsp_enable"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "-1", "rtsp_enable not equal -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "rtsp_enable=0", "rtsp_enable should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtsp_enable=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void rtsp_enable_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsp_enable = response.getBody();
        assertFalse("Response should not contain OK", rtsp_enable.contains("OK"));
        assertTrue("Response should contain NG", rtsp_enable.contains("NG"));
        assertTrue("Response should contain rtsp_enable", rtsp_enable.contains("rtsp_enable"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtsp_enable"), "rtsp_enable=0", "rtsp_enable should be 0");
    }
}
