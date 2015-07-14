package com.onsemi.matrix.api.tests.network;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;

@RunWith( HttpJUnitRunner.class )
public class GatewayTest {
	@Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(10000);

    @Context
    private Response response;
    
    private static final String DefaultGateway = "192.168.001.001";

    @BeforeClass
    public static void resetSettingsBeforeTests(){
        Utils.setValue("lan_gateway", DefaultGateway);
    }
    
    @After
    public void resetSettingsAfterTest() throws InterruptedException {
    	Utils.setValue("lan_gateway", DefaultGateway);
        Thread.sleep(Settings.getAfterTestDelay());
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=lan_gateway",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void langateway_GetDefaultValue_ShouldBeEqualDefaultGateway() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_gateway", "response doesn't contain 'OK lan_gateway'");
        String gatewayResponse = response.getBody().replace("OK lan_gateway=", "").replace("\n", "");
        assertTrue(String.format("Expected: '%s' Actual: '%s'", DefaultGateway, gatewayResponse), Utils.isEqualIPs(DefaultGateway, gatewayResponse));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_gateway=192.168.001.002",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void langateway_SetTo192_168_1_2_ShouldBe192_168_1_2() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_gateway", "response doesn't contain 'OK lan_gateway'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_gateway"), 
        		"lan_gateway=192.168.001.002", "lan_gateway value isn't equal 192.168.001.002");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_gateway=192.168.1.NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void langateway_SetTo192_168_1_NaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_gateway", "response doesn't contain 'NG lan_gateway'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_gateway"), 
        		String.format("lan_gateway=%s", DefaultGateway), String.format("lan_gateway value isn't equal %s", DefaultGateway));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_gateway=192.168.1.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3
    )
    public void langateway_SetTo192_168_1_EmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_gateway", "response doesn't contain 'NG lan_gateway'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_gateway"), 
        		String.format("lan_gateway=%s", DefaultGateway), String.format("lan_gateway value isn't equal %s", DefaultGateway));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_gateway=192.168.1.-1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4
    )
    public void langateway_SetTo192_168_1_NegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_gateway", "response doesn't contain 'NG lan_gateway'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_gateway"), 
        		String.format("lan_gateway=%s", DefaultGateway), String.format("lan_gateway value isn't equal %s", DefaultGateway));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_gateway=192.168.1.256",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5
    )
    public void langateway_SetTo192_168_1_256_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_gateway", "response doesn't contain 'NG lan_gateway'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_gateway"), 
        		String.format("lan_gateway=%s", DefaultGateway), String.format("lan_gateway value isn't equal %s", DefaultGateway));
    }

}
