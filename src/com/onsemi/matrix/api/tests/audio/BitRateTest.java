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

package com.onsemi.matrix.api.tests.audio;

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
public class BitRateTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.DefaultTimeout);

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultAudioBitRate(){
        Utils.setValue("audiobitrate", "0");
    }

    @After
    public void setAudiobitrateTo1(){
        Utils.setValue("audiobitrate", "0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=audiobitrate",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void audiobitrate_GetDefaultValue_ShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiobitrate=0", "default audiobitrate value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void audiobitrate_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiobitrate", "response contains audiobitrate");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=0", "audiobitrate value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void audiobitrate_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiobitrate", "response contains audiobitrate");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=1", "audiobitrate value is 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=2",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3
    )
    public void audiobitrate_SetTo2_ValueShouldBe2(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiobitrate", "response contains audiobitrate");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=2", "audiobitrate value is 2");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void audiobitrate_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiobitrate = response.getBody();
        assertFalse("Response should not contain OK", audiobitrate.contains("OK"));
        assertTrue("Response should contain NG", audiobitrate.contains("NG"));
        assertTrue("Response should contain audiobitrate", audiobitrate.contains("audiobitrate"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "NaN", "audiobitrate not equal NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=0", "audiobitrate should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=3",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void audiobitrate_SetTo3_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiobitrate = response.getBody();
        assertFalse("Response should not contain OK", audiobitrate.contains("OK"));
        assertTrue("Response should contain NG", audiobitrate.contains("NG"));
        assertTrue("Response should contain audiobitrate", audiobitrate.contains("audiobitrate"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "3", "audiobitrate not equal 3");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=0", "audiobitrate should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void audiobitrate_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiobitrate = response.getBody();
        assertFalse("Response should not contain OK", audiobitrate.contains("OK"));
        assertTrue("Response should contain NG", audiobitrate.contains("NG"));
        assertTrue("Response should contain audiobitrate", audiobitrate.contains("audiobitrate"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "-1", "audiobitrate not equal -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=0", "audiobitrate should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 7)
    public void audiobitrate_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiobitrate = response.getBody();
        assertFalse("Response should not contain OK", audiobitrate.contains("OK"));
        assertTrue("Response should contain NG", audiobitrate.contains("NG"));
        assertTrue("Response should contain audiobitrate", audiobitrate.contains("audiobitrate"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitrate"), "audiobitrate=0", "audiobitrate should be 0");
    }
}