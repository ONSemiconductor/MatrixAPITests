package com.onsemi.matrix.api.tests.network;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

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

import static org.junit.Assert.assertTrue;

@RunWith( HttpJUnitRunner.class )
public class IPAddressTest {
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
            path = "/vb.htm?paratest=lan_ip",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void lanip_GetDefaultValue_ShouldBeDefaultIP() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_ip", "response doesn't contain 'OK lan_ip'");
        String defaultIP = Settings.getUrl().replaceFirst("https?://", "");
        String ipResponse = response.getBody().replace("OK lan_ip=", "").replace("\n", "");
        assertTrue(String.format("Expected: '%s' Actual: '%s'", defaultIP, ipResponse), Utils.isEqualIPs(defaultIP, ipResponse));
    }
}
