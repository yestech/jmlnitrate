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

//==============================================================================
// I M P O R T E D   P A C K A G E S
//==============================================================================
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.handler.response.ResponseHandler;

/**
 * This class represents an {@link OutboundTransformation} that doesn't do any
 * transformation.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class NullOutboundTransformation
    extends BaseOutboundTransformation {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
     final private static Log logger = LogFactory.getLog(NullOutboundTransformation.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public NullOutboundTransformation() {
        super();
    }

    //--------------------------------------------------------------------------
    // O U T B O U N D T R A N S F O R M A T I O N   C O N T R A C T
    // M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Transforms a {@link ResponseHandler} to a valid output format.
     *
     * @param response The Response to transform
     * @throws Exception if an error happens
     */
    public void transform(ResponseHandler response) throws Exception {
        setTransformationResult(null);
    }

}
