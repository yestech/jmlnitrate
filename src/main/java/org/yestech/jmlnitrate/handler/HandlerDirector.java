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

/**
 * This class represents the Director to use when trying to use {@link
 * HandlerFactory}.  The director is used to decouple the client from having to
 * know the internals of how all the factories work.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class HandlerDirector {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(HandlerDirector.class);

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.  Private for convienence and safety.
     */
    private HandlerDirector() {
        super();
    }

    //--------------------------------------------------------------------------
    // F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates a new RequestHandler from a Raw Request and the Specified FQN of
     * the HandlerFactory create to use the Micro-Kernel.
     *
     * @param rawRequest The Raw Request to process.
     * @param className The FQN of the HandlerFactory to use
     * @return the RequestHandler the process the request.
     * @throws Exception if error happens creating the Handler
     */
    public static RequestHandler createRequestHandler(Object rawRequest, String
                                                className) throws Exception {
        HandlerFactory factory = getFactory(className, rawRequest);
        return factory.getRequestHandler();
    }

    /**
     * Creates a new ResponseHandler from a Raw Response and the Specified FQN
     * of the HandlerFactory create.
     *
     * @param rawResponse The Raw Response to process.
     * @param className The FQN of the HandlerFactory to use
     * @return the ResponseHandler the process the response.
     * @throws Exception if error happens creating the Handler
     */
    public static ResponseHandler createResponseHandler(Object rawResponse,
                                                        String className) throws
                                                       Exception {
        HandlerFactory factory = getFactory(className, rawResponse);
        return factory.getResponseHandler();
    }

    //--------------------------------------------------------------------------
    // I N T E R N A L   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and returns a configured HandlerFactory.
     *
     * @param className the ClassName of the Factory to create
     * @param parameter the Parameter to set on the factory
     * @return the Factory
     */
    private static HandlerFactory getFactory(String className, Object parameter)
        throws Exception {
        //get the factory to use.
        HandlerFactory factory = HandlerFactory.getFactory(className);
        factory.setParameter(parameter);
        return factory;
    }
}

/*
 * $Log: HandlerDirector.java,v $
 * Revision 1.2  2002/12/03 05:58:57  boo
 * updates
 *
 * Revision 1.1  2002/11/19 07:05:11  boo
 * initial add from move to YES
 *
 * Revision 1.6  2002/01/07 05:51:39  boo
 * repackaged the request and responses.
 *
 * Revision 1.5  2002/01/06 03:50:11  boo
 * integrated the logging framework.
 *
 * Revision 1.4  2001/10/28 17:41:53  boo
 * first prototype for getting xsl working.
 *
 * Revision 1.3  2001/10/12 04:06:27  boo
 * initial working implementation of the kernel working with the handlers.
 *
 * Revision 1.2  2001/08/28 02:01:46  boo
 * initial add of Factories.
 *
 * Revision 1.1  2001/08/25 23:48:45  boo
 * initial add.
 *
 */
