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
package org.yestech.jmlnitrate.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is repsonsible for being able to Navigate/Process a request
 * through {@link JMLNitrate} to the response view.  It doesn't actual process
 * anything.  It is used if you don't need any actual processing.  Think of it
 * as a Null Action.
 * <br>
 * Example URL Parameters: 
 *  <ul>
 *     <li>responseView=&lt;FQN VIEW PATH&gt; ex: index.xsl or some-root/file.xsl
 *     <li>class=org.jmlnitrate.client.Navigation
 *     <li>method=navigate^true
 *  </ul>
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class Navigation {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(Navigation.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    private Navigation() {
        super();
    }

    //--------------------------------------------------------------------------
    // P R O C E S S I N G   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Process the Request.
     */
    public static void navigate() {

    }
}
