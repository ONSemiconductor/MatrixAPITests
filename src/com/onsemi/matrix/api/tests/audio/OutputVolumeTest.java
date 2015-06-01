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
        Utils.setValue("audiooutvolume", "50");
    }

    @After
    public void setAudioOutVolumeTo50(){
        Utils.setValue("audiooutvolume", "50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=audiooutvolume",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0)
    public void audiooutvolume_GetDefaultValue_ShouldBe50(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiooutvolume=50", "default audioin_enable value is 50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1)
    public void audiooutvolume_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiooutvolume", "response contains audiooutvolume");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"), "audiooutvolume=0", "audiooutvolume value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=100",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void audiooutvolume_SetTo100_ValueShouldBe100(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audiooutvolume", "response contains audiooutvolume");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=100", "audiooutvolume value is 100");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void audiooutvolume_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response should not contain OK", audiooutvolume.contains("OK"));
        assertTrue("Response should contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response should contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "NaN", "audiooutvolume not equal NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=50", "audiooutvolume should be 50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=101",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void audiooutvolume_SetTo101_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response should not contain OK", audiooutvolume.contains("OK"));
        assertTrue("Response should contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response should contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "101", "audiooutvolume not equal 101");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=50", "audiooutvolume should be 50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void audiooutvolume_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response should not contain OK", audiooutvolume.contains("OK"));
        assertTrue("Response should contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response should contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "-1", "audiooutvolume not equal -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=50", "audiooutvolume should be 50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiooutvolume=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void audiooutvolume_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audiooutvolume = response.getBody();
        assertFalse("Response should not contain OK", audiooutvolume.contains("OK"));
        assertTrue("Response should contain NG", audiooutvolume.contains("NG"));
        assertTrue("Response should contain audioinvolume", audiooutvolume.contains("audiooutvolume"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiooutvolume"),
                "audiooutvolume=50", "audiooutvolume should be 50");
    }
}
