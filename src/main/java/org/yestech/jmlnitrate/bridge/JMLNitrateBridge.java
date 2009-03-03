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
package org.yestech.jmlnitrate.bridge;

import org.yestech.jmlnitrate.util.JMLNitratePlant;

import java.io.Serializable;

/**
 * This interface represents the contract for <b>ALL</b> bridges that need to
 * use be used by the facade {@link org.yestech.jmlnitrate.client.JMLNitrate}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface JMLNitrateBridge extends Serializable {

    /**
     * Serial UID
     */
    static final long serialVersionUID = 3777605565766284908L;

    /**
     * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use
     * for the request
     *
     * @return the Plant
     */
    public JMLNitratePlant getRequestPlant();

    /**
     * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use
     * for the response.
     *
     * @return the Plant
     */
    public JMLNitratePlant getResponsePlant();

    /**
     * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use
     * for the inboundTransformation.
     *
     * @return the Plant
     */
    public JMLNitratePlant getInboundPlant();

    /**
     * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use
     * for the outbound transformation.
     *
     * @return the Plant
     */
    public JMLNitratePlant getOutboundPlant();
}
