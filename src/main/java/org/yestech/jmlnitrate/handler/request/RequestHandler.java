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

import org.yestech.jmlnitrate.transformation.inbound.InboundTransformation;

/**
 * This interface represents the base interface for <b>ALL</b> Request
 * Handlers.  A request handler represents a Class that can take a raw request
 * for the kernel and transform it into format that is request independant.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface RequestHandler {
    /**
     * Holds the request key
     */
    final public static String REQUEST = "CLIENT_REQUEST";


    /**
     * This methods accepts an {@link InboundTransformation} and triggers it to
     * transform the Request.
     *
     * @param transformer The InboundTransformer to use
     * @throws Exception if an error happens while transforming the request
     */
    public void transformRequest(InboundTransformation transformer) throws
    Exception;
}

