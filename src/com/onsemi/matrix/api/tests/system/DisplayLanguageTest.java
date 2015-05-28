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
public class DisplayLanguageTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultLanguage(){
        Utils.setValue("language", "0");
    }

    @After
    public void setLanguageTo0(){
        Utils.setValue("language", "0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=language",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void language_GetDefaultValue_ShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "language=0", "default language value is 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?language=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void language_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "language", "response contains language");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=language"), "language=1", "language value is 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?language=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void language_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String languageResponse = response.getBody();
        assertFalse("Response should not contain OK", languageResponse.contains("OK"));
        assertTrue("Response should contain NG", languageResponse.contains("NG"));
        assertTrue("Response should contain language", languageResponse.contains("language"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=language"),
                "NaN", "language not equal NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=language"), "language=0", "language should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?language=3",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void language_SetTo3_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String languageResponse = response.getBody();
        assertFalse("Response should not contain OK", languageResponse.contains("OK"));
        assertTrue("Response should contain NG", languageResponse.contains("NG"));
        assertTrue("Response should contain language", languageResponse.contains("language"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=language"),
                "3", "language not equal 3");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=language"),
                "language=0", "language should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?language=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void language_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String languageResponse = response.getBody();
        assertFalse("Response should not contain OK", languageResponse.contains("OK"));
        assertTrue("Response should contain NG", languageResponse.contains("NG"));
        assertTrue("Response should contain language", languageResponse.contains("language"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=language"),
                "-1", "language not equal -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=language"),
                "language=0", "language should be 0");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?language=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void language_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String languageResponse = response.getBody();
        assertFalse("Response should not contain OK", languageResponse.contains("OK"));
        assertTrue("Response should contain NG", languageResponse.contains("NG"));
        assertTrue("Response should contain language", languageResponse.contains("language"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=language"), "language=0", "language should be 0");
    }
}
