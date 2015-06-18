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
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class DHCPEnableTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;
    
    @After
	public void resetSettingAfterTest() throws InterruptedException {
		Thread.sleep(Settings.getAfterTestDelay());
	}

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=lan_dhcpenable",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void lan_dhcpenable_GetValue_ShouldBe0or1(){
        Utils.printResponse(response);
        String lan_mask = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", lan_mask.contains("OK"));
        Utils.verifyResponse(response, "lan_dhcpenable", "response contains lan_dhcpenable");
        assertTrue("Response contains neither 0 nor 1", response.getBody().contains("0") || response.getBody().contains("1"));
    }
}
