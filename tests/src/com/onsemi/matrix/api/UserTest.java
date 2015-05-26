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
