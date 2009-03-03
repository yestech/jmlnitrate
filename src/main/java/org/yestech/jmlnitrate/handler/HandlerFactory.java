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
package org.yestech.jmlnitrate.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.handler.request.RequestHandler;
import org.yestech.jmlnitrate.handler.response.ResponseHandler;
import org.yestech.jmlnitrate.util.CacheManager;

/**
 * This class represents the Base class/Base Factory for all the different types
 * of {@link RequestHandler} and {@link ResponseHandler}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public abstract class HandlerFactory {

    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(HandlerFactory.class);

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the parameter to the factory
     */
    private Object parameter;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    protected HandlerFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // S T A T I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Factory method to create the concrete implementation of the handler
     * factory that will satisfy handling the parameter.
     *
     * @param factoryName The FQN of the Factory to use
     * @return the concrete HandlerFactory needed.
     * @throws Exception if an error happens while trying to create the
     * Handler
     */
    protected static HandlerFactory getFactory(String factoryName) throws
    Exception {
        return getFactory(CacheManager.getClass(factoryName));
    }

    /**
     * Factory method to create the concrete implementation of the handler
     * factory that will satisfy handling the parameter.
     *
     * @param factoryClass The Class of the Factory to use
     * @return the concrete HandlerFactory needed.
     * @throws Exception if an error happens while trying to create the
     * Handler
     */
    protected static HandlerFactory getFactory(Class factoryClass) throws
    Exception {
        return (HandlerFactory)factoryClass.newInstance();
    }

    /**
     * Set the parameter needed by the concrete implementation for processing.
     *
     * @param parameter This is the Request to handle
     */
    protected void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    /**
     * Returns a <b>Live</b> reference for the parameter needed by the concrete
     * implementation for processing.
     *
     * @return the Request object to handle
     */
    protected Object getParameter() {
        return parameter;
    }

    //--------------------------------------------------------------------------
    // T E M P L A T E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and return the {@link RequestHandler} that can handle the
     * request.
     *
     * @return the RequestHandler
     * @throws Exception if an error happens creating the {@link
     * RequestHandler}.
     */
    public abstract RequestHandler getRequestHandler() throws Exception;

    /**
     * Creates and return the {@link ResponseHandler} that can handle the
     * response.
     *
     * @return the ResponseHandler
     * @throws Exception if an error happens creating the {@link
     * RequestHandler}.
     */
    public abstract ResponseHandler getResponseHandler() throws Exception;
}

/*
 * $Log: HandlerFactory.java,v $
 * Revision 1.1  2002/11/19 07:05:11  boo
 * initial add from move to YES
 *
 * Revision 1.7  2002/03/07 06:45:18  boo
 * remove all local class caches and added the new Utilities based class cache module.
 *
 * Revision 1.6  2002/03/02 23:42:33  boo
 * added a factoryClass cache.
 *
 * Revision 1.5  2002/01/07 05:51:39  boo
 * repackaged the request and responses.
 *
 * Revision 1.4  2002/01/06 03:50:11  boo
 * integrated the logging framework.
 *
 * Revision 1.3  2001/10/28 17:41:53  boo
 * first prototype for getting xsl working.
 *
 * Revision 1.2  2001/10/12 04:06:27  boo
 * initial working implementation of the kernel working with the handlers.
 *
 * Revision 1.1  2001/08/28 02:01:46  boo
 * initial add of Factories.
 *
 */
