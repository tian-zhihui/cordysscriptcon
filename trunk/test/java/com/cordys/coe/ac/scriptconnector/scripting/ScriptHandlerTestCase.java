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
package com.cordys.coe.ac.scriptconnector.scripting;

import com.cordys.coe.ac.scriptconnector.ScriptConnectorTestCase;

import java.io.File;

/**
 * Base class for script handler test cases.
 *
 * @author  mpoyhone
 */
public abstract class ScriptHandlerTestCase extends ScriptConnectorTestCase
{
    /**
     * Executes the given script static content ("data value").
     *
     * @param   script     Script code.
     * @param   extension  Script extension.
     *
     * @throws  Exception
     */
    protected void executeStaticTest(String script, String extension)
                        throws Exception
    {
        executeTest(script, extension, "    <result xmlns='xxx'><data>data value</data></result>");
    }
    
    /**
     * Executes the given script with dynamic content (from Cordys.getRequestUserDN()).
     *
     * @param   script     Script code.
     * @param   extension  Script extension.
     *
     * @throws  Exception
     */
    protected void executeDynamicTest(String script, String extension)
                        throws Exception
    {
        executeTest(script, extension, "    <result xmlns='xxx'><data>dummy-user-dn</data></result>");
    }

    /**
     * Executes the given script.
     *
     * @param   script                  Script code.
     * @param   extension               Script extension.
     * @param   expectedMethodContents  SOAP response method contents.
     *
     * @throws  Exception  Thrown if the script failed.
     */
    protected void executeTest(String script, String extension, String expectedMethodContents)
                        throws Exception
    {
        File scriptFile = createTextFile("Test." + extension, script);

        String requestXml = "<Test xmlns=\"http://schemas.cordys.com/1.0/coe/ScriptConnector\" />";
        String responseXml = "<TestResponse xmlns=\"http://schemas.cordys.com/1.0/coe/ScriptConnector\">" +
                             expectedMethodContents + "</TestResponse>";
        int expectedResponse = parse(responseXml);
        int actualResponse;

        actualResponse = executeScriptMethod(requestXml, scriptFile);

        assertNodesEqual(expectedResponse, getSoapMethod(actualResponse), true);
    }
}