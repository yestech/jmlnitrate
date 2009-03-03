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

import java.util.HashMap;

/**
 * This class represents the Base class for <b>ALL</b> {@link Transformation}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class BaseTransformation implements org.yestech.jmlnitrate.transformation.Transformation
{
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(BaseTransformation.class);    

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Result of the Transformation.
     */
    private Object result;

    /**
     * Holds all the parameters;
     */
    private HashMap params;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public BaseTransformation() {
        super();
        params = new HashMap();
    }

    //--------------------------------------------------------------------------
    // T R A N S F O R M A T I O N   C O N T R A C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Return the result of the Transformation.
     *
     * @return Result of the transformation
     */
    public Object getTransformationResult() {
        return result;
    }

    //--------------------------------------------------------------------------
    // F R I E N D L Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set the Result of the Transformation
     *
     * @param result The Result of the Transformation
     */
    protected void setTransformationResult(Object result) {
        this.result = result;
    }


    /**
     * Set the parameters for the transformation to be executed
     *
     * @param params This a map of the parameters
     */
    public void setParameters(HashMap params) {
        this.params = params;
    }

    /**
     * Return the parameters reference
     */
    protected HashMap getParameters() {
        return params;
    }

    protected Object getParameter(Object key) {
        return this.params.get(key);
    }
}
