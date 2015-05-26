
Settings:

Settings.java
 - username (default: admin)
 - password (default: admin)

 - timeout (default: 5000 ms) - test timeout

config.properties
 - url (default: http://192.168.1.168) - camera url

*******************************************************************************************************

How to run tests from Eclipse:

1) Import project
   - Click "File > Import...";
   - Select "General > Existing Projects into Workspace" and click "Next";
   - Select project folder in "Select root directory" and click "Finish";

2) Run tests
   - Run one group (like video, audio)
     - Example (for video group): select VideoTest in com.onsemi.matrix.api namespace, click right mouse button, click "Run As > JUnit Test"

   - Run one command (like alarmlevel, audiobitrate)
     - Example (for alarmlevel command): select AlarmLevelTest in com.onsemi.matrix.api.tests.audio namespace, click right mouse button, click "Run As > JUnit Test"

*******************************************************************************************************

How to run tests from cmd:

 - Run one group (like video, audio)
   - Example (for video group): 
       navigate to projectPath\bin (example: D:\cameratests\tests\bin)
       copy config.ini to projectPath\bin
       run: "java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.VideoTest"

 - Run one command (like alarmlevel, audiobitrate)
   - Example (for alarmlevel command): 
       navigate to projectPath\bin (example: D:\cameratests\tests\bin)
       copy config.ini to projectPath\bin
       run: "java -cp .;"..\libs\*" org.junit.runner.JUnitCore com.onsemi.matrix.api.tests.audio.AlarmLevelTest"
