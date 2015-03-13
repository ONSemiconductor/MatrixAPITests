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

public class AudioTest {

	@Rule
	public Destination restfuse = new Destination( this, "http://192.168.1.168" );

	@Context
	private Response response;

	
@HttpTest (
		method = Method.GET,
		path ="/vb.htm?audioin_enable=0",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 0)
	public void setaudioin_enable() {
		  printResponse();
		  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=audioin_enable",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 1)
public void getaudioin_enable() {
	  printResponse();
	  assertOk(response);
	  String audioenable = response.getBody();
	  assertTrue(audioenable.contains("audioin_enable=0"));
	  
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?audioin_enable=1",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 2)
	public void setdefaultaudioin_enable() {
		  printResponse();
		  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?audioinvolume=50",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 3)
public void setinputaudiovolume() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=audioinvolume",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 4)
public void getinputaudiovolume() {
	  printResponse();
	  assertOk(response);
	  String audioinvol = response.getBody();
	  assertTrue(audioinvol.contains("audioinvolume=50"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?encoding=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 5)
public void setencoding() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=encoding",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 6)
public void getencoding() {
	  printResponse();
	  assertOk(response);
	  String encodingtype = response.getBody();
	  assertTrue(encodingtype.contains("encoding=1"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?encoding=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 7)
public void setdefaultencoding() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?samplerate=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 8)
public void setsamplerate() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=samplerate",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 9)
public void getsamplerate() {
	  printResponse();
	  assertOk(response);
	  String samplerate = response.getBody();
	  assertTrue(samplerate.contains("samplerate=1"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?samplerate=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 10)
public void setdefaultsamplerate() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?audiobitrate=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 11)
public void setbitrate() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=audiobitrate",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 12)
public void getbitrate() {
	  printResponse();
	  assertOk(response);
	  String bitrate = response.getBody();
	  assertTrue(bitrate.contains("audiobitrate=1"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?audiobitrate=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 13)
public void setdefaultbitrate() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?audiooutvolume=50",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 14)
public void setaudiooutvolume() {
	  printResponse();
	  assertOk(response);
	 
}
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=audiooutvolume",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 15)
public void getaudiooutvolume() {
	  printResponse();
	  assertOk(response);
	  String audiooutvol = response.getBody();
	  assertTrue(audiooutvol.contains("audiooutvolume=50"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?audiomode=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 16)
public void setaudiomode() {
	  printResponse();
	  assertOk(response);

}
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=audiomode",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 17)
public void getaudiomode() {
	  printResponse();
	  assertOk(response);
	  String audiomode = response.getBody();
	  assertTrue(audiomode.contains("audiomode=1"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?audiomode=2",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 18)
public void setdefaultaudiomode() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?alarmlevel=75",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 19)
public void setalarmlevel() {
	  printResponse();
	  assertOk(response);

}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=alarmlevel",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 20)
public void getalarmlevel() {
	  printResponse();
	  assertOk(response);
	  String alarmlevel = response.getBody();
	  assertTrue(alarmlevel.contains("alarmlevel=75"));	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?alarmlevel=50",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 21)
public void setdefaultalarmlevel() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=sampleratename",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 22)
public void getsampleratename() {
	  printResponse();
	  assertOk(response);
	  String sampleratename = response.getBody();
	  assertTrue(sampleratename.contains("sampleratename=8Khz;16Khz"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=encodingname",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 23)
public void encodingname() {
	  printResponse();
	  assertOk(response);
	  String encodingname = response.getBody();
	  assertTrue(encodingname.contains("encodingname=G711;AAC-LC"));	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=audiobitratename",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 24)
public void getaudiobitratename() {
	  printResponse();
	  assertOk(response);
	  String bitratename = response.getBody();
	  assertTrue(bitratename.contains("audiobitratename=24Kbps;36Kbps;48Kbps"));
	  
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
