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

import java.util.HashMap;

/**
 * This interface represents the Base Interface for all Transformation Engines.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface Transformation {

    /**
     * Return the result of the Transformation.
     *
     * @return Result of the transformation
     */
    public Object getTransformationResult();

    /**
     * Set the parameters for the transformation to be executed
     * 
     * @param params This a map of the parameters
     */
    public void setParameters(HashMap params);
}
