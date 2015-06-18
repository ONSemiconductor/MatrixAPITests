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

import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.Method;

import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

@RunWith( HttpJUnitRunner.class )
public class AudioInEnableTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultAudioEnable(){
        Utils.setValue("audioenable", "1");
    }

    @After
    public void setAudioenableTo1() throws InterruptedException{
        Utils.setValue("audioenable", "1");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=audioenable",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void audioenable_GetDefaultValue_ShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audioenable=1", "Default audioenable value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioenable=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void audioenable_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audioenable", "Response doesn't contain audioenable");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioenable"), "audioenable=0", "audioenable value isn't equal 0");
    }


    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioenable=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void audioenable_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audioenable", "Response doesn't contain audioenable");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioenable"), "audioenable=1", "Audioenable value isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioenable=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void audioenable_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioin_enable = response.getBody();
        assertFalse("Response contains OK", audioin_enable.contains("OK"));
        assertTrue("Response doesn't contain NG", audioin_enable.contains("NG"));
        assertTrue("Response doesn't contain audioenable", audioin_enable.contains("audioenable"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audioenable"), "NaN", "Audioenable equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioenable"), "audioenable=0", "Audioenable isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioenable=3",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void audioenable_SetTo3_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioin_enable = response.getBody();
        assertFalse("Response contains OK", audioin_enable.contains("OK"));
        assertTrue("Response doesn't contain NG", audioin_enable.contains("NG"));
        assertTrue("Response doesn't contain audioenable", audioin_enable.contains("audioenable"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audioenable"), "3", "Audioenable equals 3");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioenable"), "audioenable=1", "Audioenable isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioenable=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
     public void audioenable_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioin_enable = response.getBody();
        assertFalse("Response contains OK", audioin_enable.contains("OK"));
        assertTrue("Response doesn't contain NG", audioin_enable.contains("NG"));
        assertTrue("Response doesn't contain audioenable", audioin_enable.contains("audioenable"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audioenable"), "-1", "Audioenable equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioenable"), "audioenable=1", "Audioenable isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioenable=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void audioenable_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioin_enable = response.getBody();
        assertFalse("Response contains OK", audioin_enable.contains("OK"));
        assertTrue("Response doesn't contain NG", audioin_enable.contains("NG"));
        assertTrue("Response doesn't contain audioenable", audioin_enable.contains("audioenable"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioenable"), "audioenable=1", "Audioenable isn't equal 1");
    }
}
