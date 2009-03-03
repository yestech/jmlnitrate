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

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents a Plant that would like to be attached to JMLNitrate.
 * A Plant represents some component that can be dynamically added to
 * JMLNitrate.  Plants are introduced into JMLNitrate by the configuration file:
 * "saphari-jmlnitrate".  Please look @ the file for information of it's
 * format. Plants are modeled as a Configuration Container Object that carries
 * some MetaData, and Parameter Data that is needed by Caller.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class JMLNitratePlant implements Serializable {

    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S
    //--------------------------------------------------------------------------
    /**
     * Serial UID
     */
    final static private long serialVersionUID = 1911643631033094310L;

    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(JMLNitratePlant.class);

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S
    //--------------------------------------------------------------------------
    /**
     * Holds the name
     */
    private String name;

    /**
     * Holds the factory
     */
    private String factory;

    /**
     * Holds the type
     */
    private String type;

    /**
     * Holds the parameters
     */
    final private HashMap params = new HashMap();

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S
    //--------------------------------------------------------------------------
    /**
     * Default ctor
     */
    public JMLNitratePlant(String name, String factory, String type) {
        super();
        this.name = name;
        this.factory = factory;
        this.type = type;
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S
    //--------------------------------------------------------------------------
    /**
     * Name Accessor
     */
    public String getName() {
        return name;
    }

    /**
     * Factory Accessor
     */
    public String getFactory() {
        return factory;
    }

    /**
     * Type Accessor
     */
    public String getType() {
        return type;
    }

    /**
     * Mutator to a Parameter.
     *
     * @param key The Key
     * @param value The Parameter
     */
    public void addParam(Object key, Object value) {
        synchronized (this) {
            params.put(key, value);
        }
    }

    /**
     * Accessor to get a Parameter.
     *
     * @param key The Key
     * @return the Parameter
     */
    public Object getParam(Object key) {
        Object val = null;
        synchronized (this) {
            val = params.get(key);
        }
        return val;
    }

    /**
     * Mutator to remove a Parameter.
     *
     * @param key The Key
     */
    public void removeParam(Object key) {
        synchronized (this) {
            params.remove(key);
        }
    }

    /**
     * Accessor to get a Iterator of all the Parameter Keys.
     *
     * @return the Parameter keys Iterator
     */
    public Iterator getParamKeys() {
        return Collections.unmodifiableSet(params.keySet()).iterator();
    }
    /**
     * Accessor to get the number Parameters.
     *
     * @return the Parameter count
     */
    public int getParamCount() {
        return params.size();
    }
}

