/*
 * File name:           $RCSfile: ConfigurationFileType.java,v $
 *
 * Revision:            $Revision: 3 $
 * Last revised by:     $Author: yeslinux $
 * Last revision date:  $Date: 2008-08-16 21:24:01 -0700 (Sat, 16 Aug 2008) $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 */

//==============================================================================
// P A C K A G E
//==============================================================================
package org.yestech.jmlnitrate.configuration;

import java.io.Serializable;
import java.io.InvalidObjectException;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

/**
 * This Class represents an enumeration of All the different types of
 * configuration file types that are reconized.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class ConfigurationFileType implements Serializable {

    /**
     * Holds the serial version number
     */
     //final private static lonf serialVersionUID = L;

    /**
     * This {@link ConfigurationFileType} represents a xml file.
     */
    public static final ConfigurationFileType XML;

    /**
     * This {@link ConfigurationFileType} represents a properties file.
     */
    public static final ConfigurationFileType PROPERTIES;

    /**
     * This interface contains the constants from the
     * {@link ConfigurationFileType} class so the user can implement the
     * constants and avoid using the class name to scope the constants.
     * <br><br>
     * For example:<br><br>
     * <pre>
     *
     * public class MyClass implements ConfigurationFileType.Constants {
     *
     *  . . .
     *
     * }
     * </pre>
     *
     */
    public static interface Constants {
        /**
         * This is the {@link ConfigurationFileType#XML} constant.
         *
         * @see ConfigurationFileType#XML
         */
        public static final ConfigurationFileType XML =
            ConfigurationFileType.XML;

        /**
         * This is the {@link ConfigurationFileType#PROPERTIES} constant.
         *
         * @see ConfigurationFileType#PROPERTIES
         */
        public static final ConfigurationFileType PROPERTIES =
            ConfigurationFileType.PROPERTIES;
    }

    /**
     * This is a mapping of all instance names to the actual instance.
     */
    private static Map instanceMap = null;

    /**
     * This is immutable list of all instances that is provided to the user.
     */
    private static Set instanceSet = null;

    /**
     * This is the static initializer which initializes the legal instances
     * of this class and sets up the internal collections.
     */
    static {
        // construct all instances of this class
        XML = new ConfigurationFileType("XML");
        PROPERTIES = new ConfigurationFileType("Properties");

        // set up the map of names to instances
        instanceMap = new HashMap();
        instanceMap.put(XML.getName(), XML);
        instanceMap.put(PROPERTIES.getName(), PROPERTIES);
        instanceMap = Collections.unmodifiableMap(instanceMap);

        // set up the set of all instances
        instanceSet = new HashSet();
        instanceSet.add(XML);
        instanceSet.add(PROPERTIES);
        instanceSet = Collections.unmodifiableSet(instanceSet);
    }

    /**
     * The name for this constant.
     */
    private String name = null;

    /**
     * Private constructor that constructs with the specified name.  This
     * constructor is private so that all instances of this class are those
     * provided as public static constants.
     *
     * @param name The name for this instance.
     */
    private ConfigurationFileType(String name) {
        super();
        this.name = name;
    }

    /**
     * Accessor to get the system name for this instance.  The returned name
     * should be unique among all instances of this class.  This descriptive
     * name does <b>not</b> take the current locale into account.  The user
     * should create a mapping of class instances to text strings if the
     * user desires to create a properly localized application.
     *
     *  @return The system name for this instance.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Returns the name of this instance.  The name is unique among all other
     * instances of this class.
     *
     * @return The unique name of this instance.
     */
    public final String toString() {
        return this.name;
    }

    /**
     * This method handles resolving deserialized instances of this class
     * to one of the known instances in the current JVM.  The documentation
     * for the {@link Serializable} interface discusses the use of this
     * method further.
     *
     * @return One of the known instances of this class for the current JVM.
     *
     * @throws InvalidObjectException If this instance cannot be resovled to
     *                                 one of the known instances for this JVM.
     */
    public final Object readResolve() throws InvalidObjectException {
        Object type = instanceMap.get(this.name);
        if (type == null) {
            throw new InvalidObjectException(
                "Source code versions for ConfigurationFileType class are not "
                + "compatible.  The name of the deserialized " +
                "ConfigurationFileType "
                + "instances was not recognized.  name=[ " + this.name + " ]");
        }
        return type;
    }

    /**
     * Returns an immutable {@link Set} containing all legal instances of
     * this enumeration class.
     *
     * @return An immutable {@link Set} containing all legal instances of this
     *         enumeration class.
     */
    public static Set getPossibleValues() {
        return instanceSet;
    }
}

/*
 * $Log: ConfigurationFileType.java,v $
 * Revision 1.1  2002/11/19 06:47:08  boo
 * initial add from move to YES
 *
 * Revision 1.1  2001/12/22 09:31:50  boo
 * initial add.
 *
 */
