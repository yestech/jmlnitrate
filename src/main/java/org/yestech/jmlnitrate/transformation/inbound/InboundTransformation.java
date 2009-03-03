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
package org.yestech.jmlnitrate.transformation.inbound;

import org.yestech.jmlnitrate.handler.request.RequestHandler;

import org.yestech.jmlnitrate.transformation.Transformation;
/**
 * This {@link Transformation} only applies to inbound requests to use the
 * microkernel.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface InboundTransformation extends Transformation {

    /**
     * Transforms a {@link RequestHandler} to a {@link
     * org.jmlnitrate.core.KernelContext}.
     *
     * @param request The Request to transform
     * @throws Exception if an error happens
     */
    public void transform(RequestHandler request) throws Exception;

}

