/*
 *   Copyright 2004 Cordys R&D B.V. 
 *
 *   This file is part of the Cordys Script Connector. 
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.cordys.coe.ac.scriptconnector;

import com.eibus.localization.message.Message;
import com.eibus.localization.message.MessageSet;

/**
 * This code is generated by running com.cordys.coe.cep.wizards.localization.CoEMessageGenerator.
 */
public class LogMessages
{
	/**
	 * Holds the definition of the message set.
	 */
	public static final MessageSet MESSAGE_SET = MessageSet.getMessageSet("com.cordys.coe.ac.scriptconnector.LogMessages");

	/**
	 * Holds the definition of the message with ID UnableToOpenConnector.
	 * Message text:
	 * Unable to open the connector object.
	 */
	public static final Message UNABLE_TO_OPEN_CONNECTOR = MESSAGE_SET.getMessage("UnableToOpenConnector");
	/**
	 * Holds the definition of the message with ID TransactionFailed.
	 * Message text:
	 * ScriptConnector transaction failed: {0}
	 */
	public static final Message TRANSACTION_FAILED = MESSAGE_SET.getMessage("TransactionFailed");
	/**
	 * Holds the definition of the message with ID CONNECTOR_INITIALIZATION_FAILED.
	 * Message text:
	 * Connector initialization failed.
	 */
	public static final Message CONNECTOR_INITIALIZATION_FAILED = MESSAGE_SET.getMessage("CONNECTOR_INITIALIZATION_FAILED");

}