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
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class InputVolumeTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void resetSettingsBeforeTests(){
        Utils.setValue("audioinvolume", "75");
    }

    @After
    public void resetSettingsAfterTest() throws InterruptedException{
        Utils.setValue("audioinvolume", "75");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=audioinvolume",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void audioinvolume_GetDefaultValue_ShouldBe75(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "audioinvolume=75", "Default value isn't equal 75");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioinvolume=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
     public void audioinvolume_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK audioinvolume", "Response doesn't contain 'OK audioinvolume'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioinvolume"),
                "audioinvolume=1", "audioinvolume value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioinvolume=100",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void audioinvolume_SetTo100_ValueShouldBe100(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK audioinvolume", "Response doesn't contain 'OK audioinvolume'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioinvolume"),
                "audioinvolume=100", "audioinvolume value isn't equal 100");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioinvolume=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void audioinvolume_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioinvolume = response.getBody();
        assertTrue("Response doesn't contain 'NG audioinvolume'", audioinvolume.contains("NG audioinvolume"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioinvolume"), "audioinvolume=75", 
        		"audioinvolume doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioinvolume=101",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void audioinvolume_SetTo101_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioinvolume = response.getBody();
        assertTrue("Response doesn't contain 'NG audioinvolume'", audioinvolume.contains("NG audioinvolume"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioinvolume"), "audioinvolume=75", 
        		"audioinvolume doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioinvolume=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void audioinvolume_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioinvolume = response.getBody();
        assertTrue("Response doesn't contain 'NG audioinvolume'", audioinvolume.contains("NG audioinvolume"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioinvolume"), "audioinvolume=75", 
        		"audioinvolume doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audioinvolume=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void audioinvolume_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String audioinvolume = response.getBody();
        assertTrue("Response doesn't contain 'NG audioinvolume'", audioinvolume.contains("NG audioinvolume"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audioinvolume"), "audioinvolume=75", 
        		"audioinvolume doesn't have default value");
    }
}
