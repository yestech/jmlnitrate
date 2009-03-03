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
 package org.yestech.jmlnitrate.handler.response;

//==============================================================================
// I M P O R T E D   P A C K A G E S
//==============================================================================
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.util.ServletResponseAdaptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This {@link ResponseHandler} represents an Adaptor for a
 * {ServletResponseAdaptor}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class HttpServletResponseHandler extends BaseResponseHandler {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(HttpServletResponseHandler.class);

    /**
     * Holds the Key associated with the result to transform. "responseResult"
     */
    final public static String KEY_RESULT = "responseResult";
    
    /**
     * Holds the Key associated with the view to use in the
     * response. "responseView"
     */
    final public static String KEY_VIEW = "responseView";

    /**
     * Holds the Key associated with the {@link HttpServletRequest} to use by
     * the response if a jsp is needed. "responseRequest"
     */
    final public static String KEY_REQUEST = "responseRequest";
    
    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Response the Handle
     */
    private ServletResponseAdaptor response;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public HttpServletResponseHandler() {
        super();
    }
    
    //--------------------------------------------------------------------------
    // R E S P O N S E H A N D L E R   C O N T R A C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Processes the Result from the tramsformation.
     *
     * @param transformationResult the result from the OutboundTransformation
     */
    public void process(Object transformationResult) throws Exception {
        String view = (String)getValue(KEY_VIEW);
        if (view != null) {
            HttpServletRequest request =
                (HttpServletRequest)getValue(KEY_REQUEST);
            if (view.endsWith(".jsp")) {        
                //process jsp
                request.setAttribute(KEY_RESULT, transformationResult);
                RequestDispatcher dispatcher =
                    request.getRequestDispatcher(view);
                dispatcher.forward(
                    request,
                    (HttpServletResponse)response.getResponse());
            } else if (view.endsWith(".html")) {
                RequestDispatcher dispatcher =
                    request.getRequestDispatcher(view);
                dispatcher.forward(
                    request,
                    (HttpServletResponse)response.getResponse());
            } else {
                //process everything else
                response.getWriter().println(transformationResult);
            }
        } else {
            //process everything else
            response.getWriter().println(transformationResult);
        }
    }
    
    //--------------------------------------------------------------------------
    // P R O T E C T E D   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set ServletResponseAdaptor of the Handler
     *
     * @param response The ServletResponse to Handle
     */
    protected void setServletResponse(ServletResponseAdaptor response)
        throws Exception {
        this.response = response;
    }

    /**
     * Mutator to add a Parameter to a Response
     *
     * @param key the Key to the Parameter
     * @param value the Value of the Parameter
     */
    protected void addParameter(Object key, Object value) {
        response.putParameter(key, value);
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to get ServletResponseAdaptor of the Handler
     *
     * @return The ServletResponseAdaptor
     */
    public ServletResponseAdaptor getServletResponse() throws Exception {
        return response;
    }

    /**
     * Mutator to get the value associated with a Parameter on the Response.
     *
     * @param key Key associated with the Value to retrieve
     * @return the Value associated with the Key
     */
    public Object getValue(String key) {
        return response.getParameter(key);
    }
}

/*
 * $Log: HttpServletResponseHandler.java,v $
 * Revision 1.1  2002/11/19 07:05:11  boo
 * initial add from move to YES
 *
 * Revision 1.4  2002/04/06 20:53:24  boo
 * added RequestAdaptor to ServletBridge.
 *
 * Revision 1.3  2002/03/23 19:03:39  boo
 * made all package scope protected.
 *
 * Revision 1.2  2002/03/03 01:52:53  boo
 * added optimizations. part 1
 *
 * Revision 1.1  2002/01/07 05:51:39  boo
 * repackaged the request and responses.
 *
 * Revision 1.3  2002/01/06 03:50:11  boo
 * integrated the logging framework.
 *
 * Revision 1.2  2001/11/09 04:41:44  boo
 * got object/jsp outbound transformation working.
 *
 * Revision 1.1  2001/10/28 17:44:09  boo
 * initial add
 *
 * Revision 1.1  2001/10/12 04:06:27  boo
 * initial working implementation of the kernel working with the handlers.
 *
 */
