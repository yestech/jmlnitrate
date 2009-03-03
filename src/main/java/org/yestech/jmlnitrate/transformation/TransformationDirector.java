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
package org.yestech.jmlnitrate.transformation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents the Director for the creation of concrete {@link
 * Transformation}s.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class TransformationDirector {

    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(TransformationDirector.class);


    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.  Private for convienence and safety.
     */
    private TransformationDirector() {
        super();
    }

    //--------------------------------------------------------------------------
    // F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates a new Transformation from a request to use the Micro-Kernel.
     *
     * @param className This is the className of the Factory to use.
     * @param parameter the parameter to use when creating the Transformation.
     * @return the Transformation the process the request.
     * @throws Exception if error happens creating the Transformation
     */
    public static Transformation createTransformation(String className, Object
                                                      parameter) throws
                                                      Exception {
        TransformationFactory factory = null;
        //get the factory to use.
        factory = TransformationFactory.getFactory(className);
        factory.setParameter(parameter);
        return factory.getTransformation();
    }

    //--------------------------------------------------------------------------
    // I N T E R N A L   M E T H O D S 
    //--------------------------------------------------------------------------
}

