/** Copyright 2015 ON Semiconductor
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

package com.onsemi.matrix.api.tests.video;

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
public class ContrastTest {
	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@BeforeClass
	public static void resetSettingBeforeTests() {
		Utils.setValue("contrast", "128");
	}
	
	@After
	public void resetSettingAfterTest() {
		Utils.setValue("contrast", "128");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?paratest=contrast", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void contrast_GetDefaultValue_ShouldBe128() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "contrast=128", "default value is 128");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=0", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void contrast_SetTo0_ValueShouldBe0() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK contrast", "response contains 'OK contrast'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=contrast"), 
				"contrast=0", "contrast value is 0");
	}
	
	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=128", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void contrast_SetTo128_ValueShouldBe128() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK contrast", "response contains 'OK contrast'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=contrast"),
				"contrast=128", "contrast value is 128");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=255", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 3)
	public void contrast_SetTo255_ValueShouldBe255() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK contrast", "response contains 'OK contrast'");
		Utils.verifyResponse(Utils.sendRequest("/vb.htm?paratest=contrast"),
				"contrast=255", "contrast value is 255");
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=NaN", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 4)
	public void contrast_SetToNaN_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG contrast", "response contains 'NG contrast'");
		String contrastGetResponse = Utils.sendRequest("/vb.htm?paratest=contrast").getBody();
		assertTrue("contrast has default value", contrastGetResponse.contains("contrast=128"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=512", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 5)
	public void contrast_SetTo512_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG contrast", "response contains 'NG contrast'");
		String contrastGetResponse = Utils.sendRequest("/vb.htm?paratest=contrast").getBody();
		assertTrue("contrast has default value", contrastGetResponse.contains("contrast=128"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=-1", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 6)
	public void contrast_SetToNegativeNumber_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG contrast", "response contains 'NG contrast'");
		String contrastGetResponse = Utils.sendRequest("/vb.htm?paratest=contrast").getBody();
		assertTrue("contrast has default value", contrastGetResponse.contains("contrast=128"));
	}

	@HttpTest(method = Method.GET, path = "/vb.htm?contrast=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 7)
	public void contrast_SetToEmpty_ShouldReturnNG() {
		Utils.printResponse(response);
		Utils.verifyResponse(response, "NG contrast", "response contains 'NG contrast'");
		String contrastGetResponse = Utils.sendRequest("/vb.htm?paratest=contrast").getBody();		
		assertTrue("contrast has default value", contrastGetResponse.contains("contrast=128"));
	}
}
