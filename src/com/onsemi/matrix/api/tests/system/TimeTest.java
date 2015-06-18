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

package com.onsemi.matrix.api.tests.system;

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
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class TimeTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );

    @Context
    private Response response;

    @BeforeClass
    public static void setTime(){
        Utils.setValue("time", "00:00:01");
    }

    @After
    public void setTimeTo00_00_01() throws InterruptedException{
        Utils.setValue("time", "00:00:01");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=time",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void time_GetDefaultValue_ShouldBe00_00_01(){
        Utils.printResponse(response);
        String timesynch_mode = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", timesynch_mode.contains("OK"));
        Utils.verifyResponse(response, "time", "response contains time");
        assertTrue("time has correct format", response.getBody().matches("time=\\d{2}:\\d{2}:\\d{2}"));
        Utils.verifyResponse(response, "time=00:00:", "default time is 00:00:01");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=23:59:59",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void time_SetTo23_59_59_ValueShouldContain00_00(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utils.printResponse(response);
        String time = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", time.contains("OK"));
        Utils.verifyResponse(response, "time", "response contains time");
        assertTrue("time has correct format", response.getBody().matches("time=\\d{2}:\\d{2}:\\d{2}"));
        Utils.verifyResponse(response, "time=00:00:", "time contain 00:00");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void time_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String time = response.getBody();
        assertFalse("Response should not contain OK", time.contains("OK"));
        assertTrue("Response should contain NG", time.contains("NG"));
        assertTrue("Response should contain time", time.contains("time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "time should contain 00:00:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=23:2016:01",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void time_SetToIncorrectFormat_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String time = response.getBody();
        assertFalse("Response should not contain OK", time.contains("OK"));
        assertTrue("Response should contain NG", time.contains("NG"));
        assertTrue("Response should contain time", time.contains("time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "time should be 00:00:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=IncorrectTime",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void time_SetToString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String time = response.getBody();
        assertFalse("Response should not contain OK", time.contains("OK"));
        assertTrue("Response should contain NG", time.contains("NG"));
        assertTrue("Response should contain time", time.contains("time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "time should contain 00:00:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=25:65:67",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void time_SetTo25_65_67_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String time = response.getBody();
        assertFalse("Response should not contain OK", time.contains("OK"));
        assertTrue("Response should contain NG", time.contains("NG"));
        assertTrue("Response should contain time", time.contains("time"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=time"), "time=25:65:", "time should not contain 25:65:");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "time should contain 00:00:");
    }
}
