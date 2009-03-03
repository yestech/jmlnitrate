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

import org.yestech.jmlnitrate.util.JMLNitratePlant;

import javax.servlet.http.HttpServletRequest;
/**
 * This {@link org.yestech.jmlnitrate.handler.HandlerFactory} is responsible for handling requests from a
 * {@link javax.servlet.http.HttpServletRequest}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class HttpServletRequestHandlerFactory
    extends BaseRequestHandlerFactory {
    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    public HttpServletRequestHandlerFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // H A N D L E R F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and return the {@link RequestHandler} that can handle the
     * request.
     *
     * @return the RequestHandler
     * @throws Exception if an error happens creating the {@link
     * RequestHandler}.
     */
    public RequestHandler getRequestHandler() throws Exception {
        HttpServletRequestHandler handler = new HttpServletRequestHandler();
        JMLNitratePlant plant = (JMLNitratePlant)getParameter();
        handler.setHttpServletRequest(
            (HttpServletRequest)plant.getParam(RequestHandler.REQUEST));
        return handler;
    }
}

