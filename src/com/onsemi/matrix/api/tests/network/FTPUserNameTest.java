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
public class FTPUserNameTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultFTPUserName(){
        Utils.setValue("ftpuser", "");
    }

    @After
    public void setFTPUserNameToBlank() throws InterruptedException{
        Utils.setValue("ftpuser", "");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=ftpuser",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void ftpuser_GetDefaultValue_UsernameShouldBeBlank(){
        Utils.printResponse(response);
        String ftpuser = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftpuser.contains("OK"));
        Utils.verifyResponse(response, "ftpuser", "Response doesn't contain ftpuser");
        assertTrue("Response isn't equal", response.getBody().replaceAll("\n|\r\n", "").equals("OK ftpuser="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=ValidName",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void ftpuser_SetValidName_UsernameShouldBeValidName(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpuser", "Response doesn't contain ftpuser");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpuser"), "ftpuser=ValidName", "Ftpuser value isn't equal ValidName");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=ValidName12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void ftpuser_SetValidNameWithNumbers_UsernameShouldBeValidNameWithNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpuser", "Response doesn't contain ftpuser");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpuser"), "ftpuser=ValidName12345", "Ftpuser value isn't equal ValidName12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=ValidName@#$:.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void ftpuser_SetValidNameWithSpecialSymbols_UsernameShouldBeValidNameWithSpecialSymbols(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpuser", "Response doesn't contain ftpuser");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpuser"), "ftpuser=ValidName@#$:.", "Ftpuser value isn't equal ValidName@#$:.");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=ValidName@#$:.12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 5
    )
    public void ftpuser_SetValidNameWithSpecialSymbolsAndNumbers_UsernameShouldBeValidNameWithSpecialSymbolsAndNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpuser", "Response doesn't contain ftpuser");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpuser"), "ftpuser=ValidName@#$:.12345", "Ftpuser value isn't equal ValidName@#$:.12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=ABCDEFGHIJKLMNOPQRSTUVWXYZ123456",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 6
    )
    public void ftpuser_SetToStringWithLenght32_UsernameShouldBeStringWithLenght32(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftpuser", "Response doesn't contain ftpuser");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftpuser"), "ftpuser=ValidName@#$:.12345", "Ftpuser value isn't equal ValidName@#$:.12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=@#$:.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 7
    )
    public void ftpuser_SetToSpecialSymbol_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpuserBody = response.getBody();
        assertFalse("Response contains OK", ftpuserBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpuserBody.contains("NG"));
        assertTrue("Response doesn't contain ftpuser", ftpuserBody.contains("ftpuser"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpuser"),
                "@#$:.", "Ftpuser equals @#$:.");
        assertTrue("Response isn't equal", Utils.sendRequest("/vb.htm?paratest=ftpuser").getBody()
                .replaceAll("\n|\r\n", "").equals("OK ftpuser="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 8
    )
    public void ftpuser_SetToStringWithLenght33_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpuserBody = response.getBody();
        assertFalse("Response contains OK", ftpuserBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpuserBody.contains("NG"));
        assertTrue("Response doesn't contain ftpuser", ftpuserBody.contains("ftpuser"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpuser"),
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567", "Ftpuser equals ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567");
        assertTrue("Response isn't equal", Utils.sendRequest("/vb.htm?paratest=ftpuser").getBody()
                .replaceAll("\n|\r\n", "").equals("OK ftpuser="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftpuser=InvalidName()_-,%^&*+=/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 9
    )
    public void ftpuser_SetToStringWithIncorrectSymbols_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftpuserBody = response.getBody();
        assertFalse("Response contains OK", ftpuserBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftpuserBody.contains("NG"));
        assertTrue("Response doesn't contain ftpuser", ftpuserBody.contains("ftpuser"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftpuser"),
                "InvalidName()_-,%^&*+=/", "Ftpuser equals InvalidName()_-,%^&*+=/");
        assertTrue("Response isn't equal", Utils.sendRequest("/vb.htm?paratest=ftpuser").getBody()
                .replaceAll("\n|\r\n", "").equals("OK ftpuser="));
    }
}
