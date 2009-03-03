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
package org.yestech.jmlnitrate.servlet;

import org.yestech.jmlnitrate.client.JMLNitrate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This servlet represents a test for JMLNitrate from a {@link HttpServlet}.
 * This servlet represents the Bridge for {@link JMLNitrate} to use.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 */
//public class TestHttpServlet extends HttpServlet implements JMLNitrateBridge {
public class TestHttpServlet extends HttpServlet
{

    public TestHttpServlet()
    {
        super();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        try
        {
            //call JMLNitrate to process the request
            JMLNitrate.create().process(req, resp);
            PrintWriter writer = resp.getWriter();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //--------------------------------------------------------------------------
    // J M L N I T R A T E B R I D G E   C O N T R A C T   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Returns the FQN of the {@link
     * org.yestech.jmlnitrate.handler.HandlerFactory} to use when processing the
     * request.
     *
     * @return the FQN of the Factory to use
     */
    public String getHandlerFactory()
    {
        return "";
    }
}

