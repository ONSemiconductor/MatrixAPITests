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
public class DNSTest {
	@Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;
    
    private static final String DefaultDNS = "192.168.001.001";
    
    @BeforeClass
    public static void resetSettingsBeforeTests(){
        Utils.setValue("lan_dns1", DefaultDNS);
    }

    @After
    public void resetSettingsAfterTest() throws InterruptedException {
        Utils.setValue("lan_dns1", DefaultDNS);
        Thread.sleep(Settings.getAfterTestDelay());
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=lan_dns1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void landns1_GetDefaultValue_ShouldBeEqualDefaultDNS() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_dns1", "response doesn't contain 'OK lan_dns1'");
        String dnsResponse = response.getBody().replace("OK lan_dns1=", "").replace("\n", "");
        assertTrue(String.format("Expected: '%s' Actual: '%s'", "", dnsResponse), Utils.isEqualIPs(DefaultDNS, dnsResponse));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dns1=192.168.001.002",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 1
    )
    public void lanmask_SetTo192_168_1_2_ShouldBe192_168_1_2() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_dns1", "Response doesn't contain 'OK lan_dns1'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dns1"), 
        		"lan_dns1=192.168.001.002", "lan_dns1 value isn't equal 192.168.001.002");
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dns1=192.168.1.NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 2
    )
    public void landns1_SetTo192_168_1_NaN_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_dns1", "Response doesn't contain 'NG lan_dns1'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dns1"), 
        		String.format("lan_dns1=%s", DefaultDNS), String.format("lan_dns1 value isn't equal %s", DefaultDNS));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dns1=192.168.1.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 3
    )
    public void landns1_SetTo192_168_1_EmptyString_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_dns1", "response doesn't contain 'NG lan_dns1'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dns1"), 
        		String.format("lan_dns1=%s", DefaultDNS), String.format("lan_dns1 value isn't equal %s", DefaultDNS));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dns1=192.168.1.-1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 4
    )
    public void landns1_SetTo192_168_1_NegativeNumber_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_dns1", "response doesn't contain 'NG lan_dns1'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dns1"), 
        		String.format("lan_dns1=%s", DefaultDNS), String.format("lan_dns1 value isn't equal %s", DefaultDNS));
    }

    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_dns1=192.168.1.256",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password)},
            order = 5
    )
    public void landns1_SetTo192_168_1_256_ResponseShouldContainNG(){
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "NG lan_dns1", "response doesn't contain 'NG lan_dns1'");
        Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=lan_dns1"), 
        		String.format("lan_dns1=%s", DefaultDNS), String.format("lan_dns1 value isn't equal %s", DefaultDNS));
    }
}
