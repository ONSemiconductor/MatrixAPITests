
# Settings



Settings.java
	
- username (default: admin)
	
- password (default: admin)



config.properties
	
- defaultIP (default: 192.168.1.168) - camera IP

- testIP (default: 192.168.1.169) - available IP used to change camera IP in tests

- afterTestDelay (default: 0 ms) - interval between launch of tests

- defaultTimeout (default: 5000 ms) - test timeout



# How to run tests from Eclipse


1. Import project
	
	1. Click "File > Import...";
	
	2. Select "General > Existing Projects into Workspace" and click "Next";
	
	3. Select project folder in "Select root directory" and click "Finish";


2. Run tests
	* Run all tests: select AllTest in com.onsemi.matrix.api namespace, click right mouse button, click "Run As > JUnit Test"
                   
	Note: these tests - IPAddressTest, FirmwareStartFirmwareUpgradeTest, ConfigurationRestoreTest are commented out in AllTest.java because they could be a reason of other tests fail. These tests should be run independently or as part of their groups (Video, Audio and etc).

	* Run one group (like video, audio): select VideoTest in com.onsemi.matrix.api namespace, click right mouse button, click "Run As > JUnit Test"
	
	* Run one command (like alarmlevel, audiobitrate): select AlarmLevelTest in com.onsemi.matrix.api.tests.audio namespace, click right mouse button, click "Run As > JUnit Test"



# How to run tests from cmd
* Run all tests: 
		
	1. change working directory on projectPath\bin (example: D:\MatrixAPITests\bin)
		
	2. copy config.properties to projectPath\bin
		
	3. run: `java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.AllTest`

	
* Run one group (like video, audio): 
		
	1. change working directory on projectPath\bin (example: D:\MatrixAPITests\bin)
		
	2. copy config.properties to projectPath\bin
		
	3. run: `java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.VideoTest`

	
* Run one command (like alarmlevel, audiobitrate): 
		
	1. change working directory on projectPath\bin (example: D:\MatrixAPITests\bin)
		
	2. copy config.properties to projectPath\bin
		
	3. run: `java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.tests.audio.AlarmLevelTest`
