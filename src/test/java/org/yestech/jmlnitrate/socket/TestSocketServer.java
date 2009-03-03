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
package org.yestech.jmlnitrate.socket;

import org.yestech.jmlnitrate.client.JMLNitrate;

import java.net.ServerSocket;
import java.net.Socket;
/**
 * This servlet represents a test for JMLNitrate from a {@link javax.servlet.http.HttpServlet}.
 * This servlet represents the Bridge for {@link JMLNitrate} to use.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
//public class TestSocketServer implements JMLNitrateBridge {
public class TestSocketServer {

    public TestSocketServer() {
        super();
    }

    protected void process() {
        try {
            ServerSocket server = new ServerSocket(1010);
            Socket socket = server.accept();

            //call JMLNitrate to process the request
            JMLNitrate.create().process(socket, null);
        } catch (Exception e) {
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
    public String getHandlerFactory() {
        return "";
    }

}

