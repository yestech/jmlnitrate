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

/**
 * This {@link org.yestech.jmlnitrate.handler.HandlerFactory} is responsible for handler request from a
 * reference request.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ReferenceHandlerFactory extends BaseRequestHandlerFactory {
    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    public ReferenceHandlerFactory() {
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
        ReaderRequestHandler handler = new ReaderRequestHandler();

        return handler;
    }
}

