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
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class TimeTest {
    @Rule
    public Destination restfuse = new Destination(this, Settings.getUrl());

    @Context
    private Response response;

    @BeforeClass
    public static void resetSettingsBeforeTests() {
        Utils.setValue("time", "00:00:00");
    }

    @After
    public void resetSettingsAfterTest() throws InterruptedException {
        Utils.setValue("time", "00:00:00");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=time",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void time_GetDefaultValue_ShouldBe00_00_00() {
        Utils.printResponse(response);
        assertOk(response);
        String timeResponse = response.getBody().replaceAll("\n", "");
        Utils.verifyResponse(response, "OK time", "response doesn't contain 'OK time'");
        assertTrue("response doesn't have correct format", timeResponse.matches("OK\\stime=\\d{2}:\\d{2}:\\d{2}"));
        Utils.verifyResponse(response, "00:00:", "default value isn't 00:00:00");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=23:59:59",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void time_SetTo23_59_59_ValueShouldContain00_00() {
    	Utils.printResponse(response);
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertOk(response);
        Utils.verifyResponse(response, "OK time", "response doesn't contain 'OK time'");
        Response timeGetResponse = Utils.sendRequest("/vb.htm?paratest=time");
        String timeGetResponseBody = timeGetResponse.getBody().replace("\n", "");
        assertTrue("response doesn't have correct format", timeGetResponseBody.matches("OK\\stime=\\d{2}:\\d{2}:\\d{2}"));
        Utils.verifyResponse(timeGetResponse, "time=00:00:", "response doesn't contain 00:00:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void time_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String timeResponse = response.getBody();
        assertTrue("response doesn't contain 'NG time'", timeResponse.contains("NG time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "response doesn't contain 00:00:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=23:2016:01",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void time_SetToIncorrectFormat_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String timeResponse = response.getBody();
        assertTrue("response doesn't contain 'NG time'", timeResponse.contains("NG time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "response doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=IncorrectTime",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void time_SetToString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String timeResponse = response.getBody();
        assertTrue("response doesn't contain 'NG time'", timeResponse.contains("NG time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "response doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?time=25:65:67",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void time_SetTo25_65_67_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String timeResponse = response.getBody();
        assertTrue("response doesn't contain 'NG time'", timeResponse.contains("NG time"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=time"), "time=00:00:", "response doesn't have default value");
    }
}
