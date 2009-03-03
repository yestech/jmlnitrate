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
package org.yestech.jmlnitrate.handler.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * This {@link RequestHandler} represents an Adaptor for an {InputStream}.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class InputStreamRequestHandler extends BaseRequestHandler {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(InputStreamRequestHandler.class);

    /**
     * Holds the default buffer size
     */
    final private static int DEFAULT_BUFFER_SIZE = 92;

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the InputStream in a Buffered state.
     */
    private BufferedInputStream stream;

    /**
     * Holds the buffer size to use
     */
    private int bufferSize = DEFAULT_BUFFER_SIZE;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public InputStreamRequestHandler() {
        super();
    }

    //--------------------------------------------------------------------------
    // P A C K A G E   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Mutator to set internal InputStream.
     *
     * @param stream This is the InputStream
     */
    void setStream(InputStream stream) throws Exception {
        setStream(stream, bufferSize);
    }

    /**
     * Mutator to set internal InputStream with a buffer size supplied.
     *
     * @param stream This is the InputStream
     * @param bufferSize This is the Size of the Buffer
     */
    void setStream(InputStream stream, int bufferSize) throws Exception {
        if (bufferSize < 1) {
            logger.warn("Sorry but the bufferSize must be atleast 1");
            throw new IllegalArgumentException("Sorry but the " +
                                               "bufferSize must be atleast 1");
        }
        if (stream == null) {
            logger.warn("Sorry but the stream must not be NULL");
            throw new NullPointerException("Sorry but the " +
                                               "stream must not be NULL");
        }

        this.bufferSize = bufferSize;
        this.stream = new BufferedInputStream(stream, this.bufferSize);
    }

    //--------------------------------------------------------------------------
    // P U B L I C   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Accessor to return the internal {@link InputStream} of the request.
     *
     * @return the {@link BufferedInputStream}
     */
    public BufferedInputStream getStream() {
        return stream;
    }

    /**
     * Accessor to return the size of the buffer of the internal {@link
     * InputStream} of the request.
     *
     * @return the buffer size
     */
    public int getBufferSize() {
        return bufferSize;
    }
}
