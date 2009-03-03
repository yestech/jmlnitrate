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
package org.yestech.jmlnitrate.handler.request;

import java.io.Serializable;

/**
 * This interface represents <b>ALL</b> the parameters that can be passed in
 * to be used by a {@link RequestHandler}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface RequestParameters extends Serializable {

    /**
     * Holds the Parameter that represents the class to instanceate.
     */
    final public static String CLASS_NAME = "className";

    /**
     * Holds the Parameter that represents the name of method to execute in the
     * class.
     */
    final public static String METHOD_NAME = "method";

    /**
     * Holds the Parameter that represents the method Values for the method to
     * execute in the class.
     */
    final public static String METHOD_VALUE = "methodValue";

    /**
     * Holds the Parameter that represents the method Type of the Parameters for
     * the method to execute in the class.
     */
    final public static String METHOD_TYPE = "methodType";

    /**
     * Holds the Parameter that represents the Constructor Name of class to
     * instanceate.
     */
    final public static String CONSTRUCTOR_NAME = "ctor";

    /**
     * Holds the Parameter that represents the Constructor Type of the
     * Parameters for the class to instanceate.
     */
    final public static String CONSTRUCTOR_TYPE = "ctorType";

    /**
     * Holds the Parameter that represents the Constructor Parameter Values of
     * the Constructor.
     */
    final public static String CONSTRUCTOR_VALUE = "ctorValue";

    /**
     * Holds the Parameter that represents whether the method is static or
     * instance.
     */
    final public static String STATIC_METHOD = "staticMethod";
}

