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
package org.yestech.jmlnitrate.transformation.inbound;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.core.KernelContext;
import org.yestech.jmlnitrate.handler.request.RequestHandler;
import org.yestech.jmlnitrate.transformation.TransformationParameter;

import javax.servlet.http.HttpServletRequest;
/**
 *  This class represents an {InboundTransformation} that is specific for HTML
 *  type front-ends that use the {@link HttpServletRequest}s. The result of this
 *  transformation will be a {@link KernelContext}. This transformation is not
 *  Thread-Safe. <br>
 *  <br>
 *  The main difference between this transformer and {@link
 *  HttpServletRequestInboundTransformation} is that this transformer assumes,
 *  therefore makes every methodArgument of type String. With {@link
 *  HttpServletRequestInboundTransformation} the methodArgument is free to be
 *  dynamic Object.
 *
 * @author  Arthur Copeland
 * @version  $Revision: 3 $
 */
public class HTMLFrontEndHttpServletRequestInboundTransformation
     extends HttpServletRequestInboundTransformation
{
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S
    //--------------------------------------------------------------------------
    /**
     *  Holds the logger
     */
    final private static Log logger = LogFactory.getLog(HTMLFrontEndHttpServletRequestInboundTransformation.class);


    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S
    //--------------------------------------------------------------------------
    /**
     *  Default Ctor.
     */
    public HTMLFrontEndHttpServletRequestInboundTransformation() {
        super();
    }


    //--------------------------------------------------------------------------
    // I N B O U N D T R A N S F O R M A T I O N   C O N T R A C T
    // M E T H O D S
    //--------------------------------------------------------------------------
    /**
     *  Transforms a {@link RequestHandler} to a {@link
     *  org.jmlnitrate.core.KernelContext}. <br>
     *  The format of the Request: <i>Required</i>
     *  <ul>
     *    <li> <i>className</i> - The FQN Class Name of the Class to execute
     *    <li> ctorType - The Class Types needed for the Constructor to use by the
     *    Class Name. If not specified then a default ctor is assumed. (ie
     *    ctorType=java.lang.String)
     *    <li> ctorArg - The Class Arguments needed for the Constructor to use by
     *    the Class Name. If not specified then a default ctor is assumed. (ie
     *    ctorArg=Billy)
     *    <li> staticMethod - Whether the method is static or not. If not set then
     *    it is assumed to be static. (True,Y,1 : False,N,0)
     *    <li> <i>methodName</i> - The name of the method to execute.
     *  </ul>
     *  All the other parameters are assumed to be the arguments needed by the
     *  method during execution. The format of the method arguments is: <br>
     *  FQN ClassName Type# = Value. (ie java.lang.String1=Test Argument <br>
     *  It is assumes that the Constructor Object will be <b>in order</b> .
     *
     * @param  request The Request to transform
     * @throws  Exception if an error happens
     */
    public void transform(RequestHandler request) throws Exception {
        super.transform(request);
    }


    /**
     *  Retrieves the Method Array from the raw string request. The request of the
     *  String must be: <br>
     *  <pre>
     * </pre>
     *
     * @param  arguments The Array of Arguments needed for the Method
     * @return  an Object Array in the valid format for the {@link
     *      org.jmlnitrate.core.Kernel#createKernelProcess(String, String,
     *      Object[], Object[])}
     * @throws  Exception if error happens
     */
    protected Object[] getMethodArguments(Object[] arguments)
         throws Exception {
        if ((arguments != null && arguments.length != 0) && arguments.length <
            1) {
            logger.error("Parameter " +
                TransformationParameter.METHOD_ARGUMENT.getName()
                 + "must be supplied....");
            throw new NullPointerException("Parameter " +
                TransformationParameter.METHOD_ARGUMENT.getName()
                 + " must be supplied....");
        }
        if (arguments == null || (arguments != null && arguments.length == 0)) {
            return null;
        } else {
            int size = arguments.length;
            Object[] args = new Object[size];
            for (int i = 0; i < size; i++) {
                Object arg = arguments[i];
                if (arg.getClass().isArray()) {
                    Object[] argArray = (Object[]) arg;
                    int as = argArray.length;
                    if (as == 1) {
                        Object pargArray[] = new Object[1];
                        pargArray[0] = (String) argArray[0];
                        args[i] = pargArray;
                        //args[i] = (String) argArray[0];
                    } else {
                        Object[] ta = new Object[as];
                        for (int a = 0; a < as; a++) {
                            Object pargArray[] = new Object[1];
                            pargArray[0] = (String) argArray[0];
                            ta[a] = pargArray;
                            //ta[a] = (String) argArray[a];
                        }
                        args[i] = ta;
                    }
                } else {
                    Object pargArray[] = new Object[1];
                    pargArray[0] = (String) arg;
                    args[i] = pargArray;
                    //args[i] = (String) arg;
                }
            }
            return args;
        }
    }

}

