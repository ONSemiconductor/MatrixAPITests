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

import com.onsemi.matrix.api.tests.user.AddUserTest;
import com.onsemi.matrix.api.tests.user.ChangePasswordTest;
import com.onsemi.matrix.api.tests.user.DelUserTest;
import com.onsemi.matrix.api.tests.user.EditPrivilegeTest;
import com.onsemi.matrix.api.tests.user.GetPrivilegeTest;
import com.onsemi.matrix.api.tests.user.LoginCountTest;
import com.onsemi.matrix.api.tests.user.ResetPasswordTest;


@RunWith( Suite.class )
@Suite.SuiteClasses({
	AddUserTest.class,
	DelUserTest.class,
	EditPrivilegeTest.class,
	GetPrivilegeTest.class,
	LoginCountTest.class,
	ChangePasswordTest.class,
	ResetPasswordTest.class
})
public class UserTest {
}
