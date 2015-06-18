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
public class UPnPTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultUPnP(){
        Utils.sendRequest("/vb.htm?upnp_on=1");
    }

    @After
    public void setUPnPTo1() throws InterruptedException{
        Utils.sendRequest("/vb.htm?upnp_on=1");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=upnp_on",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void upnp_on_GetDefaultValue_ShouldBe1(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("Response contains OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "upnp_on", "Response doesn't contain rtsp_enable");
        Utils.verifyResponse(response, "upnp_on=1", "Default upnp_on value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?upnp_on=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void upnp_on_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "upnp_on", "Response doesn't contain upnp_on");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=upnp_on"), "upnp_on=1", "Upnp_on value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?upnp_on=2",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void upnp_on_SetTo2_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String upnp_onBody = response.getBody();
        String upnp_on = Utils.sendRequest("/vb.htm?paratest=upnp_on").getBody();
        assertFalse("Response contains OK", upnp_onBody.contains("OK"));
        assertTrue("Response doesn't contain NG", upnp_onBody.contains("NG"));
        assertTrue("Response doesn't contain upnp_on", upnp_on.contains("upnp_on"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "2", "Upnp_on equals 2");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "upnp_on=2", "Upnp_on isn't equal 2");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?upnp_on=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void upnp_on_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String upnp_onBody = response.getBody();
        String upnp_on = Utils.sendRequest("/vb.htm?paratest=upnp_on").getBody();
        assertFalse("Response contains OK", upnp_onBody.contains("OK"));
        assertTrue("Response doesn't contain NG", upnp_onBody.contains("NG"));
        assertTrue("Response doesn't contain upnp_on", upnp_on.contains("upnp_on"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "-1", "Upnp_on equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "upnp_on=1", "Upnp_on isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?upnp_on=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void upnp_on_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String upnp_onBody = response.getBody();
        String upnp_on = Utils.sendRequest("/vb.htm?paratest=upnp_on").getBody();
        assertFalse("Response contains OK", upnp_onBody.contains("OK"));
        assertTrue("Response doesn't contain NG", upnp_onBody.contains("NG"));
        assertTrue("Response doesn't contain upnp_on", upnp_on.contains("upnp_on"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "NaN", "Upnp_on equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "upnp_on=1", "Upnp_on isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?upnp_on=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void upnp_on_SetToEmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String upnp_onBody = response.getBody();
        String upnp_on = Utils.sendRequest("/vb.htm?paratest=upnp_on").getBody();
        assertFalse("Response contains OK", upnp_onBody.contains("OK"));
        assertTrue("Response doesn't contain NG", upnp_onBody.contains("NG"));
        assertTrue("Response doesn't contain upnp_on", upnp_on.contains("upnp_on"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=upnp_on"),
                "upnp_on=1", "Upnp_on isn't equal 1");
    }
}
