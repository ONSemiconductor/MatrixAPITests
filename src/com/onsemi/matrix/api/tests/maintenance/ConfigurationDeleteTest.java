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

package com.onsemi.matrix.api.tests.maintenance;

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

@RunWith( HttpJUnitRunner.class )
public class ConfigurationDeleteTest {

	@Rule
	public Destination restfuse = new Destination(this, Settings.getUrl());
	
	@Rule
	public Timeout timeout = new Timeout(Settings.getDefaultTimeout());

	@Context
	private Response response;
	
	@After
    public void resetSettingAfterTest() throws InterruptedException{
        Thread.sleep(Settings.getAfterTestDelay());
    }

	@HttpTest(method = Method.GET, path = "vb.htm?configdelete=filename", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 0)
	public void configdelete_DeleteConfigFile_ShouldReturnOK() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "OK", "Response doesn't contain 'OK'");
		//TODO: need to check: does file exist?
	}
	
	@HttpTest(method = Method.GET, path = "vb.htm?configdelete=", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 1)
	public void configdelete_DeleteConfigFileWithEmptyParameterValue_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG", "Response doesn't contain 'NG'");
	}
	
	@HttpTest(method = Method.GET, path = "vb.htm?configdelete", 
			authentications = { @Authentication(type = BASIC, user = Settings.Username, password = Settings.Password) }, order = 2)
	public void configdelete_DeleteConfigFileWithoutParameterValue_ShouldReturnNG() {
		Utils.printResponse(response);
		assertOk(response);
		Utils.verifyResponse(response, "NG", "Response doesn't contains 'NG'");
	}
}
