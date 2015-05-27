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
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class SynchronisationIntervalTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.DefaultTimeout);

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultSynchronisationInterval(){
        Utils.setValue("sntp_synch_interval", "1");
    }

    @After
    public void setSynchronisationIntervalTo1(){
        Utils.setValue("sntp_synch_interval", "1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=sntp_synch_interval",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void sntp_synch_interval_GetDefaultValue_ShouldBe1(){
        Utils.printResponse(response);
        String sntp_synch_interval = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", sntp_synch_interval.contains("OK"));
        Utils.verifyResponse(response, "sntp_synch_interval", "response contains sntp_synch_interval");
        Utils.verifyResponse(response, "sntp_synch_interval=1", "default sntp_synch_interval value is 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=24",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetTo24_ValueShouldBe24(){
        Utils.printResponse(response);
        String timezone = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", timezone.contains("OK"));
        Utils.verifyResponse(response, "sntp_synch_interval", "response contains sntp_synch_interval");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval"), "sntp_synch_interval=24", "sntp_synch_interval value is 25");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=15",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetTo15_ValueShouldBe15(){
        Utils.printResponse(response);
        String timezone = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", timezone.contains("OK"));
        Utils.verifyResponse(response, "sntp_synch_interval", "response contains sntp_synch_interval");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval"), "sntp_synch_interval=15", "sntp_synch_interval value is 15");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetTo0_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String sntp_synch_interval = response.getBody();
        String sntp_synch_intervalLevelResponse = Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval").getBody();
        assertFalse("Response should not contain OK", sntp_synch_interval.contains("OK"));
        assertTrue("Response should contain NG", sntp_synch_interval.contains("NG"));
        assertTrue("Response should contain sntp_synch_interval", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval"));
        assertFalse("sntp_synch_interval should not be 0", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=0"));
        assertTrue("sntp_synch_interval should be 1", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=1"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=-1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String sntp_synch_interval = response.getBody();
        String sntp_synch_intervalLevelResponse = Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval").getBody();
        assertFalse("Response should not contain OK", sntp_synch_interval.contains("OK"));
        assertTrue("Response should contain NG", sntp_synch_interval.contains("NG"));
        assertTrue("Response should contain sntp_synch_interval", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval"));
        assertFalse("sntp_synch_interval should not be -1", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=-1"));
        assertFalse("sntp_synch_interval should not be 255", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=255"));
        assertTrue("sntp_synch_interval should be 1", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=1"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=8",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetTo25_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String sntp_synch_interval = response.getBody();
        String sntp_synch_intervalLevelResponse = Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval").getBody();
        assertFalse("Response should not contain OK", sntp_synch_interval.contains("OK"));
        assertTrue("Response should contain NG", sntp_synch_interval.contains("NG"));
        assertTrue("Response should contain sntp_synch_interval", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval"));
        assertFalse("sntp_synch_interval should not be 25", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=25"));
        assertTrue("sntp_synch_interval should be 1", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=1"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String sntp_synch_interval = response.getBody();
        String sntp_synch_intervalLevelResponse = Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval").getBody();
        assertFalse("Response should not contain OK", sntp_synch_interval.contains("OK"));
        assertTrue("Response should contain NG", sntp_synch_interval.contains("NG"));
        assertTrue("Response should contain sntp_synch_interval", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval"));
        assertFalse("sntp_synch_interval should not be NaN", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=NaN"));
        assertTrue("sntp_synch_interval should be 1", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=1"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sntp_synch_interval=",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void sntp_synch_interval_SetToEmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String sntp_synch_interval = response.getBody();
        String sntp_synch_intervalLevelResponse = Utils.sendRequest("/vb.htm?paratest=sntp_synch_interval").getBody();
        assertFalse("Response should not contain OK", sntp_synch_interval.contains("OK"));
        assertTrue("Response should contain NG", sntp_synch_interval.contains("NG"));
        assertTrue("Response should contain sntp_synch_interval", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval"));
        assertTrue("sntp_synch_interval should be 1", sntp_synch_intervalLevelResponse.contains("sntp_synch_interval=1"));
    }
}
