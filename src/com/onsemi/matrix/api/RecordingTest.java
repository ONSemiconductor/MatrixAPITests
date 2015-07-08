/** Copyright 2015 ON Semiconductor
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**  http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/

package com.onsemi.matrix.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.onsemi.matrix.api.tests.recording.CloudEnableTest;
import com.onsemi.matrix.api.tests.recording.CloudFileTest;
import com.onsemi.matrix.api.tests.recording.CloudIPTest;
import com.onsemi.matrix.api.tests.recording.CloudPasswordTest;
import com.onsemi.matrix.api.tests.recording.CloudPortTest;
import com.onsemi.matrix.api.tests.recording.CloudStartRecordingTest;
import com.onsemi.matrix.api.tests.recording.CloudStopRecordingTest;
import com.onsemi.matrix.api.tests.recording.CloudStreamTest;
import com.onsemi.matrix.api.tests.recording.CloudUserTest;
import com.onsemi.matrix.api.tests.recording.PushButtonTest;
import com.onsemi.matrix.api.tests.recording.PushEnableTest;
import com.onsemi.matrix.api.tests.recording.PushMessagesTest;
import com.onsemi.matrix.api.tests.recording.PushServiceURLTest;
import com.onsemi.matrix.api.tests.recording.SDCardOverwriteSchemeTest;
import com.onsemi.matrix.api.tests.recording.SnapshotTest;


@RunWith( Suite.class )
@Suite.SuiteClasses({
	SnapshotTest.class,
	SDCardOverwriteSchemeTest.class,
	PushEnableTest.class,
	PushButtonTest.class,
	PushServiceURLTest.class,
	PushMessagesTest.class,
	CloudStartRecordingTest.class,
	CloudStopRecordingTest.class,
	CloudEnableTest.class,
	CloudFileTest.class,
	CloudIPTest.class,
	CloudPasswordTest.class,
	CloudPortTest.class,
	CloudStreamTest.class,
	CloudUserTest.class
})
public class RecordingTest {
}
