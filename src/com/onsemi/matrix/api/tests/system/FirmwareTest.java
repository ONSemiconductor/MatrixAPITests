package com.onsemi.matrix.api.tests.system;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.assertTrue;

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
public class FirmwareTest {
	@Rule
    public Destination restfuse = new Destination( this, Settings.getUrl() );
    
    @Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

    @Context
    private Response response;

    @HttpTest(method = Method.GET,
            path = "/vb.htm?paratest=fwversion",
            authentications = { @Authentication( type = BASIC, user = Settings.Username, password = Settings.Password ) },
            order = 0
    )
    public void firmware_GetFirmwareVersion(){
        Utils.printResponse(response);
        assertOk(response);
        String actual = response.getBody();
        String expected = "OK\\sfwversion=(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)";
        assertTrue(String.format("Expected: '%s' Actual: '%s'", expected, actual.replace("\n", "")), actual.matches(expected));
    }
}
