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

import org.junit.BeforeClass;
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
  public Destination restfuse = new Destination( this, Settings.getHostname() );
  
  @Context
  private Response response;

  @BeforeClass
  public static void resetSettings() {
		Utils.setDefaultSystemSettings();
  }

  @HttpTest( method = Method.GET, 
		  path = "/vb.htm?timezone=16",
		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 1)
  public void settimezone() {
	  Utils.printResponse(response);
	  assertOk( response );
  }
    
   @HttpTest( method = Method.GET, 
  		  path = "/vb.htm?paratest=timezone",
  		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
		  order = 0)
    public void verifytimezone() {
      assertOk( response );
      Utils.printResponse(response);
      Utils.verifyResponse(response, "timezone=16");
  }
   
@HttpTest( method = Method.GET, 
			  path = "/vb.htm?timezone=50",
			  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
			  order = 2) 
	  public void setinvalidtimezone() {
	String zone = response.getBody();
    assertFalse(zone.contains("OK"));
		  
	  }

@ HttpTest( method = Method.GET, 
  		  path = "/vb.htm?timesynch_mode=1",
  		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
		  order = 3)  
    public void settimesynch() {
  	  Utils.printResponse(response);
  	  assertOk( response );
}
      
     @HttpTest( method = Method.GET, 
    		  path = "/vb.htm?paratest=timesynch_mode",
    		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
    		  order = 4)  
      public void verifytimesynch() {
        assertOk( response );
        Utils.printResponse(response);
        Utils.verifyResponse(response, "timesynch_mode=1");
 }
    
@HttpTest( method = Method.GET, 
     		  path = "/vb.htm?timesynch_mode=3",
     		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
   		  order = 5)  
       public void setinvalidtimesynch() {
     	  Utils.printResponse(response);
     	  assertOk( response );
     	 String mode = response.getBody();
         assertFalse(mode.contains("OK"));
 }
      
@HttpTest( method = Method.GET, 
      		  path = "/vb.htm?current_sntp=6",
      		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
    		  order = 6) 
        public void setselectedsntp_server() {
      	  Utils.printResponse(response);
      	  assertOk( response );
}
          
@HttpTest( method = Method.GET, 
        	   path = "/vb.htm?paratest=current_sntp",
        	   authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
     		  order = 7)  
         public void verifyselectedsntp_server() {
           assertOk( response );
           Utils.printResponse(response);
           Utils.verifyResponse(response, "current_sntp=6");
}
      
@HttpTest( method = Method.GET, 
       		  path = "/vb.htm?current_sntp=10",
       		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
     		  order = 8) 
         public void setinvalidedsntp_server() {
       	  Utils.printResponse(response);
       	  assertOk( response );
       	String selected = response.getBody();
        assertFalse(selected.contains("OK"));
}
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?custom_sntp=onsemi",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 9)  
    public void setcustom_sntp() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      
 }
@HttpTest( method = Method.GET, 
  		  path = "/vb.htm?paratest=sntp_list",
  		  authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 10)  
    public void verifygetserverlist() {
      assertOk( response );
      Utils.printResponse(response);
      Utils.verifyResponse(response, "sntp_list=1:pool.ntp.org,2:asia.pool.ntp.org,3:europe.pool.ntp.org,4:north-america.pool.ntp.org,5:oceania.pool.ntp.org,6:south-america.pool.ntp.org,7:onsemi");
     
  }
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=custom_sntp",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 11)  
    public void verifycustom_sntp() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "custom_sntp=onsemi");
 }
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?sntp_synch_interval=10",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 12)  
    public void setsynch_interval() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      
}
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=sntp_synch_interval",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 13)  
    public void verifysych_interval() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "sntp_synch_interval=10");
}
    
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=uptime",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 14)  
    public void getuptime() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "uptime");
}
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=serialno",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 15)  
    public void getserialno() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "serialno=sprs_mcam_12345");

 }
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=mac",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 16)  
    public void getmac() {
    	  	  Utils.printResponse(response);
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
//    		order=17) 
//    public void setlanip() {
//    	      assertOk( response );
//    	      Utils.printResponse(response);
//    	      
//    }
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=lan_ip",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 18)  
    public void verifylanip() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "lan_ip=192.168.001.168");
}
    
@HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?lan_ip=192.168.1.168",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 19) 
    public void xsetnewlanip() {
    	      assertOk( response );
    	      Utils.printResponse(response);
 }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?title=onsemi_IOT",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 20)  
    public void setcamname() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=title",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 21)  
    public void verifycamname() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "title=onsemi_IOT");
    }
    
    @HttpTest( method = Method.GET, 
    		path = "/vb.htm?title=TI_IPNC",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 22)  
    public void xsetdefaultname() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?language=1",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 23)  
    public void setlanguage() {
    	      assertOk(response);
    	      Utils.printResponse(response);
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=language",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 24)  
    public void verifylanguage() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "language=1");
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?language=5",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 25)  
    public void setinvalidlanguage() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      String language = response.getBody();
      	  	  assertFalse(language.contains("OK"));
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?language=0",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 26)  
    public void xsetvvdndefaultlanguage() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?date=2015/03/04",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 27)  
    public void setdate() {
    	      assertOk( response );
    	      Utils.printResponse(response);
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=date",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 28)  
    public void verifydate() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "date=2015/03/04");
    }
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?time=17:02:49",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) } ,
  		  order = 29) 
    public void settime() {
    	      assertOk(response);
    	      Utils.printResponse(response);
    	      
    }
    
    @HttpTest( method = Method.GET, 
    	  	path = "/vb.htm?paratest=time",
    	  	authentications = { @Authentication( type = BASIC, user = "admin", password = "admin" ) },
  		  order = 30)  
    public void verifytime() {
    	  	  Utils.printResponse(response);
    	  	  assertOk( response );
    	  	  Utils.verifyResponse(response, "time=17:02:52");
    }
  
}	
