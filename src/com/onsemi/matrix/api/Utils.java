package com.onsemi.matrix.api;

import com.eclipsesource.restfuse.AuthenticationType;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.internal.AuthenticationInfo;
import com.eclipsesource.restfuse.internal.InternalRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Utils {

    public static void printResponse(Response response){
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

    public static void verifyResponse(Response response, String verifystr, String assertMessage){
        String body = response.getBody();
        assertTrue(assertMessage, body.contains(verifystr));
    }

    public static void verifyResponseNonContainString(Response response, String verifystr, String assertMessage){
        String body = response.getBody();
        assertFalse(assertMessage, body.contains(verifystr));
    }

    public static void setDefaultSystemSettings(){
        setDefaultSettings(Settings.defaultSystemSettings());
    }
    public static void setDefaultAudioSettings(){
        setDefaultSettings(Settings.defaultAudioSettings());
    }
    public static void setDefaultVideoSettings(){
        setDefaultSettings(Settings.defaultVideoSettings());
    }
    public static void setDefaultNetworkSettings(){
        setDefaultSettings(Settings.defaultNetworkSettings());
    }

    private static void setDefaultSettings(HashMap<String, String> settingsHashMap){
        for(Map.Entry<String, String> map : settingsHashMap.entrySet()){
            getRequest(getRequestString(map.getKey(), map.getValue()));
        }
    }

    private static String getRequestString(String key, String value){
        return "/vb.htm?" + key + "=" + value;
    }

    private static void getRequest(String requestString) {
        AuthenticationInfo authenticationInfoInfo = new AuthenticationInfo(AuthenticationType.BASIC, "admin", "admin");
        InternalRequest request = new InternalRequest(Settings.getHostname() + requestString);
        request.addAuthenticationInfo(authenticationInfoInfo);
        request.get();
    }

    public static Response getResponse(String requestString) {
        AuthenticationInfo authenticationInfoInfo = new AuthenticationInfo(AuthenticationType.BASIC, "admin", "admin");
        InternalRequest request = new InternalRequest(Settings.getHostname() + requestString);
        request.addAuthenticationInfo(authenticationInfoInfo);
        System.out.print(requestString + "  :  " + request.get().getBody());
        return request.get();
    }

    public static void setDefaultValue(String setting){
        try{
            String requestString = getRequestString(setting, Settings.getValueFromConfig(setting));
            getRequest(requestString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void setValue(String setting, String value){
        try{
            String requestString = getRequestString(setting, value);
            getRequest(requestString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
