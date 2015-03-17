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
import com.onsemi.matrix.api.MaintenanceTest;


@RunWith( HttpJUnitRunner.class )
public class UserTest {
  
  @Rule
  public Destination restfuse = new Destination( this, "http://192.168.1.168" );
  
  @Context
  private Response response;
  private MaintenanceTest MTest;
 
  
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?adduser=tester:1234:0110",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" )},
		  order = 0)
  public void addUser() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 1)
  public void checkUserList() {
	  printResponse();
	  assertOk(response);
	  verifyResponse("tester:0110");
	  
  }
  
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?editprivilege=tester:1110",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 2)
  public void geteditpriviledgestatus() {
	  printResponse();
	  assertOk(response);
	  
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getprivilege",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 3)
  public void getnewUserPrivilege() {
	  printResponse();
	  assertOk(response);
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 4)
public void getuserprivilege() {
	  printResponse();
	  assertOk(response);
	  verifyResponse("tester:1110");
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?resetpassword=tester:1234",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 5)
public void getuserresetpasswordstatus() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?changepassword=tester:1234:4321",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 6)
public void getuserchangepasswordstatus() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?logincount=1:tester",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 7)
public void setlogincount() {
	  printResponse();
	  assertOk(response);
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=usercount",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 8)
public void getlogincount() {
	  printResponse();
	  assertOk(response);
	  verifyResponse("usercount=2");
	  
}
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?logincount=tester:tester",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 9)
public void setuserlogout() {
	  printResponse();
	  assertOk(response);
	  
}
 
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?deluser=tester",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 10)
  public void usersetdel() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 11)
  public void verifyUserListafterdel() {
	  printResponse();
	  assertOk(response);
	  String bodystr = response.getBody();
	  assertFalse(bodystr.contains("tester"));
	  
  }
  
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?adduser=admin:4567:0110",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" )},
		  order = 12)
  public void addinvalidUser() {
	  printResponse();
	  assertOk(response);
	  String bodystr = response.getBody();
	  assertFalse(bodystr.contains("OK"));
  }
  
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?changepassword=admin:4567:4321",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 13)
public void changeincorrectpassword() {
	  printResponse();
	  assertOk(response);
	  String bodystr = response.getBody();
	  assertFalse(bodystr.contains("OK"));
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?deluser=onsemi",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 14)
public void delnonexistuser() {
	  printResponse();
	  assertOk(response);
	  String bodystr = response.getBody();
	  assertFalse(bodystr.contains("OK"));
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
