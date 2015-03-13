/*******************************************************************************
 * Copyright (c) 2011 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Holger Staudacher - initial API and implementation
 ******************************************************************************/

package com.onsemi.matrix.api;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.AuthenticationType.BASIC;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;


@RunWith( HttpJUnitRunner.class )
public class NetworkTest {
  
  @Rule
  public Destination restfuse = new Destination( this, "http://192.168.1.168" );
  
  @Context
  private Response response;
  
 
  
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_dhcpenable=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" )},
		  order = 0)
  public void enabledhcp() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=lan_dhcpenable",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 1)
  public void getlan_dhcpenable() {
	  printResponse();
	  assertOk(response);
	  String dhcpenable = response.getBody();
	  assertTrue(dhcpenable.contains("lan_dhcpenable=1"));
	  
  }
  
  
 @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?rtsp_enable=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 2)
  public void setrtspenable() {
	  printResponse();
	  assertOk(response);
	  
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=rtsp_enable",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 3)
  public void getrtspenable() {
	  printResponse();
	  assertOk(response);
	  String rtspenable = response.getBody();
	  assertTrue(rtspenable.contains("rtsp_enable=1"));
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?rtspports=9000",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 4)
public void setrtspports() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=rtspports",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 5)
public void getrtspportscount() {
	  printResponse();
	  assertOk(response);
	  String rtspport = response.getBody();
	  assertTrue(rtspport.contains("rtspports=9000"));
	  
}
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?upnp_on=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 6)
public void setupnp() {
	  printResponse();
	  assertOk(response);
	  
}
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=upnp_on",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 7)
  public void getupnp() {
	  printResponse();
	  assertOk(response);
	  String upnp = response.getBody();
	  assertTrue(upnp.contains("upnp_on=0"));
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?upnp_on=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 8)
public void setdefaultupnp() {
	  printResponse();
	  assertOk(response);
	  
}
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?internet_wifi=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 9)
  public void setinternetwifi() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=internet_wifi",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 10)
  public void getinternetwifi() {
	  printResponse();
	  assertOk(response);
	  String internetwifi = response.getBody();
	  assertTrue(internetwifi.contains("internet_wifi=1"));
  }
  
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?internet_wifi=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 11)
  public void setdefaultinternetwifi() {
	  printResponse();
	  assertOk(response);
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?dynamic_bitrate_fps=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 12)
public void setdynamic_bitrate() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=dynamic_bitrate_fps",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 13)
public void getidynamicbitrate() {
	  printResponse();
	  assertOk(response);
	  String dynamicbitrate = response.getBody();
	  assertTrue(dynamicbitrate.contains("dynamic_bitrate_fps=1"));
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_mask=255.255.000.000",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 14)
public void setlan_mask() {
	  printResponse();
	  assertOk(response);	  
} 

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=lan_mask",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 15)
public void getlan_mask() {
	  printResponse();
	  assertOk(response);	  
	  String lan_mask = response.getBody();
	  assertTrue(lan_mask.contains("lan_mask=255.255.000.000"));
}
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_mask=255.255.255.000",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 16)
public void setdefaultlanmask() {
	  printResponse();
	  assertOk(response);	  
} 

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_gateway=001.000.000.000",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 17)
public void setlangateway() {
	  printResponse();
	  assertOk(response);	  
} 


@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=lan_gateway",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 18)
public void getlangateway() {
	  printResponse();
	  assertOk(response);
	  String lan_gateway = response.getBody();
	  assertTrue(lan_gateway.contains("lan_gateway=001.000.000.000"));
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_gateway=000.000.000.000",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 19)
public void setdefaultlangateway() {
	  printResponse();
	  assertOk(response);
	  
} 

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_dns1=192.168.001.000",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 20)
public void setdnsl() {
	  printResponse();
	  assertOk(response);	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=lan_dns1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 21)
public void getdns1() {
	  printResponse();
	  assertOk(response);
	  String dns1 = response.getBody();
	  assertTrue(dns1.contains("lan_dns1=192.168.001.000"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?lan_dns1=192.168.001.001",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 22)
public void setdefaultdnsl() {
	  printResponse();
	  assertOk(response);	  
}
  
  private void printResponse(){
	  System.out.println("Status=" + response.getStatus());
	  if (response.hasBody()) {
		  System.out.println("Body=" + response.getBody());
	  }
	  System.out.println("mediaType=" + response.getType());
	  System.out.println("===========");

  }
}