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

package com.onsemi.matrix.api.tests.network;

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
public class FTPPortNumberTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultFTPPort(){
        Utils.sendRequest("/vb.htm?ftpport=21");
    }

    @After
    public void setFTPPortTo21() throws InterruptedException{
        Utils.sendRequest("/vb.htm?ftpport=21");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=ftpport",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void ftpport_GetDefaultValue_ShouldBe21(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK ftpport", "Response doesn't contain 'OK ftpport'");
        Utils.verifyResponse(response, "ftpport=21", "Default ftpport value isn't 21");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void ftpport_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK ftpport", "Response contains 'OK ftpport'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"), "ftpport=0", "ftpport value isn't 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=8442",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void ftpport_SetTo8442_ValueShouldBe8442(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpport", "Response doesn't contain ftpport");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"), "ftpport=8442", "ftpport value isn't 8442");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=64000",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3
    )
    public void ftpport_SetTo64000_ValueShouldBe64000(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpport", "Response doesn't contain ftpport");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"), "ftpport=64000", "ftpport value isn't 64000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void ftpport_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpportsBody = response.getBody();
        assertFalse("Response contains OK", ftpportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpportsBody.contains("NG"));
        assertTrue("Response doesn't contain ftpport", ftpportsBody.contains("ftpport"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "-1", "ftpport equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "ftpport=21", "ftpport isn't equal 21");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void ftpport_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpportsBody = response.getBody();
        assertFalse("Response contains OK", ftpportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpportsBody.contains("NG"));
        assertTrue("Response doesn't contain ftpport", ftpportsBody.contains("ftpport"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "NaN", "ftpport equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "ftpport=21", "ftpport isn't equal 21");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void ftpport_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpportsBody = response.getBody();
        assertFalse("Response contains OK", ftpportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpportsBody.contains("NG"));
        assertTrue("Response doesn't contain ftpport", ftpportsBody.contains("ftpport"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "ftpport=21", "ftpport isn't equal 21");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=443",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 7)
    public void ftpport_SetTo443_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpportsBody = response.getBody();
        assertFalse("Response contains OK", ftpportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpportsBody.contains("NG"));
        assertTrue("Response doesn't contain ftpport", ftpportsBody.contains("ftpport"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "443", "ftpport equals 443");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "ftpport=21", "ftpport isn't equal 21");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=80",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 8)
    public void ftpport_SetTo80_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpportsBody = response.getBody();
        assertFalse("Response contains OK", ftpportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpportsBody.contains("NG"));
        assertTrue("Response doesn't contain ftpport", ftpportsBody.contains("ftpport"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "80", "ftpport equals 80");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "ftpport=21", "ftpport isn't equal 21");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpport=64001",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 9)
    public void ftpport_SetTo64001_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpportsBody = response.getBody();
        assertFalse("Response contains OK", ftpportsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpportsBody.contains("NG"));
        assertTrue("Response doesn't contain ftpport", ftpportsBody.contains("ftpport"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "64001", "ftpport equals 64001");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpport"),
                "ftpport=21", "ftpport isn't equal 21");
    }
}
