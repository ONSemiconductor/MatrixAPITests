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

import com.onsemi.matrix.api.tests.system.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	DateTest.class,
	MacAddressTest.class,
	SerialNoTest.class,
	FirmwareTest.class,
	TimeTest.class,
	UptimeTest.class,
	CameraNameTest.class
})
public class SystemTest {
}	
