/*******************************************************************************
 * Copyright (c) 2015 Gautam Sabba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.codescaping.automation.ui.exceptions;

public class DriverException extends Exception {
	
	private static final long serialVersionUID = 1159518894670458957L;
	

	public DriverException() {
		super();
	}
	
	public DriverException(String msg) {
		super(msg);
	}
	
	public DriverException(String msg, Throwable exp) {
		super(msg, exp);
	}

}
