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
    	Utils.setValue("lan_dhcpenable", "0");
		Thread.sleep(Settings.getAfterTestDelay());
	}

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=lan_dhcpenable",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void landhcpenable_GetDefaultValue_ShouldBe0() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_dhcpenable", "response doesn't contain 'OK lan_dhcpenable'");
        Utils.verifyResponse(response, "lan_dhcpenable=0", "response doesn't contain 'OK lan_dhcpenable'");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dhcpenable=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void landhcpenable_SetTo0_ValueShouldBe0(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "lan_dhcpenable", "Response doesn't contain lan_dhcpenable");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dhcpenable"), "lan_dhcpenable=0", "lan_dhcpenable value isn't equal 0");
    }


    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dhcpenable=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void landhcpenable_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "lan_dhcpenable", "Response doesn't contain lan_dhcpenable");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dhcpenable"), "lan_dhcpenable=1", "lan_dhcpenable value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dhcpenable=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void landhcpenable_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String landhcpenable = response.getBody();
        assertTrue("Response doesn't contain 'NG lan_dhcpenable'", landhcpenable.contains("NG lan_dhcpenable"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dhcpenable"), "lan_dhcpenable=0", "lan_dhcpenable doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dhcpenable=3",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void landhcpenable_SetTo3_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String landhcpenable = response.getBody();
        assertTrue("Response doesn't contain 'NG lan_dhcpenable'", landhcpenable.contains("NG lan_dhcpenable"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dhcpenable"), "lan_dhcpenable=0", "lan_dhcpenable doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dhcpenable=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
     public void landhcpenable_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String landhcpenable = response.getBody();
        assertTrue("Response doesn't contain 'NG lan_dhcpenable'", landhcpenable.contains("NG lan_dhcpenable"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dhcpenable"), "lan_dhcpenable=0", "lan_dhcpenable doesn't have default value");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dhcpenable=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 6)
    public void landhcpenable_SetToEmpty_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String landhcpenable = response.getBody();
        assertTrue("Response doesn't contain 'NG lan_dhcpenable'", landhcpenable.contains("NG lan_dhcpenable"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dhcpenable"), "lan_dhcpenable=0", "lan_dhcpenable doesn't have default value");
    }

}
