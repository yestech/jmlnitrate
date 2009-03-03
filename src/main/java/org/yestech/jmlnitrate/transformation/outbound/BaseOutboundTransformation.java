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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.transformation.BaseTransformation;

/**
 * This class represents the Base class for <b>ALL</b> {@link
 * OutboundTransformation}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class BaseOutboundTransformation extends BaseTransformation
    implements OutboundTransformation
{
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(BaseOutboundTransformation.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public BaseOutboundTransformation() {
        super();
    }

}

