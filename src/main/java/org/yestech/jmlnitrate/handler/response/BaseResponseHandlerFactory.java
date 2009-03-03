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

import org.yestech.jmlnitrate.handler.request.RequestHandler;
import org.yestech.jmlnitrate.handler.HandlerFactory;

/**
 * This class is the Base Factory for <b>ALL</b> {@link ResponseHandler}
 * Factories. The main responsibility for this class is to implement the
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class BaseResponseHandlerFactory extends HandlerFactory {
    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    protected BaseResponseHandlerFactory() {
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
        throw new UnsupportedOperationException("This is a ResponseHandler " +
                                                "Factory...");
    }
}

