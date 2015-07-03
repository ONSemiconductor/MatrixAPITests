package com.onsemi.matrix.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.onsemi.matrix.api.tests.audio.AudioBitRateNameTest;
import com.onsemi.matrix.api.tests.audio.AudioInEnableTest;
import com.onsemi.matrix.api.tests.audio.BitRateTest;
import com.onsemi.matrix.api.tests.audio.EncodingNameTest;
import com.onsemi.matrix.api.tests.audio.EncodingTest;
import com.onsemi.matrix.api.tests.audio.InputVolumeTest;
import com.onsemi.matrix.api.tests.audio.OutputVolumeTest;
import com.onsemi.matrix.api.tests.audio.SampleRateNameTest;
import com.onsemi.matrix.api.tests.audio.SampleRateTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationDownloadTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationGetFileListTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationSaveTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationStartUploadingFilesTest;
import com.onsemi.matrix.api.tests.maintenance.ConfigurationUploadTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareGainSpanFirmwareTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareGainSpanWifiUpgradeTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareHardwareRevisionTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareUBLVersionTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareUbootVersionTest;
import com.onsemi.matrix.api.tests.maintenance.FirmwareVersionTest;
import com.onsemi.matrix.api.tests.maintenance.FormatSDCardTest;
import com.onsemi.matrix.api.tests.maintenance.MountUnmountSDCardTest;
import com.onsemi.matrix.api.tests.maintenance.SSLCertificateUploadTest;
import com.onsemi.matrix.api.tests.maintenance.SSLDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.SSLKeyUploadTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogCommonAPITest;
import com.onsemi.matrix.api.tests.maintenance.SysLogDeleteMessageTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogDeleteTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogEnableTest;
import com.onsemi.matrix.api.tests.maintenance.SysLogSearchTest;
import com.onsemi.matrix.api.tests.network.DNSTest;
import com.onsemi.matrix.api.tests.network.EthernetWifiSelectionSupportTest;
import com.onsemi.matrix.api.tests.network.GatewayTest;
import com.onsemi.matrix.api.tests.network.NetmaskTest;
import com.onsemi.matrix.api.tests.network.RTSPPortTest;
import com.onsemi.matrix.api.tests.network.SecureRTSPTest;
import com.onsemi.matrix.api.tests.network.UPnPTest;
import com.onsemi.matrix.api.tests.recording.SDCardOverwriteSchemeTest;
import com.onsemi.matrix.api.tests.recording.SnapshotTest;
import com.onsemi.matrix.api.tests.system.DateTest;
import com.onsemi.matrix.api.tests.system.FirmwareTest;
import com.onsemi.matrix.api.tests.system.MacAddressTest;
import com.onsemi.matrix.api.tests.system.SerialNoTest;
import com.onsemi.matrix.api.tests.system.TimeTest;
import com.onsemi.matrix.api.tests.system.UptimeTest;
import com.onsemi.matrix.api.tests.user.AddUserTest;
import com.onsemi.matrix.api.tests.user.ChangePasswordTest;
import com.onsemi.matrix.api.tests.user.DelUserTest;
import com.onsemi.matrix.api.tests.user.GetPrivilegeTest;
import com.onsemi.matrix.api.tests.user.ResetPasswordTest;
import com.onsemi.matrix.api.tests.video.BacklightTest;
import com.onsemi.matrix.api.tests.video.BrightnessTest;
import com.onsemi.matrix.api.tests.video.ContrastTest;
import com.onsemi.matrix.api.tests.video.DefaultTest;
import com.onsemi.matrix.api.tests.video.FPSTest;
import com.onsemi.matrix.api.tests.video.HDRTest;
import com.onsemi.matrix.api.tests.video.NoiseControlTest;
import com.onsemi.matrix.api.tests.video.RTSPURLTest;
import com.onsemi.matrix.api.tests.video.RateControlTest;
import com.onsemi.matrix.api.tests.video.SaturationTest;
import com.onsemi.matrix.api.tests.video.SharpnessTest;
import com.onsemi.matrix.api.tests.video.VideoCompressionTest;
import com.onsemi.matrix.api.tests.video.VideoResolutionTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	    // audio
		AudioBitRateNameTest.class,
		AudioInEnableTest.class,
		BitRateTest.class,
		EncodingNameTest.class,
		EncodingTest.class,
		InputVolumeTest.class,
		OutputVolumeTest.class,
		SampleRateNameTest.class,
		SampleRateTest.class,
		
		// video
		BitRateTest.class,
		FPSTest.class,
		HDRTest.class,
		RateControlTest.class,
		VideoCompressionTest.class,
		VideoResolutionTest.class,
		BacklightTest.class,
		BrightnessTest.class,
		ContrastTest.class,
		DefaultTest.class,
		RTSPURLTest.class,
		SaturationTest.class,
		SharpnessTest.class,
		NoiseControlTest.class,
		
		// system
		DateTest.class,
		MacAddressTest.class,
		SerialNoTest.class,
		FirmwareTest.class,
		TimeTest.class,
		UptimeTest.class,
		
		// network
		EthernetWifiSelectionSupportTest.class,
		NetmaskTest.class,
		RTSPPortTest.class,
		SecureRTSPTest.class,
		UPnPTest.class,
		//IPAddressTest.class,
		GatewayTest.class,
		DNSTest.class,
		
		// maintenance
		ConfigurationDeleteTest.class,
		ConfigurationDownloadTest.class,
		ConfigurationGetFileListTest.class,
		ConfigurationSaveTest.class,
		ConfigurationUploadTest.class,
		ConfigurationStartUploadingFilesTest.class,
		//FirmwareStartFirmwareUpgradeTest.class,
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
		FirmwareGainSpanFirmwareTest.class,
		//ConfigurationRestoreTest.class,
		
		// user
		AddUserTest.class,
		DelUserTest.class,
		GetPrivilegeTest.class,
		ChangePasswordTest.class,
		ResetPasswordTest.class,
		
		// recording
		SnapshotTest.class,
		SDCardOverwriteSchemeTest.class
})
public class AllTest {
}
