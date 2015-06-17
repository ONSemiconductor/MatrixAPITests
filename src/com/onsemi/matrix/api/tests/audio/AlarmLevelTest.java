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
public class AlarmLevelTest {
    @Rule
    public Destination restfuse = new Destination(this, Settings.getUrl());
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultAlarmLevel(){
        Utils.sendRequest("/vb.htm?alarmlevel=50");
    }

    @After
    public void setAlarmLevelTo50(){
        Utils.sendRequest("/vb.htm?alarmlevel=50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=alarmlevel",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void alarmlevel_GetDefaultValue_ShouldBe50(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "alarmlevel", "Response doesn't contain alarmlevel");
        Utils.verifyResponse(response, "alarmlevel=50", "Default audioin_enable value isn't equal 50");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?alarmlevel=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void alarmlevel_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "alarmlevel", "Response doesn't contain alarmlevel");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=alarmlevel"), "alarmlevel=0", "Alarmlevel value isn't equal 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?alarmlevel=100",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void alarmlevel_SetTo100_ValueShouldBe100(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", alarmlevel.contains("OK"));
        Utils.verifyResponse(response, "alarmlevel", "Response doesn't contain alarmlevel");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=alarmlevel"), "alarmlevel=100", "Alarmlevel value isn't equal 100");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?alarmlevel=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void alarmlevel_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        String alarmLevelResponse = Utils.sendRequest("/vb.htm?paratest=alarmlevel").getBody();
        assertFalse("Response should not contain OK", alarmlevel.contains("OK"));
        assertTrue("Response doesn't contain NG", alarmlevel.contains("NG"));
        assertTrue("Response doesn't contain alarmlevel", alarmlevel.contains("alarmlevel"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=alarmlevel"), "NaN", "Alarmlevel equals NaN");
        assertTrue("Alarmlevel isn't equal 50", alarmLevelResponse.contains("alarmlevel=50"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?alarmlevel=101",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void alarmlevel_SetTo101_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        String alarmLevelResponse = Utils.sendRequest("/vb.htm?paratest=alarmlevel").getBody();
        assertFalse("Response contains OK", alarmlevel.contains("OK"));
        assertTrue("Response doesn't contain NG", alarmlevel.contains("NG"));
        assertTrue("Response doesn't contain alarmlevel", alarmlevel.contains("alarmlevel"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=alarmlevel"), "101", "Alarmlevel equals 101");
        assertTrue("Alarmlevel isn't equal 50", alarmLevelResponse.contains("alarmlevel=50"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?alarmlevel=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void alarmlevel_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        String alarmLevelResponse = Utils.sendRequest("/vb.htm?paratest=alarmlevel").getBody();
        assertFalse("Response contains OK", alarmlevel.contains("OK"));
        assertTrue("Response doesn't contain NG", alarmlevel.contains("NG"));
        assertTrue("Response doesn't contain alarmlevel", alarmlevel.contains("alarmlevel"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=alarmlevel"), "-1", "Alarmlevel equals -1");
        assertTrue("Alarmlevel isn't equal 50", alarmLevelResponse.contains("alarmlevel=50"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?alarmlevel=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void alarmlevel_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String alarmlevel = response.getBody();
        String alarmLevelResponse = Utils.sendRequest("/vb.htm?paratest=alarmlevel").getBody();
        assertFalse("Response contains OK", alarmlevel.contains("OK"));
        assertTrue("Response doesn't contain NG", alarmlevel.contains("NG"));
        assertTrue("Response doesn't contain alarmlevel", alarmlevel.contains("alarmlevel"));
        assertTrue("alarmlevel isn't equal 50", alarmLevelResponse.contains("alarmlevel=50"));
    }
}
