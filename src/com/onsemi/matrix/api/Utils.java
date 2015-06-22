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

import com.eclipsesource.restfuse.AuthenticationType;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.internal.AuthenticationInfo;
import com.eclipsesource.restfuse.internal.InternalRequest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Utils {

    public static void printResponse(Response response) {
        System.out.println("Status=" + response.getStatus());
        
        if (response.hasBody()) {
            System.out.println("Body=" + response.getBody());
        }
        
        System.out.println("mediaType=" + response.getType());
        System.out.println("===========");
    }

    public static void verifyResponse(Response response, String verifystr){
        String body = response.getBody();
        assertTrue(body.contains(verifystr));
    }

    public static void verifyResponse(Response response, String verifystr, String assertMessage) {
        String body = response.getBody();
        assertTrue(assertMessage, body.contains(verifystr));
    }

    public static void verifyResponseNonContainString(Response response, String verifystr, String assertMessage) {
        String body = response.getBody();
        assertFalse(assertMessage, body.contains(verifystr));
    }
    
    public static Response sendRequest(String url, String requestString) {
        AuthenticationInfo authenticationInfoInfo = new AuthenticationInfo(AuthenticationType.BASIC, Settings.Username, Settings.Password);
        
        InternalRequest request = new InternalRequest(url + requestString);
        request.addAuthenticationInfo(authenticationInfoInfo);
        
        return request.get();
    }
    
    public static Response sendRequest(String requestString) {
        AuthenticationInfo authenticationInfoInfo = new AuthenticationInfo(AuthenticationType.BASIC, Settings.Username, Settings.Password);
        
        InternalRequest request = new InternalRequest(Settings.getUrl() + requestString);
        request.addAuthenticationInfo(authenticationInfoInfo);
        
        return request.get();
    }

    public static void setValue(String setting, String value) {
        try {
            String requestString = getRequestString(setting, value);
            sendRequest(requestString);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getRequestString(String key, String value) {
        return "/vb.htm?" + key + "=" + value;
    }
    
    public static Boolean isEqualIPs(String oneIP, String otherIP) {
    	String[] oneIPParts = oneIP.split("\\.");
    	String[] otherIPParts = otherIP.split("\\.");
    	
    	if(oneIPParts.length != otherIPParts.length) {
    		return false;
    	}
    	
    	for(int i = 0; i < oneIPParts.length; i++) {
    		if (Integer.parseInt(oneIPParts[i]) != 
    				Integer.parseInt(otherIPParts[i])) {
    			return false;
    		}
    	}
    	
    	return true;
    }
}
