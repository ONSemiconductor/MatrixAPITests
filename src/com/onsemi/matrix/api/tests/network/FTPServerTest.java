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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith( HttpJUnitRunner.class )
public class FTPServerTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultFTPServer(){
        Utils.sendRequest("/vb.htm?ftpserver=192.168.001.001");
    }

    @After
    public void setFTPServerTo192_168_001_001() throws InterruptedException {
        Utils.sendRequest("/vb.htm?ftpserver=192.168.001.001");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=ftpserver",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void ftpserver_GetDefaultValue_ShouldBe192_168_001_001(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK ftpserver", "Response doesn't contain 'OK ftpserver'");
        Utils.verifyResponse(response, "ftpserver=192.168.001.001", "Default ftpserver value isn't 192.168.001.001");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=192.168.001.002",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void ftpserver_SetTo192_168_001_002_ShouldBe192_168_001_002(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK ftpserver", "Response doesn't contain 'OK ftpserver'");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=192.168.001.002", "ftpserver value isn't 192.168.001.002");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=TestValidName",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void ftpserver_SetToTestValidName_ShouldBeTestValidName(){
        Utils.printResponse(response);
        String ftpserver = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpserver.contains("OK"));
        Utils.verifyResponse(response, "ftpserver", "Response doesn't contain ftpserver");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=TestValidName", "ftpserver value isn't equal TestValidName");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=TestValidName@#$.:",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void ftpserver_SetToTestValidNameWithSpecialSymbols_ShouldBeValidNameWithSpecialSymbols(){
        Utils.printResponse(response);
        String ftpserver = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpserver.contains("OK"));
        Utils.verifyResponse(response, "ftpserver", "Response doesn't contain ftpserver");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=TestValidName@#$.:", "ftpserver value isn't equal TestValidName@#$.:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=TestValidName123456789",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 4
    )
    public void ftpserver_SetToTestValidNameWithNumbers_ShouldBeValidNameWithNumbersWithNumbers(){
        Utils.printResponse(response);
        String ftpserver = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpserver.contains("OK"));
        Utils.verifyResponse(response, "ftpserver", "Response doesn't contain ftpserver");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=TestValidName123456789", "ftpserver value isn't equal TestValidName123456789");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=TestValidName123456789@#$.:",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 5
    )
    public void ftpserver_SetToTestValidNameWithNumbersAndSpecialSymbols_ShouldBeValidTestValidNameWithNumbersAndSpecialSymbols(){
        Utils.printResponse(response);
        String ftpserver = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpserver.contains("OK"));
        Utils.verifyResponse(response, "ftpserver", "Response doesn't contain ftpserver");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=TestValidName123456789@#$.:", "ftpserver value isn't eqaul TestValidName123456789@#$.:");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmn",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 6
    )
    public void ftpserver_SetToStringWithLenght40_ShouldBeStringWithLenght40(){
        Utils.printResponse(response);
        String ftpserver = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpserver.contains("OK"));
        Utils.verifyResponse(response, "ftpserver", "Response doesn't contain ftpserver");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmn",
                "ftpserver value isn't equal ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmn");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=A",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 7
    )
    public void ftpserver_SetToStringWithLenght1_ShouldBeStringWithLenght1(){
        Utils.printResponse(response);
        String ftpserver = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpserver.contains("OK"));
        Utils.verifyResponse(response, "ftpserver", "Response doesn't contain ftpserver");
        Utils.verifyResponse(response, "/vb.htm?paratest=ftpserver=A",
                "ftpserver value isn't equal A");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 8)
    public void ftpserver_SetToEmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpserverBody = response.getBody();
        assertFalse("Response contains OK", ftpserverBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpserverBody.contains("NG"));
        assertTrue("Response doesn't contain ftpserver", ftpserverBody.contains("ftpserver"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "ftpserver=192.168.001.001", "ftpserver isn't equal 192.168.001.001");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=123",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 8)
    public void ftpserver_SetToNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpserverBody = response.getBody();
        assertFalse("Response contains OK", ftpserverBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpserverBody.contains("NG"));
        assertTrue("Response doesn't contain ftpserver", ftpserverBody.contains("ftpserver"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "123", "ftpserver equals 123");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "ftpserver=192.168.001.001", "ftpserver isn't equal 192.168.001.001");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=@#$.:",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 9)
    public void ftpserver_SetToSymbol_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpserverBody = response.getBody();
        assertFalse("Response contains OK", ftpserverBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpserverBody.contains("NG"));
        assertTrue("Response doesn't contain ftpserver", ftpserverBody.contains("ftpserver"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "@#$.:", "ftpserver equals @#$.:");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "ftpserver=192.168.001.001", "ftpserver isn't equal 192.168.001.001");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=TestName!?,",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 10)
    public void ftpserver_SetToStringWithInvalidSymbol_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpserverBody = response.getBody();
        assertFalse("Response contains OK", ftpserverBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpserverBody.contains("NG"));
        assertTrue("Response doesn't contain ftpserver", ftpserverBody.contains("ftpserver"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "TestName!?,", "ftpserver equals TestName!?,");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "ftpserver=192.168.001.001", "ftpserver isn't equal 192.168.001.001");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=192.168.001.256",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 11)
    public void ftpserver_SetToInvalidIP192_168_001_256_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpserverBody = response.getBody();
        assertFalse("Response contains OK", ftpserverBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpserverBody.contains("NG"));
        assertTrue("Response doesn't contain ftpserver", ftpserverBody.contains("ftpserver"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "192.168.1.256", "ftpserver equals 192.168.001.256");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "ftpserver=192.168.001.001", "ftpserver isn't equal 192.168.001.001");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpserver=192.168.001.A",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 12)
    public void ftpserver_SetToInvalidIP192_168_001_A_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpserverBody = response.getBody();
        assertFalse("Response contains OK", ftpserverBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpserverBody.contains("NG"));
        assertTrue("Response doesn't contain ftpserver", ftpserverBody.contains("ftpserver"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "192.168.1.A", "ftpserver equals 192.168.001.A");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpserver"),
                "ftpserver=192.168.001.001", "ftpserver isn't equal 192.168.001.001");
    }
}
