/*
** Copyright 2015 ON Semiconductor
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

import com.onsemi.matrix.api.tests.system.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	CustomSNTPTest.class,
	DateTest.class,
	DisplayLanguageTest.class,
	MacAddressTest.class,
	SelectedSNTPServerTest.class,
	SerialNoTest.class,
	SNTPServerListTest.class,
	SynchronisationIntervalTest.class,
	TimeSynchModeTest.class,
	TimeTest.class,
	TimeZoneTest.class,
	TitleTest.class,
	UptimeTest.class
})
public class SystemTest {
}	
