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

/**
 * This interface represents all the XML tags that are recognized by JMLNitrate.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface JMLNitrateXMLTags {

    /**
     * Holds the root
     */
    final public static String ROOT_ELEMENT = "saphari-jmlnitrate";

    /**
     * Holds the plant element
     */
    final public static String PLANT_ELEMENT = "plant";

    /**
     * Holds the name attribute
     */
    final public static String NAME_ATTRIBUTE = "name";

    /**
     * Holds the type attribute
     */
    final public static String TYPE_ATTRIBUTE = "type";

    /**
     * Holds the factory attribute
     */
    final public static String FACTORY_ATTRIBUTE = "factory";

    /**
     * Holds the param element
     */
    final public static String PARAM_ELEMENT = "param";

    /**
     * Holds the key attribute
     */
    final public static String KEY_ATTRIBUTE = "key";

    /**
     * Holds the value element
     */
    final public static String VALUE_ELEMENT = "value";

}

