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
package org.yestech.jmlnitrate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents a Pool that is reflected to instancate a Class.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ReflectedPool {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(ReflectedPool.class);

    /**
     * Holds the Default minimum count
     */
    final private static int MINIMUM_COUNT = 10;

    /**
     * Holds the Default maximim count
     */
    final private static int MAXIMUM_COUNT = 20;

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the internal lock
     */
    private Object lock = new Object();

    /**
     * Holds the minimum number of objects to pool
     */
    private int minCount;

    /**
     * Holds the maximum number of objects to pool
     */
    private int maxCount;

    //--------------------------------------------------------------------------
    // C O N T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public ReflectedPool() {
        this(MINIMUM_COUNT, MAXIMUM_COUNT);
    }

    /**
     * Creates a new ReflectedPool with a Minimum and a Maximum.
     *
     * @param minCount Minimum Count of the pool
     * @param maxCount Maximim Count of the pool
     */
    public ReflectedPool(int minCount, int maxCount) {
        super();
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

}

