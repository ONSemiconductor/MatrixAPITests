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


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.onsemi.matrix.api.tests.maintenance.BatteryCapacityTest;
import com.onsemi.matrix.api.tests.maintenance.BatteryStatusTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareGainSpanFirmwareTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationDownloadTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationFileListTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationRestoreTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationSaveTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationStartUploadingFilesTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationUploadTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareStartFirmwareUpgradeTest;
import com.onsemi.matrix.api.tests.maintenance.FormatSDCardTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareGainSpanWifiUpgradeTest;
import com.onsemi.matrix.api.tests.maintenance.MountUnmountSDCardTest;
import com.onsemi.matrix.api.tests.maintenance.RebootSystemTest;
import com.onsemi.matrix.api.tests.maintenance.SSLCertificateUploadTest;
import com.onsemi.matrix.api.tests.maintenance.SSLDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.SSLKeyUploadTest;
import com.onsemi.matrix.api.tests.maintenance.StandbyEnableTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogCommonAPITest;
import com.onsemi.matrix.api.tests.maintenance.SysLogDeleteMessageTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogEnableTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareVersionTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareHardwareRevisionTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogSearchTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareUBLVersionTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareUbootVersionTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
		ConfigurationDeleteTest.class,
		ConfigurationDownloadTest.class,
		ConfigurationFileListTest.class,
		ConfigurationSaveTest.class,
		ConfigurationUploadTest.class,
		ConfigurationStartUploadingFilesTest.class,
		BatteryCapacityTest.class,
		BatteryStatusTest.class,
		StandbyEnableTest.class,
		FirmwareStartFirmwareUpgradeTest.class,
		FormatSDCardTest.class,
		MountUnmountSDCardTest.class,
		SSLCertificateUploadTest.class,
		SSLKeyUploadTest.class,
		SSLDeleteTest.class,
		SysLogCommonAPITest.class,
		SysLogDeleteMessageTest.class,
		SysLogDeleteTest.class,
		SysLogEnableTest.class,
		FirmwareVersionTest.class,
		FirmwareHardwareRevisionTest.class,
		SysLogSearchTest.class,
		FirmwareUBLVersionTest.class,
		FirmwareUbootVersionTest.class,
		FirmwareGainSpanWifiUpgradeTest.class,
		RebootSystemTest.class,
		FirmwareGainSpanFirmwareTest.class,
		ConfigurationRestoreTest.class // test can change IP on default (192.168.1.168) -> camera is unavailable
})
public class MaintenanceTest {
}
