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
public class SystemTest {
  
  @Rule
  public Destination restfuse = new Destination( this, "http://192.168.1.168" );
  
  @Context
  private Response response;
  
  @HttpTest( method = Method.GET, 
		  path = "/vb.htm?timezone=16",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 0) 
  public void settimezone() {
	  printResponse();
	  assertOk( response );
  }
    
    @HttpTest( method = Method.GET, 
  		  path = "/vb.htm?paratest=timezone",
  		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 1)  
    public void verifytimezone() {
      assertOk( response );
      printResponse();
      String zone = response.getBody();
      assertTrue(zone.contains("timezone=16"));
  }
    @HttpTest( method = Method.GET, 
  		  path = "/vb.htm?timesynch_mode=1",
  		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 2)  
    public void settimesynch() {
  	  printResponse();
  	  assertOk( response );
    }
      
     @HttpTest( method = Method.GET, 
    		  path = "/vb.htm?paratest=timesynch_mode",
    		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
    		  order = 3)  
      public void verifytimesynch() {
        assertOk( response );
        printResponse();
        String mode = response.getBody();
        assertTrue(mode.contains("timesynch_mode=1"));
    }
      @HttpTest( method = Method.GET, 
    		  path = "/vb.htm?paratest=sntp_list",
    		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
    		  order = 4)  
      public void verifygetserverlist() {
        assertOk( response );
        printResponse();
        String list = response.getBody();
        assertTrue(list.contains("sntp_list=1:pool.ntp.org,2:asia.pool.ntp.org,3:europe.pool.ntp.org,4:north-america.pool.ntp.org,5:oceania.pool.ntp.org,6:south-america.pool.ntp.org,7:onsemi"));
    }
      
      @HttpTest( method = Method.GET, 
      		  path = "/vb.htm?current_sntp=6",
      		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
    		  order = 5) 
        public void setselectedsntp_server() {
      	  printResponse();
      	  assertOk( response );
        }
          
       @HttpTest( method = Method.GET, 
        	   path = "/vb.htm?paratest=current_sntp",
        	   authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
     		  order = 6)  
         public void verifyselectedsntp_server() {
           assertOk( response );
           printResponse();
           String selected = response.getBody();
           assertTrue(selected.contains("current_sntp=6"));
        }
       
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?custom_sntp=onsemi",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 7)  
    public void setcustom_sntp() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=custom_sntp",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 8)  
    public void verifycustom_sntp() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String custom = response.getBody();
  	      assertTrue(custom.contains("custom_sntp=onsemi"));
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?sntp_synch_interval=10",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 9)  
    public void setsynch_interval() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=sntp_synch_interval",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 10)  
    public void verifysych_interval() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String interval = response.getBody();
  	      assertTrue(interval.contains("sntp_synch_interval=10"));
    }
    
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=uptime",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 11)  
    public void getuptime() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String uptime = response.getBody();
  	      assertTrue(uptime.contains("uptime="));
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=serialno",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 12)  
    public void getserialno() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String serialno = response.getBody();
  	      assertTrue(serialno.contains("serialno=sprs_mcam_12345"));
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=mac",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 13)  
    public void getmac() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String mac = response.getBody();
  	      assertTrue(mac.contains("mac="));
    }
//Changing the ip address to 192.168.1.166 will hang the test
//need to figure out how this will work or a bug.
//    
//    @HttpTest( method = Method.GET, 
//    	  	path = "/vb.htm?lan_ip=192.168.1.168",
//    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
//    		order=14) 
//    public void setlanip() {
//    	      assertOk( response );
//    	      printResponse();
//    	      
//    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=lan_ip",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 15)  
    public void verifylanip() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String ipaddr = response.getBody();
  	      assertTrue(ipaddr.contains("lan_ip=192.168.001.168"));
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?lan_ip=192.168.1.168",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 16) 
    public void xsetnewlanip() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?title=onsemi_IOT",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 17)  
    public void setamname() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=title",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 18)  
    public void verifycamname() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String camname = response.getBody();
  	      assertTrue(camname.contains("title=onsemi_IOT"));
    }
    
    @HttpTest( method = Method.GET, 
    		path = "/vb.htm?title=TI_IPNC",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 19)  
    public void xsetdefaultname() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?language=1",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 20)  
    public void setlanguage() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=language",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 21)  
    public void verifylanguage() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String language = response.getBody();
  	      assertTrue(language.contains("language=1"));
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?language=0",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 22)  
    public void xsetvvdndefaultlanguage() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?date=2015/03/04",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 23)  
    public void setdate() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=date",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 24)  
    public void verifydate() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String date = response.getBody();
  	      assertTrue(date.contains("date=2015/03/04"));
    }
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?time=17:02:49",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 25) 
    public void settime() {
    	      assertOk( response );
    	      printResponse();
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=time",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 26)  
    public void verifytime() {
    	  	  printResponse();
    	  	  assertOk( response );
    	  	String time = response.getBody();
  	      assertTrue(time.contains("time=17:02:52"));
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
