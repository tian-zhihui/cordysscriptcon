/**
 * (c) 2008 Cordys R&D B.V. All rights reserved. The computer program(s) is the
 * proprietary information of Cordys B.V. and provided under the relevant
 * License Agreement containing restrictions on use and disclosure. Use is
 * subject to the License Agreement.
 */
package com.cordys.coe.ac.scriptconnector.processor;

import com.cordys.coe.ac.scriptconnector.ScriptConnector;
import com.cordys.coe.ac.scriptconnector.aclib.ISoapRequestContext;
import com.cordys.coe.ac.scriptconnector.exception.ScriptConnectorException;
import com.cordys.coe.ac.scriptconnector.scripting.ConfiguredScript;
import com.cordys.coe.util.FileUtils;

import com.eibus.xml.nom.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Map;

/**
 * Script pre-processor which writes the SOAP request to a text file.
 *
 * @author  mpoyhone
 */
public class RequestLogger
    implements IScriptPreProcessor
{
    /**
     * Writes to the log file are synchronized over this object.
     */
    private static final Object syncObject = new Object();
    /**
     * Marker for end-of-record.
     */
    private static final String END_OF_RECORD_LINE = "================================================================================";
    /**
     * Files are written into this folder.
     */
    private File outputFolder;

    /**
     * @see  com.cordys.coe.ac.scriptconnector.processor.IScriptPreProcessor#execute(com.cordys.coe.ac.scriptconnector.ScriptConnector,
     *       com.cordys.coe.ac.scriptconnector.scripting.ConfiguredScript,
     *       com.cordys.coe.ac.scriptconnector.aclib.ISoapRequestContext)
     */
    public boolean execute(ScriptConnector connector, ConfiguredScript script,
                           ISoapRequestContext requestContext)
                    throws ScriptConnectorException
    {
        long timeNow = System.currentTimeMillis();
        String fileName = String.format("request-log.%tF.txt", timeNow);

        synchronized (syncObject)
        {
            File logFile = new File(outputFolder, fileName);
            PrintWriter out = null;

            try
            {
                out = new PrintWriter(new FileWriter(logFile, true));
                out.println(script.getScriptName());
                out.println(String.format("%tF %tT.%tL", timeNow, timeNow, timeNow));
                out.println(Node.writeToString(requestContext.getRequestMethodNode(), false));
                out.println(END_OF_RECORD_LINE);
                out.flush();
            }
            catch (Exception e)
            {
                throw new ScriptConnectorException("Unable to write log file: " + logFile, e);
            }
            finally
            {
                FileUtils.closeWriter(out);
            }
        }

        return true;
    }

    /**
     * @see  com.cordys.coe.ac.scriptconnector.processor.IScriptPreProcessor#initialize(com.cordys.coe.ac.scriptconnector.ScriptConnector,
     *       java.util.Map)
     */
    public boolean initialize(ScriptConnector connector, Map<String, String> params)
                       throws ScriptConnectorException
    {
        String tmp = params.get("outputfolder");

        if ((tmp == null) || (tmp.length() == 0))
        {
            throw new ScriptConnectorException("Output folder is not set.");
        }

        outputFolder = new File(tmp);

        if (!outputFolder.exists() && !outputFolder.mkdirs())
        {
            throw new ScriptConnectorException("Unable to create output folder: " + outputFolder);
        }

        return true;
    }
}
