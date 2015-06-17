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
public class WanStreamingTest {
    @Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @BeforeClass
    public static void setDefaultDynamicBitrateFps(){
        Utils.sendRequest("/vb.htm?dynamic_bitrate_fps=1");
    }

    @After
    public void setDynamicBitrateFpsTo1(){
        Utils.sendRequest("/vb.htm?dynamic_bitrate_fps=1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=dynamic_bitrate_fps",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void dynamic_bitrate_fps_GetDefaultValue_ShouldBe1(){
        Utils.printResponse(response);
        String dynamic_bitrate_fps = response.getBody();
        assertOk(response);
        assertTrue("Response doesn't contain OK", dynamic_bitrate_fps.contains("OK"));
        Utils.verifyResponse(response, "dynamic_bitrate_fps", "Response doesn't contain dynamic_bitrate_fps");
        Utils.verifyResponse(response, "dynamic_bitrate_fps=1", "Default dynamic_bitrate_fps value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?dynamic_bitrate_fps=1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void dynamic_bitrate_fps_SetTo1_ValueShouldBe1(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "dynamic_bitrate_fps", "Response doesn't contain dynamic_bitrate_fps");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"), "dynamic_bitrate_fps=1", "Dynamic_bitrate_fps value isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?dynamic_bitrate_fps=2",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2)
    public void dynamic_bitrate_fps_SetTo2_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String dynamic_bitrate_fpsBody = response.getBody();
        String dynamic_bitrate_fps = Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps").getBody();
        assertFalse("Response contains OK", dynamic_bitrate_fpsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", dynamic_bitrate_fpsBody.contains("NG"));
        assertTrue("Response doesn't contain dynamic_bitrate_fps", dynamic_bitrate_fps.contains("dynamic_bitrate_fps"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "2", "Dynamic_bitrate_fps equals 2");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "dynamic_bitrate_fps=2", "Dynamic_bitrate_fps isn't equal 2");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?dynamic_bitrate_fps=-1",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3)
    public void dynamic_bitrate_fps_SetToNegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String dynamic_bitrate_fpsBody = response.getBody();
        String dynamic_bitrate_fps = Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps").getBody();
        assertFalse("Response contains OK", dynamic_bitrate_fpsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", dynamic_bitrate_fpsBody.contains("NG"));
        assertTrue("Response doesn't contain dynamic_bitrate_fps", dynamic_bitrate_fps.contains("dynamic_bitrate_fps"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "-1", "Dynamic_bitrate_fps equals -1");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "dynamic_bitrate_fps=1", "Dynamic_bitrate_fps isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?dynamic_bitrate_fps=NaN",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4)
    public void dynamic_bitrate_fps_SetToNaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String dynamic_bitrate_fpsBody = response.getBody();
        String dynamic_bitrate_fps = Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps").getBody();
        assertFalse("Response contains OK", dynamic_bitrate_fpsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", dynamic_bitrate_fpsBody.contains("NG"));
        assertTrue("Response doesn't contain dynamic_bitrate_fps", dynamic_bitrate_fps.contains("dynamic_bitrate_fps"));
        Utils.verifyResponseNonContainString(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "NaN", "Dynamic_bitrate_fps equals NaN");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "dynamic_bitrate_fps=1", "Dynamic_bitrate_fps isn't equal 1");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?dynamic_bitrate_fps=",
            authentications = {@Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5)
    public void dynamic_bitrate_fps_SetToEmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        String dynamic_bitrate_fpsBody = response.getBody();
        String dynamic_bitrate_fps = Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps").getBody();
        assertFalse("Response contains OK", dynamic_bitrate_fpsBody.contains("OK"));
        assertTrue("Response doesn't contain NG", dynamic_bitrate_fpsBody.contains("NG"));
        assertTrue("Response doesn't contain dynamic_bitrate_fps", dynamic_bitrate_fps.contains("dynamic_bitrate_fps"));
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=dynamic_bitrate_fps"),
                "dynamic_bitrate_fps=1", "Dynamic_bitrate_fps isn't equal 1");
    }
}
