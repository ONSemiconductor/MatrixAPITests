package com.onsemi.matrix.api;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class Settings {
    public static String getHostname(){
        return "http://" + getIP();
    }

    private static String getIP(){
        String ip = null;
        try {
            File configXmlFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configXmlFile);
            NodeList nList = doc.getElementsByTagName("lan_ip");
            ip = nList.item(0).getTextContent();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getValueFromConfig(String settingName){
        String setting = null;
        try {
            File configXmlFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configXmlFile);
            NodeList nList = doc.getElementsByTagName(settingName);
            setting = nList.item(0).getTextContent();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return setting;
    }

    private static HashMap<String, String> getConfigHashMap(String categoryName){
        HashMap<String, String> settingsMap = new HashMap<String, String>();
        try {
            File configXmlFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configXmlFile);
            Node node = doc.getElementsByTagName(categoryName).item(0);
            NodeList settingList = node.getChildNodes();
            for(int i = 0; i < settingList.getLength(); i++) {
                settingsMap.put(settingList.item(i).getNodeName(), settingList.item(i).getTextContent());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return settingsMap;
    }

    public static HashMap<String, String> defaultSystemSettings() {
        return getConfigHashMap("system");
    }
    public static HashMap<String, String> defaultAudioSettings() {
        return getConfigHashMap("audio");
    }
    public static HashMap<String, String> defaultNetworkSettings() {
        return getConfigHashMap("network");
    }
    public static HashMap<String, String> defaultVideoSettings() {
        return getConfigHashMap("video");
    }
}
