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

@RunWith( HttpJUnitRunner.class )
public class NetmaskTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void resetSettingsBeforeTests(){
        Utils.setValue("lan_mask", "255.255.255.0");
    }

    @After
    public void resetSettingsAfterTest() throws InterruptedException {
        Utils.setValue("lan_mask", "255.255.255.0");
        Thread.sleep(Settings.getAfterTestDelay());
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=lan_mask",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void lanmask_GetDefaultValue_ShouldBe255_255_255_0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_mask", "Response doesn't contain 'OK lan_mask'");
        Utils.verifyResponse(response, "lan_mask=255.255.255.000", "Default value isn't equal 255.255.255.000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_mask=255.255.254.000",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void lanmask_SetTo255_255_254_000_ValueShouldBe255_255_254_000(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_mask", "Response doesn't contain 'OK lan_mask'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.254.000", "lan_mask value isn't equal 255.255.254.000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_mask=255.255.254.NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void lan_mask_SetTo255_255_254_NaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_mask", "Response doesn't contain 'NG lan_mask'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.NaN", "lan_mask value equals 255.255.254.NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.000", "lan_mask value isn't equal 255.255.254.000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_mask=255.255.254.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3
    )
    public void lan_mask_SetTo255_255_254_EmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_mask", "Response doesn't contain 'NG lan_mask'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.000", "lan_mask value isn't equal 255.255.255.000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_mask=255.255.254.-1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4
    )
    public void lan_mask_SetTo255_255_254_NegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_mask", "Response doesn't contain 'NG lan_mask'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.-1", "lan_mask value equals 255.255.255.-1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.000", "lan_mask value isn't equal 255.255.255.000");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_mask=255.255.255.256",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5
    )
    public void lan_mask_SetTo255_255_255_256_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_mask", "Response doesn't contain 'NG lan_mask'");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.256", "Lan_mask value equals 255.255.255.256");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_mask"), "lan_mask=255.255.255.000", "Lan_mask value isn't equal 255.255.255.000");
    }
}
