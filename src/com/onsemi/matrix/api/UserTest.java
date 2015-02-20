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
public class UserTest {
  
  @Rule
  public Destination restfuse = new Destination( this, "http://192.168.1.168" );
  
  @Context
  private Response response;
  
 
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getuserlist",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void getUserList() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getprevilege",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void getUserPreviligeStatus() {
	  printResponse();
	  assertOk(response);
  }
 
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?adduser=onsemi:1234:0110",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void setUserPreviligeStatus() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=getprevilege",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void getNewUserInfo() {
	  printResponse();
	  assertOk(response);
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?deluser=onsemi",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } )
  public void delNewUser() {
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
