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

import java.io.IOException;

public class Settings {
    private static SettingsProvider settingsProvider = null;
    
    public static final String Username = "admin";
    public static final String Password = "admin";
    
    static
    {
    	try {
			settingsProvider = new DefaultSettingsProvider();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void setSettingsProvider(SettingsProvider newSettingsProvider) {
    	settingsProvider = newSettingsProvider;
    }
	
    public static String getUrl() {
        return settingsProvider.getUrl();
    }
    
    public static int getAfterTestDelay() {
        return settingsProvider.getAfterTestDelay();
    }
    
    public static int getDefaultTimeout() {
        return settingsProvider.getDefaultTimeout();
    }
}
