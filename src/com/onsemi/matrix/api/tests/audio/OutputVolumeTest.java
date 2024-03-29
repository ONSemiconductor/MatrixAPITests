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
public class OutputVolumeTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultAudioOutVolume(){
        Utils.setValue("audiooutvolume", "75");
    }

    @After
    public void resetSettingsAfterTest() throws InterruptedException{
        Utils.setValue("audiooutvolume", "75");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=audiooutvolume",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0)
    public void audiooutvolume_GetDefaultValue_ShouldBe75(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiooutvolume=75", "Default value isn't equal 75");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1)
    public void audiooutvolume_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiooutvolume", "Response doesn't contain audiooutvolume");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"), "audiooutvolume=1", "audiooutvolume isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=100",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void audiooutvolume_SetTo100_ValueShouldBe100(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiooutvolume", "Response doesn't contain audiooutvolume");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=100", "audiooutvolume isn't equal 100");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void audiooutvolume_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response contains OK", audiooutvolume.contains("OK"));
        assertTrue("Response doesn't contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response doesn't contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "NaN", "audiooutvolume equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=75", "audiooutvolume isn't equal 75");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=101",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void audiooutvolume_SetTo101_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response contains OK", audiooutvolume.contains("OK"));
        assertTrue("Response doesn't contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response doesn't contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "101", "audiooutvolume equals 101");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=75", "Audiooutvolume isn't equal 75");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void audiooutvolume_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response contains OK", audiooutvolume.contains("OK"));
        assertTrue("Response doesn't contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response doesn't contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "-1", "audiooutvolume equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=75", "audiooutvolume isn't equal 75");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void audiooutvolume_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response contains OK", audiooutvolume.contains("OK"));
        assertTrue("Response doesn't contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response doesn't contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=75", "audiooutvolume isn't equal 75");
    }
}
