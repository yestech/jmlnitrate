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
import org.junit.Test;
import org.junit.Ignore;

/**
 * This class represents the testcases for JMLNitrate.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 */
public class JMLNitrateUnitTest {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
            LogFactory.getLog(JMLNitrateUnitTest.class);

    //--------------------------------------------------------------------------
    // T E S T C A S E   M E T H O D S 
    //--------------------------------------------------------------------------

    @Test
    @Ignore
    public void testConfig() {
        logger.debug("pre.....");

        JMLNitrate kernel = JMLNitrate.create();

        logger.debug("post.....");
    }

    @Test
    @Ignore
    public void testTwoLoads() {
        logger.debug("pre.....");

        JMLNitrate kernel1 = JMLNitrate.create();

        JMLNitrate kernel2 = JMLNitrate.create();

        logger.debug("post.....");
    }
}
