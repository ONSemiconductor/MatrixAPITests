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

import com.onsemi.matrix.api.tests.video.BacklightTest;
import com.onsemi.matrix.api.tests.video.BitRateTest;
import com.onsemi.matrix.api.tests.video.BrightnessTest;
import com.onsemi.matrix.api.tests.video.ContrastTest;
import com.onsemi.matrix.api.tests.video.DefaultTest;
import com.onsemi.matrix.api.tests.video.FPSTest;
import com.onsemi.matrix.api.tests.video.HDRTest;
import com.onsemi.matrix.api.tests.video.LDCTest;
import com.onsemi.matrix.api.tests.video.NoiseControlTest;
import com.onsemi.matrix.api.tests.video.RTSPURLTest;
import com.onsemi.matrix.api.tests.video.RateControlTest;
import com.onsemi.matrix.api.tests.video.SaturationTest;
import com.onsemi.matrix.api.tests.video.SharpnessTest;
import com.onsemi.matrix.api.tests.video.VideoCompressionTest;
import com.onsemi.matrix.api.tests.video.VideoResolutionTest;


@RunWith( Suite.class )
@Suite.SuiteClasses({
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
		LDCTest.class
})
public class VideoTest {
}