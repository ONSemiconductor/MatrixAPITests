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
public class AudioBitRateNameTest {

    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=0",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void audiobitratename_GetValueWhereAudioBitRateIsDefault_ShouldBe24Kbps(){
        Utils.printResponse(Utils.sendRequest("/vb.htm?paratest=audiobitratename"));
        assertOk(Utils.sendRequest("/vb.htm?paratest=audiobitratename"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "audiobitratename=24Kbps", "audiobitratename value is 24Kbps");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "36Kbps", "audiobitratename value is not 36Kbps");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "48Kbps", "audiobitratename value is not 48Kbps");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void audiobitratename_GetValueWhereAudioBitRateIs1_ShouldBe36Kbps(){
        Utils.printResponse(Utils.sendRequest("/vb.htm?paratest=audiobitratename"));
        assertOk(Utils.sendRequest("/vb.htm?paratest=audiobitratename"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "audiobitratename=36Kbps", "audiobitratename value is 36Kbps");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "24Kbps", "audiobitratename value is not 24Kbps");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "48Kbps", "audiobitratename value is not 48Kbps");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?audiobitrate=2",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void audiobitratename_GetValueWhereAudioBitRateIs2_ShouldBe48Kbps(){
        Utils.printResponse(Utils.sendRequest("/vb.htm?paratest=audiobitratename"));
        assertOk(Utils.sendRequest("/vb.htm?paratest=audiobitratename"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "audiobitratename=48Kbps", "audiobitratename value is 48Kbps");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "36Kbps", "audiobitratename value is not 36Kbps");
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=audiobitratename"), "24Kbps", "audiobitratename value is not 24Kbps");
    }
}
