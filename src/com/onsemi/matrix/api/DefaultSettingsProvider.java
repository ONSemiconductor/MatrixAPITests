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

package com.onsemi.matrix.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefaultSettingsProvider implements SettingsProvider {
	private InputStream file = null;
	private Properties properties = null;
	
	public DefaultSettingsProvider() throws IOException
	{
		file = new FileInputStream("config.properties");
			
		properties = new Properties();
		properties.load(file);
	}

	@Override
	public String getUrl() {
		return properties.getProperty("url");
	}
	
	@Override
	public int getAfterTestDelay() {
		return Integer.parseInt(properties.getProperty("afterTestDelay"));
	}
	
	@Override
	public int getDefaultTimeout() {
		return Integer.parseInt(properties.getProperty("defaultTimeout"));
	}
}
