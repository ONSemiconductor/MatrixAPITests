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
public class TitleTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.DefaultTimeout);

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultTitle(){
        Utils.setValue("title", "TI_IPNC");
    }

    @After
    public void setCustomTitleoBlank(){
        Utils.setValue("title", "TI_IPNC");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=current_sntp",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void title_GetDefaultValue_ShouldBeTI_IPNC(){
        Utils.printResponse(response);
        String timesynch_mode = response.getBody();
        assertOk(response);
        assertTrue("Response should contain OK", timesynch_mode.contains("OK"));
        Utils.verifyResponse(response, "title", "response contains current_sntp");
        assertTrue("default current_sntp value is TI_IPNC", timesynch_mode.contains("TI_IPNC"));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void title_SetValidTitle_UserTitleShouldBeValidTitle(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "title", "response contains title");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle", "title value is ValidTitle");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void title_SetValidTitleWithNumbers_UserTitleShouldBeValidTitleWithNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "title", "response contains title");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle12345", "title value is ValidTitle12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle@#$:.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void title_SetValidTitleWithSpecialSymbols_UserTitleShouldBeValidTitleWithSpecialSymbols(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "title", "response contains title");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle@#$:.", "title value is ValidTitle@#$:.");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ValidTitle@#$:.12345",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 5
    )
    public void title_SetValidTitleWithSpecialSymbolsAndNumbers_UserTitleShouldBeValidTitleWithSpecialSymbolsAndNumbers(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "title", "response contains title");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ValidTitle@#$:.12345", "title value is ValidTitle@#$:.12345");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ABCDEFGHIJKLMNOPQRSTUVWXYZ123456",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 6
    )
    public void title_SetToStringWithLenght32_UserTitleShouldBeStringWithLenght32(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "title", "response contains title");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=title"), "title=ABCDEFGHIJKLMNOPQRSTUVWXYZ123456", "title value is ABCDEFGHIJKLMNOPQRSTUVWXYZ123456");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=@#$:.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 7
    )
    public void title_SetToSpecialSymbol_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String titleBody = response.getBody();
        assertFalse("Response should not contain OK", titleBody.contains("OK"));
        assertTrue("Response should contain NG", titleBody.contains("NG"));
        assertTrue("Response should contain title", titleBody.contains("title"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=title"),
                "@#$:.", "title not equal @#$:.");
        assertTrue("Response equal", Utils.sendRequest("/vb.htm?paratest=title").getBody()
                .replaceAll("\n|\r\n", "").equals("OK title="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 8
    )
    public void title_SetToStringWithLenght33_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String titleBody = response.getBody();
        assertFalse("Response should not contain OK", titleBody.contains("OK"));
        assertTrue("Response should contain NG", titleBody.contains("NG"));
        assertTrue("Response should contain title", titleBody.contains("title"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=title"),
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567", "title not equal ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567");
        assertTrue("Response equal", Utils.sendRequest("/vb.htm?paratest=title").getBody()
                .replaceAll("\n|\r\n", "").equals("OK title="));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?title=InvalidTitle()_-,%^&*+=/",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 9
    )
    public void title_SetToStringWithIncorrectSymbols_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String titleBody = response.getBody();
        assertFalse("Response should not contain OK", titleBody.contains("OK"));
        assertTrue("Response should contain NG", titleBody.contains("NG"));
        assertTrue("Response should contain title", titleBody.contains("title"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=title"),
                "InvalidTitle()_-,%^&*+=/", "title not equal InvalidTitle()_-,%^&*+=/");
        assertTrue("Response equal", Utils.sendRequest("/vb.htm?paratest=title").getBody()
                .replaceAll("\n|\r\n", "").equals("OK title="));
    }
}
