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

public class MaintenanceTest {

	@Rule
	public Destination restfuse = new Destination( this, "http://192.168.1.168" );

	@Context
	private Response response;

	
@HttpTest (
		method = Method.GET,
		path ="/vb.htm?configsave",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 0)
	public void saveconfiguration() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?sdunmount=1",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 1)
	public void unmountsd() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?sdunmount=8",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 2)
	public void sdmount() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}
@HttpTest (
		method = Method.GET,
		path ="/vb.htm?write_log=prog:fac:msg",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 3)
	public void writelog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}
@HttpTest (
		method = Method.GET,
		path ="/vb.htm?log_search=log",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 4)
	public void searchlog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?log_delete=1",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 5)
	public void deletelog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?log_enable=0",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 6)
	public void disablelog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?paratest=log_enable",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 7)
	public void getdislog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("log_enable=0");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?log_enable=1",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 8)
	public void enablelog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?paratest=log_enable",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 9)
	public void getenablelog() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("log_enable=1");
}



@HttpTest (
		method = Method.GET,
		path ="/vb.htm?delete_msg=1",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 10)
	public void deletemsg() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?paratest=getfwversion",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 11)
	public void getfwver() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK getfwversion=");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?paratest=getublversion",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 12)
	public void getublver() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK getublversion=");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?paratest=getubootversion",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 13)
	public void getubootver() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK getubootversion=2.1.1.1049");
}

@HttpTest (
		method = Method.GET,
		path ="/vb.htm?paratest=gethwrevision",
		authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		order = 14)
	public void gethwrevision() {
		  printResponse();
		  assertOk(response);
		  verifyResponse("OK gethwrevision=ICDK_REV_A");
}


private void printResponse(){
	  System.out.println("Status=" + response.getStatus());
	  if (response.hasBody()) {
		  System.out.println("Body=" + response.getBody());
	  }
	  System.out.println("mediaType=" + response.getType());
	  System.out.println("===========");

}

private void verifyResponse(String verifystr){
		String body = response.getBody();
		assertTrue(body.contains(verifystr));

}
}
