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


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.onsemi.matrix.api.tests.maintenance.ConfigurationDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationDownloadTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationGetFileListTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationRebootTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationSaveTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationUploadTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareRebootTheSystemToPrimaryTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareStartUpgradeTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareStartUploadingTheFilesTest;
import com.onsemi.matrix.api.tests.maintenance.FormatSDCardTest;
import com.onsemi.matrix.api.tests.maintenance.MountUnmountSDCardTest;
import com.onsemi.matrix.api.tests.maintenance.SSLCertificateUploadTest;
import com.onsemi.matrix.api.tests.maintenance.SSLDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.SSLKeyUploadTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogCommonAPITest;
import com.onsemi.matrix.api.tests.maintenance.SysLogDeleteMessageTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogEnableTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogFirmwareVersionTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogGetHWRevisionTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogHWVersionTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogSearchTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogUBLVersionTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogUbootVersionTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
		ConfigurationDeleteTest.class,
		ConfigurationDownloadTest.class,
		ConfigurationGetFileListTest.class,
		ConfigurationRebootTest.class,
		ConfigurationSaveTest.class,
		ConfigurationUploadTest.class,
		FirmwareRebootTheSystemToPrimaryTest.class,
		FirmwareStartUpgradeTest.class,
		FirmwareStartUploadingTheFilesTest.class,
		FormatSDCardTest.class,
		MountUnmountSDCardTest.class,
		SSLCertificateUploadTest.class,
		SSLKeyUploadTest.class,
		SSLDeleteTest.class,
		SysLogCommonAPITest.class,
		SysLogDeleteMessageTest.class,
		SysLogDeleteTest.class,
		SysLogEnableTest.class,
		SysLogFirmwareVersionTest.class,
		SysLogGetHWRevisionTest.class,
		SysLogHWVersionTest.class,
		SysLogSearchTest.class,
		SysLogUBLVersionTest.class,
		SysLogUbootVersionTest.class,
})
public class MaintenanceTest {
}
