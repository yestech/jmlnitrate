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
package org.yestech.jmlnitrate.transformation.outbound;

import org.yestech.jmlnitrate.handler.response.ResponseHandler;
import org.yestech.jmlnitrate.transformation.Transformation;
/**
 * This {@link Transformation} only applies to transforming the ressponse of the
 * {@link OutboundTransformation} request to the appropriate outbound format.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface OutboundTransformation extends Transformation {

    /**
     * Transforms a {@link ResponseHandler} to a valid output format.
     *
     * @param response The Response to transform
     * @throws Exception if an error happens
     */
    public void transform(ResponseHandler response) throws Exception;

}
