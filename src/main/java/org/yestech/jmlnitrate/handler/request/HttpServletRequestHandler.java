/*
 * File name:           $RCSfile: $
 *
 * Revision:            $Revision: $
 * Last revised by:     $Author: $
 * Last revision date:  $Date: $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 *
 */
package org.yestech.jmlnitrate.handler.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This {@link RequestHandler} represents an Adaptor for a {HttpServletRequest}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class HttpServletRequestHandler extends BaseRequestHandler {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(HttpServletRequestHandler.class);

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Request the Handle
     */
    private HttpServletRequest request;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public HttpServletRequestHandler() {
        super();
    }

    //--------------------------------------------------------------------------
    // P R O T E C T E D   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set HttpServletRequest of the Handler
     *
     * @param request The HttpServletRequest to Handle
     */
    protected void setHttpServletRequest(HttpServletRequest request)
        throws Exception {
        this.request = request;
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to return the {@link HttpServletRequest} of the handler.
     *
     * @return the {@link HttpServletRequest}
     */
    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    /**
     * Accessor to get the value associated with a Parameter on the Request.
     *
     * @param key Key associated with the Value to retrieve
     * @return the Array of Values associated with the Key
     */
    public String[] getValues(String key) {
        return request.getParameterValues(key);
    }

    /**
     * Accessor to get the value associated with a Parameter on the Request.
     *
     * @param key Key associated with the Value to retrieve
     * @return the Value associated with the Key
     */
    public String getValue(String key) {
        return request.getParameter(key);
    }

    /**
     * Returns all the values of the Request as a {@link Map}.
     *
     * @return the Map of the Request Parameters
     */
    public Map getParameters() {
        HashMap parameters = new HashMap();
        java.util.Enumeration paramNamesEnum = request.getParameterNames();
        while (paramNamesEnum.hasMoreElements()) {
            String key = (String)paramNamesEnum.nextElement();
            String[] values = request.getParameterValues(key);
            parameters.put(key, values);
        }
        return parameters;
    }

    /**
     * Accessor to get the value associated with an Attribute on the Request.
     *
     * @param key Key associated with the Value to retrieve
     * @return the Value associated with the Key
     */
    public Object getAttribute(String key) {
        return request.getAttribute(key);
    }

}
