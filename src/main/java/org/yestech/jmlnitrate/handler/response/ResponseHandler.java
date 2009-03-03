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

import org.yestech.jmlnitrate.transformation.outbound.OutboundTransformation;

/**
 * This interface represents the base interface for <b>ALL</b> Response
 * Handlers.  A response handler represents a Class that can take a client
 * independant response from a kernel and transform it into format that is
 * client dependant.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface ResponseHandler {
    /**
     * Holds the response key
     */
    final public static String RESULT = "KERNEL_RESULT";

    /**
     * Holds the response key
     */
    final public static String RESPONSE = "CLIENT_RESPONSE";


    /**
     * This methods accepts an {@link OutboundTransformation} and triggers it to
     * transform the Response.
     *
     * @param transformer The OutboundTransformer to use
     * @throws Exception if an error happens while transforming the request
     */
    public void transformResponse(OutboundTransformation transformer) throws
    Exception;

    /**
     * Processes the Result from the tramsformation.
     *
     * @param transformationResult the result from the OutboundTransformation
     */
    public void process(Object transformationResult) throws Exception;
}

