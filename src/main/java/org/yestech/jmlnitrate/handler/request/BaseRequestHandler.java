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
import org.yestech.jmlnitrate.transformation.inbound.InboundTransformation;

/**
 * This class represents a Base class for <b>ALL</b> {@link RequestHandler}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class BaseRequestHandler implements RequestHandler
{
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(BaseRequestHandler.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public BaseRequestHandler() {
        super();
    }

    //--------------------------------------------------------------------------
    // R E Q U E S T H A N D L E R   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * This methods accepts an {@link InboundTransformation} and triggers it to
     * transform the Request.
     *
     * @param transformer The InboundTransformer to use
     * @throws Exception if an error happens while transforming the request
     */
    public void transformRequest(InboundTransformation transformer) throws
    Exception {
        transformer.transform(this);
    }
}

