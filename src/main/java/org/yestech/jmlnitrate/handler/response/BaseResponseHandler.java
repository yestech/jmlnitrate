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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.transformation.outbound.OutboundTransformation;

/**
 * This class represents a Base class for <b>ALL</b> {@link ResponseHandler}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class BaseResponseHandler implements ResponseHandler {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(BaseResponseHandler.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public BaseResponseHandler() {
        super();
    }

    //--------------------------------------------------------------------------
    // R E Q U E S T H A N D L E R   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * This methods accepts an {@link OutboundTransformation} and triggers it to
     * transform the Response.
     *
     * @param transformer The OutboundTransformer to use
     * @throws Exception if an error happens while transforming the response
     */
    public void transformResponse(OutboundTransformation transformer) throws
    Exception {
        transformer.transform(this);
    }
}
