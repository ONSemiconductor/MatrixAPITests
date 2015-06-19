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
public class SampleRateTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultSampleRate(){
        Utils.setValue("samplerate", "0");
    }

    @After
    public void resetSettingAfterTest() throws InterruptedException{
        Utils.setValue("samplerate", "0");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=samplerate",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void samplerate_GetDefaultValue_ShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "samplerate=0", "Default value isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?samplerate=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void samplerate_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "samplerate", "Response doesn't contain samplerate");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=samplerate"), "samplerate=1", "samplerate isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?samplerate=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void samplerate_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String samplerate = response.getBody();
        assertFalse("Response contains OK", samplerate.contains("OK"));
        assertTrue("Response doesn't contain NG", samplerate.contains("NG"));
        assertTrue("Response doesn't contain samplerate", samplerate.contains("samplerate"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=samplerate"),
                "NaN", "samplerate equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=samplerate"), "samplerate=0", "samplerate isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?samplerate=3",
            authentications = {@Authentication( type = BASIC, user = "admin", password = "admin")},
            order = 3)
    public void samplerate_SetTo3_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String samplerate = response.getBody();
        assertFalse("Response contains OK", samplerate.contains("OK"));
        assertTrue("Response doesn't contain NG", samplerate.contains("NG"));
        assertTrue("Response doesn't contain samplerate", samplerate.contains("samplerate"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=samplerate"),
                "3", "samplerate equals 3");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=samplerate"),
                "samplerate=0", "samplerate isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?samplerate=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void samplerate_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String samplerate = response.getBody();
        assertFalse("Response contains OK", samplerate.contains("OK"));
        assertTrue("Response doesn't contain NG", samplerate.contains("NG"));
        assertTrue("Response doesn't contain samplerate", samplerate.contains("samplerate"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=samplerate"),
                "-1", "samplerate equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=samplerate"),
                "samplerate=0", "samplerate isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?samplerate=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void samplerate_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String samplerate = response.getBody();
        assertFalse("Response contains OK", samplerate.contains("OK"));
        assertTrue("Response doesn't contain NG", samplerate.contains("NG"));
        assertTrue("Response doesn't contain samplerate", samplerate.contains("samplerate"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=samplerate"), "samplerate=0", "samplerate isn't equal 0");
    }
}
