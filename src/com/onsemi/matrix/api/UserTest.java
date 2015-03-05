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
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;


import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith( HttpJUnitRunner.class )
public class UserTest {
  
  @Rule
  public Destination restfuse = new Destination( this, "http://192.168.1.168" );
  
  @Context
  private Response response;
  
 
  
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?adduser=tester:1234:0110",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void addUser() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void checkUserList() {
	  printResponse();
	  assertOk(response);
	  String userlist = response.getBody();
	  assertTrue(userlist.contains("tester:0110"));
	  
  }
  
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?editprivilege=tester:1110",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void geteditpriviledgestatus() {
	  printResponse();
	  assertOk(response);
	  
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getprivilege",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void getnewUserInfo() {
	  printResponse();
	  assertOk(response);
  }

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
public void getusprivilege() {
	  printResponse();
	  assertOk(response);
	  String userlist = response.getBody();
	  assertTrue(userlist.contains("tester:1110"));
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?resetpassword=tester:1234",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
public void getuserresetpasswordstatus() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?changepassword=1234:4321",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
public void getuserchangepasswordstatus() {
	  printResponse();
	  assertOk(response);
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?logincount=1:tester",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
public void setcountlogin() {
	  printResponse();
	  assertOk(response);
	  
}

@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=usercount",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
public void userlogincount() {
	  printResponse();
	  assertOk(response);
	  String count = response.getBody();
	  assertTrue(count.contains("usercount=1"));
	  
}
@HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?logincount=tester:tester",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
public void userlogout() {
	  printResponse();
	  assertOk(response);
	  
}
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?deluser=tester",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void usersetdel() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void verifyUserListafterdel() {
	  printResponse();
	  assertOk(response);
	  String userlist = response.getBody();
	  assertFalse(userlist.contains("tester"));
	  
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
