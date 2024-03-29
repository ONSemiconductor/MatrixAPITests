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

import com.onsemi.matrix.api.tests.network.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith( Suite.class )
@Suite.SuiteClasses({
		EthernetWifiSelectionSupportTest.class,
		NetmaskTest.class,
		RTSPPortTest.class,
		SecureRTSPTest.class,
		UPnPTest.class,
		DHCPEnableTest.class,
		GatewayTest.class,
		DNSTest.class,
		IPAddressTest.class
})
public class NetworkTest {
}
