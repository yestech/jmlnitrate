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

/**
 * This interface represents a Response to a Request.  The significance of this
 * interface is that it allows a client to override the response view
 * programatically if it is needed.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface Response {

    /**
     * Returns a the View to display
     *
     * @return the View
     */
    public String getView();

}
