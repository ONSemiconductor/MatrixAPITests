
# Settings



Settings.java
	
- username (default: admin)
	
- password (default: admin)



config.properties
	
- url (default: http://192.168.1.168) - camera url
	
- defaultTimeout (default: 5000 ms) - test timeout



# How to run tests from Eclipse


1. Import project
	
	1. Click "File > Import...";
	
	2. Select "General > Existing Projects into Workspace" and click "Next";
	
	3. Select project folder in "Select root directory" and click "Finish";


2. Run tests
	
	* Run one group (like video, audio): select VideoTest in com.onsemi.matrix.api namespace, click right mouse button, click "Run As > JUnit Test"
	
	* Run one command (like alarmlevel, audiobitrate): select AlarmLevelTest in com.onsemi.matrix.api.tests.audio namespace, click right mouse button, click "Run As > JUnit Test"



# How to run tests from cmd
	
* Run one group (like video, audio): 
		
	1. change working directory on projectPath\bin (example: D:\MatrixAPITests\bin)
		
	2. copy config.properties to projectPath\bin
		
	3. run: `java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.VideoTest`

	
* Run one command (like alarmlevel, audiobitrate): 
		
	1. change working directory on projectPath\bin (example: D:\MatrixAPITests\bin)
		
	2. copy config.properties to projectPath\bin
		
	3. run: `java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.tests.audio.AlarmLevelTest`