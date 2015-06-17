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
public class RTSPPortTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultPortRTSP(){
        Utils.sendRequest("/vb.htm?rtspports=8551");
    }

    @After
    public void setRTSPPortTo0(){
        Utils.sendRequest("/vb.htm?rtspports=8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=rtspports",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void rtspports_GetDefaultValue_ShouldBe8551(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "rtspports", "Response doesn't contain rtspports");
        Utils.verifyResponse(response, "rtspports=8551", "Default rtspports value isn't equal 8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void rtspports_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "rtspports", "Response doesn't contain rtspports");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"), "rtspports=0", "Rtspports value isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=8442",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void rtspports_SetTo8442_ValueShouldBe8442(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "rtspports", "Response doesn't contain rtspports");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"), "rtspports=8442", "Rtspports value isn't equal 8442");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=64000",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void rtspports_SetTo64000_ValueShouldBe64000(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "rtspports", "Response doesn't contain rtspports");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"), "rtspports=64000", "Rtspports value isn't equal 64000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtspports_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsportsBody = response.getBody();
        String rtspports = Utils.sendRequest("/vb.htm?paratest=rtspports").getBody();
        assertFalse("Response contains OK", rtsportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", rtsportsBody.contains("NG"));
        assertTrue("Response doesn't contain rtspports", rtspports.contains("rtspports"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "-1", "Rtspports equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "rtspports=8551", "Rtspports isn't equal 8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtspports_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsportsBody = response.getBody();
        String rtspports = Utils.sendRequest("/vb.htm?paratest=rtspports").getBody();
        assertFalse("Response contains OK", rtsportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", rtsportsBody.contains("NG"));
        assertTrue("Response doesn't contain rtspports", rtspports.contains("rtspports"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "NaN", "Rtspports equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "rtspports=8551", "Rtspports isn't equal 8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtspports_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsportsBody = response.getBody();
        String rtspports = Utils.sendRequest("/vb.htm?paratest=rtspports").getBody();
        assertFalse("Response contains OK", rtsportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", rtsportsBody.contains("NG"));
        assertTrue("Response doesn't contain rtspports", rtspports.contains("rtspports"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "rtspports=8551", "Rtspports isn't equal 8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=443",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtspports_SetTo443_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsportsBody = response.getBody();
        String rtspports = Utils.sendRequest("/vb.htm?paratest=rtspports").getBody();
        assertFalse("Response contains OK", rtsportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", rtsportsBody.contains("NG"));
        assertTrue("Response doesn't contain rtspports", rtspports.contains("rtspports"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "443", "Rtspports equals 443");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "rtspports=8551", "Rtspports isn't equal 8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=80",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtspports_SetTo80_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsportsBody = response.getBody();
        String rtspports = Utils.sendRequest("/vb.htm?paratest=rtspports").getBody();
        assertFalse("Response contains OK", rtsportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", rtsportsBody.contains("NG"));
        assertTrue("Response doesn't contain rtspports", rtspports.contains("rtspports"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "80", "Rtspports equals 80");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "rtspports=8551", "Rtspports isn't equal 8551");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?rtspports=64001",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void rtspports_SetTo64001_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String rtsportsBody = response.getBody();
        String rtspports = Utils.sendRequest("/vb.htm?paratest=rtspports").getBody();
        assertFalse("Response contains OK", rtsportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", rtsportsBody.contains("NG"));
        assertTrue("Response doesn't contain rtspports", rtspports.contains("rtspports"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "64001", "Rtspports equals 64001");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=rtspports"),
                "rtspports=8551", "Rtspports isn't equal 8551");
    }
}
