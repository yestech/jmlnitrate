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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents the testcases for JMLNitrate.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class TestJMLNitrate extends TestCase {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger =
        LogFactory.getLog(TestJMLNitrate.class);

    //--------------------------------------------------------------------------
    // I N S T A N C E   V A R I A B L E S 
    //--------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Deault Ctor.  Creates a new TestJMLNitrate Object
     */
    public TestJMLNitrate() {
        this(TestJMLNitrate.class.getName());
    }

    /**
     * Creates a new TestJMLNitrate Object with a name
     *
     * @param name Name of the TestCase
     */
    public TestJMLNitrate(String name) {
        super(name);
    }

    //--------------------------------------------------------------------------
    // T E S T C A S E   C O N T R A C T   M E T H O D S 
    //--------------------------------------------------------------------------
    protected void setUp() throws Exception {
    }

    //--------------------------------------------------------------------------
    // T E S T R U N N E R   C O N T R A C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * TestRunner uses this method to run TestSuite and display the TestResult
     *
     * @return the Suite of Tests
     */
    public static Test suite() {
        TestSuite suite= new TestSuite();
        suite.addTestSuite(TestJMLNitrate.class);
        return suite;
    }

    //--------------------------------------------------------------------------
    // D R I V E R   M E T H O D 
    //--------------------------------------------------------------------------
    /**
     * This is the driver or the class
     *
     * @param args arguments to pass to the class
     */
    public static void main(String args[]) {
        //Run the test suite
        TestResult result = new TestResult();
        logger.debug("Begin - " + TestJMLNitrate.class.getName() +
                     " TestCase");
        suite().run(result);
        logger.debug("Result: " + result);
        logger.debug("Success: " + result.wasSuccessful());
        logger.debug("End - " + TestJMLNitrate.class.getName()
                     + " TestCase");
    }

    //--------------------------------------------------------------------------
    // T E S T C A S E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Tests that a kernel can be loaded.
     */
      public void testConfig() {
          logger.debug("pre.....");

          JMLNitrate kernel = JMLNitrate.create();

          logger.debug("post.....");
      }

    /**
     * Tests that a kernel can be loaded more than once.
     */
      public void testTwoLoads() {
          logger.debug("pre.....");

          JMLNitrate kernel1 = JMLNitrate.create();

          JMLNitrate kernel2 = JMLNitrate.create();

          logger.debug("post.....");
      }
}
