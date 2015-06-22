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
public class DateTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void resetSettingsBeforeTests(){
        Utils.setValue("date", "2015/01/01");
    }

    @After
    public void resetSettingsAfterTest() throws InterruptedException{
        Utils.setValue("date", "2015/01/01");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=date",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void date_GetDefaultValue_ShouldBe2015_01_01() {
        Utils.printResponse(response);
        String dateResponse = response.getBody();
        assertOk(response);
        assertTrue("response doesn't contain 'OK date'", dateResponse.contains("OK date"));
        Utils.verifyResponse(response, "date=2015/01/01", "default value isn't 2015/01/01");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?date=2015/02/01",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void date_SetTo2015_02_01_ValueShouldBe2015_02_01(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK date", "response doesn't contain 'OK date'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=date"), "date=2015/02/01", "date isn't 2015/02/01");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?date=1970/01/01",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void date_SetTo1970_01_01_ValueShouldBe1970_01_01(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK date", "response doesn't contain 'OK date'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=date"), "date=1970/01/01", "date isn't 1970/01/01");
    }
}
