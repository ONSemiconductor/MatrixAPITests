package com.onsemi.matrix.api.tests.network;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.HttpJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;

import com.onsemi.matrix.api.Settings;
import com.onsemi.matrix.api.Utils;


@RunWith( HttpJUnitRunner.class )
public class IPAddressTest {
	@Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(600000);

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
    public void lanip_GetDefaultValue_ShouldBeEqualDefaultIP() {
        Utils.printResponse(response);
        assertOk(response);
        Utils.verifyResponse(response, "OK lan_ip", "response doesn't contain 'OK lan_ip'");
        String defaultIP = Settings.getDefaultIP();
        String ipResponse = getIP(response);
        assertTrue(String.format("Expected: '%s' Actual: '%s'", defaultIP, ipResponse), Utils.isEqualIPs(defaultIP, ipResponse));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_ip=168.1.168.NaN",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 1
    )
    public void lanip_SetIPTo168_1_168_NaN_ShouldReturnNG() {
    	Utils.printResponse(response);
    	assertOk(response);
    	Utils.verifyResponse(response, "NG lan_ip", "response doesn't contain 'NG lan_ip'");
    	String defaultIP = Settings.getDefaultIP();
    	String ipResponse = getIP(Utils.sendRequest("/vb.htm?paratest=lan_ip"));
    	assertTrue(String.format("Expected: '%s' Actual: '%s'", defaultIP, ipResponse), Utils.isEqualIPs(defaultIP, ipResponse));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_ip=168.1.168.",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 2
    )
    public void lanip_SetIPTo168_1_168_ShouldReturnNG() {
    	Utils.printResponse(response);
    	assertOk(response);
    	Utils.verifyResponse(response, "NG lan_ip", "response doesn't contain 'NG lan_ip'");
    	String defaultIP = Settings.getDefaultIP();
    	String ipResponse = getIP(Utils.sendRequest("/vb.htm?paratest=lan_ip"));
    	assertTrue(String.format("Expected: '%s' Actual: '%s'", defaultIP, ipResponse), Utils.isEqualIPs(defaultIP, ipResponse));
    }
    
    @HttpTest(method = Method.GET,
            path = "/vb.htm?lan_ip=168.1.168.-1",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 3
    )
    public void lanip_SetIPTo168_1_168_NegativeNumber_ShouldReturnNG() {
    	Utils.printResponse(response);
    	assertOk(response);
    	Utils.verifyResponse(response, "NG lan_ip", "response doesn't contain 'NG lan_ip'");
    	String defaultIP = Settings.getDefaultIP();
    	String ipResponse = getIP(Utils.sendRequest("/vb.htm?paratest=lan_ip"));
    	assertTrue(String.format("Expected: '%s' Actual: '%s'", defaultIP, ipResponse), Utils.isEqualIPs(defaultIP, ipResponse));
    }
    
    @Test
    public void lanip_SetIPToTestIP_ShouldBeEqualTestIP() throws InterruptedException {
    	try {
			final String testUrl = String.format("http://%s", Settings.getTestIP());
			
			new Thread(new Runnable(){
				@Override
				public void run() {
					Utils.sendRequest(String.format("/vb.htm?lan_ip=%s", Settings.getTestIP()));
				}}).start();
			
			Thread.sleep(480000);
			
			Response testIPResponse = Utils.sendRequest(testUrl, "/vb.htm?paratest=lan_ip");
			Utils.printResponse(testIPResponse);
			
			assertTrue(String.format("Expected: '%s' Actual: '%s'", Settings.getTestIP(), getIP(testIPResponse)), 
					Utils.isEqualIPs(getIP(testIPResponse), Settings.getTestIP()));
			
			new Thread(new Runnable(){
				@Override
				public void run() {
					Utils.sendRequest(testUrl, 
							String.format("/vb.htm?lan_ip=%s", Settings.getDefaultIP()));
					
				}}).start();
			
			Thread.sleep(60000);
			
			Response defaultIPResponse = Utils.sendRequest("/vb.htm?paratest=lan_ip");
			Utils.printResponse(defaultIPResponse);
			
			assertTrue(String.format("Expected: '%s' Actual: '%s'", Settings.getDefaultIP(), getIP(defaultIPResponse)), 
					Utils.isEqualIPs(getIP(defaultIPResponse), Settings.getDefaultIP()));
		} catch (Exception e) {
			fail(e.toString());
		}
    }
    
    private String getIP(Response response) {
    	String body = response.getBody();
    	return body.replace("OK lan_ip=", "").replace("\n", "");
    }
}
