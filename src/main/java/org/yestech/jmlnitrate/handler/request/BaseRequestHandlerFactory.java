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

import org.yestech.jmlnitrate.handler.HandlerFactory;
import org.yestech.jmlnitrate.handler.response.ResponseHandler;
/**
 * This class is the Base Factory for <b>ALL</b> {@link RequestHandler}
 * Factories. The main responsibility for this class is to implement {@link
 * #getResponseHandler()}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class BaseRequestHandlerFactory extends HandlerFactory {
    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    protected BaseRequestHandlerFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // H A N D L E R F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and return the {@link ResponseHandler} that can handle the
     * response.
     *
     * @return the ResponseHandler
     * @throws Exception if an error happens creating the {@link
     * RequestHandler}.
     */
    public ResponseHandler getResponseHandler() throws Exception {
        throw new UnsupportedOperationException("This is a RequestHandler " +
                                                "Factory...");
    }

}
