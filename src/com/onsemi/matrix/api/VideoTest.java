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
public class VideoTest {
  
  @Rule
  public Destination restfuse = new Destination( this, "http://192.168.1.168" );
  
  @Context
  private Response response;

  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?profile_restart=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 0)
  public void restartprofile() {
	  printResponse();
	  assertOk(response);
	  String restart = response.getBody();
	  assertTrue(restart.contains("profile_restart"));
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_compression_pri_1=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 1)
  public void setvideocompression() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=video_compression_pri_1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 2)
  public void verifyvideocompression() {
	  printResponse();
	  assertOk(response);
	  String compression = response.getBody();
	  assertTrue(compression.contains("video_compression_pri_1=0"));
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_resolution_pri_1=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 3)
  public void setvideoresolution() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=video_resolution_pri_1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 4)
  public void verifyvideoresolution() {
	  printResponse();
	  assertOk(response);
	  String resolution = response.getBody();
	  assertTrue(resolution.contains("video_resolution_pri_1=1"));
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_fps_pri_1=10",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 5)
  public void setvidefps() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=video_fps_pri_1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 6)
  public void verifyvideofps() {
	  printResponse();
	  assertOk(response);
	  String fps = response.getBody();
	  assertTrue(fps.contains("video_fps_pri_1=10"));
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_fps_pri_1=30",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 7)
  public void setdefaultvidefps() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_ratecontrol_pri_1=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 8)
  public void setviderate() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=video_ratecontrol_pri_1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 9)
  public void verifyvideoratecontrol() {
	  printResponse();
	  assertOk(response);
	  String rate = response.getBody();
	  assertTrue(rate.contains("video_ratecontrol_pri_1=1"));
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_ratecontrol_pri_1=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 10)
  public void setdefaultviderate() {
	  printResponse();
	  assertOk(response);
	  
  }
  

  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_bitrate_pri_1=128",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 11)
  public void setvidebitrate() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=video_bitrate_pri_1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 12)
  public void verifyvideobitrate() {
	  printResponse();
	  assertOk(response);
	  String bitrate = response.getBody();
	  assertTrue(bitrate.contains("video_bitrate_pri_1=128"));
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_bitrate_pri_1=256",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 13)
  public void setdefaultvidebitrate() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_audio_pri_1=1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 14)
  public void setvideoaudio() {
	  printResponse();
	  assertOk(response);
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?paratest=video_audio_pri_1",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 15)
  public void verifyvideoaudio() {
	  printResponse();
	  assertOk(response);
	  String audio = response.getBody();
	  assertTrue(audio.contains("video_audio_pri_1=1"));
	  
  }
  
  @HttpTest (
		  method = Method.GET,
		  path ="/vb.htm?video_audio_pri_1=0",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 16)
  public void setdefaultvideoaudio() {
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
