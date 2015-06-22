package com.onsemi.matrix.api.tests.network;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

import org.junit.After;
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
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @After
    public void resetSettingsAfterTest() throws InterruptedException{
        Thread.sleep(Settings.getAfterTestDelay());
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=lan_gateway",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void langateway_GetDefaultValue_ShouldBeDefaultGateway() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_gateway", "response doesn't contain 'OK lan_gateway'");
        String defaultGateway = Settings.getDefaultGateway();
        String gatewayResponse = response.getBody().replace("OK lan_gateway=", "").replace("\n", "");
        assertTrue(String.format("Expected: '%s' Actual: '%s'", defaultGateway, gatewayResponse), Utils.isEqualIPs(defaultGateway, gatewayResponse));
    }
}
