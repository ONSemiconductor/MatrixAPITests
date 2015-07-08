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
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class CameraNameTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;
    
    private static final String DefaultTitle = "ONSemi_Matrix";

    @BeforeClass
    public static void setSettingsBeforeTests(){
        Utils.setValue("title", DefaultTitle);
    }

    @After
    public void setSettingsAfterTest() throws InterruptedException {
        Utils.setValue("title", DefaultTitle);
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=title",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void title_GetToDefaultValue_ShouldBeONSemiMatrix(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK title", "response doesn't contain 'OK title'");
        Utils.verifyResponse(response, DefaultTitle, String.format("default value isn't equal %s", DefaultTitle));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void title_SetToValidTitle_TitleShouldBeValidTitle(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK title", "response doesn't contain 'OK title'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle", "title value isn't ValidTitle");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void title_SetToValidTitleWithNumbers_TitleShouldBeValidTitleWithNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK title", "response doesn't contain 'OK title'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle12345", "title value isn't ValidTitle12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle@#$:.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void title_SetToValidTitleWithSpecialSymbols_TitleShouldBeValidTitleWithSpecialSymbols(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK title", "response doesn't contain 'OK title'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle@#$:.", "title value isn't ValidTitle@#$:.");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle@#$:.12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 5
    )
    public void title_SetToValidTitleWithSpecialSymbolsAndNumbers_TitleShouldBeValidTitleWithSpecialSymbolsAndNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK title", "response doesn't contain 'OK title'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle@#$:.12345", 
        		"title value isn't ValidTitle@#$:.12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ABCDEFGHIJKLMNOPQRSTUVWXYZ123456",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 6
    )
    public void title_SetToStringWithLenght32_TitleShouldBeStringWithLenght32(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK title", "response doesn't contain 'OK title'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ABCDEFGHIJKLMNOPQRSTUVWXYZ123456", 
        		"title value isn't ABCDEFGHIJKLMNOPQRSTUVWXYZ123456");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=@#$:.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 7
    )
    public void title_SetToSpecialSymbol_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String titleBody = response.getBody();
        assertTrue("response doesn't contain 'NG title'", titleBody.contains("NG title"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), 
        		String.format("title=%s", DefaultTitle), String.format("title value isn't equal %s", DefaultTitle));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 8
    )
    public void title_SetToStringWithLenght33_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String titleBody = response.getBody();
        assertTrue("response doesn't contain 'NG title'", titleBody.contains("NG title"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), 
        		String.format("title=%s", DefaultTitle), String.format("title value isn't equal %s", DefaultTitle));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=InvalidTitle()_-,%^&*+=/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 9
    )
    public void title_SetToStringWithIncorrectSymbols_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String titleBody = response.getBody();
        assertTrue("response doesn't contain 'NG title'", titleBody.contains("NG title"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), 
        		String.format("title=%s", DefaultTitle), String.format("title value isn't equal %s", DefaultTitle));
    }
}
