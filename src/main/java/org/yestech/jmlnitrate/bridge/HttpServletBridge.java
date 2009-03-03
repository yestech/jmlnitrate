/*
 * File name:           $RCSfile: $
 *
 * Revision:            $Revision: 2 $
 * Last revised by:     $Author: yeslinux $
 * Last revision date:  $Date: 2008-08-16 21:20:48 -0700 (Sat, 16 Aug 2008) $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 *
 */
package org.yestech.jmlnitrate.bridge;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

import org.yestech.jmlnitrate.client.JMLNitrate;
import org.yestech.jmlnitrate.util.ServletResponseAdaptor;
import org.yestech.jmlnitrate.util.ServletRequestAdaptor;
import org.yestech.jmlnitrate.handler.response.HttpServletResponseHandler;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
/**
 * This servlet represents the Bridge for {@link JMLNitrate} to use.
 * <br><br>
 * The Bridge can be dynamically configured through the xml deployment
 * descriptor.  There are 2 init parameters that are recognized:
 * <ul>
 *    <li>debug - true|false
 *    <li>jmlnitrate_config - fileName
 * </ul>
 * 
 * @author Arthur Copeland
 * @version $Revision: 2 $
 *
 */
public class HttpServletBridge extends HttpServlet {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(HttpServletBridge.class);

    /**
     * Holds the default error message
     */
    final private static String ERROR_MESSAGE = "Error Processing Request....";

    /**
     * Holds the default debug error message
     */
    final private static String DEBUG_ERROR_MESSAGE = "DEBUGGING - Error " +
        "Processing Request: ";

    /**
     * Debug option
     */
    final private static String DEFAULT_DEBUG = "false";

    /**
     * Debug option
     */
    final private static String DEBUG = "debug";

    /**
     * JMLNitrate Config option
     */
    final private static String JMLNITRATE_CONFIG = "jmlnitrate_config";

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the debug status
     */
    private boolean debug;

    private JMLNitrate kernel;

    private String configFile;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public HttpServletBridge() {
        super();
    }

    //--------------------------------------------------------------------------
    // H T T P S E R V L E T   C O N T R A C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Called by the Servlet Container.
     *
     * @see javax.servlet.Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        //must be called per spec
        super.init(config);

        //load configuration information
        try {
            loadConfiguration();
        } catch (Exception e) {
            logger.error("Error loading Configuration options in init....", e);
            throw new RuntimeException("Error loading Configuration options " +
                                       "in intit....");
        }
        kernel = JMLNitrate.create(configFile);
    }

    /**
     * This methods is called by the servlet container.
     *
     * @see HttpServlet#service(HttpServletRequest,HttpServletResponse)
     */
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {

            ServletResponseAdaptor response = new ServletResponseAdaptor(resp);
            ServletRequestAdaptor request = new ServletRequestAdaptor(req);

            //configure the response
            response.putParameter(HttpServletResponseHandler.KEY_VIEW,
                                  request.getParameter(
                                      HttpServletResponseHandler.KEY_VIEW));
            response.putParameter(HttpServletResponseHandler.KEY_REQUEST,
                                  request);

            //call JMLNitrate to process the request
            kernel.process(request, response);

        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            logger.error(ERROR_MESSAGE + " : " +  e, e);
            if (debug) {
                writer.println(DEBUG_ERROR_MESSAGE + e);
                e.printStackTrace(writer);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Error Message writing: " + ERROR_MESSAGE);
                }
                writer.println(ERROR_MESSAGE);
            }
        }
    }

    //--------------------------------------------------------------------------
    // B R I D G E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * This method loads all the Configuration information needed for the
     * Bridge to function properly.
     *
     * @throws Exception If and error happens loading configurations
     */
    private void loadConfiguration() throws Exception {
        //configure Debug
        String tdebug = getInitParameter(DEBUG);
        if (tdebug == null || tdebug.equals("") ||
            (!tdebug.equalsIgnoreCase("true") &&
            !tdebug.equalsIgnoreCase("false"))) {
            debug = Boolean.getBoolean(DEFAULT_DEBUG);
        } else {
            debug = new Boolean(tdebug).booleanValue();
        }
        configFile = getInitParameter(JMLNITRATE_CONFIG);
    }
}
