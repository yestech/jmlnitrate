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
import org.yestech.lib.lang.Clazz;

/**
 * This class represents the Base class/Base Factory for all the different types
 * of {@link Transformation}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class TransformationFactory {

    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(TransformationFactory.class);

    /**
     * Holds the default HandlerFactory for the micro-kernel
     */
    final static Class DEFAULT_HANDLER_FACTORY = TransformationFactory.class;

    /**
     * Holds the default TransformationFactory for the micro-kernel
     */
    //final static Class DEFAULT_TRANSFORMATION_FACTORY =
    //    ClassAndStringTransformationFactory.class;

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the parameter to process
     */
    private Object parameter;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    protected TransformationFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // S T A T I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Factory method to create the concrete implementation of the
     * transformation factory that will satisfy handling the parameter.
     *
     * @param factoryName The FQN of the Factory to create
     * @return the concrete TransformationFactory needed.
     */
    protected static TransformationFactory getFactory(String factoryName) {
        return getFactory(Clazz.getClass(factoryName));
    }

    /**
     * Factory method to create the concrete implementation of the handler
     * factory that will satisfy handling the request.
     *
     * @param factoryClass The Class of the Factory to use
     * @return the concrete TransformationFactory needed.
     */
    protected static TransformationFactory getFactory(Class factoryClass) {
        return Clazz.instantiateClass(factoryClass);
    }

    /**
     * Set the parameter needed by the concrete implementation for processing.
     *
     * @param parameter This is the Parameter to handle
     */
    protected void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    /**
     * Returns a <b>Live</b> reference for the parameter needed by the concrete
     * implementation for processing.
     *
     * @return the Parameter object to handle
     */
    protected Object getParameter() {
        return parameter;
    }

    //--------------------------------------------------------------------------
    // T E M P L A T E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and return the {@link Transformation} that can handle the
     * request.
     *
     * @return the Transformation
     * @throws Exception if an error happens creating the {@link
     * Transformation}.
     */
    protected abstract Transformation getTransformation() throws Exception;
}

