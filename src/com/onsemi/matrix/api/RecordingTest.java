package com.onsemi.matrix.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.onsemi.matrix.api.recording.SDCardOverwriteSchemeTest;
import com.onsemi.matrix.api.recording.SnapshotTest;


@RunWith( Suite.class )
@Suite.SuiteClasses({
	SnapshotTest.class,
	SDCardOverwriteSchemeTest.class
})
public class RecordingTest {

}
