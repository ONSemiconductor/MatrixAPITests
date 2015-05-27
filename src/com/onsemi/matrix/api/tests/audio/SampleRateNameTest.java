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

package com.onsemi.matrix.api.tests.audio;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

@RunWith( HttpJUnitRunner.class )
public class SampleRateNameTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.DefaultTimeout);

    @Context
    private Response response;

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sampleratename=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void sampleratename_GetValueWhereBitRateIsDefault_ShouldBe8Khz(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=sampleratename"), "8Khz", "sampleratename value is 8Khz");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=sampleratename"), "16Khz", "sampleratename value is not 16Khz");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?sampleratename=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void sampleratename_GetValueWhereBitRateIs1_ShouldBe16Khz(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "16Khz", "sampleratename value is 16Khz");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=sampleratename"), "8Khz", "sampleratename value is not 8Khz");
    }
}
