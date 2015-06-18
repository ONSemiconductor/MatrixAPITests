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
public class UploadPathTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultFTPPath(){
        Utils.setValue("ftppath", "");
    }

    @After
    public void setFTPPathToBlank() throws InterruptedException{
        Utils.setValue("ftppath", "");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=ftppath",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void ftppath_GetDefaultValue_PathShouldBeBlank(){
        Utils.printResponse(response);
        String ftppath = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", ftppath.contains("OK"));
        Utils.verifyResponse(response, "ftppath", "Response doesn't contain ftppath");
        assertTrue("Response isn't equal", response.getBody().replaceAll("\n|\r\n", "").equals("OK ftppath="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=Path",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void ftppath_SetPath_PathShouldBeValid(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftppath", "Response doesn't contain ftppath");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftppath"), "ftppath=Path", "Ftppath value isn't equal Path");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=Path12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void ftppath_SetPathWithNumbers_PathShouldBeValidWithNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftppath", "Response doesn't contain ftppath");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftppath"), "ftppath=Path12345", "Ftppath value isn't equal Path12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=Path._/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void ftppath_SetPathWithSpecialSymbols_PathShouldBePathWithSpecialSymbols(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftppath", "Response doesn't contain ftppath");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftppath"), "ftppath=Path._/", "Ftppath value isn't equal Path._/");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=Path._/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 5
    )
    public void ftppath_SetPathWithSpecialSymbolsAndNumbers_PathShouldBePathWithSpecialSymbolsAndNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftppath", "Response doesn't contain ftppath");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftppath"), "ftppath=Path._/", "Ftppath value isn't equal Path._/");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ.1234567890abcdefghijkl",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 6
    )
    public void ftppath_SetToStringWithLenght128_PathShouldBeStringWithLenght128(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "ftppath", "Response doesn't contain ftppath");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=ftppath"),
                "ftppath=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ.1234567890abcdefghijkl",
                "ftppath value isn't equal ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ.1234567890abcdefghijkl");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=._/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 7
    )
    public void ftppath_SetToSpecialSymbol_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftppathBody = response.getBody();
        assertFalse("Response contains OK", ftppathBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftppathBody.contains("NG"));
        assertTrue("Response doesn't contain ftppath", ftppathBody.contains("ftppath"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftppath"),
                "._/", "Ftppath equals ._/");
        assertTrue("Response isn't equal", Utils.sendRequest("/vb.htm?paratest=ftppath").getBody()
                .replaceAll("\n|\r\n", "").equals("OK ftppath="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ.1234567890abcdefghijklm",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 8
    )
    public void ftppath_SetToStringWithLenght129_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftppathBody = response.getBody();
        assertFalse("Response contains OK", ftppathBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftppathBody.contains("NG"));
        assertTrue("Response doesn't contain ftppath", ftppathBody.contains("ftppath"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftppath"),
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ.1234567890abcdefghijklm",
                "Ftppath equals ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ.1234567890abcdefghijklm");
        assertTrue("Response isn't equal", Utils.sendRequest("/vb.htm?paratest=ftppath").getBody()
                .replaceAll("\n|\r\n", "").equals("OK ftppath="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?ftppath=Pass()-,%^&*+=/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 9
    )
    public void ftppath_SetToStringWithIncorrectSymbols_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String ftppathBody = response.getBody();
        assertFalse("Response contains OK", ftppathBody.contains("OK"));
        assertTrue("Response doesn't contain NG", ftppathBody.contains("NG"));
        assertTrue("Response doesn't contain ftppath", ftppathBody.contains("ftppath"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=ftppath"),
                "Pass()_-,%^&*+=/", "Ftppath equals Pass()_-,%^&*+=/");
        assertTrue("Response isn't equal", Utils.sendRequest("/vb.htm?paratest=ftppath").getBody()
                .replaceAll("\n|\r\n", "").equals("OK ftppath="));
    }
}
