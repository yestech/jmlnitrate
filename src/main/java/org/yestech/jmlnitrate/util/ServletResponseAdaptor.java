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
package org.yestech.jmlnitrate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;

import java.util.HashMap;

/**
 * This class represents an Adaptor to a {@link ServletResponse} that adds the
 * ability to add Parameters to the Response.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ServletResponseAdaptor extends ServletResponseWrapper implements ServletResponse {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(ServletResponseAdaptor.class);

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------

    /**
     * Holds the Map of parameter added to the response
     */
    private HashMap parameters;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Creates a new ServletResponseAdaptor with a default SerlvetResponse.
     *
     * @param response the Servlet Response
     */
    public ServletResponseAdaptor(ServletResponse response) {
        super(response);
        parameters = new HashMap();
    }

    //--------------------------------------------------------------------------
    // P A R A M E T E R   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Adds a Parameter to the response.
     *
     * @param key the Key for the Parameter.
     * @param value the Value of the Parameter
     */
    public void putParameter(Object key, Object value) {
        parameters.put(key, value);
    }

    /**
     * Retrieves a Parameter from the response.
     *
     * @param key the Key of the Parameter to retrieve
     * @return the Value associated with the Key
     */
    public Object getParameter(Object key) {
        return parameters.get(key);
    }

    /**
     * Removes a Parameter from the response.
     *
     * @param key the Key to the Parameter to remove
     */
    public void removeParameter(Object key) {
        parameters.remove(key);
    }
}
