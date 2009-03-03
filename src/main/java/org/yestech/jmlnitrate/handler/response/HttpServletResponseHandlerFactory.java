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
package org.yestech.jmlnitrate.handler.response;

import org.yestech.jmlnitrate.util.ServletResponseAdaptor;

import org.yestech.jmlnitrate.util.JMLNitratePlant;
/**
 * This {@link org.yestech.jmlnitrate.handler.HandlerFactory} is responsible for handling responses from a
 * {@link ServletResponseAdaptor}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class HttpServletResponseHandlerFactory
    extends BaseResponseHandlerFactory {
    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    public HttpServletResponseHandlerFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // H A N D L E R F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and return the {@link ResponseHandler} that can handle the
     * request.
     *
     * @return the ResponseHandler
     * @throws Exception if an error happens creating the {@link
     * ResponseHandler}.
     */
    public ResponseHandler getResponseHandler() throws Exception {
        HttpServletResponseHandler handler = new HttpServletResponseHandler();
        JMLNitratePlant plant = (JMLNitratePlant)getParameter();
        handler.setServletResponse((ServletResponseAdaptor)plant.getParam(
            ResponseHandler.RESPONSE));
        handler.addParameter(HttpServletResponseHandler.KEY_RESULT,
                             plant.getParam(ResponseHandler.RESULT));
        return handler;
    }
}

